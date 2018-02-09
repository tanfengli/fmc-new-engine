package com.vispractice.fmc.business.service.sys.notify.impl;

import java.util.Map;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;
import com.vispractice.fmc.business.service.sys.notify.ISysNotifyTodoProvider;
import com.vispractice.fmc.business.service.sys.notify.ISysNotifyTodoService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfNodeService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfWorkitemService;

@Service
@Transactional
@Slf4j
public class SysNotifyTodoProviderImpl implements ISysNotifyTodoProvider {
	/**
	 * 节点信息服务
	 */
	@Autowired
	private ISysWfNodeService sysWfNodeService;
	
	/**
	 * 工作事项服务
	 */
	@Autowired
	private ISysWfWorkitemService sysWfWorkitemService;
	
	/**
	 * 待办信息服务
	 */
	@Autowired
	private ISysNotifyTodoService sysNotifyTodoService;

	/**
	 * 清理相关代办信息
	 */
	@Override
	public void clearSysNotifyTodo(SbWFApprovalForm sbWFApprovalForm,ProcessContext processContext) {
		log.info("@@移除相关待办开始...");
		
		String wfInstanceId = sbWFApprovalForm.getWfInstanceId();
		String sysWfNodeId = sbWFApprovalForm.getFdHandleNodeId();
		
		if (StringUtil.isNotNull(wfInstanceId) && StringUtil.isNotNull(sysWfNodeId)) {
			SysWfNode sysWfNode = sysWfNodeService.findByFdProcessIdAndFdFactNodeId(wfInstanceId,sysWfNodeId);
			int operatorType = Integer.valueOf(sbWFApprovalForm.getWfResult());
			Map<String,String> nodeAttr = ProcessLogicHelper.getNodeAttrByNodeId(processContext.getSysWfProcess().getFdDetail(),sbWFApprovalForm.getFdHandleNodeId());
			int processType = StringUtil.isEmpty(nodeAttr.get("processType")) ? 0 : Integer.valueOf(nodeAttr.get("processType"));
			
			if (StringUtil.isNotNull(sbWFApprovalForm.getFdHandleWorkItemId())) {
				SysWfWorkitem sysWfWorkitem = sysWfWorkitemService.findByFdId(sbWFApprovalForm.getFdHandleWorkItemId());
				if (sysWfWorkitem != null && StringUtil.isNotNull(sbWFApprovalForm.getWfResult())) {
					switch (operatorType) {
						case OAConstant.HANDLER_OPERATION_TYPE_SIGN:
						case OAConstant.HANDLER_OPERATION_TYPE_PASS:
							switch (processType) {
								case OAConstant.PROCESSTYPE_SERIAL:
								case OAConstant.PROCESSTYPE_ALL:
									sysNotifyTodoService.deleteTodoNotify(wfInstanceId,sysWfNode.getFdId(),sysWfWorkitem,processContext.getCurrentUser().getFdId());
									sysNotifyTodoService.clearTodoPersons(wfInstanceId,sysWfNode.getFdId(),sysWfWorkitem.getFdId());
								
									break;
								case OAConstant.PROCESSTYPE_SINGLE:
									sysNotifyTodoService.deleteTodoNotify(wfInstanceId,sysWfNode.getFdId(),sysWfWorkitem,processContext.getCurrentUser().getFdId());
									sysNotifyTodoService.clearTodoPersons(wfInstanceId,sysWfNode.getFdId(),null);
									
									break;
							}
							
							break;
						case OAConstant.HANDLER_OPERATION_TYPE_REFUSE:
							sysNotifyTodoService.deleteTodoNotify(wfInstanceId,sysWfNode.getFdId(),sysWfWorkitem,processContext.getCurrentUser().getFdId());
							sysNotifyTodoService.clearTodoPersons(wfInstanceId,sysWfNode.getFdId(),null);
							
							break;
						case OAConstant.HANDLER_OPERATION_TYPE_COMMISSION:
							sysNotifyTodoService.deleteTodoNotify(wfInstanceId,sysWfNode.getFdId(),sysWfWorkitem,processContext.getCurrentUser().getFdId());
							sysNotifyTodoService.clearTodoPersons(wfInstanceId,sysWfNode.getFdId(),sysWfWorkitem.getFdId());
							
							break;
						case OAConstant.HANDLER_OPERATION_TYPE_RTNCOMMUNICATE:
							sysNotifyTodoService.deleteTodoNotify(wfInstanceId,sysWfNode.getFdId(),sysWfWorkitem,processContext.getCurrentUser().getFdId());
							sysNotifyTodoService.clearTodoPersons(wfInstanceId,sysWfNode.getFdId(),sysWfWorkitem.getFdId());
							
							break;
						case OAConstant.HANDLER_OPERATION_TYPE_ABANDON:
							sysNotifyTodoService.deleteTodoNotify(wfInstanceId,sysWfNode.getFdId(),sysWfWorkitem,processContext.getCurrentUser().getFdId());
							sysNotifyTodoService.clearTodoPersons(wfInstanceId,null,null);
							
							break;
						case OAConstant.ADMIN_OPERATION_TYPE_CHGCURHANDLER:
						case OAConstant.ADMIN_OPERATION_TYPE_JUMP:
						case OAConstant.ADMIN_OPERATION_TYPE_MODIFYPROCESS:
							sysNotifyTodoService.deleteTodoNotify(wfInstanceId,sysWfNode.getFdId(),sysWfWorkitem,processContext.getCurrentUser().getFdId());
							sysNotifyTodoService.clearTodoPersons(wfInstanceId,sysWfNode.getFdId(),null);
						default:
							break;
						
					}
				}
			}
			
			
			switch (operatorType) {
				case OAConstant.ADMIN_OPERATION_TYPE_CHGCURHANDLER:
				case OAConstant.ADMIN_OPERATION_TYPE_JUMP:
					sysNotifyTodoService.clearTodoPersons(wfInstanceId,sysWfNode.getFdId(),null);
					
					break;
				case OAConstant.CREATOR_OPERATION_TYPE_ABANDON:
				case OAConstant.CREATOR_OPERATION_TYPE_RETURN:
				case OAConstant.ADMIN_OPERATION_TYPE_PASS:
				case OAConstant.ADMIN_OPERATION_TYPE_ABANDON:
					sysNotifyTodoService.clearTodoPersons(wfInstanceId,null,null);
					
					break;
				default:
					break;
			}
		}
		
		log.info("@@移除相关待办已完成...");
	}
}
