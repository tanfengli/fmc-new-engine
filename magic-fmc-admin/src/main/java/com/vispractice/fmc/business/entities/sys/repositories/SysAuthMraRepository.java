package com.vispractice.fmc.business.entities.sys.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.sys.SysAuthMra;
import com.vispractice.fmc.business.entity.sys.menu.SysMenu;
/**
 * 
 * 编  号：
 * 名  称：SysAuthMraRepository
 * 描  述：SysAuthMraRepository.java
 * 完成日期：2017年10月12日 下午2:44:19
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysAuthMraRepository extends JpaRepository<SysAuthMra, String>, JpaSpecificationExecutor<SysAuthMra>  {

	/**
	 * 获取当前角色拥有权限的菜单
	 * @param fdRoleId
	 * @return
	 */
	@Query("select a from SysMenu a where a.fdId in (select b.fdMenuid from SysAuthMra b where b.fdRoleid=?1)")
	public List<SysMenu> findAuthoizedMenuByRoleId(String fdRoleId);
	
	/**
	 * 根据用户获取其有权限的菜单
	 * @param userId
	 * @return
	 */
	@Query("select distinct m from SysMenu m,SysOrgElement e where (m.fdAuthorizeType = 1 or exists (select 1 from SysAuthMra rm,SysAuthUra u where rm.fdMenuid = m.fdId and rm.fdRoleid = u.fdRoleid and u.fdOrgelementid = e.fdId)) and e.fdOrgType=8 and e.fdId = ?1 order by m.fdParentId,m.fdOrder")
	public List<SysMenu> findAuthoizedMenuByUserId(String userId);
	
	/**
	 * 获取无需授权菜单
	 * @return
	 */
	@Query("from SysMenu m where m.fdAuthorizeType=1 and m.fdIsAvailable=1")
	public List<SysMenu> getNoNeedAuthMenu();
	
	/**
	 * 通过角色id删除（原生态sql）
	 * @param fdRoleid
	 */
	@Modifying
	@Query(value="delete from sys_auth_mra where fd_roleid = ?1",nativeQuery = true)
	public void delByFdRoleid(String fdRoleid);
	
	/**
	 * 插入一条数据
	 * @param fdRoleid 角色idfdid
	 * @param fdMenuid 菜单fdid
	 */
	@Modifying
	@Query(value = "insert into sys_auth_mra (fd_roleid,fd_menuid) values (?1, ?2)",nativeQuery = true)
	public void addOne(String fdRoleid,String fdMenuid);

}
