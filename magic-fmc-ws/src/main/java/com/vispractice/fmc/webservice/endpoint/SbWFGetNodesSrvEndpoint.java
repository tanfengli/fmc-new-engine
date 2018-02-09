package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.vispractice.fmc.csb.sb_wf_getnodessrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_getnodessrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFGetNodesSrv;

/**
 * 编  号：
 * 名  称：SbWFGetNodesSrv
 * 描  述：流程节点信息查询服务
 * 完成日期：2017年5月9日 下午2:45:43
 * 编码作者："ZhouYanyao"
 */
@Endpoint
public class SbWFGetNodesSrvEndpoint {
	/**
	 * 命名空间定义
	 */
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_GetNodesSrv";
	/**
	 * 模板节点服务
	 */
	@Autowired
	private SbWFGetNodesSrv sbWFGetNodesSrv;
	
	@PayloadRoot(namespace = NAMESPACE_URI,localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		ProcessResponse response = sbWFGetNodesSrv.sbWFGetNodesSrv(processRequest);
		
		return response;
	}
}
