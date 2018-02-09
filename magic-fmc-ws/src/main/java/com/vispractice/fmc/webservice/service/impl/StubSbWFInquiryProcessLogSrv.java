package com.vispractice.fmc.webservice.service.impl;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessLogV;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesslogsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesslogsrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesslogsrv.SBWFInquiryProcessLogSrvResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesslogsrv.WfProcessLog;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesslogsrv.WfProcessLogCollection;
import com.vispractice.fmc.webservice.service.SbWFInquiryProcessLogSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.TypeTranUtil;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFInquiryProcessLogSrv extends AbstractStubParentSrv implements SbWFInquiryProcessLogSrv {
	@Override
	public ProcessResponse inquiryProcessLog(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFInquiryProcessLogSrvResponse result = new SBWFInquiryProcessLogSrvResponse();
		WfProcessLogCollection wfProcessLogCollection = new WfProcessLogCollection();
		List<WfProcessLog> wfProcessLogs = wfProcessLogCollection.getWFPROCESSLOG();
		String wfInstanceId = processRequest.getRequest().getWFINSTANCEID();
		String message = "";
		
		try { 
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFInquiryProcessLogSrv");
			validateFacade.validateWfInstance(wfInstanceId);
			
			List<ProcessLogV> processLogVs = processFacade.sbWFInquiryProcessLogSrv(wfInstanceId);
			for(int i = 0; i < processLogVs.size(); i++) {
				WfProcessLog wfProcessLog = new WfProcessLog();
				ProcessLogV processLogV = processLogVs.get(i);
				copyProperty(wfProcessLog,processLogV);
				wfProcessLogs.add(wfProcessLog);
			}
				
			if (processLogVs.isEmpty()) {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.inquiryLog.error",language));
			} else {
				result.setWFPROCESSLOGCOLLECTION(wfProcessLogCollection);
				result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
				message = resourceUtil.getLocaleMessage("ws.interface.inquiryLog.OK",language);
				result.setSERVICEMESSAGE(message);
				log.info(message + JSonUtils.jsonToString(wfProcessLogCollection));
			}
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFInquiryProcessLogSrv",StubSbWFInquiryProcessLogSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
				
		return response;
	}
	
	/**
	 * 构造反馈对象属性
	 */
	private void copyProperty(WfProcessLog wfProcessLog,ProcessLogV processLogV) {
		wfProcessLog.setWFNODEID(processLogV.getWfNodeId());
		wfProcessLog.setWFNODENAME(processLogV.getWfNodeName());
		wfProcessLog.setWFOPCODE(processLogV.getWfOpCode());
		wfProcessLog.setWFOPNAME(processLogV.getWfOpName());
		wfProcessLog.setWFOPINFO(processLogV.getWfOpInfo());
		wfProcessLog.setWFOPUSERNO(processLogV.getWfOpUserNo());
		wfProcessLog.setWFOPUSERNAME(processLogV.getWfOpUserName());
		wfProcessLog.setWFOPTime(TypeTranUtil.dateToXML(processLogV.getWfOpTime()));
	}
}
