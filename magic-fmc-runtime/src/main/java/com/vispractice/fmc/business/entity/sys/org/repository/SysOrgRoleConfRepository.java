package com.vispractice.fmc.business.entity.sys.org.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;

@Repository
public interface SysOrgRoleConfRepository
		extends JpaRepository<SysOrgRoleConf, String>, JpaSpecificationExecutor<SysOrgRoleConf> {

	// 分页，排序，条件查询
	Page<SysOrgRoleConf> findAll(Specification<SysOrgRoleConf> spec, Pageable pageable);

	/***
	 * 查询有效角色线
	 */
	@Query("from SysOrgRoleConf a where a.fdIsAvailable=1 order by a.fdName")
	List<SysOrgRoleConf> findIsAvailable();

}
