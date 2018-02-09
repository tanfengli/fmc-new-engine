package com.vispractice.fmc.webservice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.csb.sb_wf_getnodessrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_getnodessrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_getnodessrv.SBWFGetNodesSrvResponse;
import com.vispractice.fmc.csb.sb_wf_getnodessrv.SBWFNodeCollection;
import com.vispractice.fmc.csb.sb_wf_getnodessrv.WfOPItem;
import com.vispractice.fmc.webservice.service.SbWFGetNodesSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFGetNodesSrv extends AbstractStubParentSrv implements SbWFGetNodesSrv {
	/**
	 * 查看流程模板所有节点信息
	 */
	@Override
	public ProcessResponse sbWFGetNodesSrv(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFGetNodesSrvResponse result = new SBWFGetNodesSrvResponse();
		SBWFNodeCollection sbWFNodeCollection = new SBWFNodeCollection();
		List<WfOPItem> wfOPItems = sbWFNodeCollection.getWFOPITEM();
		String templateId = processRequest.getRequest().getWFTEMPLATEID();
		String message = "";
		
		try {
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFGetNodesSrv");
			validateFacade.validateWfTemplate(templateId);
			
			Map<String,Map<String,String>> nodes = processFacade.sbWFGetNodes(templateId);
			
			if (nodes != null && nodes.size() > 0) {
				this.copyProperty(nodes,wfOPItems);
				result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
				result.setSBWFNODECOLLECTION(sbWFNodeCollection);
				message = resourceUtil.getLocaleMessage("ws.interface.getNodes.OK",language);
				result.setSERVICEMESSAGE(message);
				log.info(message + JSonUtils.jsonToString(sbWFNodeCollection));
			} else {
				throw new WorkflowException(resourceUtil.getLocaleMessage("ws.interface.getNodes.error",language));
			}
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSBWFNODECOLLECTION(sbWFNodeCollection);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Date fdEndTime = new Date(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFGetNodesSrv",StubSbWFGetNodesSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());

		return response;
	}

	/**
	 * 构造反馈对象属性
	 */
	private void copyProperty(Map<String,Map<String,String>> nodes,List<WfOPItem> wfOPItems) throws WorkflowException {
		 Set<String> keys = nodes.keySet();
		 
		 for (String key : keys) {
			 Map<String,String> node = nodes.get(key);
			 WfOPItem wfOPItem = new WfOPItem();
			 wfOPItem.setWFNODEID(node.get("nodeId"));
			 wfOPItem.setWFNODENAME(node.get("nodeName"));
			 wfOPItem.setWFNODETYPE(node.get("nodeType"));
			 if (StringUtils.isEmpty(node.get("bizInfoType"))) {
				 wfOPItem.setWFBIZINFOTYPE("");
			 } else {
				 wfOPItem.setWFBIZINFOTYPE(node.get("bizInfoType")); 
			 }
			 
			 wfOPItems.add(wfOPItem);
		 }
	}
}
