package com.vispractice.fmc.business.service.sys.facade;

import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;

public interface IValidateFacade {
	/**
	 * 验证业务系统是否注册 
	 * @param systemId
	 * @throws WorkflowException
	 */
	public SysBusiSys validateBusiSystem(String systemId) throws WorkflowException;
	
	/**
	 * 验证流程实例是否创建
	 * @param wfInstance
	 * @throws WorkflowException
	 */
	public SysWfProcess validateWfInstance(String wfInstanceId) throws WorkflowException;
	
	/**
	 * 验证单据状态
	 * @param applyCode
	 * @throws WorkflowException
	 */
	public void validateSysNewsMain(String sourceSystemId,String applyCode) throws WorkflowException;
	
	/**
	 * 验证模板是否创建
	 * @param wfTemplate
	 * @throws WorkflowException
	 */
	public void validateWfTemplate(String wfTemplate) throws WorkflowException;
	
	/**
	 * 验证组织结构信息是否创建或者禁用
	 * @param fdNo
	 * @throws WorkflowException
	 */
	public SysOrgElement validateSysOrgElement(String fdNo) throws WorkflowException;
	
	/**
	 * 验证提交单据信息是否合理
	 * @param sbWFApprovalForm
	 * @throws WorkflowException
	 */
	public void vildateSbWFApproval(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;

	/**
	 * 验证节点
	 * @param sbWFApprovalForm
	 * @return
	 * @throws WorkflowException
	 */
	public SysWfNode validateNode(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;
	
	/**
	 * 验证实例
	 * @param sbWFApprovalForm
	 * @return
	 * @throws WorkflowException
	 */
	public SysWfNode validateInstance(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;
	
	/**
	 * 验证审批人员权限
	 * @param sbWFApprovalForm
	 * @return
	 * @throws WorkflowException
	 */
	public void validateIdentityHandler(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;
	
	/**
	 * 验证起草人员权限
	 * @param sbWFApprovalForm
	 * @return
	 * @throws WorkflowException
	 */
	public void validateIdentityPrivileger(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;
	
	/**
	 * 验证特权人员权限
	 * @param sbWFApprovalForm
	 * @return
	 * @throws WorkflowException
	 */
	public void validateIdentityDraft(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;
	
	/**
	 * 验证授权信息是否存在
	 * @param wfAuthoritys
	 * @return
	 * @throws WorkflowException
	 */
	public void validateSbWFAuthority(String wfAuthorityIdStr) throws WorkflowException;
}
