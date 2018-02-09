package com.vispractice.fmc.webservice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.csb.sb_wf_inquiryrejectnodesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryrejectnodesrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryrejectnodesrv.SBWFInquiryRejectNodeSrvResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryrejectnodesrv.WfRejectRecord;
import com.vispractice.fmc.csb.sb_wf_inquiryrejectnodesrv.WfRejectRecordCollection;
import com.vispractice.fmc.webservice.service.SbWFInquiryRejectNodeSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFInquiryRejectNodeSrv extends AbstractStubParentSrv implements SbWFInquiryRejectNodeSrv {
	@Override
	public ProcessResponse sbWFInquiryRejectNode(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFInquiryRejectNodeSrvResponse result = new SBWFInquiryRejectNodeSrvResponse();
		WfRejectRecordCollection wfRejectRecordCollection = new WfRejectRecordCollection();
		List<WfRejectRecord> wfRejectRecords = wfRejectRecordCollection.getWFREJECTRECORD();
		String wfInstanceId = processRequest.getRequest().getWFINSTANCEID();
		String nodeId = processRequest.getRequest().getWFNODEID();
		Map<String,Map<String,String>> nodes = null;
		String message = "";
		
		try {
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFInquiryRejectNodeSrv");
			validateFacade.validateWfInstance(wfInstanceId);
			nodes = processFacade.sbWFInquiryRejectNodeSrv(wfInstanceId,nodeId);
			
			if (nodes != null && nodes.size() > 0) {
				this.copyProperty(nodes,wfRejectRecords);
				result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
				result.setWFREJECTRECORDCOLLECTION(wfRejectRecordCollection);
				message = resourceUtil.getLocaleMessage("ws.interface.inquiryRejectNode.OK",language);
				result.setSERVICEMESSAGE(message);
				log.info(message + JSonUtils.jsonToString(wfRejectRecordCollection));
			} else {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.inquiryRejectNode.error",language));
			}
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setWFREJECTRECORDCOLLECTION(wfRejectRecordCollection);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFInquiryRejectNodeSrv",StubSbWFInquiryRejectNodeSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());

		return response;
	}
	
	/**
	 * 构造返回对象属性
	 */
	private void copyProperty(Map<String,Map<String,String>> nodes,List<WfRejectRecord> wfRejectRecords) {
		Set<String> keys = nodes.keySet();
		 
		 for (String key : keys) {
			 Map<String,String> node = nodes.get(key);
			 WfRejectRecord wfRejectRecord = new WfRejectRecord();
			 wfRejectRecord.setWFNODEID(node.get("nodeId"));
			 wfRejectRecord.setWFNODENAME(node.get("nodeName"));
			 wfRejectRecords.add(wfRejectRecord);
		 }
	}
}
