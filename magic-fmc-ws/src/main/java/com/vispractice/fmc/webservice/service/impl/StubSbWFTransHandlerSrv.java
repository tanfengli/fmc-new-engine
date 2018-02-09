package com.vispractice.fmc.webservice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vispractice.fmc.csb.sb_wf_transhandlersrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_transhandlersrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_transhandlersrv.SBWFTransHandlerSrvRequest;
import com.vispractice.fmc.csb.sb_wf_transhandlersrv.SBWFTransHandlerSrvResponse;
import com.vispractice.fmc.webservice.service.SbWFNodeApprovalSrv;
import com.vispractice.fmc.webservice.service.SbWFTransHandlerSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFTransHandlerSrv extends AbstractStubParentSrv implements SbWFTransHandlerSrv {
	/**
	 * 基于节点审批接口服务
	 */
	@Autowired
	private SbWFNodeApprovalSrv sbWFNodeApprovalSrv;
	
	@Override
	public ProcessResponse sbWFTransHandler(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFTransHandlerSrvResponse result = new SBWFTransHandlerSrvResponse();
		try {
			Map<String,Object> params = this.buildParam(processRequest.getRequest());
			this.copyProperty(sbWFNodeApprovalSrv.sbWFNodeApprove(params,processRequest.getRequest().getMsgHeader()),result);
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFTransHandlerSrv",StubSbWFTransHandlerSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}
	
	/**
	 * 构造参数信息
	 */
	private Map<String,Object> buildParam(SBWFTransHandlerSrvRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put("wfInstanceId",request.getWFINSTANCEID());
		result.put("wfAuditerUserNo",request.getWFAUDITERUSERNO());
		result.put("wfHandlerNos",request.getWFHANDLERNOS());
		result.put("wfHandlerNames",request.getWFHANDLERNAMES());
		result.put("wfResult","103");
		result.put("wfNodeId",request.getWFNODEID());
		
		return result;
	}
	
	/**
	 * 构造反馈对象属性
	 */
	private void copyProperty(Map<String,String> params,SBWFTransHandlerSrvResponse response) {
		response.setSERVICEFLAG(params.get("serviceflag"));
		response.setSERVICEMESSAGE(params.get("servicemessage"));
	}
}
