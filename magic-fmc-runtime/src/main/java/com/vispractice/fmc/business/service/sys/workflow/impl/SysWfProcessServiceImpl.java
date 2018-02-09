package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;
import com.vispractice.fmc.business.entity.sys.workflow.beans.parameter.NodeParameter;
import com.vispractice.fmc.business.entity.sys.workflow.beans.parameter.WorkitemParameter;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfHistoryNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfProcessRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTemplateRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfNodeService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfProcessService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfTokenService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfWorkitemService;

/**
 * @ClassName SysWfProcessServiceImpl 流程实例服务实现类
 * @author sky
 * @Date May 15, 2017
 * @version 1.0.0
 */
@Service
@Transactional
public class SysWfProcessServiceImpl implements ISysWfProcessService {
	@Autowired
	private SysWfProcessRepository sysWfProcessRepository;

	@Autowired
	private SysWfTemplateRepository sysWfTemplateRepository;

	@Autowired
	private SysWfHistoryNodeRepository sysWfHistoryNodeRepository;

	@Autowired
	private ISysWfNodeService sysWfNodeService;

	@Autowired
	private ISysWfTokenService sysWfTokenService;

	@Autowired
	private ISysWfWorkitemService sysWfWorkitemService;
	
	/**
	 * 根据模型找出当前实例
	 */
	@Override
	public SysWfProcess findByFdModelId(String fdModelId) throws WorkflowException {
		List<SysWfProcess> list = sysWfProcessRepository.findByFdModelId(fdModelId);

		if (list.size() == 1) {
			return list.get(0);
		} else {
			throw new WorkflowException("找不到流程实例.");
		}
	}

	/**
	 * 构造流程实例上下文
	 * @param sbWFApprovalForm
	 * @return
	 */
	private void buildSysWfProcess(ProcessContext context,String modelName,String mainKey) {
		SysNewsMain sysNewsMain = context.getSysNewsMain();
		
		SysWfProcess sysWfProcess = sysWfProcessRepository.findOne(sysNewsMain.getFdId());;
		if (sysWfProcess == null) {
			sysWfProcess = new SysWfProcess();
			SysWfTemplate sysWfTemplateExample = new SysWfTemplate();
			sysWfTemplateExample.setFdModelId(sysNewsMain.getFdTemplateId());
			Example<SysWfTemplate> example = Example.of(sysWfTemplateExample);
			SysWfTemplate sysWfTemplate = sysWfTemplateRepository.findOne(example);
			if (null != sysWfTemplate) {
				sysWfProcess.setFdTemplateId(sysWfTemplate.getFdId());
				sysWfProcess.setFdModelName(modelName);
				sysWfProcess.setFdOriDetail(sysWfTemplate.getFdFlowContent());
				sysWfProcess.setFdDetail(sysWfTemplate.getFdFlowContent());
				sysWfProcess.setFdKey(mainKey);

				sysWfProcess.setFdDayNotifyPrivileger(ProcessLogicHelper.getDayOfNotify(sysWfProcess.getFdDetail(),"dayOfNotifyPrivileger"));
				sysWfProcess.setFdDayNotifyDrafter(ProcessLogicHelper.getDayOfNotify(sysWfProcess.getFdDetail(),"dayOfNotifyDrafter"));

				sysWfProcess.setFdDescriptor(ProcessLogicHelper.getProcessDescriptorXML(sysWfProcess.getFdDetail()));
				sysWfProcess.setFdParameter(ProcessLogicHelper.getProcessParameterXML(sysWfProcess.getFdDetail()));
			}
			
			sysWfProcess.setFdId(sysNewsMain.getFdId());
			sysWfProcess.setFdModelId(sysNewsMain.getFdId()); 
			sysWfProcess.setFdStatus(SysWfProcess.CREATED);
			sysWfProcess.setFdCreatorId(sysNewsMain.getDocCreatorId());
			sysWfProcess.setFdCreateTime(sysNewsMain.getDocCreateTime());
			sysWfProcess.setFdSubStatus(0L);
		}
		sysWfProcess.setFdDetail(ProcessLogicHelper.updateDrafterProcessFdDetail(context,sysWfProcess.getFdDetail()));
		sysWfProcessRepository.save(sysWfProcess);
		
		context.setSysWfProcess(sysWfProcess);
	}
	
	/**
	 * 启动流程实例
	 */
	@Override
	public void startProcess(ProcessContext context,String modelId,String mainKey) throws WorkflowException {
		//构造流程实例
		this.buildSysWfProcess(context,modelId,mainKey);
		
		//初始化节点信息
		SysWfNode sysWfNode = initWorkflowfNode(context);
		
		//初始化令牌信息
		sysWfTokenService.initRootToken(context,sysWfNode.getFdId());
	}

	/**
	 * 生成流程节点信息
	 * @param sysWfProcess
	 */
	private SysWfNode initWorkflowfNode(ProcessContext context) throws WorkflowException {
		SysWfProcess sysWfProcess = context.getSysWfProcess();
		Map<String,String> draftNode = ProcessLogicHelper.getDraftNode(sysWfProcess.getFdDescriptor());

		//当前节点
		SysWfNode sysWfNode = new SysWfNode();
		sysWfNode.setFdFactNodeId(draftNode.get("fdFactNodeId"));
		sysWfNode.setFdFactNodeName(draftNode.get("fdFactNodeName"));
		sysWfNode.setFdProcessId(sysWfProcess.getFdId());
		sysWfNode.setFdTargetId(draftNode.get("fdTargetId"));
		sysWfNode.setFdTargetName(draftNode.get("fdTargetName"));
		sysWfNode.setFdStatus(SysWfNode.ACTIVATED);
		sysWfNode.setFdRouteType("NORMAL");
		sysWfNode.setFdNodeType(draftNode.get("fdNodeType"));
		sysWfNode.setFdStartDate(sysWfProcess.getFdCreateTime());
		sysWfNode.setFdParameter(ProcessLogicHelper.getNodeParam());

		//历史节点
		SysWfHistoryNode sysWfHistoryNode = new SysWfHistoryNode();
		sysWfHistoryNode.setFdNodeId("0");
		sysWfHistoryNode.setFdProcessId(sysWfProcess.getFdId());
		sysWfHistoryNode.setFdOrder(new Date().getTime());
		sysWfHistoryNode.setFdFactNodeId(draftNode.get("startNodeId"));
		sysWfHistoryNode.setFdFactNodeName(draftNode.get("startNodeName"));
		sysWfHistoryNode.setFdStartDate(sysWfProcess.getFdCreateTime());
		sysWfHistoryNode.setFdFinishDate(sysWfProcess.getFdCreateTime());
		sysWfHistoryNode.setFdTokenHierarchyId("0");
		sysWfHistoryNode.setFdRouteType("NORMAL");
		sysWfHistoryNode.setFdTargetId(draftNode.get("fdFactNodeId"));
		sysWfHistoryNode.setFdTargetName(draftNode.get("fdFactNodeName"));
		sysWfHistoryNode.setFdNodeType(draftNode.get("startNodeType"));
		try {
			sysWfNodeService.saveNode(sysWfNode);
			sysWfHistoryNodeRepository.save(sysWfHistoryNode);
		} catch (Exception e) {
			throw new WorkflowException("生成节点信息失败." + e.getMessage());
		}
		context.setFdHandleNodeId(sysWfNode.getFdFactNodeId());

		SysWfWorkitem sysWfWorkitem = new SysWfWorkitem();
		sysWfWorkitem.setFdStartDate(sysWfProcess.getFdCreateTime());
		sysWfWorkitem.setFdNotifyOnFinish(true);
		sysWfWorkitem.setFdNotifyType("todo");
		sysWfWorkitem.setFdNodeId(sysWfNode.getFdId());
		sysWfWorkitem.setFdStatus(sysWfNode.getFdStatus());
		sysWfWorkitem.setFdExpectedId(sysWfProcess.getFdCreatorId());
		sysWfWorkitem.setFdIsDone(1L);
		try {
			sysWfWorkitemService.save(sysWfWorkitem);
		} catch (Exception e) {
			throw new WorkflowException("生成工作项信息失败." + e.getMessage());
		}
		context.setFdHandleWorkItemId(sysWfWorkitem.getFdId());
		
		WorkitemParameter wParam = ProcessLogicHelper.initWorkitemParameters(sysWfNode,OAConstant.CREATOR_OPERATION_TYPE_SUBMIT,sysWfProcess.getFdDetail());
		wParam.setWorkitemId(sysWfWorkitem.getFdId());
		wParam.setFutureNodeId(context.getFutureNodeId());
		wParam.setCurrHandlerOffset(0);
		wParam.setHandlerIdentity("2");
		wParam.setAuditNode(context.getApprovalOpinion());
		wParam.setOprNames("201:%sysWfOperations.fdOperType.draft.submit%");
		sysWfWorkitem.setFdParameter(wParam.toString());
		wParam.setHandlerId(context.getCurrentUser().getFdId());
		context.setFdWorkItemParameter(wParam.toString());
		context.setFdProcessorInfoXML(getFdProcessorInfoXML(context,sysWfNode));
		
		return sysWfNode;
	}

	

	/**
	 * 重新加载流程上下文
	 */
	@Override
	public void reloadProcessContext(ProcessContext context) throws WorkflowException {
		try {
			//更新流程实例
			this.buildSysWfProcess(context,null,null);
			
			//加载节点信息
			SysWfNode sysWfNode = new SysWfNode();
			sysWfNode.setFdProcessId(context.getSysWfProcess().getFdId());
			Example<SysWfNode> nodeExample = Example.of(sysWfNode);
			sysWfNode = sysWfNodeService.findByExample(nodeExample);
			context.setFdHandleNodeId("N2");
			
			//加载工作事项
			SysWfWorkitem sysWfWorkitem = new SysWfWorkitem();
			sysWfWorkitem.setFdNodeId(sysWfNode.getFdId());
			Example<SysWfWorkitem> workitemExample = Example.of(sysWfWorkitem);
			sysWfWorkitem = sysWfWorkitemService.findByExample(workitemExample);
			context.setFdHandleWorkItemId(sysWfWorkitem.getFdId());
			
			WorkitemParameter wParam = WorkitemParameter.parse(sysWfWorkitem.getFdParameter());
			wParam.setFutureNodeId(context.getFutureNodeId());
			wParam.setHandlerId(context.getCurrentUser().getFdId());
			wParam.setHandlerIdentity("2");
			wParam.setAuditNode(context.getApprovalOpinion());
			context.setFdWorkItemParameter(wParam.toString());
			context.setFdProcessorInfoXML(getFdProcessorInfoXML(context,sysWfNode));
		} catch (Exception e) {
			throw new WorkflowException("重新提交时,加载流程实例上下文环境失败." + e.getMessage());
		}
	}

	/**
	 * 查找流程实例信息
	 */
	@Override
	public SysWfProcess findByProcessId(String wfProcessId) throws WorkflowException {
		try {
			return sysWfProcessRepository.findOne(wfProcessId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WorkflowException(e.getMessage());
		}
	}
	
	/**
	 * 获取流程实例信息
	 * @param context
	 * @param sysWfNode
	 * @return
	 * @throws WorkflowException
	 */
	private String getFdProcessorInfoXML(ProcessContext context,SysWfNode sysWfNode) throws WorkflowException {
		StringBuffer processorInfo = new StringBuffer();
		processorInfo.append(" <processor isArray=\"false\" CHILDRENISARRAY=\"true\" identifyKey=\""
				+ OAConstant.IDENTITY_PROCESSOR + "\" identifyText=\"" + "处理人"
				+ "\">\n");
		
		try {
			processorInfo.append(getNodeFormInfo(context,sysWfNode));
		} catch (Exception e) {
			throw new WorkflowException("生成FdProcessorInfoXML错误." + e.getMessage());
		}

		return processorInfo.toString();
	}

	/**
	 * 获取节点信息
	 * @param context
	 * @param sysWfNode
	 * @return
	 * @throws Exception
	 */
	public String getNodeFormInfo(ProcessContext context,SysWfNode sysWfNode) throws Exception {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isNotEmpty(context.getFdWorkItemParameter())) {
			WorkitemParameter wiParameter = WorkitemParameter.parse(context.getFdWorkItemParameter());
	
			sb.append(" <info \n");
			sb.append("	nodeId=\"" + sysWfNode.getFdFactNodeId() + "\"\n");
			sb.append("	nodeModelId=\"" + sysWfNode.getFdId() + "\"\n");
			sb.append("	nodeType=\"" + sysWfNode.getFdNodeType() + "\"\n");
	
			sb.append("	workitemId=\"" + context.getFdHandleWorkItemId() + "\"\n");
			sb.append("	handlerId=\"" + context.getCurrentUser().getFdId() + "\"\n");
			sb.append("	notifyType=\"" + StringUtil.getString(wiParameter.getNotifyType()) + "\"\n");
	
			sb.append("	notifyMe=\"" + wiParameter.getNotifyOnFinish() + "\"\n");
			sb.append("	dayOfNotifyDrafter=\"" + StringUtil.getString(wiParameter.getDayOfNotifyDrafter()) + "\"\n");
			sb.append("	handlerIdentity=\"" + StringUtil.getString(wiParameter.getHandlerIdentity()) + "\"\n");
			sb.append("	operationType=\"" + StringUtil.getString(wiParameter.getOperationType()) + "\"\n");
			sb.append("	jumpToNodeId=\"" + StringUtil.getString(wiParameter.getJumpToNodeId()) + "\"\n");
			sb.append("	toOtherHandlerIds=\"" + StringUtil.getString(wiParameter.getToOtherHandlerIds()) + "\"\n");
			sb.append("	futureNodeId=\"" + StringUtil.getString(wiParameter.getFutureNodeId()) + "\"\n");
			
			sb.append(" reToHandlerId=\"" + StringUtil.getString(wiParameter.getReToHandlerId()) + "\"\n");
		} else {
			sb.append(" <info \n");
			sb.append("	nodeId=\"" + sysWfNode.getFdFactNodeId() + "\"\n");
			sb.append("	nodeModelId=\"" + sysWfNode.getFdId() + "\"\n");
			sb.append("	nodeType=\"" + sysWfNode.getFdNodeType() + "\"\n");
	
			sb.append("	workitemId=\"" + context.getFdHandleWorkItemId() + "\"\n");
			sb.append("	handlerId=\"" + context.getCurrentUser().getFdId() + "\"\n");
			sb.append("	notifyType=\"" + "\"\n");
	
			sb.append("	notifyMe=\"" + "\"\n");
			sb.append("	dayOfNotifyDrafter=\"" + "\"\n");
			sb.append("	handlerIdentity=\"" + "\"\n");
			sb.append("	operationType=\"" + "\"\n");
			sb.append("	jumpToNodeId=\"" + "\"\n");
			sb.append("	toOtherHandlerIds=\"" + "\"\n");
			sb.append("	futureNodeId=\"" + "\"\n");
			
			sb.append("	reToHandlerId=\"" + "\"\n");
		}
		
		if (StringUtils.isNotEmpty(sysWfNode.getFdParameter())) {
			NodeParameter nodeParemter = NodeParameter.parse(sysWfNode.getFdParameter());
			sb.append("	toRefuseThisNodeId=\"" + StringUtil.getString(nodeParemter.getToRefuseThisNodeId()) + "\"\n");
			sb.append("	toRefuseThisHandlerName=\"" + StringUtil.getString(nodeParemter.getToRefuseThisHandlerName()) + "\"\n");
			sb.append("	currHandlerOffset=\"" + nodeParemter.getCurrHandlerOffset() + "\"\n");
			sb.append("		 />\n");
		} else {
			sb.append("	toRefuseThisNodeId=\"" + "\"\n");
			sb.append("	toRefuseThisHandlerName=\"" + "\"\n");
			sb.append("	currHandlerOffset=\"" + "\"\n");
			sb.append("		 />\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取审批类型
	 */
	@Override
	public String getApprovalType(String operatorType) {
		String result = OAConstant.APPROVAL_TYPE_NODE;
		
		switch(Integer.parseInt(operatorType)) {
			case OAConstant.HANDLER_OPERATION_TYPE_PASS :
				result = OAConstant.APPROVAL_TYPE_NODE;
				break;
			case OAConstant.HANDLER_OPERATION_TYPE_REFUSE :
				result = OAConstant.APPROVAL_TYPE_NODE;
				break;
			case OAConstant.HANDLER_OPERATION_TYPE_COMMISSION :
				result = OAConstant.APPROVAL_TYPE_NODE;
				break;
			case OAConstant.HANDLER_OPERATION_TYPE_COMMUNICATE :
				result = OAConstant.APPROVAL_TYPE_NODE;
				break;
			case OAConstant.HANDLER_OPERATION_TYPE_ABANDON :
				result = OAConstant.APPROVAL_TYPE_NODE;
				break;
			case OAConstant.HANDLER_OPERATION_TYPE_SIGN :
				result = OAConstant.APPROVAL_TYPE_NODE;
				break;
			case OAConstant.HANDLER_OPERATION_TYPE_RTNCOMMUNICATE :
				result = OAConstant.APPROVAL_TYPE_NODE;
				break;
			case OAConstant.HANDLER_OPERATION_TYPE_CELCOMMUNICATE :
				result = OAConstant.APPROVAL_TYPE_NODE;
				break;
			case OAConstant.CREATOR_OPERATION_TYPE_PRESS :
				result = OAConstant.APPROVAL_TYPE_INSTANCE;
				break;
			case OAConstant.CREATOR_OPERATION_TYPE_RETURN :
				result = OAConstant.APPROVAL_TYPE_INSTANCE;
				break;
			case OAConstant.CREATOR_OPERATION_TYPE_ABANDON :
				result = OAConstant.APPROVAL_TYPE_INSTANCE;
				break;
			case OAConstant.ADMIN_OPERATION_TYPE_PASS :
				result = OAConstant.APPROVAL_TYPE_INSTANCE;
				break;
			case OAConstant.ADMIN_OPERATION_TYPE_ABANDON :
				result = OAConstant.APPROVAL_TYPE_INSTANCE;
				break;
			case OAConstant.ADMIN_OPERATION_TYPE_JUMP :
				result = OAConstant.APPROVAL_TYPE_INSTANCE;
				break;
			case OAConstant.ADMIN_OPERATION_TYPE_CHGCURHANDLER :
				result = OAConstant.APPROVAL_TYPE_NODE;
				break;
			case OAConstant.ADMIN_OPERATION_TYPE_RECOVER :
				result = OAConstant.APPROVAL_TYPE_INSTANCE;
				break;
		}
		
		
		return result;
	}
	
	/**
	 * 处理人审批流程
	 */
	public void sbWFHandlerApprovalProcess(ProcessContext context,SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		//设置工作事项参数
		WorkitemParameter workItemParameter = new WorkitemParameter();
		SysWfWorkitem sysWfWorkitem = sysWfWorkitemService.findByFdId(sbWFApprovalForm.getFdHandleWorkItemId());
		workItemParameter = ProcessLogicHelper.getHandlerWorkitemParameters(sbWFApprovalForm,sysWfWorkitem.getFdParameter());
		context.setFdHandleWorkItemId(sysWfWorkitem.getFdId());
		context.setFdWorkItemParameter(String.valueOf(workItemParameter));
		
		//更新流程实例信息
		SysWfProcess sysWfProcess = context.getSysWfProcess();
		sysWfProcess.setFdDetail(ProcessLogicHelper.updateHandlerProcessFdDetail(sbWFApprovalForm,sysWfProcess.getFdDetail()));
		sysWfProcessRepository.save(sysWfProcess);
	}
	
	/**
	 * 特权人处理流程
	 */
	public void sbWFPrivilegerApprovalProcess(ProcessContext context,SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		//更新流程实例信息
		SysWfProcess sysWfProcess = context.getSysWfProcess();
		sysWfProcess.setFdDetail(ProcessLogicHelper.updatePrivilegerProcessFdDetail(sbWFApprovalForm,sysWfProcess.getFdDetail()));
		sysWfProcessRepository.save(sysWfProcess);
				
		//设置工作事项参数
		WorkitemParameter workItemParameter = new WorkitemParameter();
		workItemParameter = ProcessLogicHelper.getPrivilegerWorkitemParameters(sbWFApprovalForm);
		context.setFdWorkItemParameter(String.valueOf(workItemParameter));
	}

	/**
	 * 起草人处理流程
	 */
	public void sbWFDrafterApprovalProcess(ProcessContext context,SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		//设置工作事项参数
		WorkitemParameter workItemParameter = new WorkitemParameter();
		workItemParameter = ProcessLogicHelper.getDrafterWorkitemParameters(sbWFApprovalForm);
		context.setFdWorkItemParameter(String.valueOf(workItemParameter));
	}
	
	/**
	 * 获取操作类型所属身份
	 * @return
	 */
	@Override
	public int getIdentity(String operatorType) {
		if (StringUtil.isEmpty(operatorType)) {
			return OAConstant.HANDLER_IDENTITY_HANDLER;
		}
		
		if (operatorType.startsWith("1")) {
			return OAConstant.HANDLER_IDENTITY_HANDLER;
		} else if (operatorType.startsWith("2")) {
			return OAConstant.HANDLER_IDENTITY_DRAFT;
		} else if (operatorType.startsWith("3")) {
			return OAConstant.HANDLER_IDENTITY_PRIVILEGER;
		} else {
			return OAConstant.HANDLER_IDENTITY_SYSTEM;
		}
	}

	
	/**
	 * 获取父流程对应子流程
	 */
	@Override
	public List<SysWfProcess> findSubProcessByParentId(String wfInstanceId) throws WorkflowException {
		return sysWfProcessRepository.findByFdParentid(wfInstanceId);
	}

	@Override
	public void save(SysWfProcess process) {
		sysWfProcessRepository.save(process);		
	}

	@Override
	public SysWfProcess lockByProcessId(String instancdId) throws WorkflowException {
		return sysWfProcessRepository.lockProcess(instancdId);
	}
}
