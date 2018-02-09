package com.vispractice.fmc.business.entity.sys.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgGroupElement;

@Repository
public interface SysOrgGroupElementRepository
		extends JpaRepository<SysOrgGroupElement, String>, JpaSpecificationExecutor<SysOrgGroupElement> {

	public List<SysOrgGroupElement> findByFdGroupid(String fdGroupid);

	@Modifying
	@Query(value="delete from Sys_Org_Group_Element a where a.fd_Groupid=?1",nativeQuery=true)
	public void deleteByFdGroupid(String groupId);
	
	@Modifying
	@Query(value="insert into sys_org_group_element (fd_groupid,fd_elementid) values (?1,?2)",nativeQuery=true)
	public void insertOne(String fdGroupid,String fdElementid);
	
	@Query(value="select o from SysOrgElement o, SysOrgGroupElement e where o.fdId=e.fdElementid and e.fdGroupid=?1")
	public List<SysOrgElement> findOrgElementsByGroupId(String groupId);
}
