package com.vispractice.fmc.webservice.service.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorize;
import com.vispractice.fmc.csb.msgheader.ExtendParam;
import com.vispractice.fmc.csb.msgheader.ExtendParamCollection;
import com.vispractice.fmc.csb.msgheader.MsgHeader;
import com.vispractice.fmc.csb.sb_wf_authorizesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_authorizesrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_authorizesrv.SBWFAuthorizeSrvRequest;
import com.vispractice.fmc.csb.sb_wf_authorizesrv.SBWFAuthorizeSrvResponse;
import com.vispractice.fmc.csb.sb_wf_authorizesrv.WfAuthorizeExtendCollection;
import com.vispractice.fmc.csb.sb_wf_authorizesrv.WfAuthorizeExtendItem;
import com.vispractice.fmc.csb.sb_wf_authorizesrv.WfAuthorizeScopeCollection;
import com.vispractice.fmc.webservice.service.SbWFAuthorizeSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.TypeTranUtil;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFAuthorizeSrv extends AbstractStubParentSrv implements SbWFAuthorizeSrv {
	/**
	 * 流程授权接口服务
	 */
	@Override
	public ProcessResponse sbWFAuthorize(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFAuthorizeSrvResponse result = new SBWFAuthorizeSrvResponse();
		String message= "";
		
		// 头部信息
		MsgHeader msgHeader = processRequest.getRequest().getMsgHeader();
		// 头部扩展信息
		ExtendParamCollection extendParamCollection = msgHeader.getEXTENDPARAMCOLLECTION();
		String wfAuthorizeIds = processRequest.getRequest().getWFAUTHORIZEID();
		
		try {
			msgHeaderSrv.validateSysData(msgHeader,"SbWFAuthorizeSrv");
			if (extendParamCollection != null) {
				List<String> deleteParams = buildDeleteParams(extendParamCollection.getEXTENDPARAM());
				processFacade.sbWFRevokeAuthorizeSrv(deleteParams);
			} else {
				if (StringUtils.isNotEmpty(wfAuthorizeIds)) {
					validateFacade.validateSbWFAuthority(wfAuthorizeIds);
				}
				
				Map<String,Object> saveParam = buildSaveParams(processRequest.getRequest());
				wfAuthorizeIds = processFacade.sbWFAuthorizeSrv(saveParam);
			}
			
			message = resourceUtil.getLocaleMessage("ws.interface.authorize.OK",language);
			result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
			result.setWFAUTHORIZEID(wfAuthorizeIds);
			result.setSERVICEMESSAGE(message);
			log.info(message + "{" + wfAuthorizeIds + "}");
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSERVICEMESSAGE(e.getMessage());
			result.setWFAUTHORIZEID(wfAuthorizeIds);
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFAuthorizeSrv",StubSbWFAuthorizeSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}
	
	/**
	 * 构造回收权限参数
	 */
	private List<String> buildDeleteParams(List<ExtendParam> extendParams) throws WorkflowException {
		List<String> result = new ArrayList<String>();
		
		if (extendParams != null && extendParams.size() < 2) {
			throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.authorize.revoke",language));
		} else {
			String isDeleteName = extendParams.get(0).getPARAMNAME();
			String isDeleteValue = extendParams.get(0).getPARAMVALUE();
			if (isDeleteName != null && isDeleteName.equals("isDel") && isDeleteValue != null && isDeleteValue.equals("Y")) {
				String deleteStr = extendParams.get(1).getPARAMVALUE();
				if (StringUtils.isEmpty(deleteStr)) {
					throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.authorize.revoke",language));
				} else {
					String[] deletes = deleteStr.split(",");
					for (String delete : deletes) {
						String[] wfAuthorizeIds = delete.split(";");
						for (String wfAuthorizeId : wfAuthorizeIds) {
							result.add(wfAuthorizeId);
						}
					}
				}
			} else {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.authorize.revoke",language));
			}
		}
		
		return result;
	}
	
	/**
	 * 构造授权参数
	 */
	private Map<String,Object> buildSaveParams(SBWFAuthorizeSrvRequest request) throws WorkflowException {
		Map<String,Object> result = new HashMap<String,Object>();
		//2017-08-28T13:00:00
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		String wfAuthorizeIds = request.getWFAUTHORIZEID();
		
		//授权人编号
		String wfAuthorizerNo = request.getWFAUTHORIZERNO();
		SysOrgElement wfAuthorizer = validateFacade.validateSysOrgElement(wfAuthorizerNo);
			
		//被授权人编号
		String wfAuthorizedNo = request.getWFAUTHORIZEDPERSONNO();
		SysOrgElement wfAuthorized = validateFacade.validateSysOrgElement(wfAuthorizedNo);

		//是否到期删除文档
		BigInteger wfIsExpireDeleted = request.getWFISEXPIREDELETED();
		//授权类型	
//		String wfAuthorizeType = request.getWFAUTHORIZETYPE();
		//授权范围
		WfAuthorizeScopeCollection wfAuthorizeScopeCollection = request.getWFAUTHORIZESCOPECOLLECTION();
		WfAuthorizeExtendCollection WfAuthorizeExtendCollection = request.getWFAUTHORIZEEXTENDCOLLECTION();
		
		if (StringUtils.isEmpty(wfAuthorizeIds)) {
			result.put("wfAuthorizeIds","");
		} else {
			result.put("wfAuthorizeIds",wfAuthorizeIds);
		}
		
		result.put("fdAuthorizer",wfAuthorizer.getFdId());
		result.put("fdAuthorizedPerson",wfAuthorized.getFdId());
//		result.put("fdAuthorizeType",wfAuthorizeType);
//		if (wfAuthorizeType.equals(SysWfAuthorize.AUTHORIZE_READING)) {
//			result.put("fdStartTime",null);
//			result.put("fdEndTime",null);
//			result.put("fdExpireDeleted",0);
//		} else {
//			//授权开始日期
//			String wfStartTime = request.getWFSTARTTIME().toString();
//			try {
//				sdf.parse(wfStartTime);
//			} catch (Exception e) {
//				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.authorize.startTime",language));
//			}
//				
//			//授权结束日期
//			String wfEndTime = request.getWFENDTIME().toXMLFormat();
//			try {
//				sdf.parse(wfEndTime);
//			} catch (Exception e) {
//				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.authorize.endTime",language));
//			}
//			
//			result.put("fdStartTime",TypeTranUtil.xmlToDate(request.getWFSTARTTIME()));
//			result.put("fdEndTime",TypeTranUtil.xmlToDate(request.getWFENDTIME()));
//			result.put("fdExpireDeleted",wfIsExpireDeleted);
//		}
			
		if (wfAuthorizeScopeCollection != null) {
			List<String> wfTemplateIds = wfAuthorizeScopeCollection.getWFTEMPLATEID();
			for (String wfTemplateId : wfTemplateIds) {
				validateFacade.validateWfTemplate(wfTemplateId);
			}
			result.put("wfTemplateIds",wfTemplateIds);
		}
			
		if (WfAuthorizeExtendCollection != null) {
			List<WfAuthorizeExtendItem> wfAuthorizeExtendItems = WfAuthorizeExtendCollection.getWFAUTHORIZEEXTENDITEM();
			List<Map<String,String>> wfAuthorizeExtends = new ArrayList<Map<String,String>>();
			for (WfAuthorizeExtendItem wfAuthorizeExtendItem : wfAuthorizeExtendItems) {
				Map<String,String> wfAuthorizeExtend = new HashMap<String,String>();
				wfAuthorizeExtend.put("fdBoeType",wfAuthorizeExtendItem.getFDBOETYPE());
				wfAuthorizeExtend.put("fdBussType",wfAuthorizeExtendItem.getFDBUSSTYPE());
				wfAuthorizeExtend.put("fdDeptId",wfAuthorizeExtendItem.getFDDEPTID());
				wfAuthorizeExtend.put("minAmount",wfAuthorizeExtendItem.getMINAMOUNT());
				wfAuthorizeExtend.put("maxAmount",wfAuthorizeExtendItem.getMAXAMOUNT());
				wfAuthorizeExtends.add(wfAuthorizeExtend);
			}
			
			result.put("wfAuthorizeExtends",wfAuthorizeExtends);
		}
				
		return result;
	}
}
