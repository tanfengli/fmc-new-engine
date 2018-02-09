package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.vispractice.fmc.csb.sb_wf_authorizesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_authorizesrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFAuthorizeSrv;

/**
 * 编  号：
 * 名  称：SbWFAuthorizeSrv
 * 描  述：流程授权服务
 * 完成日期：2017年5月9日 下午2:45:43
 * 编码作者：ZhouYanyao
 */
@Endpoint
public class SbWFAuthorizeSrvEndpoint {
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_AuthorizeSrv";
	
	@Autowired
	private SbWFAuthorizeSrv sbWFAuthorizeSrv;
	
	@PayloadRoot(namespace = NAMESPACE_URI,localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		ProcessResponse response = sbWFAuthorizeSrv.sbWFAuthorize(processRequest);
		
		return response;
	}
	
}
