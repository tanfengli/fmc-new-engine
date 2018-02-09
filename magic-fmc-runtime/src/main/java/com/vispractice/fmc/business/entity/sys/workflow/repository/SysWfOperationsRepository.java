package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfOperations;

@Repository
public interface SysWfOperationsRepository extends JpaRepository<SysWfOperations,Long>,JpaSpecificationExecutor<SysWfOperations> {
	@Query("from SysWfOperations s where s.fdOperatorId = ?1")
	public List<SysWfOperations> findByFdOperatorId(String fdOperatorId);

	@Modifying
	@Query("delete from SysWfOperations where fdOperatorId = ?1")
	public void deleteByFdOperatorId(String fdOperatorId);
}