package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.vispractice.fmc.csb.sb_wf_inquiryrejectnodesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryrejectnodesrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFInquiryRejectNodeSrv;

/**
 * 编  号：
 * 名  称：SbWFInquiryRejectNodeSrv
 * 描  述：查询驳回节点服务
 * 完成日期：2017年5月9日 下午2:29:48
 * 编码作者："ZhouYanyao"
 */
@Endpoint
public class SbWFInquiryRejectNodeSrvEndpoint {
	/**
	 * 命名空间定义
	 */
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_InquiryRejectNodeSrv";
	
	/**
	 * 驳回节点查询服务
	 */
	@Autowired
	private SbWFInquiryRejectNodeSrv sbWFInquiryRejectNodeSrv;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		ProcessResponse response = new ProcessResponse();
		response = sbWFInquiryRejectNodeSrv.sbWFInquiryRejectNode(processRequest);
		
		return response;
	}
	
}
