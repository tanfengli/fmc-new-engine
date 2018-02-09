package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfExpecterLog;

public interface SysWfExpecterLogRepository
	extends JpaRepository<SysWfExpecterLog, String>{
	
	
	/**
	 * 根据流程id和节点id获取
	 * @Title: findByFdWfProcessIdAndFdNodeId 
	 * @param processId 流程ID
	 * @param nodeId 节点ID（N1，N2...）
	 * @return
	 */
	public List<SysWfExpecterLog> findByFdWfProcessIdAndFdNodeId(String processId,String nodeId);


}
