package com.vispractice.fmc.business.service.sys.facade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.SysDocConstant;
import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.aboutmyself.AuditBillV;
import com.vispractice.fmc.business.entity.aboutmyself.VCmsTask;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;
import com.vispractice.fmc.business.entity.sys.news.view.DocumentSubmmitedV;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessHistoryV;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessLogV;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessReadV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorize;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorizeItem;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorizeScope;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuthorizeItemRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuthorizeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuthorizeScopeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfProcessRepository;
import com.vispractice.fmc.business.service.aboutmyself.IAuditBillVService;
import com.vispractice.fmc.business.service.aboutmyself.IVCmsTaskService;
import com.vispractice.fmc.business.service.category.ICategoryMainService;
import com.vispractice.fmc.business.service.sys.facade.IProcessFacade;
import com.vispractice.fmc.business.service.sys.facade.IValidateFacade;
import com.vispractice.fmc.business.service.sys.news.IProcessReadService;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainService;
import com.vispractice.fmc.business.service.sys.news.ISysNewsTemplateService;
import com.vispractice.fmc.business.service.sys.notify.ISysNotifyTodoProvider;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPersonService;
import com.vispractice.fmc.business.service.sys.workflow.IComponentLockerMainService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfEventService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfHistoryNodeService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfNodeService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfProcessService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfTemplateService;

/**
 * 流程调用服务接口实现
 * @author sky
 * @Date May 11, 2017
 * @version 1.0.0
 */
@Service
@Transactional
public class ProcessFacadeImpl implements IProcessFacade {
	/**
	 * 单据信息服务 
	 */
	@Autowired
	private ISysNewsMainService sysNewsMainService;
	
	/**
	 * 流程实例服务 
	 */
	@Autowired
	private ISysWfProcessService sysWfProcessService;
	
	/**
	 * 流程实例服务 
	 */
	@Autowired
	private SysWfProcessRepository sysWfProcessRepository;
	
	/**
	 * 流程代办事件 
	 */
	@Autowired
	private ISysWfEventService sysWfEventService;
	
	/**
	 * 代办任务服务 
	 */
	@Autowired
	private IVCmsTaskService vCmsTaskService;
	
	/**
	 * 已办任务服务 
	 */
	@Autowired
	private IAuditBillVService auditBillVService;
	
	/**
	 * 流程模板类别服务 
	 */
	@Autowired
	private ICategoryMainService categoryMainService;
	
	/**
	 * 待阅单据信息服务
	 */
	@Autowired
	private IProcessReadService processReadService;
	
	/**
	 * 流程模板服务
	 */
	@Autowired
	private ISysWfTemplateService sysWfTemplateService;
	
	/**
	 * 流程模板服务
	 */
	@Autowired
	private ISysNewsTemplateService sysNewsTemplateService;
	
	/**
	 * 审批历史服务
	 */
	@Autowired
	private ISysWfHistoryNodeService sysWfHistoryNodeService;
	
	/**
	 * 节点服务
	 */
	@Autowired
	private ISysWfNodeService sysWfNodeService;
	
	/**
	 * 组织服务
	 */
	@Autowired
	private SysOrgElementRepository sysOrgElementService;
	
	/**
	 * 人员服务
	 */
	@Autowired
	private ISysOrgPersonService sysOrgPersonService;
	
	/**
	 * 验证服务
	 */
	@Autowired
	private IValidateFacade validateFacade;
	
	/**
	 * 权限管理服务
	 */
	@Autowired
	private SysWfAuthorizeRepository sysWfAuthorizeService;
	
	/**
	 * 权限范围服务
	 */
	@Autowired
	private SysWfAuthorizeScopeRepository sysWfAuthorizeScopeService;
	
	/**
	 * 权限项服务
	 */
	@Autowired
	private SysWfAuthorizeItemRepository sysWfAuthorizeItemService;
	
	/**
	 * 代办服务
	 */
	@Autowired
	private ISysNotifyTodoProvider sysNotifyTodoProvider;
	
	/**
	 * 加锁服务
	 */
	@Autowired
	private IComponentLockerMainService componentLockerMainService;

	/**
	 * 组织导入服务 
	 */
	@Override
 	public void sbWFOrgImportSrv() throws WorkflowException {
	}

	/**
	 * 岗位导入服务 
	 */
	@Override
	public void sbWFPostImportSrv() throws WorkflowException {
	}

	/**
	 * 人员导入服务 
	 */
	@Override
	public void sbWFPersonImportSrv() throws WorkflowException {
	}
	
	/**
	 * 流程授权服务
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String sbWFAuthorizeSrv(Map<String,Object> wfAuthorize) throws WorkflowException {
		SysWfAuthorize sysWfAuthorize = new SysWfAuthorize();
		
		if (StringUtils.isNotEmpty(String.valueOf(wfAuthorize.get("wfAuthorizeIds")))) {
			String wfAuthorizeIds = String.valueOf(wfAuthorize.get("wfAuthorizeIds"));
			sysWfAuthorize.setFdId(wfAuthorizeIds);
			sysWfAuthorizeItemService.deleteByFdAuthorizeId(wfAuthorizeIds);
			sysWfAuthorizeScopeService.deleteByfdAuthorizeId(wfAuthorizeIds);
		}
		
		String fdAuthorizerNo = String.valueOf(wfAuthorize.get("fdAuthorizer"));
		sysWfAuthorize.setFdAuthorizer(fdAuthorizerNo);
		sysWfAuthorize.setFdAuthorizedPerson(String.valueOf(wfAuthorize.get("fdAuthorizedPerson")));
		sysWfAuthorize.setFdAuthorizeType(String.valueOf(wfAuthorize.get("fdAuthorizeType")));
		sysWfAuthorize.setFdStartTime((java.util.Date)wfAuthorize.get("fdStartTime"));
		sysWfAuthorize.setFdEndTime((java.util.Date)wfAuthorize.get("fdEndTime"));
		sysWfAuthorize.setFdExpireDeleted(String.valueOf(wfAuthorize.get("fdExpireDeleted")));
		sysWfAuthorize.setFdCreator(String.valueOf(wfAuthorize.get("fdAuthorizer")));
		sysWfAuthorize.setFdCreateTime(new java.util.Date(System.currentTimeMillis()));
		sysWfAuthorize = sysWfAuthorizeService.save(sysWfAuthorize);
		
		SysWfAuthorizeItem sysWfAuthorizeItem = new SysWfAuthorizeItem();
		sysWfAuthorizeItem.setFdAuthorizeId(sysWfAuthorize.getFdId());
		sysWfAuthorizeItem.setFdAuthorizeOrgId(fdAuthorizerNo);
		sysWfAuthorizeItemService.save(sysWfAuthorizeItem);
		
		List<SysOrgElement> sysOrgElements = sysOrgElementService.getPostElement(fdAuthorizerNo);
		for (SysOrgElement sysOrgElement : sysOrgElements) {
			sysWfAuthorizeItem = new SysWfAuthorizeItem();
			sysWfAuthorizeItem.setFdAuthorizeId(sysWfAuthorize.getFdId());
			sysWfAuthorizeItem.setFdAuthorizeOrgId(sysOrgElement.getFdId());
			sysWfAuthorizeItemService.save(sysWfAuthorizeItem);
		}
		
		List<String> wfTemplateIds = (List<String>) wfAuthorize.get("wfTemplateIds");
		for (String wfTemplateId : wfTemplateIds) {
			SysWfAuthorizeScope sysWfAuthorizeScope = new SysWfAuthorizeScope();
			SysNewsTemplate sysNewsTemplate = sysNewsTemplateService.findTemplateByFdId(wfTemplateId);
			sysWfAuthorizeScope.setFdAuthorizeCateId(sysNewsTemplate.getFdId());
			sysWfAuthorizeScope.setFdAuthorizeCateName(sysNewsTemplate.getFdName());
			sysWfAuthorizeScope.setFdTemplateId(sysNewsTemplate.getFdId());
			sysWfAuthorizeScope.setFdAuthorizeCateShowText(sysNewsTemplate.getFdName());
			sysWfAuthorizeScope.setFdModelName("com.ruiyi.kmss.sys.news.model.SysNewsMain");
			sysWfAuthorizeScope.setFdAuthorizeId(sysWfAuthorize.getFdId());
			sysWfAuthorizeScopeService.save(sysWfAuthorizeScope);
		}
		
		return sysWfAuthorize.getFdId();
	}

	/**
	 * 回收授权服务 
	 */
	@Override
	public void sbWFRevokeAuthorizeSrv(List<String> wfAuthorizeIds) throws WorkflowException {
		for (String wfAuthorizeId : wfAuthorizeIds) {
			sysWfAuthorizeService.deleteByFdId(wfAuthorizeId);
			sysWfAuthorizeScopeService.deleteByfdAuthorizeId(wfAuthorizeId);
			sysWfAuthorizeItemService.deleteByFdAuthorizeId(wfAuthorizeId);
		}
	}
	
	/**
	 * 启动流程信息
	 */
	public String sbWFStartProcessSrv(ProcessContext context,boolean skipDraftNode) {
		SysNewsMain sysNewsMain = context.getSysNewsMain();
		SysNewsMain existSysNewsMain = null;
		
		//查找是否重新提交单据
		if(null != sysNewsMain.getBusiSysId() && null != sysNewsMain.getApplyCode()) {
			existSysNewsMain = sysNewsMainService.findByBusiSysIdAndApplCode(sysNewsMain.getBusiSysId(),sysNewsMain.getApplyCode());
		} else if (null != sysNewsMain.getBusiSysId() && null != sysNewsMain.getFdId()) {
			existSysNewsMain = sysNewsMainService.getByFdId(sysNewsMain.getFdId());
		}
		
		if (skipDraftNode) {
			sysNewsMain.setDocStatus(SysDocConstant.DOC_STATUS_EXAMINE);
		} else {
			sysNewsMain.setDocStatus(SysDocConstant.DOC_STATUS_DRAFT);
		}

		if (null != existSysNewsMain) {
			sysNewsMain.setFdId(existSysNewsMain.getFdId());
			//修改单据信息
			sysNewsMainService.submitDocument(sysNewsMain);
			sysWfProcessService.reloadProcessContext(context);
		} else {
			//新增单据信息
			sysNewsMainService.submitDocument(sysNewsMain);
			sysWfProcessService.startProcess(context,"com.ruiyi.kmss.sys.news.model.SysNewsMain","newsMainDoc");
		}

		//生成流程待处理事件
		if (skipDraftNode) {
			sysWfEventService.addEvent(context,"N2",sysNewsMain.getDocCreatorId(),sysNewsMain.getFdImportance());
		}

		return sysNewsMain.getFdId();
	}
	
	/**
	 * 启动流程服务
	 */
	public String sbWFStartProcessSrv(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		ProcessContext context = new ProcessContext();
		context.setSysNewsMain(sbWFApprovalForm.getSysNewsMain());
		context.setCurrentUser(sysOrgPersonService.get(sbWFApprovalForm.getSysNewsMain().getDocCreatorId()));
		context.setFutureNodeId(sbWFApprovalForm.getFutureNodeId());
		context.setFutureHandIds(sbWFApprovalForm.getFutureHandlerIds());
		context.setFutureHandNames(sbWFApprovalForm.getFutureHandlerNames());
		context.setApprovalOpinion(sbWFApprovalForm.getWfOptionCon());
		
		return sbWFStartProcessSrv(context,true);
	}
	
	/**
	 * 构造业务处理上下文环境
	 * @param sbWFApprovalForm
	 * @return
	 */
	private ProcessContext buildProcessContext(SbWFApprovalForm sbWFApprovalForm) {
		ProcessContext processContext = new ProcessContext();
		SysWfProcess sysWfProcess = sysWfProcessService.findByProcessId(sbWFApprovalForm.getWfInstanceId());
		processContext.setSysWfProcess(sysWfProcess);
		processContext.setCurrentUser(sysOrgPersonService.getAvailablePersonByLoginName(sbWFApprovalForm.getWfAuditedUserNo()));
		if (StringUtils.isEmpty(sbWFApprovalForm.getFdHandleNodeId())) {
			processContext.setFdHandleNodeId("");
		} else {
			processContext.setFdHandleNodeId(sbWFApprovalForm.getFdHandleNodeId());
		}
		
		return processContext;
	}
	
	/**
	 * 基于节点审批流程服务
	 */
	@Override
	public void sbWFNodeApprovalSrv(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		//校验业务单据基本信息
		validateFacade.vildateSbWFApproval(sbWFApprovalForm);
		
		//加锁
		//componentLockerMainService.acquireLock(sbWFApprovalForm.getWfInstanceId(),sbWFApprovalForm.getWfAuditedUserId());
		
		//验证当前节点信息
		validateFacade.validateNode(sbWFApprovalForm);
		
		//构造业务上下文环境
		ProcessContext processContext = this.buildProcessContext(sbWFApprovalForm);
		
		//扩展加签功能
		if (StringUtils.isNotEmpty(sbWFApprovalForm.getWfResult()) && 
			sbWFApprovalForm.getWfResult().equals(String.valueOf(OAConstant.HANDLER_OPERATION_TYPE_COMMISSION)) &&
			sbWFApprovalForm.isRefusePassedToThisNode()) {
			sbWFApprovalForm.setWfResult(String.valueOf(OAConstant.ADMIN_OPERATION_TYPE_CHGCURHANDLER));
			sbWFApprovalForm.setSourceOperType("309:" + sbWFApprovalForm.getToOtherHandlerNames());
		}
		
		
		switch(sysWfProcessService.getIdentity(sbWFApprovalForm.getWfResult())) {
			case OAConstant.HANDLER_IDENTITY_HANDLER:
				validateFacade.validateIdentityHandler(sbWFApprovalForm);
				sysWfProcessService.sbWFHandlerApprovalProcess(processContext,sbWFApprovalForm);
				
				break;
			case OAConstant.HANDLER_IDENTITY_DRAFT:
				
				break;
			case OAConstant.HANDLER_IDENTITY_PRIVILEGER:
				validateFacade.validateIdentityPrivileger(sbWFApprovalForm);
				sysWfProcessService.sbWFPrivilegerApprovalProcess(processContext,sbWFApprovalForm);
				
				break;
			case OAConstant.HANDLER_IDENTITY_SYSTEM:
				break;
		}
		
		//生成引擎事件
		sysWfEventService.addEvent(processContext,sbWFApprovalForm.getFdHandleNodeId(),sbWFApprovalForm.getWfAuditedUserId(),new Long(0));
				
		//删除相关待办
		sysNotifyTodoProvider.clearSysNotifyTodo(sbWFApprovalForm,processContext);
	}

	/**
	 * 基于实例审批流程服务
	 */
	@Override
	public void sbWFInstanceApprovalSrv(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		//校验业务单据基本信息
		validateFacade.vildateSbWFApproval(sbWFApprovalForm);
		
		//验证当前节点信息
		validateFacade.validateInstance(sbWFApprovalForm);
		
		//构造业务上下文环境
		ProcessContext processContext = this.buildProcessContext(sbWFApprovalForm);
		
		//当前处理节点
		switch(sysWfProcessService.getIdentity(sbWFApprovalForm.getWfResult())) {
			case OAConstant.HANDLER_IDENTITY_HANDLER:
				
				break;
			case OAConstant.HANDLER_IDENTITY_DRAFT:
				validateFacade.validateIdentityDraft(sbWFApprovalForm);
				sysWfProcessService.sbWFDrafterApprovalProcess(processContext,sbWFApprovalForm);
				processContext.setFdHandleWorkItemId(sbWFApprovalForm.getFdHandleWorkItemId());
				break;
			case OAConstant.HANDLER_IDENTITY_PRIVILEGER:
				validateFacade.validateIdentityPrivileger(sbWFApprovalForm);
				sysWfProcessService.sbWFPrivilegerApprovalProcess(processContext,sbWFApprovalForm);
				
				break;
			case OAConstant.HANDLER_IDENTITY_SYSTEM:
				break;
		}
		
		//生成引擎事件
		sysWfEventService.addEvent(processContext,sbWFApprovalForm.getFdHandleNodeId(),sbWFApprovalForm.getWfAuditedUserId(),new Long(0));
						
		//删除相关待办
		sysNotifyTodoProvider.clearSysNotifyTodo(sbWFApprovalForm,processContext);
	}

	/**
	 * 根据流程实例标识获取单据信息
	 */
	@Override
	public SysNewsMain sbWFInquiryProcessStatusSrv(String instancdId) throws WorkflowException {
		SysNewsMain sysNewsMain = new SysNewsMain();
		
		SysWfProcess sysWfProcess = sysWfProcessService.findByProcessId(instancdId);
		if(null==sysWfProcess){
			return sysNewsMain;
		}
		
		sysNewsMain = sysNewsMainService.getByFdId(sysWfProcess.getFdModelId());
		
		return sysNewsMain;
	}

	/**
	 * 根据条件查询代办视图
	 */
	@Override
	public Page<VCmsTask> sbWFInquiryTaskSrv(VCmsTask vCmsTask,Pageable pageable) throws WorkflowException {
		Page<VCmsTask> page = vCmsTaskService.searchVCmsTask(vCmsTask,pageable);
		
		return page;
	}

	/**
	 * 根据条件查询已办视图
	 */
	@Override
	public Page<AuditBillV> sbWFInquiryTaskDoneSrv(AuditBillV auditBillV,Pageable pageable) throws WorkflowException {
		Page<AuditBillV> page = auditBillVService.searchTask(auditBillV,pageable);
		
		return page;
	}

	/**
	 * 根据条件查找已提交视图
	 */
	@Override
	public Page<DocumentSubmmitedV> sbWFInquiryApplySrv(DocumentSubmmitedV documentSubmmitedV,Pageable pageable) throws WorkflowException {
		Page<DocumentSubmmitedV> page = sysNewsMainService.searchDocumentSubmmitedV(documentSubmmitedV,pageable);
		
		return page;
	}

	/**
	 * 根据流程实例标识查找审批历史记录
	 */
	@Override
	public List<ProcessHistoryV> sbWFInquiryProcessHistorySrv(String wfInstanceId) throws WorkflowException {
		try{
			List<ProcessHistoryV> processHistoryVs = sysNewsMainService.findProcessHistory(wfInstanceId);
			
			return processHistoryVs;
		} catch (Exception e) {
			throw new WorkflowException(e.getMessage());
		}
	}

	/**
	 * 根据流程实例标识查找审批日志记录
	 */
	@Override
	public List<ProcessLogV> sbWFInquiryProcessLogSrv(String wfInstanceId) throws WorkflowException {
		try {
			List<ProcessLogV> processLogVs = sysNewsMainService.findProcessLog(wfInstanceId);
			
			return processLogVs;
		} catch (Exception e) {
			throw new WorkflowException(e.getMessage());
		}
	}

	/**
	 * 常用审批意见查询服务
	 */
	@Override
	public void sbWFInquiryCommonOpinionSrv() throws WorkflowException {
	}
	
	/**
	 * 流程模板查询服务 
	 */
	@Override
	public Map<String,Object> sbWFInquiryTemplateSrv(String cateId,boolean ifQueryAll) throws WorkflowException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(ifQueryAll){
			try {
				map = categoryMainService.getAllLevel(cateId);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				throw new WorkflowException("查询所有子孙级子类和模板发生错误.错误信息：" + e.getMessage());
			}
		}else{
			try {
				map = categoryMainService.getSingleLevel(cateId);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				throw new WorkflowException("查询所有子孙级子类和模板发生错误.错误信息：" + e.getMessage());
			}
		}
		
	}

	/**
	 * 查询模板所有节点信息
	 */
	@Override
	public Map<String,Map<String,String>> sbWFGetNodes(String templateId) throws WorkflowException {
		try {
			SysWfTemplate sysWfTemplate = sysWfTemplateService.findTemplateByModelIdOrNewOne(templateId);
			Map<String,Map<String,String>> result = ProcessLogicHelper.getTemplateNodes(sysWfTemplate.getFdFlowContent());
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WorkflowException(e.getMessage());
		}
	}
	
	/**
	 * 查询待阅服务
	 */
	@Override
	public Page<ProcessReadV> sbWFInquiryReadSrv(ProcessReadV processReadV,Pageable pageable) throws WorkflowException {
		processReadV.setIsRead(new Long(CommonConstant.NOT_AVAILABLE_FLAG));
		Page<ProcessReadV> result = processReadService.searchProcessRead(processReadV,pageable);
		
		return result;
	}

	/**
	 * 查询驳回节点服务
	 */
	@Override
	public Map<String,Map<String,String>> sbWFInquiryRejectNodeSrv(String wfInstanceId,String nodeId) throws WorkflowException {
		Map<String,Map<String,String>> result = new HashMap<String,Map<String,String>>();
		Map<String,SysWfHistoryNode> historyNodeMap = new HashMap<String,SysWfHistoryNode>();
		Map<String,String> item = null;
		SysWfHistoryNode historyNodeCurr = null;
		String[] nodeIds = null;
		boolean flag = true;
		List<SysWfHistoryNode> sysWfHistoryNodes = sysWfHistoryNodeService.findHistoryNodeByProcessId(wfInstanceId);
		
		for (SysWfHistoryNode sysWfHistoryNode : sysWfHistoryNodes) {
			if (sysWfHistoryNode.getFdRouteType().equals("NORMAL")) {
				nodeIds = sysWfHistoryNode.getFdTargetId().split(";");
				for (int i=0;i<nodeIds.length;i++) {
					if (!historyNodeMap.containsKey(nodeIds[i])) {
						historyNodeMap.put(nodeIds[i],sysWfHistoryNode);
					}
				}
				
				if (nodeId.equals(sysWfHistoryNode.getFdTargetId()) 
					|| sysWfHistoryNode.getFdTargetId().contains(nodeId)) {
					historyNodeCurr = sysWfHistoryNode;
				}
			}
		}
		
		while (!historyNodeCurr.getFdFactNodeId().equals("N1")) {
			switch(historyNodeCurr.getFdNodeType()) {
				case OAConstant.SPLIT_NODE_PARENT:
					flag = true;
					break;
				case OAConstant.AUDIT_NODE_PARENT:
					if (flag) {
						item = new HashMap<String,String>();
						item.put("nodeId",historyNodeCurr.getFdFactNodeId());
						item.put("nodeName",historyNodeCurr.getFdFactNodeName());
						result.put(historyNodeCurr.getFdFactNodeId(),item);
					}
					
					break;
				case OAConstant.DRAFT_NODE_PARENT:
					if (flag) {
						item = new HashMap<String,String>();
						item.put("nodeId",historyNodeCurr.getFdFactNodeId());
						item.put("nodeName",historyNodeCurr.getFdFactNodeName());
						result.put(historyNodeCurr.getFdFactNodeId(),item);
					}
					break;
				case OAConstant.JOIN_NODE_PARENT:
					flag = false;
					break;
			}
			
			historyNodeCurr = historyNodeMap.get(historyNodeCurr.getFdFactNodeId());
		}
		
		return result;
	}

	/**
	 * 查询指派节点服务
	 */
	@Override
	public Map<String,Map<String,String>> sbWFApprovalPerson(String wfInstanceId,String nodeId) throws WorkflowException {
		Map<String,Map<String,String>> result = new HashMap<String,Map<String,String>>();
		SysNewsMain sysNewsMain = sysNewsMainService.getByFdId(wfInstanceId);
		String fdFlowContent = sysWfTemplateService.getContentByTemplateId(sysNewsMain.getFdTemplateId());
		result = ProcessLogicHelper.getAssignNodes(fdFlowContent,nodeId);
		
		return result;
	}
	
	/**
	 * 查询节点上、下节点服务 
	 */
	@Override
	public Map<String,Map<String,Map<String,Object>>> sbWFLastAndNextNodes(String wfTemplateId,String nodeId) throws WorkflowException {
		try {
			SysWfTemplate sysWfTemplate = sysWfTemplateService.findTemplateByModelIdOrNewOne(wfTemplateId);
			Map<String,Map<String,Map<String,Object>>> result = ProcessLogicHelper.getNextNodes(sysWfTemplate.getFdFlowContent(),nodeId);
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WorkflowException(e.getMessage());
		}
	}
}
