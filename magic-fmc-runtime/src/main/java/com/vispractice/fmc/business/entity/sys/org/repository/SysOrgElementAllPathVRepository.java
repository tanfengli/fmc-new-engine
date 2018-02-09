package com.vispractice.fmc.business.entity.sys.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.view.SysOrgElementAllPathV;
  

@Repository
public interface SysOrgElementAllPathVRepository extends JpaRepository<SysOrgElementAllPathV,String>,JpaSpecificationExecutor<SysOrgElementAllPathV> {
	@Query("from SysOrgElementAllPathV s where s.fdParentId = ?1 and s.fdOrgType=?2 and s.fdId=?3")
	public SysOrgElementAllPathV findsysOrgElement(String fdParentid,String fdOrgType,String fdId);
	
	@Query("from SysOrgElementAllPathV s where s.fdId=?1")
	public SysOrgElementAllPathV findByFdId(String fdId);
}