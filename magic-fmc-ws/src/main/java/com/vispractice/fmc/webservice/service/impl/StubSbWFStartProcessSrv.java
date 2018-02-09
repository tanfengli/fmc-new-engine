package com.vispractice.fmc.webservice.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.base.utils.ProcessLogicHelper;
import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPersonService;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVar;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVarCollection;
import com.vispractice.fmc.csb.msgheader.MsgHeader;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.SBWFStartProcessSrvRequest;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.SBWFStartProcessSrvResponse;
import com.vispractice.fmc.webservice.service.SbWFStartProcessSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Service
@Slf4j
public class StubSbWFStartProcessSrv extends AbstractStubParentSrv implements SbWFStartProcessSrv {
	/**
	 * 人员信息服务
	 */
	@Autowired
	private ISysOrgPersonService sysOrgPersonService;
	
	/**
	 * 启动流程
	 */
	@Override
	public ProcessResponse startProcess(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFStartProcessSrvResponse result = new SBWFStartProcessSrvResponse();
		String wfApplyUserNo = processRequest.getRequest().getWFAPPLYUSERNO();
		String message = "";
		
		try {
			//验证报文头部信息 
			SysBusiSys sysBusiSys = msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFStartProcessSrv");
			//验证人员信息
			SysOrgElement sysOrgElement = validateFacade.validateSysOrgElement(wfApplyUserNo);
			//验证模板信息
			validateFacade.validateWfTemplate(processRequest.getRequest().getWFTEMPLATEID());
			//验证单据号
			if (StringUtils.isEmpty(processRequest.getRequest().getWFAPPLYCODE())) {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.startProcess.applyCode",language));
			}
			
			//验证单据状态
			this.validateFacade.validateSysNewsMain(sysBusiSys.getFdId(),processRequest.getRequest().getWFAPPLYCODE());
			//验证单据主题
			if (StringUtils.isEmpty(processRequest.getRequest().getWFAPPLYSUBJECT())) {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.validate.body.subject",language));
			}
			
			//构造参数
			SbWFApprovalForm sbWFApprovalForm = buildParam(processRequest.getRequest(),sysOrgElement,sysBusiSys);
			
			//验证指定人员信息
			if (StringUtils.isNotEmpty(sbWFApprovalForm.getFutureHandlerNos())) {
				String futureHandNoStrs[] = sbWFApprovalForm.getFutureHandlerNos().split(";");
				StringBuffer futureHandIds = new StringBuffer();
				StringBuffer futureHandNos = new StringBuffer();
				StringBuffer futureHandNames = new StringBuffer();
				
				for (String futureHandNoStr : futureHandNoStrs) {
					sysOrgElement = validateFacade.validateSysOrgElement(futureHandNoStr);
					futureHandIds.append(";" + sysOrgElement.getFdId());
					futureHandNos.append(";" + sysOrgElement.getFdNo());
					futureHandNames.append(";" + sysOrgElement.getFdName());
				}
				
				sbWFApprovalForm.setFutureHandlerIds(futureHandIds.toString().substring(1));
				sbWFApprovalForm.setFutureHandlerNos(futureHandNos.toString().substring(1));
				sbWFApprovalForm.setFutureHandlerNames(futureHandNames.toString().substring(1));
			}
			
			//启动流程实例
			String wfInstanceId = processFacade.sbWFStartProcessSrv(sbWFApprovalForm);
				
			if (StringUtils.isNotEmpty(wfInstanceId)) {
				result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
				result.setWFINSTANCEID(wfInstanceId);
				message = resourceUtil.getLocaleMessage("ws.interface.startProcess.OK",language);
				result.setSERVICEMESSAGE(message);
				
				log.info(message + "{" + wfInstanceId + "}");
			} else {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.startProcess.error",language));
			}
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setWFINSTANCEID("");
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
			
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFStartProcessSrv",StubSbWFStartProcessSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
				
		return response;
	}
	
	/**
	 * 构造参数信息
	 */
	private SbWFApprovalForm buildParam(SBWFStartProcessSrvRequest request,SysOrgElement sysOrgElement,SysBusiSys sysBusiSys) throws WorkflowException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			SbWFApprovalForm sbWFApprovalForm = new SbWFApprovalForm();
			SysNewsMain sysNewsMain = new SysNewsMain();
			MsgHeader msgHeader = request.getMsgHeader();
			
			WfProcessVarCollection wfProcessVarCollection = request.getWFPROCESSVARCOLLECTION();
			List<WfProcessVar> wfProcessVars = wfProcessVarCollection != null ? wfProcessVarCollection.getWFPROCESSVAR() : new ArrayList<WfProcessVar>();
			Map<String,Object> processVal = new HashMap<String,Object>();
			for(WfProcessVar wfProcessVar : wfProcessVars){
				processVal.put(wfProcessVar.getWFVARNAME(),wfProcessVar.getWFVARVALUE());
				
				if ("futureNodeId".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setFutureNodeId(wfProcessVar.getWFVARVALUE());
				}
				
				if ("handlerNos".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setFutureHandlerNos(wfProcessVar.getWFVARVALUE());
				}
				
				if ("handlerNames".equals(wfProcessVar.getWFVARNAME())) {
					sbWFApprovalForm.setFutureHandlerNames(wfProcessVar.getWFVARVALUE());
				}
			}
			
			sysNewsMain.setFdNewsSource(msgHeader.getSOURCESYSTEMID());
			sysNewsMain.setFdDescription(request.getWFDESCRIPTION());
			sysNewsMain.setFdTemplateId(request.getWFTEMPLATEID());
			sysNewsMain.setApplyCode(request.getWFAPPLYCODE());
			sysNewsMain.setDocSubject(request.getWFAPPLYSUBJECT());
			sysNewsMain.setApplyDescrption(request.getWFAPPLYCONTENT());
			
			try {
				sysNewsMain.setApplyDate(new Date(sdf.parse(request.getWFAPPLYDATE().toXMLFormat()).getTime()));
			} catch (Exception e) {
				sysNewsMain.setApplyDate(new Date(System.currentTimeMillis()));
			}
			
			sysNewsMain.setFdImportance(Long.parseLong(StringUtils.isEmpty(request.getURGENTLEVEL())?"1":request.getURGENTLEVEL()));
			sysNewsMain.setProcessVarXml(ProcessLogicHelper.objectXmlEncoder(processVal));
			sysNewsMain.setDocCreateTime(new Date(System.currentTimeMillis()));
			sysNewsMain.setDocCreatorId(sysOrgElement.getFdId());
			sysNewsMain.setFdSummary(request.getWFAPPLYREASON());
			sysNewsMain.setFdLinkUrl(request.getLINKURL());
			sysNewsMain.setBusiSysId(sysBusiSys.getFdId());
			
			sysNewsMain.setAttr1((String) processVal.get("__EXT_ATTR1"));
			sysNewsMain.setAttr2((String) processVal.get("__EXT_ATTR2"));
			sysNewsMain.setAttr3((String) processVal.get("__EXT_ATTR3"));
			sysNewsMain.setAttr4((String) processVal.get("__EXT_ATTR4"));
			sysNewsMain.setAttr5((String) processVal.get("__EXT_ATTR5"));
			sysNewsMain.setAttr6((String) processVal.get("__EXT_ATTR6"));
			sysNewsMain.setAttr7((String) processVal.get("__EXT_ATTR7"));
			sysNewsMain.setAttr8((String) processVal.get("__EXT_ATTR8"));
			sysNewsMain.setAttr9((String) processVal.get("__EXT_ATTR9"));
			sysNewsMain.setAttr10((String) processVal.get("__EXT_ATTR10"));
			sysNewsMain.setAttr11((String) processVal.get("__EXT_ATTR11"));
			sysNewsMain.setAttr12((String) processVal.get("__EXT_ATTR12"));
			sysNewsMain.setAttr13((String) processVal.get("__EXT_ATTR13"));
			sysNewsMain.setAttr14((String) processVal.get("__EXT_ATTR14"));
			sysNewsMain.setAttr15((String) processVal.get("__EXT_ATTR15"));
			sysNewsMain.setAttr16((String) processVal.get("__EXT_ATTR16"));
			sysNewsMain.setAttr17((String) processVal.get("__EXT_ATTR17"));
			sysNewsMain.setAttr18((String) processVal.get("__EXT_ATTR18"));
			sysNewsMain.setAttr19((String) processVal.get("__EXT_ATTR19"));
			sysNewsMain.setAttr20((String) processVal.get("__EXT_ATTR20"));
			
			sbWFApprovalForm.setSysNewsMain(sysNewsMain);
			
			return sbWFApprovalForm;
		} catch (Exception e) {
			throw new WorkflowException(e.getMessage());
		}
	}
}
