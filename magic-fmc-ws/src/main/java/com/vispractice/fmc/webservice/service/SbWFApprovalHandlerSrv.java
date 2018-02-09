package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_approvalhandlersrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_approvalhandlersrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFApprovalHandlerSrv
 * 描  述：指派节点审批服务
 * 完成日期：2017年6月16日 上午8:55:00
 * 编码作者：ZhouYanyao
 */
public interface SbWFApprovalHandlerSrv {
	/**
	 * 指派节点审批服务
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFApprovalHandler(ProcessRequest processRequest);
}
