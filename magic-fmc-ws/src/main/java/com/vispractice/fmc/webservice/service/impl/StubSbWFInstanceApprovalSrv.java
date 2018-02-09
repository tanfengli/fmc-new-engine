package com.vispractice.fmc.webservice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsMainRepository;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVar;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVarCollection;
import com.vispractice.fmc.csb.msgheader.MsgHeader;
import com.vispractice.fmc.csb.sb_wf_instance_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_instance_approvalsrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_instance_approvalsrv.SBWFInstanceApprovalSrvRequest;
import com.vispractice.fmc.csb.sb_wf_instance_approvalsrv.SBWFInstanceApprovalSrvResponse;
import com.vispractice.fmc.webservice.service.SbWFInstanceApprovalSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFInstanceApprovalSrv extends AbstractStubParentSrv implements SbWFInstanceApprovalSrv {
	/**
	 * 流程业务查询
	 */
	@Autowired
	private SysNewsMainRepository sysNewsMainRepository;
	
	/**
	 * 基于流程实例审批接口
	 */
	@Override
	public ProcessResponse sbWFInstanceApprove(ProcessRequest processRequest) {
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		MsgHeader msgHeader = processRequest.getRequest().getMsgHeader();
		WfProcessVarCollection wfProcessVarCollection = processRequest.getRequest().getWFPROCESSVARCOLLECTION();
		SbWFApprovalForm sbWFApprovalForm = this.buildParam(processRequest.getRequest());
		ProcessResponse response = this.sbWFInstanceApprove(msgHeader,sbWFApprovalForm,wfProcessVarCollection);
		
		return response;
	}
	
	/**
	 * 对外流程实例审批接口
	 */
	@Override
	public Map<String,String> sbWFInstanceApprove(Map<String,Object> params,MsgHeader msgHeader) {
		Map<String,String> result = new HashMap<String,String>();
		SbWFApprovalForm sbWFApprovalForm = new SbWFApprovalForm();
		WfProcessVarCollection wfProcessVarCollection = new WfProcessVarCollection();
		List<WfProcessVar> wfProcessVars = wfProcessVarCollection.getWFPROCESSVAR();
		WfProcessVar wfProcessVar = null;
		
		sbWFApprovalForm.setWfInstanceId(String.valueOf(params.get("wfInstanceId")));
		sbWFApprovalForm.setWfAuditedUserNo(String.valueOf(params.get("wfAuditerUserNo")));
		sbWFApprovalForm.setWfResult(String.valueOf(params.get("wfResult")));
		sbWFApprovalForm.setWfOptionCon(String.valueOf(params.get("wfOpinionCon")));
		
		//驳回或指派下一节点扩展参数
		sbWFApprovalForm.setFutureNodeId(String.valueOf(params.get("wfRouteId")));
		String wfPassedToThis = String.valueOf(params.get("wfPassedToThis"));
		if (StringUtils.isEmpty(wfPassedToThis) || false == Boolean.parseBoolean(wfPassedToThis)) {
			sbWFApprovalForm.setRefusePassedToThisNode(false);
		} else {
			sbWFApprovalForm.setRefusePassedToThisNode(true);
		}
		
		//转办扩展参数
		sbWFApprovalForm.setWfTransferUserNo(String.valueOf(params.get("wfTransferUserNo")));
		
		@SuppressWarnings("unchecked")
		Map<String,String> wfExtendVar = (Map<String,String>) params.get("wfProcessVar");
		for (String key : wfExtendVar.keySet()) {
			wfProcessVar = new WfProcessVar();
			wfProcessVar.setWFVARNAME(key);
			wfProcessVar.setWFVARVALUE(wfExtendVar.get(key));
			wfProcessVars.add(wfProcessVar);
		}
		
		ProcessResponse response = this.sbWFInstanceApprove(msgHeader,sbWFApprovalForm,wfProcessVarCollection);
		
		result.put("serviceflag",response.getReturn().getSERVICEFLAG());
		result.put("servicemessage",response.getReturn().getSERVICEMESSAGE());
		
		return result;
	}
	
	/**
	 * 实例审批内部服务
	 * @param msgHeader
	 * @param sbWFApprovalForm
	 * @param wfProcessVarCollection
	 * @return
	 */
	private ProcessResponse sbWFInstanceApprove(MsgHeader msgHeader,SbWFApprovalForm sbWFApprovalForm,WfProcessVarCollection wfProcessVarCollection) {
		ProcessResponse response = new ProcessResponse();
		SBWFInstanceApprovalSrvResponse result = new SBWFInstanceApprovalSrvResponse();
		String message = "";
		
		try {
			msgHeaderSrv.validateSysData(msgHeader,"SbWFInstanceApprovalSrv");
			processFacade.sbWFInstanceApprovalSrv(sbWFApprovalForm);
				
			message = resourceUtil.getLocaleMessage("ws.interface.instanceApproval.OK",language);
			result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
			result.setSERVICEMESSAGE(message);
			
			this.updateSysNewsMain(wfProcessVarCollection,sbWFApprovalForm.getWfInstanceId());
			
			log.info(message);
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
		}
		response.setReturn(result);
		
		return response;
	}
	
	/**
	 * 构造审批服务参数
	 */
	private SbWFApprovalForm buildParam(SBWFInstanceApprovalSrvRequest request) {
		SbWFApprovalForm sbWFApprovalForm = new SbWFApprovalForm();
		sbWFApprovalForm.setWfInstanceId(request.getWFINSTANCEID());
		sbWFApprovalForm.setWfAuditedUserNo(request.getWFAUDITERUSERNO());
		sbWFApprovalForm.setWfResult(request.getWFRESULT());
		sbWFApprovalForm.setWfOptionCon(request.getWFOPINIONCON());
	
		//驳回或指派下一节点扩展参数
		sbWFApprovalForm.setFutureNodeId(request.getWFROUTEID());
		if (StringUtils.isEmpty(request.getWFPASSEDTOTHIS())) {
			sbWFApprovalForm.setRefusePassedToThisNode(false);
		} else {
			sbWFApprovalForm.setRefusePassedToThisNode(true);
		}
		
		//转办扩展参数
		sbWFApprovalForm.setWfTransferUserNo(request.getWFTRANSFERUSERNO());
		
		return sbWFApprovalForm;
	}
	
	/**
	 * 更新扩展参数
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	private void updateSysNewsMain(WfProcessVarCollection wfProcessVarCollection,String wfInstanceId) throws WorkflowException {
		Map<String,Object> processVal = null;
		List<WfProcessVar> wfProcessVars = null;
		List wfExtends = null;
		
		if (wfProcessVarCollection != null) {
			wfProcessVars = wfProcessVarCollection.getWFPROCESSVAR();
			if (wfProcessVars.size() > 0) {
				processVal = new HashMap<String,Object>();
				SysNewsMain sysNewsMain = sysNewsMainRepository.findOne(wfInstanceId);
				
				try {
					if (StringUtils.isNotEmpty(sysNewsMain.getProcessVarXml())) {
						wfExtends = ProcessLogicHelper.objectXmlDecoder(sysNewsMain.getProcessVarXml());
						for (int i = 0;i < wfExtends.size();i++) {
							processVal.putAll((Map<String,String>)wfExtends.get(0));
						}
					}
					
					for(WfProcessVar wfProcessVar : wfProcessVars){
						processVal.put(wfProcessVar.getWFVARNAME(),wfProcessVar.getWFVARVALUE());
					}
					
					sysNewsMain.setProcessVarXml(ProcessLogicHelper.objectXmlEncoder(processVal));
					sysNewsMainRepository.save(sysNewsMain);
				} catch (Exception e) {
					throw new WorkflowException(e.getMessage());
				}
			}
		}
	}
}