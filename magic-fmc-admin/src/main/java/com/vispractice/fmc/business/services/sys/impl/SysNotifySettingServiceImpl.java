package com.vispractice.fmc.business.services.sys.impl;
 
import java.util.List;

import javax.transaction.Transactional; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 

import com.vispractice.fmc.business.entities.sys.SysNotifySetting; 
import com.vispractice.fmc.business.entities.sys.repositories.SysNotifySettingRepository; 
import com.vispractice.fmc.business.services.sys.ISysNotifySettingService;
 

@Service
@Transactional
public class SysNotifySettingServiceImpl implements ISysNotifySettingService{

	@Autowired
	private SysNotifySettingRepository sysNotifySettingRepository;

	@Override
	public List<SysNotifySetting> findList() { 
		return sysNotifySettingRepository.findAll();
	}

	@Override
	public SysNotifySetting save(SysNotifySetting sysNotifySetting) {
		return sysNotifySettingRepository.save(sysNotifySetting);		 
	}

	@Override
	public SysNotifySetting get(String id) {
		return sysNotifySettingRepository.getOne(id);
	} 
	
	@Override
	public void delete(String fdId) {
		sysNotifySettingRepository.delete(fdId);		
	}

}