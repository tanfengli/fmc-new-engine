package com.vispractice.fmc.webservice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.csb.extendparamcollection.WfProcessVar;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVarCollection;
import com.vispractice.fmc.csb.sb_wf_abandonsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_abandonsrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_abandonsrv.SBWFAbandonSrvRequest;
import com.vispractice.fmc.csb.sb_wf_abandonsrv.SBWFAbandonSrvResponse;
import com.vispractice.fmc.webservice.service.SbWFAbandonSrv;
import com.vispractice.fmc.webservice.service.SbWFInstanceApprovalSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFAbandonSrv extends AbstractStubParentSrv implements SbWFAbandonSrv {
	/**
	 * 基于实例审批接口服务
	 */
	@Autowired
	private SbWFInstanceApprovalSrv sbWFInstanceApprovalSrv;
	
	/**
	 * 作废流程实例
	 */
	@Override
	public ProcessResponse sbWFAbandon(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFAbandonSrvResponse result = new SBWFAbandonSrvResponse();
		
		try {
			Map<String,Object> params = this.buildParam(processRequest.getRequest());
			this.copyProperty(sbWFInstanceApprovalSrv.sbWFInstanceApprove(params,processRequest.getRequest().getMsgHeader()),result);
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFAbandon",StubSbWFAbandonSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}
	
	/**
	 * 构造参数信息
	 */
	private Map<String,Object> buildParam(SBWFAbandonSrvRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		WfProcessVarCollection wfProcessVarCollection = request.getWFPROCESSVARCOLLECTION();
		List<WfProcessVar> wfProcessVars = null;
		Map<String,String> wfExtendVar = new HashMap<String,String>();
		
		result.put("wfInstanceId",request.getWFINSTANCEID());
		result.put("wfAuditerUserNo",request.getWFAUDITERUSERNO());
		result.put("wfResult",request.getWFRESULT());
		result.put("wfOpinionCon",request.getWFOPINIONCON());
		result.put("wfRouteId","");
		result.put("wfTransferUserNo","");
		
		if (wfProcessVarCollection != null) {
			wfProcessVars = wfProcessVarCollection.getWFPROCESSVAR();
			for (WfProcessVar wfProcessVar : wfProcessVars) {
				wfExtendVar.put(wfProcessVar.getWFVARNAME(),wfProcessVar.getWFVARVALUE());
			}
		}
		result.put("wfProcessVar",wfExtendVar);
		
		return result;
	}
	
	/**
	 * 构造反馈对象属性
	 */
	private void copyProperty(Map<String,String> params,SBWFAbandonSrvResponse response) {
		response.setSERVICEFLAG(params.get("serviceflag"));
		response.setSERVICEMESSAGE(params.get("servicemessage"));
	}
}
