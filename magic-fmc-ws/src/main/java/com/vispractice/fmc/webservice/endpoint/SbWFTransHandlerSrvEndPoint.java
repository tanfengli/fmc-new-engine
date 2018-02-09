package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.vispractice.fmc.csb.sb_wf_transhandlersrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_transhandlersrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFTransHandlerSrv;

/**
 * 编  号：
 * 名  称：SbWFTransHandlerSrvEndPoint
 * 描  述：转办/转交接口服务
 * 完成日期：2017年5月9日 下午2:08:13
 * 编码作者：ZhouYanyao
 */
@Endpoint
public class SbWFTransHandlerSrvEndPoint {
	/**
	 * 命名空间定义
	 */
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_ChangeHandlerSrv";
	
	/**
	 * 启动流程服务
	 */
	@Autowired
	private SbWFTransHandlerSrv sbWFTransHandlerSrv;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		ProcessResponse response = sbWFTransHandlerSrv.sbWFTransHandler(processRequest);
		
		return response;
	}
}
