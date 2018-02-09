package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryWorkitem;

public interface SysWfHistoryWorkitemRepository extends JpaRepository<SysWfHistoryWorkitem,String> {

	/**
	 * 根据流程实例获取审批历史
	 * @param fdProcessId
	 * @return
	 */
	List<SysWfHistoryWorkitem> findByFdHistoryId(String fdHistoryId);
}
