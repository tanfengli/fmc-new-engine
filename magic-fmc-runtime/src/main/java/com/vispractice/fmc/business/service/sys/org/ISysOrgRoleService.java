package com.vispractice.fmc.business.service.sys.org;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;

public interface ISysOrgRoleService {

	public Page<SysOrgRole> findAll(SysOrgRole orgRole, Pageable pageable);

	/**
	 * 保存
	 */
	public void save(SysOrgRole orgRole);

	/**
	 * 查询
	 */
	public SysOrgRole get(String id);

	/**
	 * 删除
	 */
	public void delete(SysOrgRole orgRole);

}
