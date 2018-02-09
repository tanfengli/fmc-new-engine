package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.vispractice.fmc.csb.sb_wf_approvalpersonsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_approvalpersonsrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFApprovalPersonSrv;

/**
 * 编  号：
 * 名  称：SbWFApprovalPersonSrvEndpoint
 * 描  述：查询指派节点服务
 * 完成日期：2017年5月9日 下午2:05:47
 * 编码作者：ZhouYanyao
 */
@Endpoint
public class SbWFApprovalPersonSrvEndpoint {
	/**
	 * 命名空间
	 */
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_ApprovalPersonSrv";
	
	/**
	 * 查询指派节点服务
	 */
	@Autowired
	private SbWFApprovalPersonSrv sbWFApprovalPersonSrv;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		ProcessResponse response = new ProcessResponse();
		response = sbWFApprovalPersonSrv.sbWFApprovalPerson(processRequest);
		
		return response;
	}
}
