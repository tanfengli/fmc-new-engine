package com.vispractice.fmc.business.service.sys.config;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.config.SysAppConfig;

public interface ISysAppConfigService {
	public  List<SysAppConfig> findList();
 
	/**
	 * 保存
	 */
	public void save(SysAppConfig sysAppConfig);

	/**
	 * 查询
	 */
	public SysAppConfig get(String id);
	
	
	public SysAppConfig getNotifyCrashTargetIds();

	/**
	 * 删除
	 */
	public void delete(SysAppConfig sysAppConfig);

	public void delete(String fdField);
	
	public SysAppConfig findSysAppConfig(String fdField);
}
