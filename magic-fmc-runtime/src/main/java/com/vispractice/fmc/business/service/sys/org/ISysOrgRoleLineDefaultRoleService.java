package com.vispractice.fmc.business.service.sys.org;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLineDefaultRole;

/**
 * 
 * 编  号：
 * 名  称：ISysOrgRoleLineDefaultRoleService
 * 描  述：ISysOrgRoleLineDefaultRoleService.java
 * 完成日期：2017年8月7日 下午6:56:55
 * 编码作者："LaiJiashen"
 */
public interface ISysOrgRoleLineDefaultRoleService {

	/**
	 * 保存角色线成员默认岗位
	 * @Title: save 
	 * @param list
	 */
	public void save(List<SysOrgRoleLineDefaultRole> list);

	/**
	 * 
	 * 根据角色线ID和人员ID获取默认岗位，若没有则新建一个
	 * @param confId
	 * @param personId
	 * @return SysOrgRoleLineDefaultRole实体
	 */
	public SysOrgRoleLineDefaultRole getOrNewOne(String confId, String personId);

}
