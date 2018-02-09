package com.vispractice.fmc.business.base.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.betwixt.XMLIntrospector;
import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.xml.sax.InputSource;
import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.OANodeType;
import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ProcessDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ResultDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.parameter.WorkitemParameter;

@Slf4j
public class ProcessLogicHelper {
	/**
	 * 生成流程实例SYS_WF_PROCESS FD_DESCRIPTOR表字段
	 */
	private static String WORKFLOW_DESCRIPTOR_NODE = "<process />";

	/**
	 * 生成流程实例SYS_WF_PROCESS表FD_PARAMETER字段
	 */
	private static String WORKFLOW_PARAMETER_NODE = "<process-param />";

	/**
	 * 生成流程参数明细
	 * @param fdDescriptor
	 * @return
	 * @throws WorkflowException
	 */
	@SuppressWarnings("unchecked")
	public static String getProcessDescriptorXML(String processDetail) throws WorkflowException {
		String processDescriptorXML = WORKFLOW_DESCRIPTOR_NODE;

		if (StringUtil.hasLength(processDetail)) {
			Document detailDocument;
			StringWriter sWriter = new StringWriter();
			try {
				detailDocument = DocumentHelper.parseText(processDetail);
				Element detailRoot = detailDocument.getRootElement();
				ProcessDescriptor processDescriptor = new ProcessDescriptor();

				List<Element> nodeList = detailRoot.selectNodes("/process/nodes/*");
				List<Element> lineList = detailRoot.selectNodes("/process/lines/*");
				for (int i = 0; i < nodeList.size(); i++) {
					Element detailNode = nodeList.get(i);
					NodeDescriptor descNode = new NodeDescriptor();

					//生成节点信息
					switch (detailNode.getName()) {
					//开始节点
					case "startNode": 
						descNode.setParent("oaInitNode");
						processDescriptor.setInitialNodeDescriptor(descNode);
						break;
					//结束节点
					case "endNode": 
						descNode.setParent("oaFinalNode");
						processDescriptor.setFinallyNodeDescriptor(descNode);
						break;
					//起草节点
					//审批节点
					//条件分支
					//人工决策
					//签字节点
					//并行分支
					//并行分支
					//抄送节点
					//机器人节点
					default:
						descNode.setParent("oa" + detailNode.getName().substring(0, 1).toUpperCase()
								+ detailNode.getName().substring(1));
						processDescriptor.addNodeDescriptor(descNode);
						break;
					}

					if (null != descNode) {
						descNode.setId(detailNode.attributeValue("id"));
						descNode.setName(detailNode.attributeValue("name"));

						for (int j = 0; j < lineList.size(); j++) {
							Element line = lineList.get(j);
							//增加出线信息
							if (descNode.getId().equals(line.attributeValue("startNodeId"))) {
								ResultDescriptor resultDescriptor = new ResultDescriptor();
								if (null != line.attributeValue("priority")) {
									resultDescriptor.setPriority(Integer.valueOf(line.attributeValue("priority")));
								}
								resultDescriptor.setCondition(line.attributeValue("condition"));
								resultDescriptor.setTarget(line.attributeValue("endNodeId"));
								descNode.addResultDescriptor(resultDescriptor);
							}
						}
					}
				}

				if (null != processDescriptor) {
					BeanWriter beanWriter = new BeanWriter(sWriter);
					XMLIntrospector introspector = beanWriter.getXMLIntrospector();
					introspector.getConfiguration().setAttributesForPrimitives(true);

					introspector.register(
							new InputSource(processDescriptor.getClass().getResourceAsStream("betwixt-config.xml")));
					beanWriter.getBindingConfiguration().setMapIDs(false);
					beanWriter.enablePrettyPrint();
					beanWriter.write(processDescriptor);
					processDescriptorXML = sWriter.toString();
				}
			} catch (Exception e) {
				throw new WorkflowException("生成流程描述错误", e);
			}
		}

		return processDescriptorXML;
	}

	/**
	 * 实现流程:通过节点获取节点属性
	 * @param processDetail 流程图XML
	 * @param nodeId 节点
	 * @return 节点属性Map<String,String>
	 * @throws WorkflowException
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public static Map<String,String> getNodeAttrByNodeId(String processDetail,String nodeId) throws WorkflowException {
		Map<String,String> nodeAttrMap = new HashMap<String, String>();
		if (StringUtil.hasLength(processDetail)) {
			Document detailDocument;
			try {
				detailDocument = DocumentHelper.parseText(processDetail);
				Element detailRoot = detailDocument.getRootElement();

				// 获取所有节点
				List<Element> nodeList = detailRoot.selectNodes("/process/nodes/*");

				// 将节点属性映射到map
				for (Element node : nodeList) {
					List nodeAttrs = node.attributes();
					// 获取节点id
					String nodeIdAttrVal = node.attributeValue("id");
					// 遍历节点属性
					if (null != nodeIdAttrVal && nodeIdAttrVal.equals(nodeId)) {
						for (int i = 0; i < nodeAttrs.size(); i++) {
							Attribute nodeAttr = (Attribute) nodeAttrs.get(i);
							nodeAttrMap.put(nodeAttr.getName(), nodeAttr.getValue());
						}
						break;
					}
				}

				return nodeAttrMap;
			} catch (DocumentException e) {
				throw new WorkflowException("通过节点Id获取节点属性 时发生错误：" + e.getMessage());
			}
		}

		return nodeAttrMap;
	}

	/**
	 * 依据流程定义与节点查找节点信息
	 * @param processDetail
	 * @param nodeId
	 * @return
	 * @throws WorkflowException
	 */
	@SuppressWarnings("unchecked")
	public static NodeDescriptor getNodeDescriptor(String processDetail,String nodeId) throws WorkflowException {
		Document detailDocument;
		NodeDescriptor nodeDesc = new NodeDescriptor();
		try {
			detailDocument = DocumentHelper.parseText(processDetail);
			List<Element> nodeList = detailDocument.selectNodes("/process/nodes/*");
			List<Element> finalNode = detailDocument.selectNodes("/process/finally-node");
			
			for (int i = 0; i < nodeList.size(); i++) {
				Element detailNode = nodeList.get(i);
				if (detailNode.attributeValue("id").equals(nodeId)) {
					nodeDesc.setId(detailNode.attributeValue("id"));
					nodeDesc.setParent(detailNode.attributeValue("parent"));
					nodeDesc.setName(detailNode.attributeValue("name"));
				}
			}
			//新增结束节点
			for (int i = 0; i < finalNode.size(); i++) {
				Element detailNode = finalNode.get(i);
				if (detailNode.attributeValue("id").equals(nodeId)) {
					nodeDesc.setId(detailNode.attributeValue("id"));
					nodeDesc.setParent(detailNode.attributeValue("parent"));
					nodeDesc.setName(detailNode.attributeValue("name"));
				}
			}
		} catch (Exception e) {
			throw new WorkflowException("获取流程节点描述错误", e);
		}
		return nodeDesc;
	}

	/**
	 * 实现流程:获取流程图XML的process节点属性
	 * @param processDetail 流程图XML
	 * @return process节点属性Map
	 * @throws WorkflowException
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String,String> getProcessAttr(String processDetail) throws WorkflowException {
		try {
			Document doc = DocumentHelper.parseText(processDetail);
			Element root = doc.getRootElement();
			List processAttrs = root.attributes();
			Map<String, String> nodeMap = null;
			//将节点属性遍历
			for (int i = 0; i < processAttrs.size(); i++) {
				Attribute nodeAttr = (Attribute) processAttrs.get(i);
				nodeMap = new HashMap<String, String>();
				nodeMap.put(nodeAttr.getName(), nodeAttr.getValue());
			}
		} catch (DocumentException e) {
			throw new WorkflowException(e.getMessage());
		}

		return null;
	}

	/**
	 * 获取通知天数
	 * @param processDetail
	 * @param attributeName
	 * @return
	 * @throws WorkflowException
	 */
	public static Long getDayOfNotify(String processDetail, String attributeName) throws WorkflowException {
		Long dayOfNotify = null;
		try {
			Document doc = DocumentHelper.parseText(processDetail);
			Element root = doc.getRootElement();
			if (null != root.attributeValue(attributeName)) {
				dayOfNotify = Long.valueOf(root.attributeValue(attributeName));
			}
		} catch (Exception e) {
			throw new WorkflowException("流程模板获取[" + attributeName + "]参数失败.", e);
		}

		return dayOfNotify;
	}

	/**
	 * 获取SYS_WF_PROCESS的FD_PARAMETER字段
	 * @param processParam
	 * @return
	 * @throws WorkflowException
	 */
	public static String getProcessParameterXML(String processParam) throws WorkflowException {
		String processParameterXML = WORKFLOW_PARAMETER_NODE;

		if (null != processParam) {
			Document paramDocument = DocumentHelper.createDocument();
			Element paramRoot = paramDocument.addElement("process-param");
			paramRoot.addAttribute("fdWfType", "oa");

			processParameterXML = paramRoot.asXML();
		}

		return processParameterXML;
	}

	/**
	 * 获取起草节点信息
	 * 
	 * @param processDescriptor
	 * @return
	 * @throws WorkflowException
	 */
	public static Map<String, String> getDraftNode(String processDescriptor) throws WorkflowException {
		Map<String, String> result = new HashMap<String, String>();

		try {
			Document doc = DocumentHelper.parseText(processDescriptor);
			Element root = doc.getRootElement();
			Element initialNode = root.element("initial-node");
			if (null != initialNode) {
				String drafId = initialNode.attributeValue("nextTargetNodeIds");
				Element drafNode = (Element) root.selectSingleNode("/process/nodes/node[@id='" + drafId + "']");
				Element targetNode = (Element) root.selectSingleNode(
						"/process/nodes/node[@id='" + drafNode.attributeValue("nextTargetNodeIds") + "']");
				result.put("startNodeId", initialNode.attributeValue("id"));
				result.put("startNodeName", initialNode.attributeValue("name"));
				result.put("startNodeType", initialNode.attributeValue("parent"));
				result.put("fdFactNodeId", drafNode.attributeValue("id"));
				result.put("fdFactNodeName", drafNode.attributeValue("name"));
				result.put("fdNodeType", drafNode.attributeValue("parent"));
				result.put("fdTargetId", drafNode.attributeValue("nextTargetNodeIds"));
				result.put("fdTargetName", targetNode.attributeValue("name"));
			}
		} catch (DocumentException e) {
			throw new WorkflowException(e.getMessage());
		}

		return result;

	}

	/**
	 * 获取开始节点信息
	 * @param processDescriptor
	 * @return
	 * @throws WorkflowException
	 */
	public static Map<String, String> getInitialNode(String processDescriptor) throws WorkflowException {
		Map<String, String> result = new HashMap<String, String>();

		try {
			Document doc = DocumentHelper.parseText(processDescriptor);
			Element root = doc.getRootElement();
			Element initialNode = root.element("initial-node");
			if (null != initialNode) {
				result.put("fdFromNodeId", initialNode.attributeValue("id"));
				result.put("fdNodeFacatId", initialNode.attributeValue("nextTargetNodeIds"));
			} else {
				throw new WorkflowException("未找到开始节点.");
			}
		} catch (Exception e) {
			throw new WorkflowException(e.getMessage());
		}

		return result;
	}

	/**
	 * 构造节点参数
	 * @return
	 */
	public static String getNodeParam() {
		Document paramDocument = DocumentHelper.createDocument();
		Element root = paramDocument.addElement("node-param");
		//当前处理人
		root.addAttribute("currHandlerOffset", "0");
		//节点
		root.addAttribute("nodeId", "N2");
		//驳回时返回本节点的处理人的位移
		root.addAttribute("toRefuseThisHandlerOffset", "0");
		//驳回时返回本节点的节点类型
		root.addAttribute("toRefuseThisProcessType", "0");

		return root.asXML();
	}

	/**
	 * 转换XML信息
	 * @param map
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static String objectXmlEncoder(Map<String,Object> map) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLEncoder encoder = new XMLEncoder(baos);
		encoder.writeObject(map);
		encoder.flush();
		encoder.close();
		String rtnVal = new String(baos.toByteArray(), "UTF-8");
		baos.close();

		return rtnVal;
	}

	/**
	 * 将String转换成XML
	 * @param xmlString
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public static List objectXmlDecoder(String xmlString) throws IOException {
		List objList = new ArrayList();
		InputStream ins = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
		XMLDecoder decoder = new XMLDecoder(ins);
		Object obj = null;
		try {
			while ((obj = decoder.readObject()) != null) {
				objList.add(obj);
			}
		} catch (Exception e) {
		}
		ins.close();
		decoder.close();

		return objList;
	}

	/**
	 * 生成工作项参数
	 * @param node
	 * @param operation_type
	 * @param processDetail
	 * @return
	 * @throws WorkflowException
	 */
	public static WorkitemParameter initWorkitemParameters(SysWfNode node, int operation_type, String processDetail) throws WorkflowException {
		WorkitemParameter parameter = new WorkitemParameter();
		parameter.setNodeId(node.getFdFactNodeId());

		try {
			Document detailDocument = DocumentHelper.parseText(processDetail);
			Element detailRoot = detailDocument.getRootElement();
			parameter.setNotifyOnFinish(detailRoot.attributeValue("notifyDraftOnFinish"));
			parameter.setNotifyType(detailRoot.attributeValue("notifyType"));
			parameter.setOprNames(operation_type + ":%sysWfOperations.fdOperType.draft.submit%");

			return parameter;
		} catch (Exception e) {
			throw new WorkflowException("生成workitem参数失败.", e);
		}
	}

	/**
	 * 实现流程:获取处理人审批工作事项参数
	 * @param sbWFApprovalForm审批
	 * @param fdParameter原workItem参数
	 * @return WorkitemParameter的bean
	 */
	public static WorkitemParameter getHandlerWorkitemParameters(SbWFApprovalForm sbWFApprovalForm,String fdParameter) {
		WorkitemParameter parameter = new WorkitemParameter();

		try {
			//原工作事项参数保存
			Document detailDocument = DocumentHelper.parseText(fdParameter);
			Element paramRoot = detailDocument.getRootElement();
			parameter.setCurrHandlerOffset(Integer.parseInt(paramRoot.attributeValue("currHandlerOffset")));
			parameter.setNotifyOnFinish(paramRoot.attributeValue("notifyOnFinish"));
			parameter.setNotifyType(paramRoot.attributeValue("notifyType"));
			parameter.setOprNames(paramRoot.attributeValue("oprNames"));
			parameter.setWorkitemId(paramRoot.attributeValue("workitemId"));
			
			//来自页面的参数
			parameter.setHandlerIdentity(String.valueOf(OAConstant.HANDLER_IDENTITY_HANDLER));
			//审批意见
			parameter.setAuditNode(sbWFApprovalForm.getWfOptionCon());
			
			String operationName = null;
			int operationType = Integer.valueOf(sbWFApprovalForm.getWfResult());
			switch(operationType){
				case OAConstant.HANDLER_OPERATION_TYPE_ABANDON:
				operationName="废弃";
				break;
				case OAConstant.HANDLER_OPERATION_TYPE_COMMISSION:
					operationName="转办";
					//转办人标示和名称
					parameter.setToOtherHandlerIds(sbWFApprovalForm.getToOtherHandlerIds());
					parameter.setToOtherHandlerNames(sbWFApprovalForm.getToOtherHandlerNames());
					break;
				case OAConstant.HANDLER_OPERATION_TYPE_COMMUNICATE:
					operationName="沟通";
					//设置沟通人员
					parameter.setToOtherHandlerIds(sbWFApprovalForm.getToOtherHandlerIds());
					parameter.setToOtherHandlerNames(sbWFApprovalForm.getToOtherHandlerNames());
					if (StringUtils.isEmpty(paramRoot.attributeValue("relationWorkitemId"))) {
						//是否隐藏审批意见
						parameter.setIsHiddenAuditNote(StringUtils.isEmpty(sbWFApprovalForm.getIsHiddenAuditNote())?"false":sbWFApprovalForm.getIsHiddenAuditNote());
						//是否允许多级沟通
						parameter.setIsMutiCommunicate(StringUtils.isEmpty(sbWFApprovalForm.getIsMutiCommunicate())?"false":sbWFApprovalForm.getIsMutiCommunicate());
					}
					//限制子级沟通范围人标示和名称
					parameter.setCommunicateScopeHandlerIds(sbWFApprovalForm.getCommunicateScopeHandlerIds());
					parameter.setCommunicateScopeHandlerNames(sbWFApprovalForm.getCommunicateScopeHandlerNames());
					parameter.setFdIdentityFlag(paramRoot.attributeValue("fdIdentityFlag"));
					
					//多级沟通
					if (StringUtils.isNotEmpty(paramRoot.attributeValue("relationWorkitemId"))) {
						parameter.setRelationWorkitemId(paramRoot.attributeValue("relationWorkitemId"));
						parameter.setIsHiddenAuditNote(paramRoot.attributeValue("isHiddenAuditNote"));
					}
					
					break;
				case OAConstant.HANDLER_OPERATION_TYPE_RTNCOMMUNICATE:
					operationName="回复";
					parameter.setRelationWorkitemId(paramRoot.attributeValue("relationWorkitemId"));
					//是否隐藏审批意见
					parameter.setIsHiddenAuditNote(paramRoot.attributeValue("isHiddenAuditNote"));
					
					break;
				case OAConstant.HANDLER_OPERATION_TYPE_CELCOMMUNICATE:
					operationName="取消沟通";
					parameter.setFdIdentityFlag(paramRoot.attributeValue("fdIdentityFlag"));
					parameter.setIsHiddenAuditNote(paramRoot.attributeValue("isHiddenAuditNote"));
					parameter.setIsMutiCommunicate(paramRoot.attributeValue("isMutiCommunicate"));
					parameter.setToOtherHandlerIds(sbWFApprovalForm.getToOtherHandlerIds());
					parameter.setToOtherHandlerNames(sbWFApprovalForm.getToOtherHandlerNames());
					parameter.setCelRelationWorkitemIds(sbWFApprovalForm.getCelRelationWorkitemIds());
					break;
				case OAConstant.HANDLER_OPERATION_TYPE_PASS:
					operationName = "通过";
					if (sbWFApprovalForm.getFutureNodeId() != null) {
						parameter.setFutureNodeId(sbWFApprovalForm.getFutureNodeId());
						parameter.setJumpToNodeId(sbWFApprovalForm.getFutureNodeId());
					}
					
					break;
				case OAConstant.HANDLER_OPERATION_TYPE_REFUSE:
					operationName="驳回";
					//驳回节点标示
					parameter.setJumpToNodeId(sbWFApprovalForm.getFutureNodeId() + ":15cf8186b5d49d9090501cb4e1a91e1a");
					//驳回后返回本节点
					if (sbWFApprovalForm.isRefusePassedToThisNode()) {
						parameter.setRefusePassedToThisNode(String.valueOf(sbWFApprovalForm.isRefusePassedToThisNode()));
					}
					
					break;
				case OAConstant.HANDLER_OPERATION_TYPE_SIGN:
					operationName="签字";
					break;
			}
			
			parameter.setOperationType(operationType + ":" + operationName);
			parameter.setNodeId(sbWFApprovalForm.getFdHandleNodeId());
			parameter.setHandlerId(sbWFApprovalForm.getWfAuditedUserId());
		}catch(DocumentException e){
			throw new WorkflowException("生成处理人审批workitem参数失败.",e);
		}
		
		return parameter;
	}

	/**
	 * 处理人修改流程实例
	 * @param sbWFApprovalForm
	 * @param fdDetail
	 * @return
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public static String updateHandlerProcessFdDetail(SbWFApprovalForm sbWFApprovalForm,String fdDetail) {
		String operatorType = sbWFApprovalForm.getWfResult();
		String result = sbWFApprovalForm.getFlowChartXml();

		try {
			Document document = DocumentHelper.parseText(fdDetail);
			Element root = document.getRootElement();
			List<Element> nodes = root.selectNodes("/process/nodes/*");

			//处理人指派节点审批人
			if (sbWFApprovalForm.getModifiedNodeList() != null && sbWFApprovalForm.getModifiedNodeList().size() > 0) {
				for (int i = 0; i < nodes.size(); i++) {
					Element node = nodes.get(i);
					String nodeId = node.attributeValue("id");

					for (int j = 0; j < sbWFApprovalForm.getModifiedNodeList().size(); j++) {
						Map map = (HashMap) sbWFApprovalForm.getModifiedNodeList().get(j);
						String handleNodeId = String.valueOf(map.get("id"));
						String handlerIds = String.valueOf(map.get("handlerIds"));
						String handlerNames = String.valueOf(map.get("handlerNames"));
						String handlerSelectType = String.valueOf(map.get("handlerSelectType"));

						if (nodeId.equals(handleNodeId)) {
							node.addAttribute("handlerIds",handlerIds);
							node.addAttribute("handlerNames",handlerNames);
							node.addAttribute("handlerSelectType",handlerSelectType);

							break;
						}
					}
				}
			}

			//指派节点审批人
			if (String.valueOf(OAConstant.HANDLER_OPERATION_TYPE_PASS).equals(operatorType)) {
				for (int i = 0; i < nodes.size(); i++) {
					Element node = nodes.get(i);
					String nodeId = node.attributeValue("id");
					
					if (StringUtils.isNotEmpty(sbWFApprovalForm.getFutureNodeId()) 
							&& nodeId.equals(sbWFApprovalForm.getFutureNodeId())
							&& StringUtils.isNotEmpty(sbWFApprovalForm.getFutureHandlerIds())
							&& StringUtils.isNotEmpty(sbWFApprovalForm.getFutureHandlerNames())) {
						node.addAttribute("handlerSelectType","org");
						node.addAttribute("handlerIds",sbWFApprovalForm.getFutureHandlerIds());
						node.addAttribute("handlerNames",sbWFApprovalForm.getFutureHandlerNames());
					}
				}
			}
			
			result = document.getRootElement().asXML();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取特权人处理工作事项参数
	 * @param sbWFApprovalForm
	 * @return
	 */
	public static WorkitemParameter getPrivilegerWorkitemParameters(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		WorkitemParameter workitemParamenter = new WorkitemParameter();
		workitemParamenter.setNodeId(sbWFApprovalForm.getFdHandleNodeId());
		try {
			workitemParamenter.setHandlerIdentity(String.valueOf(OAConstant.HANDLER_IDENTITY_PRIVILEGER));
			workitemParamenter.setNotifyOnFinish(String.valueOf(sbWFApprovalForm.isNotifyOnFinish()));
			workitemParamenter.setNotifyType("todo");
			workitemParamenter.setHandlerId(sbWFApprovalForm.getWfAuditedUserId());
			workitemParamenter.setAuditNode(sbWFApprovalForm.getWfOptionCon());

			int operationType = Integer.valueOf(sbWFApprovalForm.getWfResult());
			String operationName = null;
			switch (operationType) {
				case OAConstant.ADMIN_OPERATION_TYPE_PASS:
					operationName = "终审通过";
					break;
				case OAConstant.ADMIN_OPERATION_TYPE_JUMP:
					operationName = "重新定位";
					if(StringUtils.isNotEmpty(sbWFApprovalForm.getIsRecoverPassedSubprocess())) {
						workitemParamenter.setIsRecoverPassedSubprocess(sbWFApprovalForm.getIsRecoverPassedSubprocess());
					}
					workitemParamenter.setJumpToNodeId(sbWFApprovalForm.getFutureNodeId());
					break;
				case OAConstant.ADMIN_OPERATION_TYPE_ABANDON:
					operationName = "直接废弃";
					break;
				case OAConstant.ADMIN_OPERATION_TYPE_CHGCURHANDLER:
					if (StringUtils.isEmpty(sbWFApprovalForm.getSourceOperType())) {
						operationName = "修改当前处理人";
					} else {
						operationName = "加签审批";
						workitemParamenter.setSourceOperType(sbWFApprovalForm.getSourceOperType());
					}
					workitemParamenter.setOldHandlerNames(sbWFApprovalForm.getToOldHandlerNames());
	
					break;
				case OAConstant.ADMIN_OPERATION_TYPE_MODIFYPROCESS:
					operationName = "修改流程";
					break;
				case OAConstant.ADMIN_OPERATION_TYPE_RECOVER:
					operationName = "回收子流程";
					workitemParamenter.setRecoverProcessIds(sbWFApprovalForm.getRecoverProcessIds());
					break;
			}

			workitemParamenter.setOperationType(String.valueOf(operationType) + ":" + operationName);
			workitemParamenter.setOprNames("301:终审通过;302:重新定位;303:直接废弃;304:修改当前处理人;305:修改流程");

			return workitemParamenter;
		} catch (Exception e) {
			throw new WorkflowException("生成起特权人操作workItem参数失败.", e);
		}
	}

	/**
	 * 修改流程实例处理人
	 * @param sbWFApprovalForm
	 * @param fdDetail
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String updatePrivilegerProcessFdDetail(SbWFApprovalForm sbWFApprovalForm,String fdDetail) {
		//修改流程信息
		if (String.valueOf(OAConstant.ADMIN_OPERATION_TYPE_MODIFYPROCESS).equals(sbWFApprovalForm.getWfResult())) {
			return sbWFApprovalForm.getFlowChartXml();
		}

		try {
			Document document = DocumentHelper.parseText(fdDetail);
			Element root = document.getRootElement();
			List<Element> nodes = root.selectNodes("/process/nodes/*");
			//修改当前处理人时需要修改当前流程实例的流程
			if (String.valueOf(OAConstant.ADMIN_OPERATION_TYPE_CHGCURHANDLER).equals(sbWFApprovalForm.getWfResult())) {
				for (int i = 0; i < nodes.size(); i++) {
					Element node = nodes.get(i);
					String nodeId = node.attributeValue("id");
					
					if (nodeId.equals(sbWFApprovalForm.getFdHandleNodeId())) {
						if (StringUtils.isEmpty(sbWFApprovalForm.getSourceOperType())) {
							if (StringUtils.isEmpty(node.attributeValue("handlerNames"))) {
								sbWFApprovalForm.setToOldHandlerNames("undefined");
							} else {
								sbWFApprovalForm.setToOldHandlerNames(node.attributeValue("handlerNames"));
							}

							node.addAttribute("handlerSelectType","org");
							node.addAttribute("handlerIds",sbWFApprovalForm.getToOtherHandlerIds());
							node.addAttribute("handlerNames",sbWFApprovalForm.getToOtherHandlerNames());
						} else {
							String handlerIdStr = node.attributeValue("handlerIds");
							String handlerNameStr = node.attributeValue("handlerNames");
							handlerIdStr = handlerIdStr.substring(handlerIdStr.indexOf(sbWFApprovalForm.getWfAuditedUserId().trim()));
							handlerNameStr = handlerNameStr.substring(handlerNameStr.indexOf(sbWFApprovalForm.getWfAuditedUserName().trim()));
							sbWFApprovalForm.setToOldHandlerNames(node.attributeValue("handlerNames"));
							
							node.addAttribute("handlerSelectType","org");
							node.addAttribute("handlerIds",sbWFApprovalForm.getToOtherHandlerIds().trim() + ";" + handlerIdStr.trim());
							node.addAttribute("handlerNames",sbWFApprovalForm.getToOtherHandlerNames().trim() + ";" + handlerNameStr.trim());
						}
					}
				}
			}

			return document.getRootElement().asXML();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 获取起草人处理工作事项参数
	 * @param sbWFApprovalForm
	 * @throws WorkflowException
	 */
	public static WorkitemParameter getDrafterWorkitemParameters(SbWFApprovalForm sbWFApprovalForm) throws WorkflowException {
		WorkitemParameter parameter = new WorkitemParameter();
		parameter.setNodeId(sbWFApprovalForm.getFdHandleNodeId());
		try {
			parameter.setHandlerIdentity(String.valueOf(OAConstant.HANDLER_IDENTITY_DRAFT));
			parameter.setNotifyOnFinish(String.valueOf(sbWFApprovalForm.isNotifyOnFinish()));
			parameter.setNotifyType("todo");
			parameter.setAuditNode(sbWFApprovalForm.getWfOptionCon());

			int operationType = Integer.valueOf(sbWFApprovalForm.getWfResult());
			String operationName = null;
			switch (operationType) {
			case OAConstant.CREATOR_OPERATION_TYPE_PRESS:
				operationName = "催办";
				break;
			case OAConstant.CREATOR_OPERATION_TYPE_RETURN:
				operationName = "撤回";
				break;
			case OAConstant.CREATOR_OPERATION_TYPE_ABANDON:
				operationName = "废弃";
				break;
			}
			parameter.setOperationType(String.valueOf(operationType) + ":" + operationName);

			parameter.setOprNames("202:催办;203:撤回;204:废弃");

			return parameter;
		} catch (Exception e) {
			throw new WorkflowException("生成起草人操作workitem参数失败.", e);
		}
	}

	/**
	 * 修改流程实例起草人
	 * 
	 * @param sysNewsMain
	 * @param fdDetail
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String updateDrafterProcessFdDetail(ProcessContext context, String fdDetail) {
		String handerId = context.getFutureNodeId();
		String result = fdDetail;

		try {
			Document document = DocumentHelper.parseText(fdDetail);
			// 修改当前处理人时需要修改当前流程实例的流程XML
			Element root = document.getRootElement();
			List<Element> nodes = root.selectNodes("/process/nodes/*");
			for (int i = 0; i < nodes.size(); i++) {
				Element node = nodes.get(i);
				String nodeId = node.attributeValue("id");

				if (StringUtils.isNotBlank(context.getFutureHandIds()) && nodeId.equals(handerId)) {
					node.addAttribute("handlerSelectType", "org");
					node.addAttribute("handlerIds", context.getFutureHandIds());
					node.addAttribute("handlerNames", context.getFutureHandNames());
				}
			}

			result = document.getRootElement().asXML();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 获取流程模板节点信息
	 * 
	 * @param fdFlowContent
	 * @return
	 * @throws WorkflowException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, String>> getTemplateNodes(String fdFlowContent) throws WorkflowException {
		Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();

		try {
			Document document = DocumentHelper.parseText(fdFlowContent);
			Element root = document.getRootElement();
			List<Element> elements = root.selectNodes("/process/nodes/*");
			for (Element element : elements) {
				String nodeId = element.attributeValue("id");
				String nodeType = element.getName();
				String nodeName = element.attributeValue("name");
				String bizInfoType = element.attributeValue("bizInfoType");
				String nodeItem = element.asXML();

				Map<String, String> node = new HashMap<String, String>();
				node.put("nodeId", nodeId);
				node.put("nodeType", nodeType);
				node.put("nodeName", nodeName);
				node.put("nodeItem", nodeItem);
				node.put("bizInfoType", bizInfoType);
				result.put(nodeId, node);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new WorkflowException(e.getMessage());
		}

		return result;
	}

	/**
	 * 查询指派节点服务
	 * 
	 * @param fdFlowContent
	 * @return
	 * @throws WorkflowException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Map<String, String>> getAssignNodes(String fdFlowContent, String nodeId)
			throws WorkflowException {
		Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();

		try {
			Document document = DocumentHelper.parseText(fdFlowContent);
			Element root = document.getRootElement();
			List<Element> nodes = root.selectNodes("/process/nodes/*");
			String[] nodeStrs = new String[0];

			for (Element node : nodes) {
				if (nodeId.equals(node.attributeValue("id"))) {
					nodeStrs = node.attributeValue("canModifyHandlerNodeIds").split(";");
					break;
				}
			}

			for (String nodeStr : nodeStrs) {
				for (Element node : nodes) {
					if (nodeStr.equals(node.attributeValue("id"))) {
						Map<String, String> item = new HashMap<String, String>();
						item.put("nodeId", node.attributeValue("id"));
						item.put("nodeName", node.attributeValue("name"));
						item.put("handlerId", node.attributeValue("optHandlerIds"));
						item.put("handlerName", node.attributeValue("optHandlerNames"));

						result.put(nodeStr, item);
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new WorkflowException(e.getMessage());
		}

		return result;
	}
	
	/**
	 * 修改流程图XML
	 * 
	 * @Title: updateProcessFdDetail
	 * @param sbWFApprovalForm
	 * @param updateNodeId
	 *            需要更改的节点ID
	 * @param fdDetail
	 *            流程图XML
	 * @return 新流程图
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String sbWFApprovalHandler(String nodeId,String handlerIds,String handlerNames,String fdDetail) throws WorkflowException {

		try {
			Document document = DocumentHelper.parseText(fdDetail);
			Element root = document.getRootElement();
			List<Element> nodes = root.selectNodes("/process/nodes/*");
			for (int i = 0; i < nodes.size(); i++) {
				Element node = nodes.get(i);
				if (nodeId.equals(node.attributeValue("id"))) {
					node.addAttribute("handlerIds", handlerIds);
					node.addAttribute("handlerNames", handlerNames);
				}
			}
			
			fdDetail = document.getRootElement().asXML();
		} catch (Exception e) {
			log.error("更新当前实例流程图XML时失败.........." + e.getMessage());
			throw new WorkflowException("更新当前实例流程图XML时失败.........." + e.getMessage());
		}

		return fdDetail;
	}
	
	/**
	 * 获取下一审批节点信息
	 * @param fdFlowContent
	 * @param nodeId
	 * @return
	 * @throws WorkflowException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Map<String,Map<String,Object>>> getNextNodes(String fdFlowContent,String wfNodeId) throws WorkflowException {
		Map<String, Map<String,Map<String, Object>>> result = new HashMap<String,Map<String,Map<String,Object>>>();
		Map<String, Map<String,Object>> lastNodes = new HashMap<String,Map<String,Object>>();
		Map<String, Map<String,Object>> nextNodes = new HashMap<String,Map<String,Object>>();

		try {
			Document document = DocumentHelper.parseText(fdFlowContent);
			Element root = document.getRootElement();
			List<Element> lines = root.selectNodes("/process/lines/*");
			List<Element> nodes = root.selectNodes("/process/nodes/*");
			initNextNode(lines, nodes, wfNodeId, lastNodes, false);
			initNextNode(lines, nodes, wfNodeId, nextNodes, true);

			result.put("last", lastNodes);
			result.put("next", nextNodes);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new WorkflowException(e.getMessage());
		}

		return result;
	}
	
	/**
	 * 迭代查找上一个或者下一个审批节点
	 * @param lines
	 * @param nodes
	 * @param wfNodeId
	 * @param result
	 * @param flag
	 */
	@SuppressWarnings("unchecked")
	public static void initNextNode(List<Element> lines,List<Element> nodes,String wfNodeId,Map<String,Map<String,Object>> result,boolean flag) {
		List<Map<String, String>> drafterOperators;
		List<Map<String, String>> handlerOperators;
		List<Map<String, String>> defineVariants;
		Map<String, String> operatorItem;
		Map<String, String> variantItem;
		Map<String, Object> nodeItem;
		String nodeStr = "";

		for (Element line : lines) {
			String startNodeId = line.attributeValue("startNodeId");
			String endNodeId = line.attributeValue("endNodeId");

			if (flag) {
				if (startNodeId.equals(wfNodeId)) {
					nodeStr += ";" + endNodeId;
				}
			} else {
				if (endNodeId.equals(wfNodeId)) {
					nodeStr += ";" + startNodeId;
				}
			}
		}

		if (nodeStr.length() > 0) {
			nodeStr = nodeStr.substring(1);
		}

		for (Element node : nodes) {
			String nodeId = node.attributeValue("id");
			String nodeType = node.getName();
			String nodeName = node.attributeValue("name");
			String bizInfoType = node.attributeValue("bizInfoType");
			Element operatorRoot = node.element("operations");
			Element variantsRoot = node.element("variants");

			if (nodeStr.indexOf(nodeId) > -1) {
				if (OANodeType.REVIEW_NODE.equals(nodeType) || OANodeType.SIGN_NODE.equals(nodeType)) {
					nodeItem = new HashMap<String, Object>();
					nodeItem.put("nodeId", nodeId);
					nodeItem.put("nodeType", nodeType);
					nodeItem.put("nodeName", nodeName);
					if (StringUtils.isEmpty(bizInfoType)) {
						nodeItem.put("bizInfoType", "");
					} else {
						nodeItem.put("bizInfoType", bizInfoType);
					}

					if (variantsRoot != null) {
						List<Element> variants = variantsRoot.elements();
						defineVariants = new ArrayList<Map<String, String>>();
						for (Element variant : variants) {
							String variantName = variant.attributeValue("name");
							String variantValue = variant.attributeValue("value");

							variantItem = new HashMap<String, String>();
							variantItem.put("name", variantName);
							variantItem.put("value", variantValue);

							defineVariants.add(variantItem);
						}

						nodeItem.put("defineVariants", defineVariants);
					}

					if (operatorRoot != null) {
						if (StringUtils.isEmpty(operatorRoot.attributeValue("refId"))) {
							List<Element> operators = operatorRoot.elements();
							drafterOperators = new ArrayList<Map<String, String>>();
							handlerOperators = new ArrayList<Map<String, String>>();
							for (Element operator : operators) {
								String operatorName = operator.attributeValue("name");
								String operatorType = operator.attributeValue("type");
								if (Integer.parseInt(operatorType) < OAConstant.DRAFT_ROLE) {
									operatorItem = new HashMap<String, String>();
									operatorItem.put("name", operatorName);
									operatorItem.put("type", operatorType);
									handlerOperators.add(operatorItem);
								} else {
									operatorItem = new HashMap<String, String>();
									operatorItem.put("name", operatorName);
									operatorItem.put("type", operatorType);
									drafterOperators.add(operatorItem);
								}
							}

							nodeItem.put("refId", "");
							nodeItem.put("drafterOperators", drafterOperators);
							nodeItem.put("handlerOperators", handlerOperators);
						} else {
							nodeItem.put("refId", operatorRoot.attributeValue("refId"));
						}
					}

					result.put(nodeId, nodeItem);
				} else {
					initNextNode(lines, nodes, nodeId, result, flag);
				}
			}
		}
	}
}
