package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfOperMain;

@Repository
public interface SysWfOperMainRepository
		extends JpaRepository<SysWfOperMain, String>, JpaSpecificationExecutor<SysWfOperMain> {

	public void deleteByFdId(String fdId);

	@Query("select count(*) from SysWfOperMain a where a.fdNodeType=?1 and a.fdIsDefault=?2 and a.fdId not in ?3")
	public int findByFdNodeTypeAndFdIsDefault(String fdNodeType, String fdIsDefault, String fdId);

	@Query("select a from SysWfOperMain a ")
	public List<SysWfOperMain> findList();
}