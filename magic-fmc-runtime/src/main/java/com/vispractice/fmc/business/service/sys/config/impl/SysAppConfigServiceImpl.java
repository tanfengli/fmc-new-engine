package com.vispractice.fmc.business.service.sys.config.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.config.SysAppConfig;
import com.vispractice.fmc.business.entity.sys.config.repository.SysAppConfigRepository;
import com.vispractice.fmc.business.service.sys.config.ISysAppConfigService;

@Service
@Transactional
public class SysAppConfigServiceImpl implements ISysAppConfigService {

	@Autowired
	private SysAppConfigRepository appConfigRepository;

	@Override
	public List<SysAppConfig> findList() {
		return appConfigRepository.findAll();
	}

	@Override
	public void save(SysAppConfig sysAppConfig) {
		appConfigRepository.save(sysAppConfig);
	}

	@Override
	public SysAppConfig get(String id) {
		return appConfigRepository.getOne(id);
	}

	@Override
	public void delete(SysAppConfig sysAppConfig) {
		appConfigRepository.delete(sysAppConfig);
	}

	@Override
	public void delete(String fdField) {
		appConfigRepository.delete(fdField);
	}

	@Override
	public SysAppConfig findSysAppConfig(String fdField) {
		return appConfigRepository.findByFdField(fdField);
	}

	@Override
	public SysAppConfig getNotifyCrashTargetIds() {
		return appConfigRepository.findByFdField("notifyCrashTargetIds");
	}

}