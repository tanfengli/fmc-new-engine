package com.vispractice.fmc.webservice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.csb.sb_wf_approvalpersonsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_approvalpersonsrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_approvalpersonsrv.SBWFApprovalPersonSrvResponse;
import com.vispractice.fmc.csb.sb_wf_approvalpersonsrv.WFHandlerRecord;
import com.vispractice.fmc.csb.sb_wf_approvalpersonsrv.WfHandlerRecordCollection;
import com.vispractice.fmc.webservice.service.SbWFApprovalPersonSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFApprovalPersonSrv extends AbstractStubParentSrv implements SbWFApprovalPersonSrv {
	@Override
	public ProcessResponse sbWFApprovalPerson(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFApprovalPersonSrvResponse result = new SBWFApprovalPersonSrvResponse();
		List<WfHandlerRecordCollection> wfHandlerRecordCollections = result.getWFHANDLERRECORDCOLLECTION();
		String wfInstanceId = processRequest.getRequest().getWFINSTANCEID();
		String wfNodeId = processRequest.getRequest().getWFNODEID();
		String message = "";
		
		try {
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFApprovalPersonSrv");
			this.validateFacade.validateWfInstance(wfInstanceId);
			Map<String,Map<String,String>> nodes = processFacade.sbWFApprovalPerson(wfInstanceId,wfNodeId);
			if (nodes != null && nodes.size() > 0) {
				this.copyProperty(nodes,wfHandlerRecordCollections);
				result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
				message = resourceUtil.getLocaleMessage("ws.interface.approvalPersonNode.OK",language);
				result.setSERVICEMESSAGE(message);
				log.info(message + JSonUtils.jsonToString(result));
			} else {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.approvalPersonNode.error",language));
			}
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFApprovalPersonSrv",StubSbWFApprovalPersonSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}
	
	/**
	 * 构造反馈对象属性
	 */
	private void copyProperty(Map<String,Map<String,String>> nodes,List<WfHandlerRecordCollection> wfHandlerRecordCollections) throws WorkflowException {
		 Set<String> keys = nodes.keySet();
		 
		 for (String key : keys) {
			 Map<String,String> node = nodes.get(key);
			 WfHandlerRecordCollection wfHandlerRecordCollection = new WfHandlerRecordCollection();
			 List<WFHandlerRecord> wfHandlerRecords = wfHandlerRecordCollection.getWFHANDLERRECORD();
			 wfHandlerRecordCollection.setWFNODEID(node.get("nodeId"));
			 wfHandlerRecordCollection.setWFNODENAME(node.get("nodeName"));
			 String handlerIdStr = node.get("handlerId");
			 String handlerNameStr = node.get("handlerName");
			 if (StringUtils.isNotEmpty(handlerIdStr) && StringUtils.isNotEmpty(handlerNameStr)) {
				 String[] handlerIds = handlerIdStr.split(";");
				 String[] handlerNames = handlerNameStr.split(";");
				 for (int index = 0;index < handlerIds.length;index++) {
					 WFHandlerRecord wfHandlerRecord = new WFHandlerRecord();
					 wfHandlerRecord.setWFHANDLERID(handlerIds[index]);
					 wfHandlerRecord.setWFHANDLERNAME(handlerNames[index]);
					 wfHandlerRecords.add(wfHandlerRecord);
				 }
			 }
			 
			 wfHandlerRecordCollections.add(wfHandlerRecordCollection);
		 }
	}
}
