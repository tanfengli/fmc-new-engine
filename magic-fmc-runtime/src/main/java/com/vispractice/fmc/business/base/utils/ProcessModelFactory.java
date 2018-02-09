package com.vispractice.fmc.business.base.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuditNote;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfToken;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.parameter.WorkitemParameter;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfHistoryNodeService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfWorkitemService;

@Component
@Transactional
public class ProcessModelFactory {

	@Autowired
	private ISysWfHistoryNodeService sysWfHistoryNodeService;

	@Autowired
	private ISysWfWorkitemService sysWfWorkitemService;
	
	/**
	 * 创建新节点
	 * @param process
	 * @param nodeDesc
	 * @return
	 */
	public SysWfNode createNode(SysWfProcess process, NodeDescriptor nodeDesc) {
		SysWfNode node = new SysWfNode();
		node.setFdFactNodeId(nodeDesc.getId());
		node.setFdFactNodeName(nodeDesc.getName());
		node.setFdStartDate(new Date());
		node.setFdNodeType(nodeDesc.getParent());
		node.setFdStatus(SysWfNode.ACTIVATED);
		node.setFdProcessId(process.getFdId());
		return node;
	}
	
	/**
	 * 生成审批意见
	 * @param node
	 * @return
	 */
	public SysWfAuditNote createAuditNote(SysWfNode node) {
		return createAuditNote(node, null, null);
	}
	
	/**
	 * 生成审批意见
	 * @param node
	 * @param token
	 * @return
	 */
	public SysWfAuditNote createAuditNote(SysWfNode node, SysWfToken token){
		return this.createAuditNote(node, null, token);
	}
	
	public SysWfAuditNote createAuditNote(SysWfNode node, SysWfWorkitem item, WorkitemParameter wiParam, SysWfToken token) throws Exception {
		SysWfAuditNote note = createAuditNote(node, item, token);
		if(wiParam != null) {
//			SysWfWorkitemData workitemData = sysWfWorkitemService.getWfWorkitemDataByUserId(item.getFdId(), wiParam.getHandlerId());

//			String fdParameter = item.getFdParameter();
//			if (workitemData != null) {
//				fdParameter = workitemData.getFdData();
//			}
//			WorkitemParameter parameter = WorkitemParameter.parse(fdParameter);
			note.setFdActionId(item.getFdActionId());
			note.setFdActionName(getActionName(item.getFdActionName(), wiParam.getHandlerIdentity()));// 格式{0)-{1}%%key0;key1
			note.setFdActionInfo(item.getFdActionName());// key%%value1##value2
			String auditNode = wiParam.getAuditNode();
			if (StringUtil.isNotNull(auditNode)) {
				auditNode = auditNode.replaceAll("&#xD;", "\r");
				auditNode = auditNode.replaceAll("&#xA;", "\n");
			}
			note.setFdAuditNote(auditNode);

		}
		return note;
	}
	
	public SysWfAuditNote createAuditNote(SysWfNode node, SysWfWorkitem item, SysWfToken token) {
		SysWfAuditNote note = new SysWfAuditNote();

		note.setFdFactNodeId(node.getFdFactNodeId());
		note.setFdFactNodeName(node.getFdFactNodeName());
		note.setFdCreateDate(new Date());
		note.setFdOrder(new Long(new Date().getTime()));
		note.setFdNodeId(node.getFdId());
		// note.setFdApprovalType(node.getApprovalType());
		note.setFdProcessId(node.getFdProcessId());
		if (null != item) {
			note.setFdHandlerId(item.getFdHandlerId());
			note.setFdWorkitemId(item.getFdId());
			note.setFdExpecterId(item.getFdExpectedId());
			note.setFdNotifyType(item.getFdNotifyType());
		}
		// token info
		if (token != null) {
			note.setFdTokenId(token.getFdId());
			note.setFdParentTokenId(token.getFdParentId());
		}
		return note;
	}

	/**
	 * 生成工作项历史记录
	 * @param node
	 * @param item
	 * @param token
	 * @return
	 */
	public SysWfHistoryWorkitem createHistoryWorkitem(SysWfNode node, SysWfWorkitem item, SysWfToken token) {

		SysWfHistoryNode historyNode = createHistoryNode(node, token);
		sysWfHistoryNodeService.save(historyNode);

		SysWfHistoryWorkitem historyItem = new SysWfHistoryWorkitem();
		if (item != null) {
			historyItem.setFdActionId(item.getFdActionId());
			historyItem.setFdActionName(item.getFdActionName());
			historyItem.setFdHandlerId(item.getFdHandlerId());
			historyItem.setFdStartDate(item.getFdStartDate());
			historyItem.setFdNotifyOnFinish(item.getFdNotifyOnFinish());
			historyItem.setFdNotifyType(item.getFdNotifyType());
			historyItem.setFdNodeId(node.getFdId());
			historyItem.setFdWorkitemId(item.getFdId());
			historyItem.setFdSourceWorkitemId(item.getFdSourceWorkitemId());// 用流程效率统计
		}
		historyItem.setFdFinishDate(new Date());
		historyItem.setFdOrder(new Long(new Date().getTime()));
		historyItem.setFdHistoryId(historyNode.getFdId());
		return historyItem;
	}

	/**
	 * 创建历史节点
	 * @param node
	 * @param token
	 * @return
	 */
	public SysWfHistoryNode createHistoryNode(SysWfNode node, SysWfToken token) {

		SysWfHistoryNode historyNode = sysWfHistoryNodeService.findByFdProcessIdAndFdNodeId(node.getFdProcessId(),
				node.getFdId());

		if (historyNode == null) {
			historyNode = new SysWfHistoryNode();
			historyNode.setFdNodeId(node.getFdId());
			historyNode.setFdFactNodeId(node.getFdFactNodeId());
			historyNode.setFdFactNodeName(node.getFdFactNodeName());
			historyNode.setFdStartDate(node.getFdStartDate());
			historyNode.setFdOrder(new Long(new Date().getTime()));
			historyNode.setFdFinishDate(new Date());
		}

		if (sysWfWorkitemService.countByFdNodeId(node.getFdId()) == 0) {
			if (node.getFdNodeType().equals(OAConstant.AUDIT_NODE_PARENT)
					|| node.getFdNodeType().equals(OAConstant.SIGN_NODE_PARENT)) {
				// //获取流程节点设置的时限
				// AbstractNodeDetail nodedetail =
				// (AbstractNodeDetail)process.getContext().getProcessDetail().getNodeById(node.getFdFactNodeId());
				// String delay = nodedetail.getNodeDelay();
				// //没有设置则获取通用设置
				// if(StringUtil.isNull(delay)){
				// delay=getSysWfDelaySerivce().getCommonNodeDelay();
				// }
				//
				// SysOrgPerson user = UserUtil.getUser();
				// getSysWfDelaySerivce().setHandleDuration(historyNode,node.getFdStartDate(),
				// user);
				// double duration = 0;
				// if(StringUtil.isNotNull(historyNode.getFdHandleDuration())){
				// duration=Double.parseDouble(historyNode.getFdHandleDuration());
				// }
				// //没有设置节点超时时间
				// if(StringUtil.isNull(delay)){
				// historyNode.setFdDelayTime("0.00");
				// historyNode.setFdIsDelay("0");
				// }else{
				//
				// double delayd = Double.parseDouble(delay);
				//
				// if(duration>delayd){
				// historyNode.setFdDelayTime((duration-delayd)+"");
				// historyNode.setFdIsDelay("1");
				// }else{
				// historyNode.setFdDelayTime("0.00");
				// historyNode.setFdIsDelay("0");
				// }
				// }

			}

		}
		historyNode.setFdRouteType(node.getFdRouteType());
		historyNode.setFdTargetId(node.getFdTargetId());
		historyNode.setFdTargetName(node.getFdTargetName());
		historyNode.setFdNodeType(node.getFdNodeType());
		historyNode.setFdTargetModelId(node.getFdTargetModelId());
		historyNode.setFdProcessId(node.getFdProcessId());

		// token info
		// SysWfToken token = process.findToken(node.getFdFactNodeId());
		if (token != null) {
			historyNode.setFdTokenHierarchyId(token.getFdHierarchyId());
		}

		return historyNode;
	}

	/**
	 * 创建workitem
	 * @param node
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	public SysWfWorkitem createWorkitem(SysWfNode node, WorkitemParameter parameter) throws Exception {
		SysWfWorkitem item = new SysWfWorkitem();
		item.setFdStartDate(new Date());
		item.setFdStatus(SysWfWorkitem.ACTIVATED);
		if (StringUtil.isNotNull(parameter.getNotifyOnFinish())
				&& new Boolean(parameter.getNotifyOnFinish()).booleanValue()) {
			item.setFdNotifyOnFinish(new Boolean(true));
		} else {
			item.setFdNotifyOnFinish(new Boolean(false));
		}
		item.setFdNotifyType(parameter.getNotifyType());
		item.setFdIsDone(0L);
		item.setFdRelationWorkitemId(parameter.getRelationWorkitemId());
		item.setFdNodeId(node.getFdId());
		sysWfWorkitemService.save(item);
		return item;
	}
	
	// 取得系统操作名(角色控制)
	public String getActionName(String actionName, String handlerIdentity) {
		int identity = OAConstant.HANDLER_IDENTITY_SYSTEM;
		String fdActionName = actionName;
		if (StringUtil.isNotNull(handlerIdentity)) {
			identity = Integer.parseInt(handlerIdentity);
		}
		switch (identity) {
		case OAConstant.HANDLER_IDENTITY_DRAFT:
			fdActionName = "{0}-" + fdActionName
					+ "%%sysWfProcess.processor.identity.draft";
			break;
		case OAConstant.HANDLER_IDENTITY_HANDLER:
			fdActionName = "{0}-" + fdActionName
					+ "%%sysWfProcess.processor.identity.process";
			break;
		case OAConstant.HANDLER_IDENTITY_PRIVILEGER:
			fdActionName = "{0}-" + fdActionName
					+ "%%sysWfProcess.processor.identity.authority";
			break;
		case OAConstant.HANDLER_IDENTITY_SYSTEM:
			fdActionName = "{0}-" + fdActionName
					+ "%%sysWfProcess.processor.identity.system";
			break;
		}
		return fdActionName;
	}
	
}
