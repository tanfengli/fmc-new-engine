package com.vispractice.fmc.webservice.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfOperations;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfOperationsService;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.SBWFCreateOperItemCollection;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.SBWFHandlerOperItemCollection;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.SBWFLASTNodeCollection;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.SBWFNEXTNodeCollection;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.SBWFNextNodesSrvResponse;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.SBWFNodeItem;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.SBWFOperItem;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.SBWFVariableItem;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.SBWFVariableItemCollection;
import com.vispractice.fmc.webservice.service.SbWNextNodesSrv;
import com.vispractice.fmc.webservice.util.JSonUtils;
import com.vispractice.fmc.webservice.util.WSMessageResult;

@Slf4j
@Service
public class StubSbWFNextNodesSrv extends AbstractStubParentSrv implements SbWNextNodesSrv {
	@Autowired
	private ISysWfOperationsService sysWfOperationsService;

	/**
	 * 查询下一审批节点服务
	 */
	@Override
	public ProcessResponse sbWFNextNodesSrv(ProcessRequest processRequest) {
		Date fdStartTime = new Date(System.currentTimeMillis());
		log.info(resourceUtil.getLocaleMessage("ws.enter.info",language) + JSonUtils.jsonToString(processRequest.getRequest()));
		
		ProcessResponse response = new ProcessResponse();
		SBWFNextNodesSrvResponse result = new SBWFNextNodesSrvResponse();
		SBWFLASTNodeCollection sbWFLASTNodeCollection = new SBWFLASTNodeCollection();
		SBWFNEXTNodeCollection sbWFNEXTNodeCollection = new SBWFNEXTNodeCollection();
		List<SBWFNodeItem> sbWfLastNodeItems = sbWFLASTNodeCollection.getWFOPITEM();
		List<SBWFNodeItem> sbWfNextNodeItems = sbWFNEXTNodeCollection.getWFOPITEM();
		
		String wfTemplateId = processRequest.getRequest().getWFTEMPLATEID();
		String wfNodeId = processRequest.getRequest().getWFNODEID();
		String message = "";
		
		try {
			msgHeaderSrv.validateSysData(processRequest.getRequest().getMsgHeader(),"SbWFNextNodesSrv");
			validateFacade.validateWfTemplate(wfTemplateId);
			
			Map<String,Map<String,Map<String,Object>>> nodes = processFacade.sbWFLastAndNextNodes(wfTemplateId,wfNodeId);
			this.copyProperty(nodes.get("last"),sbWfLastNodeItems);
			this.copyProperty(nodes.get("next"),sbWfNextNodeItems);
			
			result.setSERVICEFLAG(WSMessageResult.FLAG_Y);
			result.setSBWFLASTNodeCollection(sbWFLASTNodeCollection);
			result.setSBWFNEXTNodeCollection(sbWFNEXTNodeCollection);
			message = resourceUtil.getLocaleMessage("ws.interface.nextNodes.OK",language);
			result.setSERVICEMESSAGE(message);
			log.info(message + JSonUtils.jsonToString(sbWFNEXTNodeCollection));
		} catch (Exception e) {
			result.setSERVICEFLAG(WSMessageResult.FLAG_N);
			result.setSBWFLASTNodeCollection(sbWFLASTNodeCollection);
			result.setSBWFNEXTNodeCollection(sbWFNEXTNodeCollection);
			result.setSERVICEMESSAGE(e.getMessage());
			log.error(e.getMessage());
		}
		response.setReturn(result);
		
		//保存日志信息
		Timestamp fdEndTime = new Timestamp(System.currentTimeMillis());
		this.saveWebServiceLog("SbWFNextNodesSrv",StubSbWFNextNodesSrv.class.getName(),
				processRequest.getRequest().getMsgHeader(),fdStartTime,fdEndTime,
				result.getSERVICEFLAG(),result.getSERVICEMESSAGE());
		
		return response;
	}

	/**
	 * 构造反馈对象属性
	 */
	@SuppressWarnings("unchecked")
	private void copyProperty(Map<String,Map<String,Object>> nodes,List<SBWFNodeItem> sbWfNodeItems) throws WorkflowException {
		Set<String> keys = nodes.keySet();
		for (String key : keys) {
			Map<String,Object> nextNode = nodes.get(key);
			SBWFNodeItem sbWFNodeItem = new SBWFNodeItem();
			SBWFVariableItemCollection sbWFVariableItemCollection = new SBWFVariableItemCollection();
			SBWFHandlerOperItemCollection sbWFHandlerOperItemCollection = new SBWFHandlerOperItemCollection();
			SBWFCreateOperItemCollection sbWFCreateOperItemCollection = new SBWFCreateOperItemCollection();
			
			sbWFNodeItem.setNodeId(String.valueOf(nextNode.get("nodeId")));
			sbWFNodeItem.setNodeName(String.valueOf(nextNode.get("nodeName")));
			sbWFNodeItem.setNodeType(String.valueOf(nextNode.get("nodeType")));
			sbWFNodeItem.setBizInfoType(String.valueOf(nextNode.get("bizInfoType"))); 
			
			List<Map<String,String>> defineVariants = (List<Map<String,String>>)nextNode.get("defineVariants");
			if (defineVariants != null) {
				for (Map<String,String> defineVariant : defineVariants) {
					SBWFVariableItem sbWFVariableItem = new SBWFVariableItem();
					sbWFVariableItem.setName(defineVariant.get("name"));
					sbWFVariableItem.setValue(defineVariant.get("value"));
					sbWFVariableItemCollection.getWFOPITEM().add(sbWFVariableItem);
				}
			}
			sbWFNodeItem.setSBWFVariableItemCollection(sbWFVariableItemCollection);
			
			String refId = String.valueOf(nextNode.get("refId"));
			if (StringUtils.isEmpty(refId)) {
				List<Map<String,String>> drafterOperators = (List<Map<String,String>>)nextNode.get("drafterOperators");
				List<Map<String,String>> handlerOperators = (List<Map<String,String>>)nextNode.get("handlerOperators");
				
				if (drafterOperators != null) {
					for (Map<String,String> drafterOperator : drafterOperators) {
						SBWFOperItem sbWFOperItem = new SBWFOperItem();
						sbWFOperItem.setName(drafterOperator.get("name"));
						sbWFOperItem.setType(drafterOperator.get("type"));
						sbWFCreateOperItemCollection.getWFOPITEM().add(sbWFOperItem);
					}
				}
				
				if (handlerOperators != null) {
					for (Map<String,String> handlerOperator : handlerOperators) {
						SBWFOperItem sbWFOperItem = new SBWFOperItem();
						sbWFOperItem.setName(handlerOperator.get("name"));
						sbWFOperItem.setType(handlerOperator.get("type"));
						sbWFHandlerOperItemCollection.getWFOPITEM().add(sbWFOperItem);
					}
				}
			} else {
				List<SysWfOperations> sysWfOperations = sysWfOperationsService.findByFdOperatorId(refId);
				for (SysWfOperations sysWfOperation : sysWfOperations) {
					SBWFOperItem sbWFOperItem = new SBWFOperItem();
					sbWFOperItem.setName(sysWfOperation.getFdOperName());
					sbWFOperItem.setType(sysWfOperation.getFdOperType().toString());
					
					if (sysWfOperation.getFdOperType().intValue() < OAConstant.DRAFT_ROLE) {
						sbWFHandlerOperItemCollection.getWFOPITEM().add(sbWFOperItem);
					} else {
						sbWFCreateOperItemCollection.getWFOPITEM().add(sbWFOperItem);
					}
				}
			}
			sbWFNodeItem.setSBWFCreateOperItemCollection(sbWFCreateOperItemCollection);
			sbWFNodeItem.setSBWFHandlerOperItemCollection(sbWFHandlerOperItemCollection);
			 
			sbWfNodeItems.add(sbWFNodeItem);
		}
	}
}
