package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_approvalsrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFApprovalSrv
 * 描  述：流程审批服务
 * 完成日期：2017年6月16日 上午8:55:00
 * 编码作者：ZhouYanyao
 */
public interface SbWFApprovalSrv {
	/**
	 * 流程审批服务
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFapprove(ProcessRequest processRequest);
}
