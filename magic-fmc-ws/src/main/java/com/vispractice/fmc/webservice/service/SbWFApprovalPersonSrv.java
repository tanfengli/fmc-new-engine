package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_approvalpersonsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_approvalpersonsrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFApprovalPersonSrv
 * 描  述：查询指派节点服务
 * 完成日期：2017年6月16日 上午8:55:00
 * 编码作者：ZhouYanyao
 */
public interface SbWFApprovalPersonSrv {
	/**
	 * 流程审批服务
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFApprovalPerson(ProcessRequest processRequest);
}
