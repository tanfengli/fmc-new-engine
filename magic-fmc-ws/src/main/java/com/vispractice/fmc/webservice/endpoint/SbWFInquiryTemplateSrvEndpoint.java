package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.vispractice.fmc.csb.sb_wf_inquirytemplatesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytemplatesrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFInquiryTemplateSrv;


/**
 * 
 * 编  号：
 * 名  称：SbWFInquiryTemplateSrvEndpoint
 * 描  述：流程模板查询服务
 * 完成日期：2017年6月23日 下午2:43:35
 * 编码作者："LaiJiashen"
 */
@Endpoint
public class SbWFInquiryTemplateSrvEndpoint {
	
	@Autowired
	private SbWFInquiryTemplateSrv inquiryTemplateSrv;
	
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_InquiryTemplateSrv";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		
		
		ProcessResponse response = new ProcessResponse();
		
		response = inquiryTemplateSrv.inquiryTemplate(processRequest);
		
		return response;
	}

}
