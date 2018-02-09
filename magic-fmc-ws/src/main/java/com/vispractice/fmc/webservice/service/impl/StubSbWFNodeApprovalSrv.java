package com.vispractice.fmc.webservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsMainRepository;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVar;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVarCollection;
import com.vispractice.fmc.csb.msgheader.MsgHeader;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.SBWFNodeApprovalSrvRequest;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.SBWFNodeApprovalSrvResponse;
import com.vispractice.fmc.webservice.service.SbWFNodeApprovalSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFNodeApprovalSrv extends AbstractStubParentSrv implements SbWFNodeApprovalSrv {
	/**
	 * 流程业务查询
	 */
	@Autowired
	private SysNewsMainRepository sysNewsMainRepository;
	
	/**
	 * 基于节点流程审批接口
	 */
	@Override
	public ProcessResponse sbWFNodeApprove(ProcessRequest processRequest) {
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		MsgHeader msgHeader = processRequest.getRequest().getMsgHeader();
		WfProcessVarCollection wfProcessVarCollection = processRequest.getRequest().getWFPROCESSVARCOLLECTION();
		SbWFApprovalForm sbWFApprovalForm = this.buildParam(processRequest.getRequest());
		ProcessResponse response = this.sbWFNodeApprove(msgHeader,sbWFApprovalForm,wfProcessVarCollection);
		
		return response;
	}
	
	/**
	 * 对外流程审批接口
	 */
	@Override
	public Map<String,String> sbWFNodeApprove(Map<String,Object> params,MsgHeader msgHeader) {
		Map<String,String> result = new HashMap<String,String>();
		SbWFApprovalForm sbWFApprovalForm = new SbWFApprovalForm();
		WfProcessVarCollection wfProcessVarCollection = new WfProcessVarCollection();
		List<WfProcessVar> wfProcessVars = wfProcessVarCollection.getWFPROCESSVAR();
		WfProcessVar wfProcessVar = null;
		
		sbWFApprovalForm.setWfInstanceId(String.valueOf(params.get("wfInstanceId")));
		sbWFApprovalForm.setFdHandleNodeId(String.valueOf(params.get("wfNodeId")));
		sbWFApprovalForm.setWfAuditedUserNo(String.valueOf(params.get("wfAuditerUserNo")));
		sbWFApprovalForm.setWfResult(String.valueOf(params.get("wfResult")));
		sbWFApprovalForm.setWfOptionCon(String.valueOf(params.get("wfOpinionCon")));
		
		//驳回节点/指派下一节点扩展参数
		sbWFApprovalForm.setFutureNodeId(String.valueOf(params.get("wfRouteId")));
		String wfPassedToThis = String.valueOf(params.get("wfPassedToThis"));
		if (StringUtils.isEmpty(wfPassedToThis) || false == Boolean.parseBoolean(wfPassedToThis)) {
			sbWFApprovalForm.setRefusePassedToThisNode(false);
		} else {
			sbWFApprovalForm.setRefusePassedToThisNode(true);
		}
		
		//转办/转交/加签扩展参数
		sbWFApprovalForm.setWfTransferUserNo(String.valueOf(params.get("wfTransferUserNo")));
		sbWFApprovalForm.setToOtherHandlerNos(String.valueOf(params.get("wfHandlerNos")));
		sbWFApprovalForm.setToOtherHandlerNames(String.valueOf(params.get("wfHandlerNames")));
		sbWFApprovalForm.setSourceOperType(String.valueOf(params.get("sourceOperType")));
		
		//沟通人员/指派人员扩展参数
		@SuppressWarnings("unchecked")
		Map<String,String> wfExtendVar = (Map<String,String>)params.get("wfProcessVar");
		for (String key : wfExtendVar.keySet()) {
			wfProcessVar = new WfProcessVar();
			wfProcessVar.setWFVARNAME(key);
			wfProcessVar.setWFVARVALUE(wfExtendVar.get(key));
			wfProcessVars.add(wfProcessVar);
			
			if(String.valueOf(OAConstant.HANDLER_OPERATION_TYPE_COMMUNICATE).equals(params.get("wfResult")) ||
				String.valueOf(OAConstant.HANDLER_OPERATION_TYPE_CELCOMMUNICATE).equals(params.get("wfResult"))) {
				if ("handlerNos".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setCommicateHandlerNos(wfProcessVar.getWFVARVALUE());
				}
				
				if ("handlerNames".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setCommicateHandlerNames(wfProcessVar.getWFVARVALUE());
				}
			} else {
				if ("handlerNos".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setFutureHandlerNos(wfProcessVar.getWFVARVALUE());
				}
				
				if ("handlerNames".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setFutureHandlerNames(wfProcessVar.getWFVARVALUE());
				}
			}
		}
		
		ProcessResponse response = this.sbWFNodeApprove(msgHeader,sbWFApprovalForm,wfProcessVarCollection);
		
		result.put("serviceflag",response.getReturn().getSERVICEFLAG());
		result.put("servicemessage",response.getReturn().getSERVICEMESSAGE());
		
		return result;
	}
	
	/**
	 * 节点审批内部服务
	 * @param msgHeader
	 * @param sbWFApprovalForm
	 * @param wfProcessVarCollection
	 * @return
	 */
	private ProcessResponse sbWFNodeApprove(MsgHeader msgHeader,SbWFApprovalForm sbWFApprovalForm,WfProcessVarCollection wfProcessVarCollection) {
		ProcessResponse response = new ProcessResponse();
		SBWFNodeApprovalSrvResponse result = new SBWFNodeApprovalSrvResponse();
		String message = "";
		
		try {
			msgHeaderSrv.validateSysData(msgHeader,"SbWFNodeApprovalSrv");
			processFacade.sbWFNodeApprovalSrv(sbWFApprovalForm);
				
			message = resourceUtil.getLocaleMessage("ws.interface.nodeApproval.OK",language);
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
	private SbWFApprovalForm buildParam(SBWFNodeApprovalSrvRequest request) {
		SbWFApprovalForm sbWFApprovalForm = new SbWFApprovalForm();
		sbWFApprovalForm.setWfInstanceId(request.getWFINSTANCEID());
		sbWFApprovalForm.setFdHandleNodeId(request.getWFNODEID());
		sbWFApprovalForm.setWfAuditedUserNo(request.getWFAUDITERUSERNO());
		sbWFApprovalForm.setWfResult(request.getWFRESULT());
		sbWFApprovalForm.setWfOptionCon(request.getWFOPINIONCON());
		
		//转办扩展参数
		sbWFApprovalForm.setWfTransferUserNo(request.getWFTRANSFERUSERNO());
		
		//驳回/指派下一节点扩展参数
		sbWFApprovalForm.setFutureNodeId(request.getWFROUTEID());
		if (StringUtils.isEmpty(request.getWFPASSEDTOTHIS()) || false == Boolean.parseBoolean(request.getWFPASSEDTOTHIS())) {
			sbWFApprovalForm.setRefusePassedToThisNode(false);
		} else {
			sbWFApprovalForm.setRefusePassedToThisNode(true);
		}
		
		//沟通人员/指派人员扩展参数
		WfProcessVarCollection wfProcessVarCollection = request.getWFPROCESSVARCOLLECTION();
		List<WfProcessVar> wfProcessVars = wfProcessVarCollection != null ? wfProcessVarCollection.getWFPROCESSVAR() : new ArrayList<WfProcessVar>();
		for(WfProcessVar wfProcessVar : wfProcessVars){
			if(String.valueOf(OAConstant.HANDLER_OPERATION_TYPE_COMMUNICATE).equals(request.getWFRESULT()) ||
				String.valueOf(OAConstant.HANDLER_OPERATION_TYPE_CELCOMMUNICATE).equals(request.getWFRESULT())) {
				if ("handlerNos".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setCommicateHandlerNos(wfProcessVar.getWFVARVALUE());
				}
				
				if ("handlerNames".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setCommicateHandlerNames(wfProcessVar.getWFVARVALUE());
				}
			} else {
				if ("handlerNos".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setFutureHandlerNos(wfProcessVar.getWFVARVALUE());
				}
				
				if ("handlerNames".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setFutureHandlerNames(wfProcessVar.getWFVARVALUE());
				}
			}
		}
		
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