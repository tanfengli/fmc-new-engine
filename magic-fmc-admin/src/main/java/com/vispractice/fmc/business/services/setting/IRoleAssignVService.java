package com.vispractice.fmc.business.services.setting;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.web.domain.setting.SysRoleAssignForm;
import com.vispractice.fmc.business.entities.setting.view.RoleAssignV;

public interface IRoleAssignVService {
	
	
	/**
	 * 删除角色
	 * @Title: deleteById 
	 * @param fdId
	 */
	public void deleteById(String fdId);
	
	/**
	 * 获取新增/修改表单
	 */
	public SysRoleAssignForm findForm(RoleAssignV roleAssignV);

	/**
	 * 获取权限角色（分页）
	 * @param RoleAssignV
	 * @param pageable
	 * @return
	 */
	public Page<RoleAssignV> findBySearch(RoleAssignV RoleAssignV, Pageable pageable);

	/**
	 * 保存角色
	 * @param sysRoleAssignForm
	 * @param currentUserId 当前登录用户
	 */
	public void save(SysRoleAssignForm sysRoleAssignForm, String currentUserId);

	/**
	 * 获取所有权限角色
	 * @Title: findAll 
	 * @return
	 */
	public List<RoleAssignV> findAll();
	

}
