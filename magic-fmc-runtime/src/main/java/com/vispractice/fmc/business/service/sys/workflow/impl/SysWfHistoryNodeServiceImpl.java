package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryNode;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfHistoryNodeRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfHistoryNodeService;

/**
 * @ClassName SysWfHistoryNodeServiceImpl 流程实例服务实现类
 * @author ZhouYanyao
 * @Date May 15, 2017
 * @version 1.0.0
 */
@Service
@Transactional
public class SysWfHistoryNodeServiceImpl implements ISysWfHistoryNodeService {
	/**
	 * 历史节点服务
	 */
	@Autowired
	private SysWfHistoryNodeRepository sysWfHistoryNodeRepository;
	
	@Override
	public List<SysWfHistoryNode> findHistoryNodeByProcessId(String wfInstanceId) {
		List<SysWfHistoryNode> result = sysWfHistoryNodeRepository.findByFdProcessId(wfInstanceId);
		
		return result;
	}

	@Override
	public void save(SysWfHistoryNode sysWfHistoryNode) {
		sysWfHistoryNodeRepository.save(sysWfHistoryNode);		
	}

	@Override
	public SysWfHistoryNode findByFdProcessIdAndFdNodeId(String processId, String nodeId) {
		return sysWfHistoryNodeRepository.findByFdProcessIdAndFdNodeId(processId, nodeId);
	}
	
}
