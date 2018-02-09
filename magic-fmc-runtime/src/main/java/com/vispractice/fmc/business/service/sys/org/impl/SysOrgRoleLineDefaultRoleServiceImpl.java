package com.vispractice.fmc.business.service.sys.org.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLineDefaultRole;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleLineDefaultRoleRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRoleLineDefaultRoleService;

/**
 * 
 * 编  号：
 * 名  称：SysOrgRoleLineDefaultRoleServiceImpl
 * 描  述：SysOrgRoleLineDefaultRoleServiceImpl.java
 * 完成日期：2017年8月7日 下午6:57:22
 * 编码作者："LaiJiashen"
 */
@Service
@Transactional
public class SysOrgRoleLineDefaultRoleServiceImpl implements ISysOrgRoleLineDefaultRoleService{
	
	@Autowired
	private SysOrgRoleLineDefaultRoleRepository sysOrgRoleLineDefaultRoleRepository;
	
	@Override
	public void save(List<SysOrgRoleLineDefaultRole> list){
		
		sysOrgRoleLineDefaultRoleRepository.save(list);
	}
	
	@Override
	public SysOrgRoleLineDefaultRole getOrNewOne(String confId,String personId){
		
		SysOrgRoleLineDefaultRole defaultRole = null;
		
		List<SysOrgRoleLineDefaultRole> defaultRoles = sysOrgRoleLineDefaultRoleRepository.findByFdRoleLineConfIdAndFdPersonId(confId, personId);
		
		if (defaultRoles.size()>0) {
			defaultRole = defaultRoles.get(0);
		} else {
			String fdId = IDGenerator.generateID();
			defaultRole = new SysOrgRoleLineDefaultRole(fdId,confId,personId);
		}
		
		return defaultRole;
	}
	

}
