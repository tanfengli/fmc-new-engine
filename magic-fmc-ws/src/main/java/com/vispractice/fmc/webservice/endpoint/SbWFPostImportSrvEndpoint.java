package com.vispractice.fmc.webservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.vispractice.fmc.csb.sb_wf_postimportsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_postimportsrv.ProcessResponse;


/**
 * 编  号：
 * 名  称：SbWFPostImportSrv
 * 描  述：岗位导入服务
 * 完成日期：2017年5月9日 下午2:08:00
 * 编码作者："LaiJiashen"
 */
@Endpoint
public class SbWFPostImportSrvEndpoint {

	
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_PostImportSrv";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		
		
		ProcessResponse response = new ProcessResponse();
		
		return response;
	}
	
}
