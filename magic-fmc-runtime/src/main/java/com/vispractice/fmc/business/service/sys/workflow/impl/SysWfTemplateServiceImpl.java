package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.utils.XMLReaderOrCreate;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfHistoryNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfProcessRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfTemplateRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfTemplateService;

@Service
@Transactional
public class SysWfTemplateServiceImpl implements ISysWfTemplateService {
	/**
	 * 流程模板服务
	 */
	@Autowired
	private SysWfTemplateRepository sysWfTemplateRepository;

	/**
	 * 审批历史记录服务
	 */
	@Autowired
	private SysWfHistoryNodeRepository historyNodeRepo;
	
	/**
	 * 审批节点服务
	 */
	@Autowired
	private SysWfNodeRepository sysWfNodeRepository;
	
	@Autowired
	private SysWfProcessRepository sysWfProcessRepository;

	@Override
	public String getFlowContent(String fdId) {
		SysWfTemplate sysWfTemplate = sysWfTemplateRepository.getOne(fdId);

		return sysWfTemplate.getFdFlowContent();
	}
	
	/**
	 * 根据流程模板模型获取流程模板
	 */
	@Override
	public SysWfTemplate findTemplateByModelIdOrNewOne(String modelId) throws Exception {
		try {
			List<SysWfTemplate> list = sysWfTemplateRepository.findByFdModelId(modelId);
			if (list.size() > 0) {
				return list.get(0);
			} 
		} catch (Exception e) {
			throw new Exception("获取流程模板出错.");
		}
		
		return new SysWfTemplate();
	}
	
	/**
	 * 获取流程模板XML
	 * @param templateId
	 * @return
	 */
	@Override
	public String getContentByTemplateId(String templateId) {
		String xml = new String();
		List<SysWfTemplate> sysWfTemplate = sysWfTemplateRepository.findByFdModelId(templateId);
		// 获取到XML
		if (sysWfTemplate.size() == 1) {
			xml = sysWfTemplate.get(0).getFdFlowContent();
		}
		
		return xml;
	}

	/**
	 * 获取流程模板XML和其中节点列表
	 * @Title: getContentAndNodeByTemplateId 
	 * @param templateId
	 * @return
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	@Override
	public Map getContentAndNodeByTemplateId(String wfTemplateId) throws Exception {
		Map map = new HashMap();
		List<Map<String,String>> nodeList = new ArrayList<Map<String,String>>();
		String xml = new String();
		try {
			List<SysWfTemplate> sysWfTemplate = sysWfTemplateRepository.findByFdModelId(wfTemplateId);
			// 获取到XML
			if (sysWfTemplate.size() == 1) {
				xml = sysWfTemplate.get(0).getFdFlowContent();
				nodeList = table(xml,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		map.put("xml",xml);
		map.put("nodeList",nodeList);
		
		return map;
	}
	
	@Override
	@SuppressWarnings({"unchecked","rawtypes"})
	public Map<String,String> getContentByInstanceId(String instanceId) throws Exception {
		Map map = new HashMap();
		List<Map<String,String>> nodeList = new ArrayList<Map<String, String>>();

		String xml = new String();
		try {
			// 获取到XML
			xml = sysWfProcessRepository.getOne(instanceId).getFdDetail();

			nodeList = table(xml,instanceId);
		} catch (Exception e) {
			throw new Exception();
		}
		map.put("xml",xml);
		map.put("nodeList",nodeList);

		return map;
	}
	
	private List<Map<String,String>> table(String xml,String fdId) {
		List<Map<String,String>> nodeList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> lineList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> nodeAllList = new ArrayList<Map<String,String>>();

		Map<String,String> nextNodeMap = null;
		Map<String,String> historyNodeMap = null;
		if (null!=fdId) {
			// 已通过节点
			List<SysWfHistoryNode> historyList = historyNodeRepo.findByFdProcessId(fdId);
			historyNodeMap = new HashMap<String,String>();
			for (SysWfHistoryNode historyNode : historyList) {
				historyNodeMap.put(historyNode.getFdFactNodeId(),historyNode.getFdFactNodeName());
			}
			// 即将流向节点
			List<SysWfNode> nextList = sysWfNodeRepository.findByFdProcessId(fdId);
			nextNodeMap = new HashMap<String,String>();
			for (SysWfNode nextNode : nextList) {
				nextNodeMap.put(nextNode.getFdFactNodeId(),nextNode.getFdFactNodeName());
			}
		}
		

		// 通过节点名获取节点
		XMLReaderOrCreate reader = new XMLReaderOrCreate();
		// 获取起草节点
		nodeList.addAll(reader.getElementAllAttrByTagName(xml,"draftNode"));
		// 获取审批,签字,抄送节点
		nodeList.addAll(reader.getElementAllAttrByTagName(xml,"reviewNode"));
		nodeList.addAll(reader.getElementAllAttrByTagName(xml,"signNode"));
		nodeList.addAll(reader.getElementAllAttrByTagName(xml,"sendNode"));

		nodeAllList.addAll(nodeList);
		nodeAllList.addAll(reader.getElementAllAttrByTagName(xml,"startNode"));
		nodeAllList.addAll(reader.getElementAllAttrByTagName(xml,"autoBranchNode"));
		nodeAllList.addAll(reader.getElementAllAttrByTagName(xml,"splitNode"));
		nodeAllList.addAll(reader.getElementAllAttrByTagName(xml,"joinNode"));
		nodeAllList.addAll(reader.getElementAllAttrByTagName(xml,"endNode"));

		// 获取line
		lineList.addAll(reader.getElementAllAttrByTagName(xml,"line"));

		// 节点指向匹配
		for (Map<String,String> node : nodeList) {
			String nodeId = node.get("id");

			// 给每个节点赋状态值
			if (null!=nextNodeMap&&nextNodeMap.containsKey(nodeId)) {
				node.put("status","3");
			} else if (null!=historyNodeMap&&historyNodeMap.containsKey(nodeId)) {
				node.put("status","2");
			} else {
				node.put("status","1");
			}

			// 线点匹配
			for (Map<String,String> line : lineList) {
				if (nodeId.equals(line.get("startNodeId"))) {
					for (Map<String,String> node2 : nodeAllList) {
						if (node2.get("id").equals(line.get("endNodeId"))) {
							node.put("gotoId",node2.get("id"));
							node.put("gotoName",node2.get("name"));
							node.put("gotoHandlerNames",node2.get("handlerNames"));
							break;
						}
					}
				}
			}
		}

		return nodeList;
	}

	@Override
	public SysWfTemplate findTemplateByFdId(String fdId) {
		return sysWfTemplateRepository.findOne(fdId);
	}
}
