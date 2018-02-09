package com.vispractice.fmc.business.entities.sys.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.sys.SysAuthRole;
import com.vispractice.fmc.business.entities.sys.SysAuthRra;

@Repository
public interface SysAuthRraRepository extends JpaRepository<SysAuthRra, String>, JpaSpecificationExecutor<SysAuthRra>  {

	/**
	 * 获取当前角色所继承的角色
	 * @param fdRoleId
	 * @return
	 */
	@Query("select a from SysAuthRole a where a.fdId in (select b.fdInhroleid from SysAuthRra b where b.fdRoleid=?1)")
	public List<SysAuthRole> findInheritRoleByRoleId(String fdRoleId);
	
	/**
	 * 通过角色id删除（原生态sql）
	 * @param fdRoleid
	 */
	@Modifying
	@Query(value="delete from sys_auth_rra where fd_roleid = ?1",nativeQuery = true)
	public void delByFdRoleid(String fdRoleid);
	
	/**
	 * 插入一条数据
	 * @param fdRoleid 角色idfdid
	 * @param fdInhRoleid 继承角色fdid
	 */
	@Modifying
	@Query(value = "insert into sys_auth_rra (fd_roleid,fd_inhroleid) values (?1, ?2)",nativeQuery = true)
	public void addOne(String fdRoleid,String fdInhRoleid);

}
