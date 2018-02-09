package com.vispractice.fmc.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFStartProcessSrv;

/**
 * 编  号：
 * 名  称：SbWFStartProcessSrvEndPoint
 * 描  述：流程启动服务
 * 完成日期：2017年5月9日 下午2:08:13
 * 编码作者："ZhouYanyao"
 */
@Endpoint
public class SbWFStartProcessSrvEndPoint {
	/**
	 * 命名空间定义
	 */
	private static final String NAMESPACE_URI = "http://csb.fmc.vispractice.com/SB_WF_StartProcessSrv";
	
	/**
	 * 启动流程服务
	 */
	@Autowired
	private SbWFStartProcessSrv sbWFStartProcessSrv;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "processRequest")
	@ResponsePayload
	public ProcessResponse handleRequest(@RequestPayload ProcessRequest processRequest) {
		ProcessResponse response = sbWFStartProcessSrv.startProcess(processRequest);
		
		return response;
	}
	
}
