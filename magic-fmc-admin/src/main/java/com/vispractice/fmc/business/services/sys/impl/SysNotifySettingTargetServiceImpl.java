package com.vispractice.fmc.business.services.sys.impl;
 
import java.util.List;

import javax.transaction.Transactional; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;  

import com.vispractice.fmc.business.entities.sys.SysNotifySettingTarget;
import com.vispractice.fmc.business.entities.sys.repositories.SysNotifySettingTargetRepository;
import com.vispractice.fmc.business.services.sys.ISysNotifySettingTargetService;

@Service
@Transactional
public class SysNotifySettingTargetServiceImpl implements ISysNotifySettingTargetService{

	@Autowired
	private SysNotifySettingTargetRepository notifySettingTargetRepository;

	@Override
	public List<String> findFdOrgId(String fdNotifySettingId) { 
		return notifySettingTargetRepository.findFdOrgId(fdNotifySettingId);
	} 
	
	@Override
	public void save(SysNotifySettingTarget notifySettingTarget) {
		notifySettingTargetRepository.save(notifySettingTarget);
	}

	@Override
	public void deleteByFdNotifySettingId(String fdNotifySettingId) {
		notifySettingTargetRepository.deleteByFdNotifySettingId(fdNotifySettingId);
	}

}