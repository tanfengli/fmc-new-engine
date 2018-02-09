package com.vispractice.fmc.webservice.service;

import java.util.Map;

import com.vispractice.fmc.csb.msgheader.MsgHeader;
import com.vispractice.fmc.csb.sb_wf_instance_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_instance_approvalsrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFInstanceApprovalSrv
 * 描  述：流程审批服务
 * 完成日期：2017年6月16日 上午8:55:00
 * 编码作者：ZhouYanyao
 */
public interface SbWFInstanceApprovalSrv {
	/**
	 * 基于实例流程审批接口
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFInstanceApprove(ProcessRequest processRequest);
	
	/**
	 * 对外实例流程审批接口
	 * @param processRequest
	 * @return
	 */
	public Map<String,String> sbWFInstanceApprove(Map<String,Object> params,MsgHeader msgHeader);
}
