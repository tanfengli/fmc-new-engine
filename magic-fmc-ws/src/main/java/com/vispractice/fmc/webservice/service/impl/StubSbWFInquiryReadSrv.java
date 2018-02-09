package com.vispractice.fmc.webservice.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessReadV;
import com.vispractice.fmc.csb.sb_wf_inquiryreadsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryreadsrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryreadsrv.SBWFInquiryReadSrvRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryreadsrv.SBWFInquiryReadSrvResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryreadsrv.WfProcessReadCollection;
import com.vispractice.fmc.csb.sb_wf_inquiryreadsrv.WfProcessReadItem;
import com.vispractice.fmc.webservice.service.SbWFInquiryReadSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.TypeTranUtil;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFInquiryReadSrv extends AbstractStubParentSrv implements SbWFInquiryReadSrv {
	/**
	 * 查询待阅信息
	 */
	@Override
	public ProcessResponse sbWfInquiryReadSrv(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFInquiryReadSrvResponse result = new SBWFInquiryReadSrvResponse();
		WfProcessReadCollection wfProcessReadCollection = new WfProcessReadCollection();
		List<WfProcessReadItem> wfProcessReadItems = wfProcessReadCollection.getWFPROCESSREADITEM();
		String message = "";
		
		try {
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFInquiryReadSrv");
			this.validateFacade.validateSysOrgElement(processRequest.getRequest().getWFREADUSERNO());
			
			ProcessReadV processReadV = this.builParam(processRequest.getRequest());
			BigDecimal currentPage = processRequest.getRequest().getMsgHeader().getCURRENTPAGE();
			BigDecimal pageSize = processRequest.getRequest().getMsgHeader().getPAGESIZE();
			Pageable pageable = new PageRequest(currentPage.intValue()-1,pageSize.intValue());
			
			Page<ProcessReadV> page = processFacade.sbWFInquiryReadSrv(processReadV,pageable);
			result.setTOTALRECORD(new BigDecimal(page.getTotalElements()));
			result.setTOTALPAGE(new BigDecimal(page.getTotalPages()));
			result.setPAGESIZE(new BigDecimal(page.getSize()));
			result.setCURRENTPAGE(new BigDecimal(page.getNumber()+1));
			
			if (page.getContent().size() > 0) {
				for (int i = 0;i < page.getContent().size();i++) {
					WfProcessReadItem wfProcessReadItem = this.copyProperty(page.getContent().get(i));
					wfProcessReadItems.add(wfProcessReadItem);
				}
				
				result.setWFPROCESSREADCOLLECTION(wfProcessReadCollection);
				result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
				message = resourceUtil.getLocaleMessage("ws.interface.inquiryRead.OK",language);
				result.setSERVICEMESSAGE(message);
				log.info(message + JSonUtils.jsonToString(wfProcessReadCollection));
			} else {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.inquiryRead.error",language));
			}
		} catch (Exception e) {
			result.setWFPROCESSREADCOLLECTION(wfProcessReadCollection);
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFInquiryReadSrv",StubSbWFInquiryReadSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		
		return response;
	}
	
	/**
	 * 构造查询参数
	 */
	private ProcessReadV builParam(SBWFInquiryReadSrvRequest request) throws WorkflowException {
		ProcessReadV processReadV = new ProcessReadV();
		processReadV.setApplyCode(request.getWFAPPLYCODE());
		processReadV.setReadUserNo(request.getWFREADUSERNO());
		processReadV.setApplyUserNo(request.getWFAPPLYUSERNO());
		processReadV.setWfTemplateId(request.getWFTEMPLATEID());
		processReadV.setApplySubject(request.getWFAPPLYSUBJECT());
		processReadV.setApplyReason(request.getWFAPPLYREASON());
		processReadV.setApplyContent(request.getWFAPPLYCONTENT());
		processReadV.setUrgentLevel(request.getWFAPPLYURGENTLEVEL());
		
		return processReadV;
	}
	
	/**
	 * 构造反馈对象属性
	 */
	private WfProcessReadItem copyProperty(ProcessReadV processReadV) throws WorkflowException {
		WfProcessReadItem wfProcessReadItem = new WfProcessReadItem();
		wfProcessReadItem.setWFINSTANCEID(processReadV.getWfInstanceId());
		wfProcessReadItem.setWFAPPLYCODE(processReadV.getApplyCode());
		wfProcessReadItem.setWFTEMPLATEID(processReadV.getWfTemplateId());
		wfProcessReadItem.setWFBUSILINK(processReadV.getWfBusiLink());
		wfProcessReadItem.setWFAPPLYUSERNO(processReadV.getApplyUserNo());
		wfProcessReadItem.setWFAPPLYUSERNAME(processReadV.getApplyUserName());
		wfProcessReadItem.setWFAPPLYDATE(TypeTranUtil.dateToXML(processReadV.getStartApplyDate()));
		wfProcessReadItem.setWFAPPLYSTATUS(processReadV.getApplyStatus());
		wfProcessReadItem.setWFAPPLYSUBJECT(processReadV.getApplySubject());
		wfProcessReadItem.setWFAPPLYURGENTLEVEL(processReadV.getUrgentLevel());
		wfProcessReadItem.setWFDESCRIPTION(processReadV.getWfDescription());
		wfProcessReadItem.setWFNODEID("");
		wfProcessReadItem.setWFNODENAME(processReadV.getNodeNames());
		wfProcessReadItem.setWFATTR1(StringUtil.getString(processReadV.getAttr1()));
		wfProcessReadItem.setWFATTR2(StringUtil.getString(processReadV.getAttr2()));
		wfProcessReadItem.setWFATTR3(StringUtil.getString(processReadV.getAttr3()));
		wfProcessReadItem.setWFATTR4(StringUtil.getString(processReadV.getAttr4()));
		wfProcessReadItem.setWFATTR5(StringUtil.getString(processReadV.getAttr5()));
		wfProcessReadItem.setWFATTR6(StringUtil.getString(processReadV.getAttr6()));
		wfProcessReadItem.setWFATTR7(StringUtil.getString(processReadV.getAttr7()));
		wfProcessReadItem.setWFATTR8(StringUtil.getString(processReadV.getAttr8()));
		wfProcessReadItem.setWFATTR9(StringUtil.getString(processReadV.getAttr9()));
		wfProcessReadItem.setWFATTR10(StringUtil.getString(processReadV.getAttr10()));
		wfProcessReadItem.setWFATTR11(StringUtil.getString(processReadV.getAttr11()));
		wfProcessReadItem.setWFATTR12(StringUtil.getString(processReadV.getAttr12()));
		wfProcessReadItem.setWFATTR13(StringUtil.getString(processReadV.getAttr13()));
		wfProcessReadItem.setWFATTR14(StringUtil.getString(processReadV.getAttr14()));
		wfProcessReadItem.setWFATTR15(StringUtil.getString(processReadV.getAttr15()));
		wfProcessReadItem.setWFATTR16(StringUtil.getString(processReadV.getAttr16()));
		wfProcessReadItem.setWFATTR17(StringUtil.getString(processReadV.getAttr17()));
		wfProcessReadItem.setWFATTR18(StringUtil.getString(processReadV.getAttr18()));
		wfProcessReadItem.setWFATTR19(StringUtil.getString(processReadV.getAttr19()));
		wfProcessReadItem.setWFATTR20(StringUtil.getString(processReadV.getAttr20()));
		
		return wfProcessReadItem;
	}
}
