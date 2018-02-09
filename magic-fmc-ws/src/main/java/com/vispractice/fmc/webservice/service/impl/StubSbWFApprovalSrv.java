package com.vispractice.fmc.webservice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTokenRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfProcessService;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVar;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVarCollection;
import com.vispractice.fmc.csb.sb_wf_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_approvalsrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_approvalsrv.SBWFApprovalSrvRequest;
import com.vispractice.fmc.csb.sb_wf_approvalsrv.SBWFApprovalSrvResponse;
import com.vispractice.fmc.webservice.service.SbWFApprovalSrv;
import com.vispractice.fmc.webservice.service.SbWFInstanceApprovalSrv;
import com.vispractice.fmc.webservice.service.SbWFNodeApprovalSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFApprovalSrv extends AbstractStubParentSrv implements SbWFApprovalSrv {
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
	public ProcessResponse sbWFapprove(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFApprovalSrvResponse result = new SBWFApprovalSrvResponse();

		try {
			Map<String,Object> params = buildParam(processRequest.getRequest());
			String approvalType = sysWfProcessService.getApprovalType(processRequest.getRequest().getWFRESULT());
			
			if (OAConstant.APPROVAL_TYPE_INSTANCE.equals(approvalType)) {
				this.copyProperty(sbWFInstanceApprovalSrv.sbWFInstanceApprove(params,processRequest.getRequest().getMsgHeader()),result);
			} else {
				this.copyProperty(sbWFNodeApprovalSrv.sbWFNodeApprove(params,processRequest.getRequest().getMsgHeader()),result);
			}
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFApprovalSrv",StubSbWFApprovalSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}

	/**
	 * 构造审批服务参数
	 */
	private Map<String,Object> buildParam(SBWFApprovalSrvRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		WfProcessVarCollection wfProcessVarCollection = request.getWFPROCESSVARCOLLECTION();
		List<WfProcessVar> wfProcessVars = null;
		Map<String,String> wfExtendVar = new HashMap<String,String>();
		
		result.put("wfInstanceId",request.getWFINSTANCEID());
		result.put("wfNodeId",request.getWFNODEID());
		result.put("wfAuditerUserNo",request.getWFAUDITERUSERNO());
		result.put("wfResult",request.getWFRESULT());
		result.put("wfOpinionCon",request.getWFOPINIONCON());
		result.put("wfRouteId",request.getWFROUTEID());
		result.put("wfTransferUserNo",request.getWFTRANSFERUSERNO());
		result.put("wfPassedToThis",request.getWFPASSEDTOTHIS());
		
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
	private void copyProperty(Map<String,String> params,SBWFApprovalSrvResponse response) {
		response.setSERVICEFLAG(params.get("serviceflag"));
		response.setSERVICEMESSAGE(params.get("servicemessage"));
	}
}
