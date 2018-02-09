package com.vispractice.fmc.business.entity.sys.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.view.SysOrgRoleLineV;

@Repository
public interface SysOrgRoleLineVRepository
		extends JpaRepository<SysOrgRoleLineV, String>, JpaSpecificationExecutor<SysOrgRoleLineV> {

	@Query("from SysOrgRoleLineV a where a.fdRoleLineConfId=?1 and a.fdParentId is null order by fdName")
	public List<SysOrgRoleLineV> findRoot(String fdRoleLineConfId);

	public List<SysOrgRoleLineV> findByFdParentId(String parentId);

}
