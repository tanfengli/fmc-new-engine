package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.vispractice.fmc.csb.sb_wf_inquiryreadsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryreadsrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFInquiryReadSrv;

@Endpoint
public class SBWFInquiryReadSrvEndpoint {
	/**
	 * 命名空间
	 */
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_InquiryReadSrv";
	
	/**
	 * 查询待阅服务
	 */
	@Autowired
	private SbWFInquiryReadSrv sbWFInquiryReadSrv;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		ProcessResponse response = sbWFInquiryReadSrv.sbWfInquiryReadSrv(processRequest);

		return response;
	}
}
