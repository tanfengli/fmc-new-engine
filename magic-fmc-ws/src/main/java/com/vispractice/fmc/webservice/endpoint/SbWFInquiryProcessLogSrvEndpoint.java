package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesslogsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesslogsrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFInquiryProcessLogSrv;

/**
 * 
 * 编  号：
 * 名  称：SbWFInquiryProcessLogSrv
 * 描  述：查询流程日志服务
 * 完成日期：2017年5月9日 下午2:37:20
 * 编码作者："ZhouYanyao"
 */
@Endpoint
public class SbWFInquiryProcessLogSrvEndpoint {
	/**
	 * 命名空间定义
	 */
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_InquiryProcessLogSrv";
	
	/**
	 * 审批日志查询服务
	 */
	@Autowired
	private SbWFInquiryProcessLogSrv sbWFInquiryProcessLogSrv;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		ProcessResponse response = new ProcessResponse();
		response = sbWFInquiryProcessLogSrv.inquiryProcessLog(processRequest);
		
		return response;
	}

}
