package com.vispractice.fmc.webservice.service.impl;

import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv.SBWFInquiryProcessStatusSrvResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv.WfStatusCollection;
import com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv.WfStatusItem;
import com.vispractice.fmc.webservice.service.SbWFInquiryProcessStatusSrv;
import com.vispractice.fmc.webservice.util.CodeToNameUtil;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFInquiryProcessStatusSrv extends AbstractStubParentSrv implements SbWFInquiryProcessStatusSrv {
	@Override
	public ProcessResponse inquiryProcessStatus(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));

		ProcessResponse response = new ProcessResponse();
		SBWFInquiryProcessStatusSrvResponse result = new SBWFInquiryProcessStatusSrvResponse();
		WfStatusCollection colletion = new WfStatusCollection();
		String wfInstanceId = processRequest.getRequest().getWFINSTANCEID();
		String message = "";
		
		try {
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFInquiryProcessStatusSrv");
			validateFacade.validateWfInstance(wfInstanceId);
			
			// 调用流程服务的查询流程状态接口
			SysNewsMain sysNewsMain = processFacade.sbWFInquiryProcessStatusSrv(wfInstanceId);
				
			if (null != sysNewsMain) {
				// 设置流程状态实体
				WfStatusItem statusItem = new WfStatusItem();
				statusItem.setWFINSTANCEID(sysNewsMain.getFdId());
				// 流程编码转化
				String wfStatus = CodeToNameUtil.docStatusTran(sysNewsMain.getDocStatus());
				statusItem.setWFSTATUS(wfStatus);
				colletion.getWFSTATUSITEM().add(statusItem);
				
				result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
				result.setWFSTATUSCOLLECTION(colletion);
				message = resourceUtil.getLocaleMessage("ws.interface.inquiryStatus.OK",language);
				result.setSERVICEMESSAGE(message);
				log.info(message + JSonUtils.jsonToString(colletion));
			} else { 
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.inquiryStatus.error",language));
			}
		} catch (WorkflowException e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFInquiryProcessStatusSrv",StubSbWFInquiryProcessStatusSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}
}
