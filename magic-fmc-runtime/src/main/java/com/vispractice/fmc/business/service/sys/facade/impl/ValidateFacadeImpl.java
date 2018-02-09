package com.vispractice.fmc.business.service.sys.facade.impl;

import java.util.List;
import java.util.Locale;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.SysDocConstant;
import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsTemplateRepository;
import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodo;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.ComponentLockerMain;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorize;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfToken;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuthorizeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTokenRepository;
import com.vispractice.fmc.business.service.sys.config.ISysBusiSysService;
import com.vispractice.fmc.business.service.sys.facade.IValidateFacade;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainService;
import com.vispractice.fmc.business.service.sys.notify.ISysNotifyTodoService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.workflow.IComponentLockerMainService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfNodeService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfProcessService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfWorkitemService;

@Slf4j
@Service
public class ValidateFacadeImpl implements IValidateFacade {
	/**
	 * 业务系统记录服务
	 */
	@Autowired
	private ISysBusiSysService sysBusiSysService;
	
	/**
	 * 流程模板服务
	 */
	@Autowired
	private SysNewsTemplateRepository sysNewsTemplateRepository;
	
	/**
	 * 组织结构信息服务
	 */
	@Autowired
	private ISysOrgElementService sysOrgElementService;
	
	/**
	 * 流程服务
	 */
	@Autowired
	private ISysWfProcessService sysWfProcessService;
	
	/**
	 * 待办服务
	 */
	@Autowired
	private ISysNotifyTodoService sysNotifyTodoService;
	
	/**
	 * 单据信息服务
	 */
	@Autowired
	private ISysNewsMainService sysNewsMainService;
	
	/**
	 * 令牌信息服务
	 */
	@Autowired
	private SysWfTokenRepository sysWfTokenService;
	
	/**
	 * 节点服务
	 */
	@Autowired
	private ISysWfNodeService sysWfNodeService;
	
	/**
	 * 权限管理服务
	 */
	@Autowired
	private SysWfAuthorizeRepository sysWfAuthorizeService;
	
	/**
	 * 流程工作事项持久化服务
	 */
	@Autowired
	private ISysWfWorkitemService sysWfWorkitemService;
	
	/**
	 * 加锁服务
	 */
	@Autowired
	private IComponentLockerMainService componentLockerMainService;
	
	/**
	 * 国际化服务
	 */
	@Autowired
	private ResourceUtil resourceUtil;
	
	/**
	 * 语言
	 */
	private Locale language = Locale.SIMPLIFIED_CHINESE;

	/**
	 * 验证业务系统是否注册
	 */
	@Override
	public SysBusiSys validateBusiSystem(String systemId) throws WorkflowException {
		SysBusiSys sysBusiSys = sysBusiSysService.getByFdCode(systemId);
		if (sysBusiSys == null || "0".equals(sysBusiSys.getFdEnabled())) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.sysbusi",language));
		}
		
		return sysBusiSys;
	}

	/**
	 * 验证流程实例是否创建
	 */
	@Override
	public SysWfProcess validateWfInstance(String wfInstance) throws WorkflowException {
		SysWfProcess sysWfProcess = sysWfProcessService.findByProcessId(wfInstance);
		if (sysWfProcess == null || StringUtils.isEmpty(sysWfProcess.getFdId())) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.wfInstance",language));
		}

		return sysWfProcess;
	}
	
	/**
	 * 验证单据状态
	 */
	@Override
	public void validateSysNewsMain(String sourceSystemId,String applyCode) throws WorkflowException {
		SysNewsMain sysNewsMain = sysNewsMainService.findByBusiSysIdAndApplCode(sourceSystemId,applyCode);
		if (null != sysNewsMain && !(SysDocConstant.DOC_STATUS_DRAFT.equals(sysNewsMain.getDocStatus()) || 
			 SysDocConstant.DOC_STATUS_REFUSE.equals(sysNewsMain.getDocStatus()))) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.sysNewsMain.status",language));	
		}
	}
	
	/**
	 * 验证流程模板是否创建
	 */
	public void validateWfTemplate(String wfTemplate) throws WorkflowException {
		SysNewsTemplate sysNewsTemplate = sysNewsTemplateRepository.findByFdId(wfTemplate);
		if (sysNewsTemplate == null || sysNewsTemplate.getFdStatus().equals("0")
				|| StringUtils.isEmpty(sysNewsTemplate.getFdId())) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.wfTemplate",language));
		}
	}
	
	/**
	 * 验证人员信息是否创建或者禁用
	 */
	@Override
	public SysOrgElement validateSysOrgElement(String fdNo) throws WorkflowException {
		SysOrgElement sysOrgElement = sysOrgElementService.findByFdNo(fdNo);
		if (sysOrgElement == null) {
			sysOrgElement = sysOrgElementService.findByFdId(fdNo);
			
			if (sysOrgElement == null) {
				throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.person",language));
			}
		}
		
		return sysOrgElement;
	}
	
	/**
	 * 验证提交单据信息是否合理
	 */
	public void vildateSbWFApproval(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		//操作类型验证
		try {
			Integer.parseInt(sbWFApprovalForm.getWfResult());
		} catch (Exception e) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("ws.validate.approval.wfResult",language));
		}
		
		//审批人验证
		SysOrgElement sysOrgElement = this.validateSysOrgElement(sbWFApprovalForm.getWfAuditedUserNo());
		sbWFApprovalForm.setWfAuditedUserId(sysOrgElement.getFdId());
		sbWFApprovalForm.setWfAuditedUserName(sysOrgElement.getFdName());
		
		//流程实例验证
		SysWfProcess sysWfProcess = this.validateWfInstance(sbWFApprovalForm.getWfInstanceId());
		if (SysWfProcess.COMPLETED.equals(sysWfProcess.getFdStatus())) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.wfInstance.status",language));
		}
		
		//加锁验证
		ComponentLockerMain componentLockerMain = componentLockerMainService.isLocked(sbWFApprovalForm.getWfInstanceId());
		if (componentLockerMain != null) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.component.locker",language));
		}
	}

	/**
	 * 验证节点
	 */
	@Override
	public SysWfNode validateNode(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		//验证当前节点
		SysWfNode sysWfNode = sysWfNodeService.getNodeByInstanceIdAndNodeId(sbWFApprovalForm.getWfInstanceId(),sbWFApprovalForm.getFdHandleNodeId());
		if (sysWfNode == null) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.node",language));
		}
		
		//当前流程令牌验证
		SysWfToken sysWfToken = sysWfTokenService.findWfTokenByNodeIdAndInstanceId(sbWFApprovalForm.getFdHandleNodeId(),sbWFApprovalForm.getWfInstanceId());
		if (sysWfToken == null) {
			List<SysWfToken> sysWfTokens = sysWfTokenService.findTokenListByFdProcessId(sbWFApprovalForm.getWfInstanceId());
			if (sysWfTokens == null || sysWfTokens.size() < 1){
				throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.token",language));
			} else {
				sysWfToken = sysWfTokens.get(0);
			}
		}
		
		return sysWfNode;
	}
	
	/**
	 * 验证实例
	 */
	public SysWfNode validateInstance(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		SysWfNode sysWfNode = sysWfNodeService.getNodeByInstanceIdAndNodeId(sbWFApprovalForm.getWfInstanceId(),sbWFApprovalForm.getFdHandleNodeId());
		if (sysWfNode == null) {
			List<SysWfNode> sysWfNodes = sysWfNodeService.findByFdProcessId(sbWFApprovalForm.getWfInstanceId());
			if (sysWfNodes == null || sysWfNodes.size() < 1) {
				sysWfNode = new SysWfNode();
				sbWFApprovalForm.setFdHandleNodeId("");
			} else {
				sysWfNode = sysWfNodes.get(0);
				sbWFApprovalForm.setFdHandleNodeId(sysWfNode.getFdFactNodeId());
			}
		} else {
			sbWFApprovalForm.setFdHandleNodeId(sysWfNode.getFdFactNodeId());
		}
		
		return sysWfNode;
	}
	
	/**
	 * 验证审批人员权限
	 */
	@Override
	public void validateIdentityHandler(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		//验证操作人待办信息
		List<SysNotifyTodo> sysNotifyTodos = sysNotifyTodoService.findByWfInstanceAndUserNo(sbWFApprovalForm.getWfAuditedUserNo(),sbWFApprovalForm.getWfInstanceId());
		
		if (sysNotifyTodos == null || sysNotifyTodos.size() < 1) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.operator",language));
		}
		
		//验证当前节点操作权限
		List<SysWfWorkitem> sysWfWorkitems = sysWfWorkitemService.findByNodeNoAndUserId(sbWFApprovalForm.getFdHandleNodeId(),sbWFApprovalForm.getWfAuditedUserId(),sbWFApprovalForm.getWfInstanceId());
		if (null == sysWfWorkitems || sysWfWorkitems.size() < 1) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.node.operator",language));
		}
		
		//sysWfWorkitemService.trylockHandlerWorkitem(sysWfWorkitem);
		//sysWfWorkitemService.save(sysWfWorkitem);
		
		sbWFApprovalForm.setFdHandleWorkItemId(sysWfWorkitems.get(0).getFdId());
		
		String fdParameter = sysWfWorkitems.get(0).getFdParameter();
		if (StringUtils.isEmpty(fdParameter)) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.node.operator",language));
		}
		
		//验证当前节点操作权限
		String operatorTypes = "";
		String[] nodeAttrs = fdParameter.split(" ");
		for (int i = 0; i < nodeAttrs.length; i++) {
			String[] nodeAttr = nodeAttrs[i].split("=");
			if ("oprNames".equals(nodeAttr[0])) {
				operatorTypes = nodeAttr[1];
				break;
			}
		}
		
		if (!operatorTypes.contains(sbWFApprovalForm.getWfResult())) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.node.operator",language));
		}
		
		//转交人/驳回节点和沟通功能验证
		String[] toOtherParams = null;
		switch (Integer.parseInt(sbWFApprovalForm.getWfResult())) {
			case OAConstant.HANDLER_OPERATION_TYPE_COMMISSION:
				if (StringUtil.isEmpty(sbWFApprovalForm.getWfTransferUserNo())) {
					StringBuffer otherHandlerId = new StringBuffer();
					StringBuffer otherHandlerNo = new StringBuffer();
					StringBuffer otherHandlerName = new StringBuffer();
					String[] toOtherHandlerNos = sbWFApprovalForm.getToOtherHandlerNos().split(";");
					
					for (String toOtherHandlerNo : toOtherHandlerNos) {
						SysOrgElement sysOrgElement = this.validateSysOrgElement(toOtherHandlerNo);
						otherHandlerId.append(";" + sysOrgElement.getFdId());
						otherHandlerNo.append(";" + sysOrgElement.getFdNo());
						otherHandlerName.append(";" + sysOrgElement.getFdName());
					}
					
					sbWFApprovalForm.setToOtherHandlerIds(otherHandlerId.toString().substring(1));
					sbWFApprovalForm.setToOtherHandlerNos(otherHandlerNo.toString().substring(1));
					sbWFApprovalForm.setToOtherHandlerNames(otherHandlerName.toString().substring(1));
				} else {
					SysOrgElement sysOrgElement = this.validateSysOrgElement(sbWFApprovalForm.getWfTransferUserNo());
					sbWFApprovalForm.setToOtherHandlerIds(sysOrgElement.getFdId());
					sbWFApprovalForm.setToOtherHandlerNos(sysOrgElement.getFdNo());
					sbWFApprovalForm.setToOtherHandlerNames(sysOrgElement.getFdName());
				}
				
				break;
			case OAConstant.HANDLER_OPERATION_TYPE_REFUSE:
				if (StringUtils.isEmpty(sbWFApprovalForm.getFutureNodeId())) {
					sbWFApprovalForm.setFutureNodeId("N2");
				}
				
				break;
			case OAConstant.HANDLER_OPERATION_TYPE_COMMUNICATE:
				if (StringUtils.isNotEmpty(sbWFApprovalForm.getCommicateHandlerNos())) {
					toOtherParams = sbWFApprovalForm.getCommicateHandlerNos().split(";");
				} else if (StringUtils.isNotEmpty(sbWFApprovalForm.getCommicateHandlerIds())) {
					toOtherParams = sbWFApprovalForm.getCommicateHandlerIds().split(";");
				} else {
					throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.communite.persons",language));
				}
				
				if (toOtherParams != null && toOtherParams.length > 0) {
					StringBuffer otherHandlerId = new StringBuffer();
					StringBuffer otherHandlerNo = new StringBuffer();
					StringBuffer otherHandlerName = new StringBuffer();
					
					for (String toOtherParam : toOtherParams) {
						SysOrgElement sysOrgElement = this.validateSysOrgElement(toOtherParam);
						otherHandlerId.append(";" + sysOrgElement.getFdId());
						otherHandlerNo.append(";" + sysOrgElement.getFdNo());
						otherHandlerName.append(";" + sysOrgElement.getFdName());
					}
					
					sbWFApprovalForm.setToOtherHandlerIds(otherHandlerId.toString().substring(1));
					sbWFApprovalForm.setToOtherHandlerNos(otherHandlerNo.toString().substring(1));
					sbWFApprovalForm.setToOtherHandlerNames(otherHandlerName.toString().substring(1));
				}
				
				break;
			case OAConstant.HANDLER_OPERATION_TYPE_CELCOMMUNICATE:
				if (StringUtils.isNotEmpty(sbWFApprovalForm.getCommicateHandlerNos())) {
					toOtherParams = sbWFApprovalForm.getCommicateHandlerNos().split(";");
				} else if (StringUtils.isNotEmpty(sbWFApprovalForm.getCommicateHandlerIds())) {
					toOtherParams = sbWFApprovalForm.getCommicateHandlerIds().split(";");
				} else {
					throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.communite.canclePersonsId",language));
				}
				
				if (toOtherParams != null && toOtherParams.length > 0) {
					StringBuffer unrelatedCommunicater = new StringBuffer();
					StringBuffer celRelationWorkitemIds = new StringBuffer();
					StringBuffer otherHandlerId = new StringBuffer();
					StringBuffer otherHandlerNo = new StringBuffer();
					StringBuffer otherHandlerName = new StringBuffer();

					for (String toOtherParam : toOtherParams) {
						SysOrgElement sysOrgElement = this.validateSysOrgElement(toOtherParam);
						SysWfWorkitem relationWorkitem = sysWfWorkitemService.findByFdRelationWorkitemIdAndFdExpectedId(sysWfWorkitems.get(0).getFdId(),sysOrgElement.getFdId());
						
						if (null == relationWorkitem) {
							unrelatedCommunicater.append(";" + sysOrgElement.getFdName());
						}else {
							otherHandlerId.append(";" + sysOrgElement.getFdId());
							otherHandlerNo.append(";" + sysOrgElement.getFdNo());
							otherHandlerName.append(";" + sysOrgElement.getFdName());
							celRelationWorkitemIds.append(";" + relationWorkitem.getFdId());
						}
					}
					
					if (StringUtils.isEmpty(celRelationWorkitemIds.toString())) {
						throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.communite.canclePersons",language));
					}
					
					if (StringUtils.isNotEmpty(unrelatedCommunicater.toString())) {
						log.warn("["+unrelatedCommunicater.toString()+"]"+"不在当前节点沟通人员范围内.....");
					}
					
					sbWFApprovalForm.setToOtherHandlerIds(otherHandlerId.toString().substring(1));
					sbWFApprovalForm.setToOtherHandlerNos(otherHandlerNo.toString().substring(1));
					sbWFApprovalForm.setToOtherHandlerNames(otherHandlerName.toString().substring(1));
					sbWFApprovalForm.setCelRelationWorkitemIds(celRelationWorkitemIds.substring(1));
				}
				
				break;
		}
	}
	
	/**
	 * 验证起草人权限
	 */
	public void validateIdentityDraft(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		SysNewsMain sysNewsMain = sysNewsMainService.findByUserNoAndWfinstanceId(sbWFApprovalForm.getWfAuditedUserNo(),sbWFApprovalForm.getWfInstanceId());
		
		if (sysNewsMain == null) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.operator",language));
		}
		
		SysWfNode sysWfNode = sysWfNodeService.getNodeByInstanceIdAndNodeId(sbWFApprovalForm.getWfInstanceId(),sbWFApprovalForm.getFdHandleNodeId());
		if (sysWfNode == null) {
			sbWFApprovalForm.setFdHandleWorkItemId("");
		} else {
			List<SysWfWorkitem> sysWfWorkitems = sysWfWorkitemService.findByNodeId(sysWfNode.getFdId());
			if (sysWfWorkitems != null && sysWfWorkitems.size() > 0) {
				sbWFApprovalForm.setFdHandleWorkItemId(sysWfWorkitems.get(0).getFdId());
			} else {
				sbWFApprovalForm.setFdHandleWorkItemId("");
			}
		}
	}
	
	/**
	 * 验证特权人权限
	 */
	public void validateIdentityPrivileger(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		SysWfNode sysWfNode = sysWfNodeService.getNodeByInstanceIdAndNodeId(sbWFApprovalForm.getWfInstanceId(),sbWFApprovalForm.getFdHandleNodeId());
		if (sysWfNode == null) {
			sbWFApprovalForm.setFdHandleWorkItemId("");
		} else {
			List<SysWfWorkitem> sysWfWorkitems = sysWfWorkitemService.findByNodeId(sysWfNode.getFdId());
			if (sysWfWorkitems != null && sysWfWorkitems.size() > 0) {
				sbWFApprovalForm.setFdHandleWorkItemId(sysWfWorkitems.get(0).getFdId());
			} else {
				sbWFApprovalForm.setFdHandleWorkItemId("");
			}
		}
		
		String[] toOtherParams = null;
		switch (Integer.parseInt(sbWFApprovalForm.getWfResult())) {
			case OAConstant.ADMIN_OPERATION_TYPE_CHGCURHANDLER:
				StringBuffer otherHandlerId = new StringBuffer();
				StringBuffer otherHandlerNo = new StringBuffer();
				StringBuffer otherHandlerName = new StringBuffer();
				
				if (StringUtils.isNotEmpty(sbWFApprovalForm.getToOtherHandlerNos())) {
					toOtherParams = sbWFApprovalForm.getToOtherHandlerNos().split(";");
				} else if (StringUtils.isNotEmpty(sbWFApprovalForm.getToOtherHandlerIds())) {
					toOtherParams = sbWFApprovalForm.getToOtherHandlerIds().split(";");
				}
				
				for (String toOtherParam : toOtherParams) {
					SysOrgElement sysOrgElement = this.validateSysOrgElement(toOtherParam);
					otherHandlerId.append(";" + sysOrgElement.getFdId());
					otherHandlerNo.append(";" + sysOrgElement.getFdNo());
					otherHandlerName.append(";" + sysOrgElement.getFdName());
				}
				
				sbWFApprovalForm.setToOtherHandlerIds(otherHandlerId.toString().substring(1));
				sbWFApprovalForm.setToOtherHandlerNos(otherHandlerNo.toString().substring(1));
				sbWFApprovalForm.setToOtherHandlerNames(otherHandlerName.toString().substring(1));
				
				break;
		}
	}

	/**
	 * 验证授权信息是否存在
	 */
	@Override
	public void validateSbWFAuthority(String wfAuthorityIdStr) throws WorkflowException {
		String[] wfAuthorityIds = wfAuthorityIdStr.split(";");
		for (String wfAuthorityId : wfAuthorityIds) {
			SysWfAuthorize sysAuthorize = sysWfAuthorizeService.findOne(wfAuthorityId);
			if (sysAuthorize == null) {
				throw new WorkflowException(resourceUtil.getLocaleMessage("runtime.validate.authority",language));
			}
		}
	}
}
