package com.vispractice.fmc.business.services.sys;

import java.util.List;

import com.vispractice.fmc.business.entities.sys.SysNotifySettingTarget;
  
public interface ISysNotifySettingTargetService {
	 
	List<String> findFdOrgId(String fdNotifySettingId); 
 
	/**
	 * 保存
	 */
	public void save(SysNotifySettingTarget notifySettingTarget);
 
	/**
	 * 删除
	 */
	public void deleteByFdNotifySettingId(String fdNotifySettingId);
}
