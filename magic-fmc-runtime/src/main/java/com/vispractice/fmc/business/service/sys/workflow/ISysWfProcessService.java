package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;
import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;

public interface ISysWfProcessService {
	/**
	 * 实现流程:根据单据号获取
	 * @param fdModelId
	 * @return SysWfProcess
	 * @throws WorkflowException 
	 */
	public SysWfProcess findByFdModelId(String fdModelId) throws WorkflowException;
	
	/**
	 * 创建新流程实例
	 * @param wfTemplateId
	 * @param docCreateId
	 * @return
	 */
	public void startProcess(ProcessContext context,String modelId,String mainKey) throws WorkflowException;
	
	/**
	 * 重新生成流程信息上下文
	 * @param context
	 * @throws WorkflowException
	 */
	public void reloadProcessContext(ProcessContext context) throws WorkflowException;
	
	/**
	 * 依据流程实例查找流程信息
	 * @param instancdId
	 * @return
	 * @throws WorkflowException
	 */
	public SysWfProcess findByProcessId(String instancdId) throws WorkflowException;
	
	/**
	 * 依据流程实例查找流程信息
	 * @param instancdId
	 * @return
	 * @throws WorkflowException
	 */
	public SysWfProcess lockByProcessId(String instancdId) throws WorkflowException;
	
	/**
	 * 获取子流程信息
	 * @param wfInstanceId
	 * @return
	 * @throws WorkflowException
	 */
	public List<SysWfProcess> findSubProcessByParentId(String wfInstanceId) throws WorkflowException;
	
	/**
	 * 判断当前审批类型
	 * @param params
	 * @return
	 */
	public String getApprovalType(String operatorType);
	
	/**
	 * 处理人审批流程
	 * @param sbWFApprovalForm
	 * @param sysWfNode
	 * @throws WorkflowException
	 */
	public void sbWFHandlerApprovalProcess(ProcessContext context,SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;
	
	/**
	 * 特权人审批流程
	 * @param sbWFApprovalForm
	 * @throws WorkflowException
	 */
	public void sbWFPrivilegerApprovalProcess(ProcessContext context,SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;
	
	/**
	 * 特权人审批流程
	 * @param sbWFApprovalForm
	 * @throws WorkflowException
	 */
	public void sbWFDrafterApprovalProcess(ProcessContext context,SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;
	
	/**
	 * 获取操作类型处理身份
	 * @param operatorType
	 * @return
	 * @throws WorkflowException
	 */
	public int getIdentity(String operatorType);
	
	/**
	 * 新增或更新流程信息
	 * @param process
	 */
	public void save(SysWfProcess process);
}
