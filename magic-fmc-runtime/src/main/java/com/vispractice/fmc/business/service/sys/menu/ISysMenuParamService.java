package com.vispractice.fmc.business.service.sys.menu;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.menu.SysMenuParam;
/**
 * 
 * 编  号：
 * 名  称：ISysMenuParamService
 * 描  述：菜单参数接口
 * 完成日期：2017年10月16日 下午6:24:58
 * 编码作者："LaiJiashen"
 */
public interface ISysMenuParamService {
	/**
	 * 保存菜单参数信息
	 * @param sysInterfaceParams
	 * @param fdInterfaceId
	 */
	public void saveSysMenuParams(List<SysMenuParam> sysMenuParams,String fdMenuId);
	
	/**
	 * 根据菜单id获取参数
	 * @param fdInterfaceId
	 * @return
	 */
	public List<SysMenuParam> findSysMenuParam(String fdMenuId);
}
