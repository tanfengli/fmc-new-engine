package com.vispractice.fmc.business.services.sys;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.menu.SysMenu;
import com.vispractice.fmc.web.domain.setting.SysRoleAssignForm;

/**
 * 
 * 名  称：ISysAuthUraService
 * 描  述：菜单授权接口
 * 完成日期：2017年10月12日 上午9:25:37
 * 编码作者："LaiJiashen"
 */
public interface ISysAuthMraService {
	
	/**
	 * 获取当前角色已拥有权限的菜单
	 * @param authRoleId
	 * @return 菜单列表
	 */
	public List<SysMenu> getMenu(String authRoleId);

	/**
	 * 保存授权的菜单
	 * @Title: saveMenuAuth 
	 * @param sysRoleAssignForm
	 */
	public void saveMenuAuth(SysRoleAssignForm sysRoleAssignForm);

	/**
	 * 获取当前用户已拥有权限的菜单
	 * @param userId
	 * @return
	 */
	public List<SysMenu> getMenuByUser(String userId);

}
