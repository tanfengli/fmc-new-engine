package com.vispractice.fmc.business.service.sys.org;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.beans.context.SysOrgRolePluginContext;
/**
 * 
 * 编  号：
 * 名  称：ISysOrgRolePlugin
 * 描  述：ISysOrgRolePlugin.java
 * 完成日期：2017年8月9日 下午5:10:20
 * 编码作者："LaiJiashen"
 */

public interface ISysOrgRolePlugin {
	
	public List<SysOrgElement> parse(SysOrgRolePluginContext pluginContext) throws Exception;

}
