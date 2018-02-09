package com.vispractice.fmc.business.entity.sys.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.SysWfInterface;

@Repository
public interface SysWfInterfaceRepository extends JpaRepository<SysWfInterface,String>,JpaSpecificationExecutor<SysWfInterface> {
	@Query("select a from SysWfInterface a where a.fdUrl is not null")
	public List<SysWfInterface> findInterface();
	
	@Query("select a from SysWfInterface a where a.fdParentId is null")
	public List<SysWfInterface> findRootElements();

	@Query("select a from SysWfInterface a where a.fdParentId = ?1")
	public List<SysWfInterface> findByFdParentId(String parentId);
	
	@Query("select a from SysWfInterface a,SysWfBusiInterface b where a.fdId = b.fdInterfaceId and b.fdBusiId = ?1 and b.fdBusiType = ?2")
	public List<SysWfInterface> findByBusiAndType(String fdBusiId,String fdType);
	
	@Query("select a from SysWfInterface a,SysWfBusiInterface b,SysBusiSys c where a.fdId = b.fdInterfaceId and b.fdBusiId = c.fdId and c.fdCode = ?1 and a.fdCode = ?2")
	public SysWfInterface findByBusiCodeAndInterfaceCode(String busiCode,String interfaceCode);
}