package com.vispractice.fmc.business.service.sys.config;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.config.SysInterfaceParam;

public interface ISysInterfaceParamService {
	/**
	 * 保存接口参数信息
	 * @param sysInterfaceParams
	 * @param fdInterfaceId
	 */
	public void saveSysInterfaceParams(List<SysInterfaceParam> sysInterfaceParams,String fdInterfaceId);
	
	/**
	 * 根据接口编码获取参数
	 * @param fdInterfaceId
	 * @return
	 */
	public List<SysInterfaceParam> findSysInterfaceParam(String fdInterfaceId);
	
	
	public SysInterfaceParam findByFdInterfaceIdAndFdName(String fdInterfaceId,String fdName);
}
