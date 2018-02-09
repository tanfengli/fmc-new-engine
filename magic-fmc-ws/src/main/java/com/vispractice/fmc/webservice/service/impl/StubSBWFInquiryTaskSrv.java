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
import com.vispractice.fmc.business.entity.aboutmyself.VCmsTask;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvResponse;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.WfOpCollection;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.WfOpItem;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.WfProcessTaskCollection;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.WfProcessTaskItem;
import com.vispractice.fmc.webservice.service.SbWFInquiryTaskSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.TypeTranUtil;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSBWFInquiryTaskSrv extends AbstractStubParentSrv implements SbWFInquiryTaskSrv {
	/**
	 * 查询待办
	 */
	@Override
	public ProcessResponse quiryTask(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFInquiryTaskSrvResponse result = new SBWFInquiryTaskSrvResponse();
		WfProcessTaskCollection coll = new WfProcessTaskCollection();
		List<WfProcessTaskItem> itemList = coll.getWFPROCESSTASKITEM();
		String message = "";

		try {
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFInquiryTaskSrv");
			this.validateFacade.validateSysOrgElement(processRequest.getRequest().getWFTASKUSERNO());
			
			VCmsTask vCmsTask = buildParam(processRequest.getRequest());
			//获取当前页和页大小
			BigDecimal currentPage = processRequest.getRequest().getMsgHeader().getCURRENTPAGE();
			BigDecimal pageSize = processRequest.getRequest().getMsgHeader().getPAGESIZE();
			Pageable pageable = new PageRequest(currentPage.intValue() - 1, pageSize.intValue());

			Page<VCmsTask> page = processFacade.sbWFInquiryTaskSrv(vCmsTask,pageable);
			
			if (page.getContent().size() > 0) {
				//封装返回参数
				for (int i = 0; i < page.getContent().size(); i++) {
					WfProcessTaskItem item = copyResponseProperties(page.getContent().get(i));
					itemList.add(item);
				}
				result.setWFPROCESSTASKCOLLECTION(coll);
				result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
				message = resourceUtil.getLocaleMessage("ws.interface.inquiryTask.OK",language);
				result.setSERVICEMESSAGE(message);
				
				if(log.isDebugEnabled()){
					log.debug(message + JSonUtils.jsonToString(coll));
				}
				
				//封装分页参数
				result.setTOTALRECORD(new BigDecimal(page.getTotalElements()));
				result.setTOTALPAGE(new BigDecimal(page.getTotalPages()));
				result.setPAGESIZE(new BigDecimal(page.getSize()));
				result.setCURRENTPAGE(new BigDecimal(page.getNumber() + 1));
			} else {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.inquiryTask.error",language));
			}
		} catch (WorkflowException e) {
			log.error(e.getMessage());
			
			//封装分页参数
			result.setTOTALRECORD(new BigDecimal("0"));
			result.setTOTALPAGE(new BigDecimal("0"));
			result.setPAGESIZE(new BigDecimal("10"));
			result.setCURRENTPAGE(new BigDecimal("1"));
			
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFInquiryTaskSrv",StubSBWFInquiryTaskSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());

		return response;
	}

	/**
	 * 构造查询参数
	 */
	private VCmsTask buildParam(SBWFInquiryTaskSrvRequest sbWFInquiryTaskSrvRequest) {
		VCmsTask vCmsTask = new VCmsTask();
		vCmsTask.setTaskUserNo(sbWFInquiryTaskSrvRequest.getWFTASKUSERNO());
		vCmsTask.setAppUserNo(sbWFInquiryTaskSrvRequest.getWFAPPLYUSERNO());
		vCmsTask.setApplyCode(sbWFInquiryTaskSrvRequest.getWFAPPLYCODE());
		vCmsTask.setFdTemplateId(sbWFInquiryTaskSrvRequest.getWFTEMPLATEID());
		if (sbWFInquiryTaskSrvRequest.getWFAPPLYSTARTDATE() != null) {
			vCmsTask.setAppDate(TypeTranUtil.xmlToDate(sbWFInquiryTaskSrvRequest.getWFAPPLYSTARTDATE()));
		}

		if (sbWFInquiryTaskSrvRequest.getWFAPPLYENDDATE() != null) {
			vCmsTask.setPublishDate(TypeTranUtil.xmlToDate(sbWFInquiryTaskSrvRequest.getWFAPPLYENDDATE()));
		}
		vCmsTask.setAppTitle(sbWFInquiryTaskSrvRequest.getWFAPPLYSUBJECT());
		vCmsTask.setApplyReason(sbWFInquiryTaskSrvRequest.getWFAPPLYREASON());
		vCmsTask.setDocContent(sbWFInquiryTaskSrvRequest.getWFAPPLYCONTENT());
		vCmsTask.setUrgentLevel(sbWFInquiryTaskSrvRequest.getWFAPPLYURGENTLEVEL());

		return vCmsTask;
	}

	/**
	 * 复制属性
	 */
	private WfProcessTaskItem copyResponseProperties(VCmsTask cmsTask) throws WorkflowException {
		WfProcessTaskItem item = new WfProcessTaskItem();
		WfOpCollection opColl = new WfOpCollection();
		List<WfOpItem> opItemList = opColl.getWFOPITEM();

		//实例序号
		item.setWFINSTANCEID(cmsTask.getWfInstanceId());
		//设置当前节点标识
		item.setWFNODEID(cmsTask.getNodeId());
		//申请单编号
		item.setWFAPPLYCODE(cmsTask.getApplyCode());
		//流程模板序号
		item.setWFTEMPLATEID(cmsTask.getFdTemplateId());
		//业务系统连接地址
		item.setWFBUSILINK(StringUtil.getString(cmsTask.getWfBusiLink()));
		//申请人编号
		item.setWFAPPLYUSERNO(cmsTask.getAppUserNo());
		//申请人名称
		item.setWFAPPLYUSERNAME(cmsTask.getAppUserName());
		//申请提交时间
		item.setWFAPPLYDATE(TypeTranUtil.dateToXML(new Date(cmsTask.getAppDate().getTime())));
		//当前状态
		item.setWFAPPLYSTATUS(cmsTask.getWfStatus());
		//申请主题
		item.setWFAPPLYSUBJECT(cmsTask.getAppTitle());
		//紧急程度
		item.setWFAPPLYURGENTLEVEL(cmsTask.getUrgentLevel());
		//流程描述
		item.setWFDESCRIPTION(cmsTask.getAppTitle());
		//当前审批节点名称
		item.setWFCURRENTNODENAME(cmsTask.getNodeName());
		//当前处理人名单
		item.setWFWAITAUDITTIP(cmsTask.getTaskUserName());

		//操作实体集合 101:通过;102:驳回;104:沟通;105:废弃
		if (null != cmsTask.getOprNames()) {
			String[] opValue = cmsTask.getOprNames().split(";");
			for (int j = 0; j < opValue.length; j++) {
				if (opValue[j].split(":").length == 2) {
					WfOpItem opItem = new WfOpItem();
					opItem.setWFOPID(opValue[j].split(":")[0]);
					opItem.setWFOPNAME(opValue[j].split(":")[1]);
					opItemList.add(opItem);
				}
			}
		}
		item.setWFOPCollection(opColl);

		item.setWFATTR1(StringUtil.getString(cmsTask.getAttr1()));
		item.setWFATTR2(StringUtil.getString(cmsTask.getAttr2()));
		item.setWFATTR3(StringUtil.getString(cmsTask.getAttr3()));
		item.setWFATTR4(StringUtil.getString(cmsTask.getAttr4()));
		item.setWFATTR5(StringUtil.getString(cmsTask.getAttr5()));
		item.setWFATTR6(StringUtil.getString(cmsTask.getAttr6()));
		item.setWFATTR7(StringUtil.getString(cmsTask.getAttr7()));
		item.setWFATTR8(StringUtil.getString(cmsTask.getAttr8()));
		item.setWFATTR9(StringUtil.getString(cmsTask.getAttr9()));
		item.setWFATTR10(StringUtil.getString(cmsTask.getAttr10()));
		item.setWFATTR11(StringUtil.getString(cmsTask.getAttr11()));
		item.setWFATTR12(StringUtil.getString(cmsTask.getAttr12()));
		item.setWFATTR13(StringUtil.getString(cmsTask.getAttr13()));
		item.setWFATTR14(StringUtil.getString(cmsTask.getAttr14()));
		item.setWFATTR15(StringUtil.getString(cmsTask.getAttr15()));
		item.setWFATTR16(StringUtil.getString(cmsTask.getAttr16()));
		item.setWFATTR17(StringUtil.getString(cmsTask.getAttr17()));
		item.setWFATTR18(StringUtil.getString(cmsTask.getAttr18()));
		item.setWFATTR19(StringUtil.getString(cmsTask.getAttr19()));
		item.setWFATTR20(StringUtil.getString(cmsTask.getAttr20()));

		return item;
	}
}
