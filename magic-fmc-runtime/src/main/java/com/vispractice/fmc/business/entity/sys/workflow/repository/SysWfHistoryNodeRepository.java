package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryNode;

public interface SysWfHistoryNodeRepository extends JpaRepository<SysWfHistoryNode,String>{
	/**
	 * 根据流程实例获取审批历史
	 * @param fdProcessId
	 * @return
	 */
	List<SysWfHistoryNode> findByFdProcessId(String fdProcessId);
	
	/**
	 * 依据流程实例、节点查找节点历史信息
	 * @param processId
	 * @param factNodeId
	 * @return
	 */
	SysWfHistoryNode findByFdProcessIdAndFdNodeId(String processId, String nodeId);
	
}