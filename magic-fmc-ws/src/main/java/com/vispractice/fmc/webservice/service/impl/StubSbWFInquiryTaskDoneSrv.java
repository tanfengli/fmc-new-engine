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
import com.vispractice.fmc.business.entity.aboutmyself.AuditBillV;
import com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv.SBWFInquiryTaskDoneSrvRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv.SBWFInquiryTaskDoneSrvResponse;
import com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv.WfProcessTaskCollection;
import com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv.WfProcessTaskItem;
import com.vispractice.fmc.webservice.service.SbWFInquiryTaskDoneSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.TypeTranUtil;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFInquiryTaskDoneSrv extends AbstractStubParentSrv implements SbWFInquiryTaskDoneSrv {
	
	/**
	 * 查询已办信息
	 */
	@Override
	public ProcessResponse inquiryTaskDone(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));

		ProcessResponse response = new ProcessResponse();
		SBWFInquiryTaskDoneSrvResponse result = new SBWFInquiryTaskDoneSrvResponse();
		// 任务信息集合
		WfProcessTaskCollection wfProcessTaskCollection = new WfProcessTaskCollection();
		List<WfProcessTaskItem> wfProcessTaskItems = wfProcessTaskCollection.getWFPROCESSTASKITEM();
		String message = "";

		try {
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFInquiryTaskDoneSrv");
			
			AuditBillV vCmsTask_done = buildParam(processRequest.getRequest());
			// 获取当前页和页大小
			BigDecimal currentPage = processRequest.getRequest().getMsgHeader().getCURRENTPAGE();
			BigDecimal pageSize = processRequest.getRequest().getMsgHeader().getPAGESIZE();
			Pageable pageable = new PageRequest(currentPage.intValue() - 1,pageSize.intValue());

			Page<AuditBillV> page = processFacade.sbWFInquiryTaskDoneSrv(vCmsTask_done,pageable);
			// 封装分页参数
			result.setTOTALRECORD(new BigDecimal(page.getTotalElements()));
			result.setTOTALPAGE(new BigDecimal(page.getTotalPages()));
			result.setPAGESIZE(new BigDecimal(page.getSize()));
			result.setCURRENTPAGE(new BigDecimal(page.getNumber()+1));

			// 封装返回参数
			for (int i = 0; i < page.getContent().size(); i++) {
				WfProcessTaskItem wfProcessTaskItem = copyResponseProperties(page.getContent().get(i));
				wfProcessTaskItems.add(wfProcessTaskItem);
			}

			result.setWFPROCESSTASKCOLLECTION(wfProcessTaskCollection);
			result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
			message = resourceUtil.getLocaleMessage("ws.interface.inquiryTaskDone.OK",language);
			result.setSERVICEMESSAGE(message);
			log.info(message + JSonUtils.jsonToString(wfProcessTaskCollection));
		} catch (WorkflowException e) {
			log.error(e.getMessage());
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFInquiryTaskDoneSrv",StubSbWFInquiryTaskDoneSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}
	
	/**
	 * 构造参数
	 */
	private AuditBillV buildParam(SBWFInquiryTaskDoneSrvRequest sbWFInquiryTaskDoneSrvRequest){
		AuditBillV vCmsTask_done = new AuditBillV();
		vCmsTask_done.setTaskUserNo(sbWFInquiryTaskDoneSrvRequest.getWFTASKUSERNO());
		vCmsTask_done.setAppUserNo(sbWFInquiryTaskDoneSrvRequest.getWFAPPLYUSERNO());
		vCmsTask_done.setApplyCode(sbWFInquiryTaskDoneSrvRequest.getWFAPPLYCODE());
		vCmsTask_done.setFdTemplateId(sbWFInquiryTaskDoneSrvRequest.getWFTEMPLATEID());
		if (null != sbWFInquiryTaskDoneSrvRequest.getWFAPPLYSTARTDATE()) {
			vCmsTask_done.setDocCreateTime(TypeTranUtil.xmlToDate(sbWFInquiryTaskDoneSrvRequest.getWFAPPLYSTARTDATE()));
		}
		
		if (null != sbWFInquiryTaskDoneSrvRequest.getWFAPPLYENDDATE()) {
			vCmsTask_done.setDocPublishTime(TypeTranUtil.xmlToDate(sbWFInquiryTaskDoneSrvRequest.getWFAPPLYENDDATE()));
		}
		vCmsTask_done.setDocSubject(sbWFInquiryTaskDoneSrvRequest.getWFAPPLYTITLE());
		vCmsTask_done.setFdDescription(sbWFInquiryTaskDoneSrvRequest.getWFAPPLYREASON());
		vCmsTask_done.setDocContent(sbWFInquiryTaskDoneSrvRequest.getWFAPPLYCONTENT());
		if (StringUtil.isEmpty(sbWFInquiryTaskDoneSrvRequest.getWFAPPLYURGENTLEVEL())) {
			vCmsTask_done.setFdImportance(Long.parseLong("1"));
		} else {
			vCmsTask_done.setFdImportance(Long.parseLong(sbWFInquiryTaskDoneSrvRequest.getWFAPPLYURGENTLEVEL()));
		}
		
		return vCmsTask_done;
	}

	/**
	 * 复制属性
	 */
	private WfProcessTaskItem copyResponseProperties(AuditBillV auditBillV) throws WorkflowException {
		WfProcessTaskItem item = new WfProcessTaskItem();
		// 实例
		item.setWFINSTANCEID(auditBillV.getFdId());
		// 申请单编号
		item.setWFAPPLYCODE(auditBillV.getApplyCode());
		// 流程模板
		item.setWFTEMPLATEID(auditBillV.getFdTemplateId());
		// 申请人编号
		item.setWFAPPLYUSERNO(auditBillV.getAppUserNo());
		// 申请人名称
		item.setWFAPPLYUSERNAME(auditBillV.getAppUserName());
		// 申请提交时间
		item.setWFAPPLYDATE(TypeTranUtil.dateToXML(auditBillV.getDocCreateTime()));
		// 当前状态
		item.setWFAPPLYSTATUS(auditBillV.getDocStatus());
		// 申请主题
		item.setWFAPPLYSUBJECT(auditBillV.getDocSubject());
		// 紧急程度
		item.setWFAPPLYURGENTLEVEL(String.valueOf(auditBillV.getFdImportance()));
		// 流程描述
		item.setWFDESCRIPTION(auditBillV.getFdDescription());
		// 当前审批节点名称
		item.setWFCURRENTNODENAME(auditBillV.getNodeName());
		// 当前处理人名单
		item.setWFWAITAUDITTIP(auditBillV.getDealName());

		return item;
	}
}
