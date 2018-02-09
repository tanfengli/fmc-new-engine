package com.vispractice.fmc.business.service.sys.facade;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.aboutmyself.AuditBillV;
import com.vispractice.fmc.business.entity.aboutmyself.VCmsTask;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.view.DocumentSubmmitedV;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessHistoryV;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessLogV;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessReadV;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;

/**
 * 流程服务接口
 * @author sky
 * @Date May 11, 2017 3:04:02 PM
 * @version 1.0.0
 */
public interface IProcessFacade {
	/**
	 * 组织导入服务 
	 * @throws WorkflowException
	 */
	public void sbWFOrgImportSrv() throws WorkflowException;

	/**
	 * 岗位导入服务 
	 * @throws WorkflowException
	 */
	public void sbWFPostImportSrv() throws WorkflowException;

	/**
	 * 人员导入服务 
	 * @throws WorkflowException
	 */
	public void sbWFPersonImportSrv() throws WorkflowException;
	
	/**
	 * 启动流程服务
	 * @param context
	 * @param skipDraftNode
	 * @return
	 * @throws WorkflowException
	 */
	public String sbWFStartProcessSrv(ProcessContext context,boolean skipDraftNode) throws WorkflowException;
	
	/**
	 * 启动流程服务 
	 * @param sysWfBusinessForm
	 * @return 流程实例号 
	 * @throws WorkflowException
	 */
	public String sbWFStartProcessSrv(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;
	
	/**
	 * 基于节点审批服务
	 * @param sbWFApprovalForm
	 * @throws WorkflowException
	 */
	public void sbWFNodeApprovalSrv(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;
	
	/**
	 * 基于流程审批服务
	 * @param sbWFApprovalForm
	 * @throws WorkflowException
	 */
	public void sbWFInstanceApprovalSrv(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException;

	/**
	 * 查询流程状态服务 
	 * @throws WorkflowException
	 */
	public SysNewsMain sbWFInquiryProcessStatusSrv(String instancdId) throws WorkflowException;

	/**
	 * 实现流程:查询代办服务  
	 * @Title:sbWFInquiryTaskSrv
	 * @param vCmsTask
	 * @return
	 * @throws WorkflowException
	 */
	public Page<VCmsTask> sbWFInquiryTaskSrv(VCmsTask vCmsTask,Pageable pageable) throws WorkflowException;

	/**
	 * 实现流程:查询已办服务 
	 * @Title:sbWFInquiryTaskDoneSrv
	 * @param auditBillV
	 * @param pageable
	 * @return
	 * @throws WorkflowException
	 */
	public Page<AuditBillV> sbWFInquiryTaskDoneSrv(AuditBillV auditBillV,Pageable pageable) throws WorkflowException;

	/**
	 * 查询我的申请服务 
	 * @throws WorkflowException
	 */
	public Page<DocumentSubmmitedV> sbWFInquiryApplySrv(DocumentSubmmitedV docSubV,Pageable pageable) throws WorkflowException;
	
	/**
	 * 查询审批历史服务 
	 * @throws WorkflowException
	 */
	public List<ProcessHistoryV> sbWFInquiryProcessHistorySrv(String wfInstanceId) throws WorkflowException;
	
	/**
	 * 查询驳回节点服务 
	 * @throws WorkflowException
	 */
	public Map<String,Map<String,String>> sbWFInquiryRejectNodeSrv(String wfInstanceId,String nodeId) throws WorkflowException;

	/**
	 * 查询指派节点服务 
	 * @throws WorkflowException
	 */
	public Map<String,Map<String,String>> sbWFApprovalPerson(String wfInstanceId,String nodeId) throws WorkflowException;

	/**
	 * 查询节点上、下节点服务 
	 * @throws WorkflowException
	 */
	public Map<String,Map<String,Map<String,Object>>> sbWFLastAndNextNodes(String wfTemplateId,String nodeId) throws WorkflowException;

	/**
	 * 查询流程日志服务 
	 * @throws WorkflowException
	 */
	public List<ProcessLogV> sbWFInquiryProcessLogSrv(String wfInstanceId) throws WorkflowException;
	
	/**
	 * 查询待阅服务 
	 * @param processReadV
	 * @param pageable
	 * @return
	 * @throws WorkflowException
	 */
	public Page<ProcessReadV> sbWFInquiryReadSrv(ProcessReadV processReadV,Pageable pageable) throws WorkflowException;
	
	/**
	 * 流程授权服务
	 * @param wfAuthorize
	 * @throws WorkflowException
	 */
	public String sbWFAuthorizeSrv(Map<String,Object> wfAuthorize) throws WorkflowException;

	/**
	 * 回收授权服务 
	 * @throws WorkflowException
	 */
	public void sbWFRevokeAuthorizeSrv(List<String> wfAuthorizeIds) throws WorkflowException;

	/**
	 * 常用审批意见查询服务  
	 * @throws WorkflowException
	 */
	public void sbWFInquiryCommonOpinionSrv() throws WorkflowException;

	/**
	 * 流程模板查询服务 
	 * @param cateId
	 * @param ifQueryAll
	 * @throws WorkflowException
	 */
	public Map<String,Object> sbWFInquiryTemplateSrv(String cateId,boolean ifQueryAll) throws WorkflowException;
	
	/**
	 * 获取流程模板所有节点信息 
	 * @param templateId
	 * @return
	 * @throws WorkflowException
	 */
	public Map<String,Map<String,String>> sbWFGetNodes(String templateId) throws WorkflowException;
}
