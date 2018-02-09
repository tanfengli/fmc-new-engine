package com.vispractice.fmc.webservice.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessHistoryV;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesshistorysrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesshistorysrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesshistorysrv.SBWFInquiryProcessHistorySrvResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesshistorysrv.WfAuditRecord;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesshistorysrv.WfAuditRecordCollection;
import com.vispractice.fmc.webservice.service.SbWFInquiryProcessHistorySrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.TypeTranUtil;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFInquiryProcessHistorySrv extends AbstractStubParentSrv implements SbWFInquiryProcessHistorySrv {
	@Override
	public ProcessResponse inquiryProcessHistory(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFInquiryProcessHistorySrvResponse result = new SBWFInquiryProcessHistorySrvResponse();
		WfAuditRecordCollection wfAuditRecordCollection = new WfAuditRecordCollection();
		List<WfAuditRecord> wfAuditRecords = wfAuditRecordCollection.getWFAUDITRECORD();
		String wfInstanceId = processRequest.getRequest().getWFINSTANCEID();
		String message = "";

		try {
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFInquiryProcessHistorySrv");
			validateFacade.validateWfInstance(wfInstanceId);
		
			List<ProcessHistoryV> processHistoryVs = processFacade.sbWFInquiryProcessHistorySrv(wfInstanceId);
			this.copyProperty(wfAuditRecords,processHistoryVs);
			
			if (processHistoryVs.isEmpty()) {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.inquiryHistory.error",language));
			} else {
				result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
				result.setWFAUDITRECORDCOLLECTION(wfAuditRecordCollection);
				message = resourceUtil.getLocaleMessage("ws.interface.inquiryHistory.OK",language);
				result.setSERVICEMESSAGE(message);
				log.info(message + JSonUtils.jsonToString(wfAuditRecordCollection));
			}
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setWFAUDITRECORDCOLLECTION(wfAuditRecordCollection);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFInquiryProcessHistorySrv",StubSbWFInquiryProcessHistorySrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}
	
	/**
	 * 构造返回对象属性
	 */
	private void copyProperty(List<WfAuditRecord> wfAuditRecords,List<ProcessHistoryV> processHistoryVs){
		Timestamp datePre = null;
		Timestamp date = null;
		
		for(int i = 0;i < processHistoryVs.size();i++) {
			WfAuditRecord wfAuditRecord = new WfAuditRecord();
			ProcessHistoryV processHistoryV = processHistoryVs.get(i);
			
			wfAuditRecord.setWFNODEID(processHistoryV.getWfNodeId());
			wfAuditRecord.setWFNODENAME(processHistoryV.getWfNodeName());
			wfAuditRecord.setWFAUDITUSERNO(processHistoryV.getWfAuditUserNo());
			wfAuditRecord.setWFAUDITUSERNAME(processHistoryV.getWfAuditUserName());
			wfAuditRecord.setWFAUDITMIND(processHistoryV.getWfAuditMind());
			wfAuditRecord.setWFAUDITRESULT(processHistoryV.getWfAuditResult());
			wfAuditRecord.setWFAUDITDATE(TypeTranUtil.timestampToXMLGregorianCalendar(processHistoryV.getWfAuditDate()));
			wfAuditRecord.setWFAUDITSPACINGINTERVAL(new BigDecimal(processHistoryV.getWfAuditSpacingInterval()));
			
			//审批间隔时间
			date = processHistoryV.getWfAuditDate();
			if (datePre!= null &&  date!= null) {
				Long diff = date.getTime()-datePre.getTime();
				wfAuditRecord.setWFAUDITSPACINGINTERVAL(BigDecimal.valueOf(diff/1000));
			} else {
				wfAuditRecord.setWFAUDITSPACINGINTERVAL(new BigDecimal(0));
			}
			datePre = date;
			
			wfAuditRecords.add(wfAuditRecord);
		}
	}
}
