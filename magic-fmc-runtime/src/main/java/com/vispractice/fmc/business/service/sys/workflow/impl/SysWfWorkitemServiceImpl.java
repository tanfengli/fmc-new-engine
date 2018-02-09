package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfByaccredit;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfByaccreditRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfHistoryWorkitemRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfWorkitemRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfNodeService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfWorkitemService;

/**
 * 编  号：
 * 名  称：SysWfWorkitemServiceImpl
 * 描  述：流程工作事项服务实现类
 * 完成日期：2017年11月24日 下午5:54:50
 * 编码作者：ZhouYanyao
 */
@Service
@Transactional
public class SysWfWorkitemServiceImpl implements ISysWfWorkitemService {
	@Autowired
	private SysWfWorkitemRepository sysWfWorkitemRepository;
	
	@Autowired 
	private SysWfByaccreditRepository sysWfByaccreditRepository;
	
	@Autowired
	private SysWfHistoryWorkitemRepository sysWfHistoryWorkitemRepository;
	
	@Autowired
	private ISysWfNodeService sysWfNodeService;
	
	@Autowired
	private ISysOrgElementService sysOrgElementService;
	
	
	@Override
	public List<SysOrgElement> getCommunicateScope(String nodeId){
		SysWfWorkitem rootWorkitem = sysWfWorkitemRepository.getRootWorkitem(nodeId);
		if (null==rootWorkitem) {
			throw new RuntimeException("获取根源工作事项失败");
		}
		
		String fdParameter = rootWorkitem.getFdParameter();
		List<SysOrgElement> communicateScopeList = null;
		
		try {
			Document detailDocument = DocumentHelper.parseText(fdParameter);
			Element paramRoot = detailDocument.getRootElement();
			String communicateScopeHandlerIds = paramRoot.attributeValue("communicateScopeHandlerIds");
			String communicateScopeHandlerNames = paramRoot.attributeValue("communicateScopeHandlerNames");
			if (StringUtils.isNotEmpty(communicateScopeHandlerIds)&&StringUtils.isNotEmpty(communicateScopeHandlerNames)) {
				String[] ids = communicateScopeHandlerIds.split(";");
				String[] names = communicateScopeHandlerNames.split(";");
				communicateScopeList = new ArrayList<SysOrgElement>();
				for (int i =0;i<ids.length;i++) {
					SysOrgElement communicater = new SysOrgElement();
					communicater.setFdId(ids[i]);
					communicater.setFdName(names[i]);
					communicateScopeList.add(communicater);
				}
			}
		} catch (DocumentException e) {
			throw new RuntimeException("获取沟通范围时发生错误.");
		}
		
		return communicateScopeList;
	}
	
	@Override
	public SysWfWorkitem findByFdId(String fdId){
		return sysWfWorkitemRepository.findOne(fdId);
	}

	@Override
	public void remove(SysWfWorkitem sysWfWorkitem) {
		sysWfWorkitemRepository.delete(sysWfWorkitem);
	}
	
	public void removeByFdNodeId(SysWfNode node) {
		String nodeId = node.getFdId();
		sysWfWorkitemRepository.deleteByFdNodeId(nodeId);
	}
	
	@Override
	public void saveHistoryWorkItem(SysWfWorkitem item,SysWfHistoryWorkitem hisWorkitem) {
		sysWfWorkitemRepository.delete(item);
		sysWfHistoryWorkitemRepository.save(hisWorkitem);		
	}

	@Override
	public void save(SysWfWorkitem item) {
		sysWfWorkitemRepository.save(item);
	}
	
	@Override
	public Long countByFdNodeId(String fdNodeId) {
		return sysWfWorkitemRepository.countByFdNodeId(fdNodeId);
	}

	@Override
	public SysWfWorkitem findByExample(Example<SysWfWorkitem> example) {
		return sysWfWorkitemRepository.findOne(example);
	}
	
	@Override
	public List<SysWfHistoryWorkitem> findByFdHistoryId(String historyId) {
		return sysWfHistoryWorkitemRepository.findByFdHistoryId(historyId);
	}

	@Override
	public List<SysWfWorkitem> findByNodeId(String wfNodeId) {
		return sysWfWorkitemRepository.findByFdNodeId(wfNodeId);
	}

	@Override
	public void saveWfItemList(List<SysWfWorkitem> itemList) {
		sysWfWorkitemRepository.save(itemList);
	}
	
	@Override
	public void saveWfByaccreditList(List<SysWfByaccredit> wfByaccreditList) {
		sysWfByaccreditRepository.save(wfByaccreditList);
	}

	@Override
	public List<String> getNodeAllExpecterAndHandler(SysWfNode node) {
		List<String> editors = new ArrayList<String>();
		
		List<SysWfWorkitem> items = sysWfWorkitemRepository.findByFdNodeId(node.getFdId());
				
		for (int i = 0; i < items.size(); i++) {
			SysWfWorkitem wf = items.get(i);
			if (wf.getFdHandlerId() != null) {
				editors.add(wf.getFdHandlerId());
			}
			if (wf.getFdExpectedId() != null) {
				editors.add(wf.getFdExpectedId());
			}
		}
		
		return editors;
	}

	@Override
	public List<SysWfWorkitem> findByNodeNoAndUserId(String nodeNo,String userId,String wfInstanceId) {
		List<SysWfWorkitem> sysWfWorkitems = sysWfWorkitemRepository.findByNodeNoAndUserId(nodeNo,userId,wfInstanceId);
		
		return sysWfWorkitems;
	}
	
	@Override
	public List<SysWfWorkitem> findByNodeIdAndUserId(String nodeId,String userId) {
		List<SysWfWorkitem> sysWfWorkitems = sysWfWorkitemRepository.findByNodeIdAndUserId(nodeId,userId);
		for (SysWfWorkitem sysWfWorkitem : sysWfWorkitems) {
			SysWfNode sysWfNode = sysWfNodeService.findByFdId(sysWfWorkitem.getFdNodeId());
			sysWfWorkitem.setFdNodeNo(sysWfNode.getFdFactNodeId());
			sysWfWorkitem.setFdNodeName(sysWfNode.getFdFactNodeName());
			SysOrgElement sysOrgElement = sysOrgElementService.findByFdId(sysWfWorkitem.getFdExpectedId());
			sysWfWorkitem.setFdExpectedName(sysOrgElement.getFdName());
		}
		
		return sysWfWorkitems;
	}
	
	@Override
	public List<SysWfWorkitem> findByFdRelationWorkitemId(String fdRelationWorkitemId) {
		List<SysWfWorkitem> sysWfWorkitems = sysWfWorkitemRepository.findByFdRelationWorkitemId(fdRelationWorkitemId);
		
		return sysWfWorkitems;
	}

	@Override
	public SysWfWorkitem findByFdRelationWorkitemIdAndFdExpectedId(String relationWorkitemId,String expectedId) {
		SysWfWorkitem sysWfWorkitem = sysWfWorkitemRepository.findByFdRelationWorkitemIdAndFdExpectedId(relationWorkitemId,expectedId);
		
		return sysWfWorkitem;
	}

	@Override
	public void trylockHandlerWorkitem(SysWfWorkitem workitem) {
		workitem.setFdIsDone(1L);
	}
	
	@Override
	public void unlockHandlerWorkitem(String workitemid) {
		SysWfWorkitem workitem = sysWfWorkitemRepository.findOne(workitemid);
		if(workitem!=null) {
			unlockHandlerWorkitem(workitem);
			sysWfWorkitemRepository.save(workitem);
		}
	}

	@Override
	public void unlockHandlerWorkitem(SysWfWorkitem workitem) {
		workitem.setFdIsDone(0L);
	}
}
