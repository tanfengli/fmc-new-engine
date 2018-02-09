package com.vispractice.fmc.business.services.sys;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.web.domain.setting.SysRoleAssignForm;

/**
 * 
 * 名  称：ISysAuthUraService
 * 描  述：分配用户接口
 * 完成日期：2017年10月12日 上午9:25:37
 * 编码作者："LaiJiashen"
 */
public interface ISysAuthUraService {
	
	/**
	 * 获取当前角色已分配的用户
	 * @param authRoleId
	 * @return 组织架构用户列表
	 */
	public List<SysOrgElement> getAssignedUser(String authRoleId);

	/**
	 * 保存已分配的角色
	 * @Title: saveAssignedUser 
	 * @param sysRoleAssignForm
	 */
	public void saveAssignedUser(SysRoleAssignForm sysRoleAssignForm);

}
