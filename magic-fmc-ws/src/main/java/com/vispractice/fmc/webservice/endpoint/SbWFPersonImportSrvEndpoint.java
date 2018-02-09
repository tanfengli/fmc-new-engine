package com.vispractice.fmc.webservice.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.vispractice.fmc.csb.sb_wf_personimportsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_personimportsrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFPersonImportSrv
 * 描  述：人人员导入服务
 * 完成日期：2017年5月9日 下午2:07:49
 * 编码作者："LaiJiashen"
 */
@Endpoint
public class SbWFPersonImportSrvEndpoint {
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_PersonImportSrv";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		
		
		ProcessResponse response = new ProcessResponse();
		
		return response;
	}
}
