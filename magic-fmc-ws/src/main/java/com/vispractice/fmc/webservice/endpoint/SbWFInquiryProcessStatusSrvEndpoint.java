package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.impl.StubSbWFInquiryProcessStatusSrv;
/**
 * 编  号：
 * 名  称：SbWFInquiryProcessStatusSrv
 * 描  述：查询流程状态服务
 * 完成日期：2017年5月9日 下午2:06:18
 * 编码作者："LaiJiashen"
 */
@Endpoint
public class SbWFInquiryProcessStatusSrvEndpoint {
	
	@Autowired
	private StubSbWFInquiryProcessStatusSrv inquiryProcessStatusSrvImpl;
	
	
	
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_InquiryProcessStatusSrv";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		
		
		ProcessResponse response = new ProcessResponse();
		
		
		response = inquiryProcessStatusSrvImpl.inquiryProcessStatus(processRequest);
		
		
		return response;
	}

}
