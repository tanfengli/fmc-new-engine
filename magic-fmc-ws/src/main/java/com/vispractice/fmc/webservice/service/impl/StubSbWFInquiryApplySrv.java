package com.vispractice.fmc.webservice.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.news.view.DocumentSubmmitedV;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.SBWFInquiryApplySrvRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.SBWFInquiryApplySrvResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.WfProcessApplyCollection;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.WfProcessApplyItem;
import com.vispractice.fmc.webservice.service.SbWFInquiryApplySrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.TypeTranUtil;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFInquiryApplySrv extends AbstractStubParentSrv implements SbWFInquiryApplySrv {
	/**
	 * 查看我的申请单据
	 */
	@Override
	public ProcessResponse inquiryApply(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));

		ProcessResponse response = new ProcessResponse();
		SBWFInquiryApplySrvResponse result = new SBWFInquiryApplySrvResponse();
		WfProcessApplyCollection coll = new WfProcessApplyCollection();
		List<WfProcessApplyItem> itemList = coll.getWFPROCESSAPPLYITEM();
		//获取当前页和页大小
		BigDecimal currentPage = processRequest.getRequest().getMsgHeader().getCURRENTPAGE();
		BigDecimal pageSize = processRequest.getRequest().getMsgHeader().getPAGESIZE();
		Pageable pageable = new PageRequest(currentPage.intValue() - 1, pageSize.intValue());
		String message = "";

		DocumentSubmmitedV documentSubmmitedV = buildParam(processRequest.getRequest());

		try {
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFInquiryApplySrv");
			this.validateFacade.validateSysOrgElement(processRequest.getRequest().getWFAPPLYUSERNO());
			
			Page<DocumentSubmmitedV> page = processFacade.sbWFInquiryApplySrv(documentSubmmitedV,pageable);
			result.setTOTALRECORD(new BigDecimal(page.getTotalElements()));
			result.setTOTALPAGE(new BigDecimal(page.getTotalPages()));
			result.setPAGESIZE(new BigDecimal(page.getSize()));
			result.setCURRENTPAGE(new BigDecimal(page.getNumber() + 1));

			if (page.getContent().size() > 0) {
				for (int i = 0; i < page.getContent().size(); i++) {
					WfProcessApplyItem item = copyResponseProperties(page.getContent().get(i));
					itemList.add(item);
				}
	
				result.setWFPROCESSAPPLYCOLLECTION(coll);
				result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
				message = resourceUtil.getLocaleMessage("ws.interface.inquiryApply.OK",language);
				result.setSERVICEMESSAGE(message);
				log.info(message + JSonUtils.jsonToString(coll));
			} else {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.inquiryApply.error",language));
			}
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);

		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFInquiryApplySrv",StubSbWFInquiryApplySrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
				
		return response;
	}

	/**
	 * 构造查询参数
	 */
	private DocumentSubmmitedV buildParam(SBWFInquiryApplySrvRequest sbWFInquiryApplySrvRequest) {
		DocumentSubmmitedV docSubV = new DocumentSubmmitedV();

		docSubV.setApplyCode(sbWFInquiryApplySrvRequest.getWFAPPLYCODE());
		docSubV.setCreatorUserNo(sbWFInquiryApplySrvRequest.getWFAPPLYUSERNO());
		docSubV.setFdTemplateId(sbWFInquiryApplySrvRequest.getWFTEMPLATEID());

		if (null != sbWFInquiryApplySrvRequest.getWFAPPLYSTARTDATE()) {
			docSubV.setDocCreateTime(TypeTranUtil.xmlToDate(sbWFInquiryApplySrvRequest.getWFAPPLYSTARTDATE()));
		}

		if (null != sbWFInquiryApplySrvRequest.getWFAPPLYENDDATE()) {
			docSubV.setDocPublishTime(TypeTranUtil.xmlToDate(sbWFInquiryApplySrvRequest.getWFAPPLYENDDATE()));
		}

		docSubV.setDocSubject(sbWFInquiryApplySrvRequest.getWFAPPLYSUBJECT());
		docSubV.setFdDescription(sbWFInquiryApplySrvRequest.getWFAPPLYREASON());
		docSubV.setDocContent(sbWFInquiryApplySrvRequest.getWFAPPLYCONTENT());
		docSubV.setDocStatus(sbWFInquiryApplySrvRequest.getWFAPPLYSTATUS());
		docSubV.setFdImportance(Long.parseLong(StringUtils.isEmpty(sbWFInquiryApplySrvRequest.getWFAPPLYURGENTLEVEL()) ? "1" : sbWFInquiryApplySrvRequest.getWFAPPLYURGENTLEVEL()));

		return docSubV;
	}

	/**
	 * 构造返回信息
	 */
	private WfProcessApplyItem copyResponseProperties(DocumentSubmmitedV documentSubmmitedV) {
		WfProcessApplyItem item = new WfProcessApplyItem();
		// WfOpCollection opColl = new WfOpCollection();
		// List<WfOpItem> opItemList = opColl.getWFOPITEM();

		// 实例
		item.setWFINSTANCEID(documentSubmmitedV.getFdId());
		// 申请单编号
		item.setWFAPPLYCODE(documentSubmmitedV.getApplyCode());
		// 流程模板
		item.setWFTEMPLATEID(documentSubmmitedV.getFdTemplateId());
		// 申请提交时间
		item.setWFAPPLYDATE(TypeTranUtil.dateToXML(documentSubmmitedV.getDocCreateTime()));
		// 当前状态
		item.setWFAPPLYSTATUS(documentSubmmitedV.getDocStatus());
		// 申请主题
		item.setWFAPPLYSUBJECT(documentSubmmitedV.getDocSubject());
		// 紧急程度
		item.setWFAPPLYURGENTLEVEL(String.valueOf(documentSubmmitedV.getFdImportance()));
		// 流程描述
		item.setWFDESCRIPTION(documentSubmmitedV.getFdDescription());
		// 当前审批节点名称
		item.setWFCURRENTNODENAME(documentSubmmitedV.getNodeNames());
		// 当前处理人名单
		item.setWFWAITAUDITTIP(documentSubmmitedV.getDealName());

		// 操作实体集合
		// 101:通过;102:驳回;104:沟通;105:废弃
		/*
		 * if (null != doc.getOprNames()) { String[] opValue =
		 * doc.getOprNames().split(";"); for (int j = 0; j < opValue.length;
		 * j++) { if (opValue[j].split(":").length == 2) { WfOpItem opItem = new
		 * WfOpItem(); opItem.setWFOPID(opValue[j].split(":")[0]);
		 * opItem.setWFOPNAME(opValue[j].split(":")[1]); opItemList.add(opItem);
		 * } } }
		 */

		item.setWFATTR1(StringUtil.getString(documentSubmmitedV.getAttr1()));
		item.setWFATTR2(StringUtil.getString(documentSubmmitedV.getAttr2()));
		item.setWFATTR3(StringUtil.getString(documentSubmmitedV.getAttr3()));
		item.setWFATTR4(StringUtil.getString(documentSubmmitedV.getAttr4()));
		item.setWFATTR5(StringUtil.getString(documentSubmmitedV.getAttr5()));
		item.setWFATTR6(StringUtil.getString(documentSubmmitedV.getAttr6()));
		item.setWFATTR7(StringUtil.getString(documentSubmmitedV.getAttr7()));
		item.setWFATTR8(StringUtil.getString(documentSubmmitedV.getAttr8()));
		item.setWFATTR9(StringUtil.getString(documentSubmmitedV.getAttr9()));
		item.setWFATTR10(StringUtil.getString(documentSubmmitedV.getAttr10()));
		item.setWFATTR11(StringUtil.getString(documentSubmmitedV.getAttr11()));
		item.setWFATTR12(StringUtil.getString(documentSubmmitedV.getAttr12()));
		item.setWFATTR13(StringUtil.getString(documentSubmmitedV.getAttr13()));
		item.setWFATTR14(StringUtil.getString(documentSubmmitedV.getAttr14()));
		item.setWFATTR15(StringUtil.getString(documentSubmmitedV.getAttr15()));
		item.setWFATTR16(StringUtil.getString(documentSubmmitedV.getAttr16()));
		item.setWFATTR17(StringUtil.getString(documentSubmmitedV.getAttr17()));
		item.setWFATTR18(StringUtil.getString(documentSubmmitedV.getAttr18()));
		item.setWFATTR19(StringUtil.getString(documentSubmmitedV.getAttr19()));
		item.setWFATTR20(StringUtil.getString(documentSubmmitedV.getAttr20()));

		return item;
	}
}
