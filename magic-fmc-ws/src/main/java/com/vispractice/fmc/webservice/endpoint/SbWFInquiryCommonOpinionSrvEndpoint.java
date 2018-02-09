package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.vispractice.fmc.csb.sb_wf_inquirycommonopinionsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirycommonopinionsrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFInquiryCommonOpinionSrv;


/**
 * 
 * 编  号：
 * 名  称：SbWFInquiryCommonOpinionSrvEndpoint
 * 描  述：常用审批意见查询服务
 * 完成日期：2017年6月23日 上午11:14:38
 * 编码作者："LaiJiashen"
 */
@Endpoint
public class SbWFInquiryCommonOpinionSrvEndpoint {
	
	@Autowired
	private SbWFInquiryCommonOpinionSrv inquiryCommonOpinionSrv;
	
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_InquiryCommonOpinionSrv";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		
		
		ProcessResponse response = new ProcessResponse();
		
		response = inquiryCommonOpinionSrv.inquiryCommonOpinion(processRequest);
		
		return response;
	}

}
