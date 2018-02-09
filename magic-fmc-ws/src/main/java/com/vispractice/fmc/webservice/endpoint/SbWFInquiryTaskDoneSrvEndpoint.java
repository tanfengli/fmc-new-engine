package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFInquiryTaskDoneSrv;

/**
 * 编  号：
 * 名  称：SbWFInquiryTaskDoneSrv
 * 描  述：查询已办服务
 * 完成日期：2017年5月9日 下午2:08:47
 * 编码作者："LaiJiashen"
 */
@Endpoint
public class SbWFInquiryTaskDoneSrvEndpoint {
	
	@Autowired
	private SbWFInquiryTaskDoneSrv sbWFInquiryTaskDoneSrv;
	
	
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_InquiryTaskDoneSrv";
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		
		
		ProcessResponse response = new ProcessResponse();
		
		response = sbWFInquiryTaskDoneSrv.inquiryTaskDone(processRequest);
		
		return response;
	}

}
