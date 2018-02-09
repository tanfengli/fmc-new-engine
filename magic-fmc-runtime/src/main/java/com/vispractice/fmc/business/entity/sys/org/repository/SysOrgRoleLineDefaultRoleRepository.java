package com.vispractice.fmc.business.entity.sys.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLineDefaultRole;
/**
 * 名  称：SysOrgRoleLineDefaultRoleRepository
 * 描  述：SysOrgRoleLineDefaultRoleRepository.java
 * 完成日期：2017年8月7日 下午5:52:14
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysOrgRoleLineDefaultRoleRepository extends JpaRepository<SysOrgRoleLineDefaultRole, String>, JpaSpecificationExecutor<SysOrgRoleLineDefaultRole>{

	/**
	 * 根据角色线和人员获取角色默认岗位
	 * @param confId 角色线id
	 * @param personId 人员id
	 * @return
	 */
	public List<SysOrgRoleLineDefaultRole> findByFdRoleLineConfIdAndFdPersonId(String confId,String personId);
	
	/**
	 * 
	 * 根据角色线和人员获取角色默认岗位id
	 * @param confId
	 * @param personId
	 * @return 岗位id
	 */
	@Query("select a.fdPostId from SysOrgRoleLineDefaultRole a where a.fdRoleLineConfId=?1 and a.fdPersonId=?2")
	public List<String> findDefaultPostId(String confId,String personId);
	
	
	
	
}
