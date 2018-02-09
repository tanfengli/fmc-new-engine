package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFNodeApprovalSrv;

/**
 * 编  号：
 * 名  称：SbWFNodeApprovalSrv
 * 描  述：流程节点审批服务
 * 完成日期：2017年5月9日 下午2:05:47
 * 编码作者：ZhouYanyao
 */
@Endpoint
public class SbWFNodeApprovalSrvEndpoint {
	/**
	 * 命名空间
	 */
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_Node_ApprovalSrv";
	
	/**
	 * 流程节点审批服务
	 */
	@Autowired
	private SbWFNodeApprovalSrv sbWFNodeApprovalSrv;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		ProcessResponse response = new ProcessResponse();
		response = sbWFNodeApprovalSrv.sbWFNodeApprove(processRequest);
		
		return response;
	}
}
