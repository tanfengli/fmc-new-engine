package com.vispractice.fmc.business.entity.sys.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;

@Repository
public interface SysOrgRoleRepository extends JpaRepository<SysOrgRole, String>, JpaSpecificationExecutor<SysOrgRole> {

	List<SysOrgRole> findByFdRoleConfId(String confId);

	SysOrgRole findByFdId(String fdId);
}