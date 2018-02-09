package com.vispractice.fmc.business.entities.sys.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.sys.SysAuthRole;

@Repository
public interface SysAuthRoleRepository  extends JpaRepository<SysAuthRole, String>, JpaSpecificationExecutor<SysAuthRole> {

	public List<SysAuthRole> findByFdAlias(String auth);

	public List<SysAuthRole> findByFdCategoryId(String fdId);
	
	/**
	 * 通过权限名称列表获取权限列表
	 * @param aliasLisr 权限名称列表
	 * @return
	 */
	@Query("from SysAuthRole a where a.fdAlias in ?1")
	public List<SysAuthRole> getAuthListByFdAliasList(List<String> aliasLisr);

}
