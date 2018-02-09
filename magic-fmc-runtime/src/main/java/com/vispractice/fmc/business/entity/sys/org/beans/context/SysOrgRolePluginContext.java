package com.vispractice.fmc.business.entity.sys.org.beans.context;

import lombok.Data;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;

/**
 * 
 * 编  号：
 * 名  称：SysOrgRolePluginContext
 * 描  述：SysOrgRolePluginContext.java
 * 完成日期：2017年8月9日 下午5:30:12
 * 编码作者："LaiJiashen"
 */
@Data
public class SysOrgRolePluginContext {
	private SysOrgElement baseElement;

	private SysOrgRole role;

	public SysOrgRolePluginContext(SysOrgElement baseElement, SysOrgRole role) {
		super();
		this.baseElement = baseElement;
		this.role = role;
	}
}
