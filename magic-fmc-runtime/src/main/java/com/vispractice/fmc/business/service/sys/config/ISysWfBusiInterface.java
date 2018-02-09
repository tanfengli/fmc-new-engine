package com.vispractice.fmc.business.service.sys.config;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.config.SysWfBusiInterface;

public interface ISysWfBusiInterface {
	/**
	 * 保存授权接口信息
	 * @param fdId
	 * @param interfaceIds
	 */
	public void saveGrantInterface(String fdBusiId,String interfaceIds);
	
	/**
	 * 保存授权回调信息
	 * @param fdId
	 * @param isCallback
	 * @param address
	 */
	public void saveGrantCallback(String fdBusiId,String isCallback,String address);
	
	/**
	 * 获取接口授权信息
	 * @param fdId
	 * @param fdType
	 * @return
	 */
	public List<SysWfBusiInterface> findBusiInterface(String fdBusiId,String fdType);
}
