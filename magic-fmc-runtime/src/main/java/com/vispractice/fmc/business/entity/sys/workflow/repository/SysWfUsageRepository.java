package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfUsage;

@Repository
public interface SysWfUsageRepository extends JpaRepository<SysWfUsage, String>, JpaSpecificationExecutor<SysWfUsage> {

	SysWfUsage findByfdUsageType(String fdUsageType);

	@Query("select count(*) from SysWfUsage a where a.fdUsageType=?1 and a.fdIsSysSetup=?2 and a.fdId not in ?3")
	public int findByFdUsageTypeAndFdIsSysSetup(String fdUsageType, String fdIsSysSetup, String fdId);

	public List<SysWfUsage> findByFdActiveFlag(String fdActiveFlag);
}