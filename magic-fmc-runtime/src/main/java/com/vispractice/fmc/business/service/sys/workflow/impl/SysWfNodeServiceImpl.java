package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfByaccreditRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfNodeRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfWorkitemRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPostPersonService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfNodeService;

/**
 * 编 号： 名 称：SysWfNodeServiceImpl 描 述：SysWfNodeServiceImpl.java 完成日期：2017年3月15日
 * 上午9:56:32 编码作者：ZhouYanyao
 */
@Service
@Transactional
public class SysWfNodeServiceImpl implements ISysWfNodeService {

	@Autowired
	private SysWfNodeRepository sysWfNodeRepository;

	@Autowired
	private ISysOrgElementService sysOrgElementService;

	@Autowired
	private SysWfWorkitemRepository sysWfWorkitemRepository;

	@Autowired
	private SysWfByaccreditRepository wfByaccreditRepo;

	@Autowired
	private ISysOrgPostPersonService orgPostPersonService;

	@Override
	public SysWfNode getWfNode(String processId, String userNo) {
		try {
			return sysWfNodeRepository.getWfNodeByBillIdAndUserNo(processId, userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public SysWfNode getNodeByInstanceIdAndNodeId(String instanceId, String nodeId) {
		SysWfNode node = sysWfNodeRepository.findByFdProcessIdAndFdFactNodeId(instanceId, nodeId);

		return node;
	}

	@Override
	public List<SysWfNode> findByFdProcessId(String fdId) {
		List<SysWfNode> sysWfNodes = sysWfNodeRepository.findByFdProcessId(fdId);
		for (SysWfNode sysWfNode : sysWfNodes) {
			List<SysWfWorkitem> sysWfWorkitems = sysWfWorkitemRepository.findByFdNodeId(sysWfNode.getFdId());
			String fdHandlerIds = "";
			String fdHandlerNames = "";
			for (SysWfWorkitem sysWfWorkitem : sysWfWorkitems) {
				SysOrgElement sysOrgElement = sysOrgElementService.findByFdId(sysWfWorkitem.getFdExpectedId());
				fdHandlerIds = fdHandlerIds + ";" + sysOrgElement.getFdId();
				fdHandlerNames = fdHandlerNames + ";" + sysOrgElement.getFdName();
			}

			if (fdHandlerIds.length() > 0 && fdHandlerNames.length() > 0) {
				sysWfNode.setFdHandlerIds(fdHandlerIds.substring(1));
				sysWfNode.setFdHandlerNames(fdHandlerNames.substring(1));
			} else {
				sysWfNode.setFdHandlerIds("");
				sysWfNode.setFdHandlerNames("");
			}
		}

		return sysWfNodes;
	}

	@Override
	public SysWfNode findByExample(Example<SysWfNode> example) {
		return sysWfNodeRepository.findOne(example);
	}

	@Override
	public SysWfNode getWfNodeByInstanceIdAndProcessorId(String instanceId, String userNo) {
		SysWfNode wfNode = sysWfNodeRepository.getSysWfNodeByWfInstanceAndUserNo(instanceId, userNo);

		return wfNode;
	}

	@Override
	public SysWfNode getWfNodeByTokenId(String wfInstanceId, String wfTokenId) {
		SysWfNode wfNode = sysWfNodeRepository.getWfNodeByTokenId(wfInstanceId, wfTokenId);

		return wfNode;
	}

	@Override
	public SysWfNode findByFdProcessIdAndFdFactNodeId(String wfInstanceId, String wfFactNodeId) {
		return sysWfNodeRepository.findByFdProcessIdAndFdFactNodeId(wfInstanceId, wfFactNodeId);
	}

	@Override
	public void delete(SysWfNode node) {
		// 先清空节点工作项
		sysWfWorkitemRepository.deleteByFdNodeId(node.getFdId());
		// 删除节点
		sysWfNodeRepository.delete(node);
	}

	@Override
	public void saveNode(SysWfNode node) {
		sysWfNodeRepository.save(node);
	}

	@Override
	public SysWfNode findByFdId(String nodeId) {
		return sysWfNodeRepository.findOne(nodeId);
	}

	public void deleteAllByFdProcessId(String fdProcessId) {
		sysWfNodeRepository.deleteAllByFdProcessId(fdProcessId);
	}

	@Override
	public void deleteAllNodeAndWorkitemByProcessId(String fdProcessId) {
		// 删除工作事项
		List<SysWfNode> nodeList = sysWfNodeRepository.findAllByFdProcessId(fdProcessId);
		for (SysWfNode sysWfNode : nodeList) {
			sysWfWorkitemRepository.deleteByFdNodeId(sysWfNode.getFdId());
		}
		// 删除节点
		// sysWfNodeRepository.deleteAllByFdProcessId(fdProcessId);
	}

	@Override
	public List<String> getNodeAllExpecterAndHandler(SysWfNode node) {

		List<String> editors = new ArrayList<String>();
		List<SysWfWorkitem> workitems = sysWfWorkitemRepository.findByFdNodeId(node.getFdId());
		
		
		for (int i = 0; i < workitems.size(); i++) {
			SysWfWorkitem wf = workitems.get(i);
			if (wf.getFdHandlerId() != null) {
				editors.add(wf.getFdHandlerId());
			}
			if (wf.getFdExpectedId() != null) {
				editors.add(wf.getFdExpectedId());
			}
		}
		return editors;
	}

}
