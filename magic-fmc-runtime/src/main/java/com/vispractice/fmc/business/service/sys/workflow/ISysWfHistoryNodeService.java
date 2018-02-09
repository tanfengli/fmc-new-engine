package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryNode;

public interface ISysWfHistoryNodeService {
	/**
	 * 根据流程实例获取审批历史记录
	 * @param wfInstanceId
	 * @return
	 */
	public List<SysWfHistoryNode> findHistoryNodeByProcessId(String wfInstanceId);
	
	public void save(SysWfHistoryNode sysWfHistoryNode);
	
	public SysWfHistoryNode findByFdProcessIdAndFdNodeId(String processId, String nodeId);
}
