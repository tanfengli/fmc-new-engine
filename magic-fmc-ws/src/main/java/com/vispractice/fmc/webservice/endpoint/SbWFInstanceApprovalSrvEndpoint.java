package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.vispractice.fmc.csb.sb_wf_instance_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_instance_approvalsrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFInstanceApprovalSrv;

/**
 * 编  号：
 * 名  称：SbWFInstanceApprovalSrv
 * 描  述：流程实例审批服务
 * 完成日期：2017年5月9日 下午2:05:47
 * 编码作者：ZhouYanyao
 */
@Endpoint
public class SbWFInstanceApprovalSrvEndpoint {
	/**
	 * 命名空间
	 */
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_Instance_ApprovalSrv";
	
	/**
	 * 流程审批服务
	 */
	@Autowired
	private SbWFInstanceApprovalSrv sbWFInstanceApprovalSrv;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		ProcessResponse response = new ProcessResponse();
		response = sbWFInstanceApprovalSrv.sbWFInstanceApprove(processRequest);
		
		return response;
	}
}
