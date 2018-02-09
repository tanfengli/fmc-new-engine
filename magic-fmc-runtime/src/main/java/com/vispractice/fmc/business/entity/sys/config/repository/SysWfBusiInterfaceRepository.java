package com.vispractice.fmc.business.entity.sys.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.SysWfBusiInterface;

@Repository
public interface SysWfBusiInterfaceRepository extends JpaRepository<SysWfBusiInterface,String>,JpaSpecificationExecutor<SysWfBusiInterface> {
	@Modifying
	@Query("delete from SysWfBusiInterface a where a.fdBusiType = ?1 and a.fdBusiId = ?2")
	public void deleteByTypeAndId(String fdBusiType,String fdBusiId);
	
	@Query("select a from SysWfBusiInterface a where a.fdBusiType = ?1 and a.fdBusiId = ?2")
	public List<SysWfBusiInterface> findByTypeAndId(String fdBusiType,String fdBusiId);
}
