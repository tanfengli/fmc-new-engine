package com.vispractice.fmc.business.services.sys;

import java.util.List;

import com.vispractice.fmc.business.entities.sys.SysNotifySetting;
 

public interface ISysNotifySettingService {
	
	public  List<SysNotifySetting> findList();
	 
	/**
	 * 保存
	 */
	public SysNotifySetting save(SysNotifySetting sysNotifySetting);

	/**
	 * 查询
	 */
	public SysNotifySetting get(String id);

	/**
	 * 删除
	 */  
	public void delete(String fdId);

}
