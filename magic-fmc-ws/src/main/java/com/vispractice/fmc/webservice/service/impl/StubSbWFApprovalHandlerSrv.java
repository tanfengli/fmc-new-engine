package com.vispractice.fmc.webservice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTokenRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfProcessService;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVar;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVarCollection;
import com.vispractice.fmc.csb.sb_wf_approvalhandlersrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_approvalhandlersrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_approvalhandlersrv.SBWFApprovalHandlerSrvRequest;
import com.vispractice.fmc.csb.sb_wf_approvalhandlersrv.SBWFApprovalHandlerSrvResponse;
import com.vispractice.fmc.csb.sb_wf_approvalhandlersrv.WfHandlerList;
import com.vispractice.fmc.webservice.service.SbWFApprovalHandlerSrv;
import com.vispractice.fmc.webservice.service.SbWFInstanceApprovalSrv;
import com.vispractice.fmc.webservice.service.SbWFNodeApprovalSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFApprovalHandlerSrv extends AbstractStubParentSrv implements SbWFApprovalHandlerSrv {
	/**
	 * 基于节点审批接口服务
	 */
	@Autowired
	private SbWFNodeApprovalSrv sbWFNodeApprovalSrv;
	
	/**
	 * 基于实例审批接口服务
	 */
	@Autowired
	private SbWFInstanceApprovalSrv sbWFInstanceApprovalSrv;
	
	/**
	 * 流程实例服务
	 */
	@Autowired
	private ISysWfProcessService sysWfProcessService;
	
	/**
	 * 流程令牌服务
	 */
	@Autowired
	private SysWfTokenRepository sysWfTokenService;
	
	/**
	 * 对外审批接口
	 */
	@Override
	public ProcessResponse sbWFApprovalHandler(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFApprovalHandlerSrvResponse result = new SBWFApprovalHandlerSrvResponse();
		try {
			Map<String,Object> params = buildParam(processRequest.getRequest());
			
			this.copyProperty(sbWFNodeApprovalSrv.sbWFNodeApprove(params,processRequest.getRequest().getMsgHeader()),result);
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFApprovalHandlerSrv",StubSbWFApprovalHandlerSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}
	
	/**
	 * 构造审批服务参数
	 */
	private Map<String,Object> buildParam(SBWFApprovalHandlerSrvRequest request) {
		WfProcessVarCollection wfProcessVarCollection = request.getWFPROCESSVARCOLLECTION();
		List<WfProcessVar> wfProcessVars = null;
		Map<String,String> wfExtendVar = new HashMap<String,String>();
		
		Map<String,Object> result = new HashMap<String,Object>();
		WfHandlerList wfHandlerRecord = request.getWFHANDLERLIST();
		
		result.put("wfInstanceId",request.getWFINSTANCEID());
		result.put("wfAuditerUserNo",request.getWFAUDITERUSERNO());
		result.put("wfResult","101");
		result.put("wfOpinionCon",request.getWFOPINIONCON());
		result.put("wfRouteId",wfHandlerRecord.getWFNODEID());
		
		if (wfProcessVarCollection != null) {
			wfProcessVars = wfProcessVarCollection.getWFPROCESSVAR();
			for (WfProcessVar wfProcessVar : wfProcessVars) {
				wfExtendVar.put(wfProcessVar.getWFVARNAME(),wfProcessVar.getWFVARVALUE());
			}
			
			WfProcessVar futureHandlerNo = new WfProcessVar();
			futureHandlerNo.setWFVARNAME("handlerNos");
			futureHandlerNo.setWFVARVALUE(wfHandlerRecord.getWFHANDLERNOS());
			wfProcessVars.add(futureHandlerNo);
			
			WfProcessVar futureHandlerName = new WfProcessVar();
			futureHandlerNo.setWFVARNAME("handlerNames");
			futureHandlerNo.setWFVARVALUE(wfHandlerRecord.getWFHANDLERNAMES());
			wfProcessVars.add(futureHandlerName);
		}
		
		
		return result;
	}
	
	
	/**
	 * 构造反馈对象属性
	 * @param params
	 * @param response
	 */
	private void copyProperty(Map<String,String> params,SBWFApprovalHandlerSrvResponse response) {
		response.setSERVICEFLAG(params.get("serviceflag"));
		response.setSERVICEMESSAGE(params.get("servicemessage"));
	}
}
