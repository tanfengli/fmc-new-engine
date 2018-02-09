package com.vispractice.fmc.webservice.service;

import java.util.Map;

import com.vispractice.fmc.csb.msgheader.MsgHeader;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFNodeApprovalSrv
 * 描  述：节点审批接口
 * 完成日期：2017年6月16日 上午8:55:00
 * 编码作者：ZhouYanyao
 */
public interface SbWFNodeApprovalSrv {
	/**
	 * 基于节点流程审批接口
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFNodeApprove(ProcessRequest processRequest);
	
	/**
	 * 对外流程审批接口
	 * @param params
	 * @return
	 */
	public Map<String,String> sbWFNodeApprove(Map<String,Object> params,MsgHeader msgHeader);
}
