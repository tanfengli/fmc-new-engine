package com.vispractice.fmc.business.entity.sys.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfEvent;

/**
 * 流程事件JPA实现类
 * @author sky
 * @Date May 11, 2017
 * @version 1.0.0
 */
public interface SysWfEventRepository extends JpaRepository<SysWfEvent, String> {
	
	public void deleteByFdProcessId(String fdProcessId);

}
