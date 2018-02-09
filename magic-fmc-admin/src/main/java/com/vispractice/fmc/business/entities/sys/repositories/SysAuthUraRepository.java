package com.vispractice.fmc.business.entities.sys.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.sys.SysAuthUra;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;


@Repository
public interface SysAuthUraRepository extends JpaRepository<SysAuthUra, String>, JpaSpecificationExecutor<SysAuthUra>{

	public List<SysAuthUra> findByFdRoleid(String fdId);

	@Modifying
	@Query(value="delete from sys_auth_ura ur where ur.fd_roleid = ?1",nativeQuery = true)
	public void delByFdRoleid(String fdRoleId);
	
	/**
	 * 根据角色id获取其已指派的用户
	 * @param roleId
	 * @return 组织机构用户列表
	 */
	@Query("select e from SysOrgElement e where e.fdId in (select a.fdOrgelementid from SysAuthUra a where a.fdRoleid=?1)")
	public List<SysOrgElement> getElementIdsByRoleId(String roleId);
	

}
