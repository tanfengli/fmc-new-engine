package com.vispractice.fmc.business.entity.sys.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;

@Repository
public interface SysOrgPersonRepository extends 
	JpaRepository<SysOrgPerson,String>,JpaSpecificationExecutor<SysOrgPerson> {
	
	/**
	 * 根据登录编号获取人员
	 * @param loginName
	 * @return
	 */
	SysOrgPerson getByFdLoginName(String loginName);
	
	@Query(value = "select p from SysOrgPerson p, SysOrgElement e where p.fdId = e.fdId and e.fdIsAvailable = 1 and p.fdLoginName=?1")
	SysOrgPerson getAvailablePersonByLoginName(String loginName);
	
}