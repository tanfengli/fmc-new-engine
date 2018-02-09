package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfVarInfo;

@Repository
public interface SysWfVarInfoRepository
		extends JpaRepository<SysWfVarInfo, Long>, JpaSpecificationExecutor<SysWfVarInfo> {

	@Query("select count(*) from SysWfVarInfo a where a.varName=?1 and a.fdId not in ?2")
	public int findByVarName(String varName, String fdId);

	@Query("select count(*) from SysWfVarInfo a where a.varCode=?1 and a.fdId not in ?2")
	public int findByVarCode(String varCode, String fdId);

	@Query("select a from SysWfVarInfo a ")
	public List<SysWfVarInfo> findAll();
}