package com.vispractice.fmc.business.entity.sys.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorizeScope;

@Repository
public interface SysWfAuthorizeScopeRepository extends JpaRepository<SysWfAuthorizeScope,String>,JpaSpecificationExecutor<SysWfAuthorizeScope> {
	/**
	 * 根据权限标识删除权限范围
	 * @param fdId
	 */
	@Modifying
	@Query("delete from SysWfAuthorizeScope where fdAuthorizeId = ?1")
	public void deleteByfdAuthorizeId(String fdId);
	
}
