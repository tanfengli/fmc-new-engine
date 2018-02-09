package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFInquiryApplySrv;

/**
 * 编  号：
 * 名  称：SbWFInquiryApplySrv
 * 描  述：查询审批历史服务
 * 完成日期：2017年5月9日 下午2:19:52
 * 编码作者："ZhouYanyao"
 */
@Endpoint
public class SbWFInquiryApplySrvEndpoint {

	@Autowired
	private SbWFInquiryApplySrv sbWFInquiryApplySrv;
	
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_InquiryApplySrv";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		
		
		ProcessResponse response = new ProcessResponse();
		response = sbWFInquiryApplySrv.inquiryApply(processRequest);
		
		return response;
	}
	
}
