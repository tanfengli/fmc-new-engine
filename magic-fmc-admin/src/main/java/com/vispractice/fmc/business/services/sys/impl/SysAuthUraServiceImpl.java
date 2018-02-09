package com.vispractice.fmc.business.services.sys.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entities.sys.SysAuthUra;
import com.vispractice.fmc.business.entities.sys.repositories.SysAuthUraRepository;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.services.sys.ISysAuthUraService;
import com.vispractice.fmc.web.domain.setting.SysRoleAssignForm;
/**
 * 
 * 名  称：SysAuthUraServiceImpl
 * 描  述：分配用户接口实现类
 * 完成日期：2017年10月12日 上午9:26:15
 * 编码作者："LaiJiashen"
 */
@Service
@Transactional
public class SysAuthUraServiceImpl implements ISysAuthUraService{
	
	@Autowired
	private SysAuthUraRepository sysAuthUraRepository;
	
	
	/**
	 * 获取当前角色中已分配的用户
	 * @param authRoleId
	 * @return
	 */
	@Override
	public List<SysOrgElement> getAssignedUser(String authRoleId){
		List<SysOrgElement> assignedUserList = null;
		assignedUserList = sysAuthUraRepository.getElementIdsByRoleId(authRoleId);
		return assignedUserList;
	}

	/**
	 * 保存当前角色中已分配的用户
	 * @param sysRoleAssignForm 
	 */
	@Override
	public void saveAssignedUser(SysRoleAssignForm sysRoleAssignForm) {
		List<SysOrgElement> assignedUserList = sysRoleAssignForm.getUraList();
		sysAuthUraRepository.delByFdRoleid(sysRoleAssignForm.getFdId());
		List<SysAuthUra> uraList = new ArrayList<SysAuthUra>();
		for (SysOrgElement sysOrgElement : assignedUserList) {
			SysAuthUra ura = new SysAuthUra();
			ura.setFdRoleid(sysRoleAssignForm.getFdId());
			ura.setFdOrgelementid(sysOrgElement.getFdId());
			uraList.add(ura);
		}
		sysAuthUraRepository.save(uraList);
	}

}
