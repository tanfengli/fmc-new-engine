package com.vispractice.fmc.business.service.callback;

import java.util.List;

import com.vispractice.fmc.business.entity.callback.SysCallBackInfo;

public interface ISysCallBackInfoService {

	/**
	 * 新增回调信息
	 * @param sysCallBackInfo
	 */
	public void save(SysCallBackInfo sysCallBackInfo);
	/**
	 * 删除回调信息
	 * @param sysCallBackInfo
	 */
	public void delete(SysCallBackInfo sysCallBackInfo);
	
	/**
	 *  根据流程实例ID删除回调信息列表
	 * @param fdId
	 */
	public void deleteSysCallBackInfoByInstanceId(String fdId);
	
	/**
	 * 查询未发送成功的回调信息
	 * @return
	 */
	public List<SysCallBackInfo> querySysCallBackInfoList();
	
	
	public List<SysCallBackInfo> findAll();
}
