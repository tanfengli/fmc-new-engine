	package com.vispractice.fmc.business.services.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entities.sys.repositories.SysAuthMraRepository;
import com.vispractice.fmc.business.entity.sys.menu.SysMenu;
import com.vispractice.fmc.business.entity.sys.menu.repository.SysMenuRepository;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.services.sys.ISysAuthMraService;
import com.vispractice.fmc.web.domain.setting.SysRoleAssignForm;
/**
 * 
 * 编  号：
 * 名  称：SysAuthMraServiceImpl
 * 描  述：SysAuthMraServiceImpl.java
 * 完成日期：2017年10月12日 下午2:53:11
 * 编码作者："LaiJiashen"
 */
@Service
@Transactional
public class SysAuthMraServiceImpl implements ISysAuthMraService{
	
	@Autowired
	private SysAuthMraRepository sysAuthMraRepository;
	
	@Autowired
	private SysMenuRepository sysMenuRepository;
	
	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;

	@Override
	public List<SysMenu> getMenu(String authRoleId) {
		List<SysMenu> menuList = sysAuthMraRepository.findAuthoizedMenuByRoleId(authRoleId);
		return menuList;
	}

	@Override
	public void saveMenuAuth(SysRoleAssignForm sysRoleAssignForm) {
		String roleId = sysRoleAssignForm.getFdId();
		sysAuthMraRepository.delByFdRoleid(roleId);
		List<SysMenu> authorizedMenuList = sysRoleAssignForm.getAuthorizedMenus();
		for (SysMenu sysMenu : authorizedMenuList) {
			sysAuthMraRepository.addOne(roleId, sysMenu.getFdId());
		}
	}
	
	@Override
	public List<SysMenu> getMenuByUser(String userId){
		
		List<SysMenu> menuList = null;
		//1. 管理员拥有所有菜单权限
		SysOrgElement sysOrgElement = sysOrgElementRepository.findOne(userId);
		if (SysOrgElement.admin.equals(sysOrgElement.getFdNo())) {
			menuList = sysMenuRepository.getAllByOrderByFdParentIdAndFdOrder();
			return menuList;
		}
		//2. 通过登录用户获取已授权及无需授权菜单
		menuList = sysAuthMraRepository.findAuthoizedMenuByUserId(userId);
		
		if (menuList.size()==0) {
			SysMenu menu = new SysMenu();
			menu.setFdName("该用户没有已授权的菜单");
			menuList.add(menu);
		}
		
//		menuList = sysMenuRepository.getAllByOrderByFdParentIdAndFdOrder();
		return menuList;
	}
	

}
