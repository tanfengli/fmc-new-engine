package com.vispractice.fmc.business.services.sys;

import java.util.List;

import com.vispractice.fmc.business.entities.setting.view.RepeatPostOnRoleconfV;
import com.vispractice.fmc.business.entities.sys.view.RoleLineMemberV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLine;
import com.vispractice.fmc.business.entity.sys.org.view.RoleMemberV;

public interface IRoleLineMemberVService {
	
	/**
	 * 根据Id查询
	 */
	public RoleLineMemberV findById(String fdId);

	/**
	 * 检查重复
	 * @param confId 角色线id
	 * @return 重复的人员列表
	 */
	public List<RepeatPostOnRoleconfV> checkRepeat(String confId);

	/**
	 * 获取角色线成员
	 * @param fdId
	 * @return
	 */
	public RoleMemberV findByFdId(String fdId);

	/**
	 * 在roleMemberParent下快速新增下级
	 * @param roleMemberParent 角色线成员
	 * @param eleIdList 新增的id列表
	 */
	public List<SysOrgRoleLine> addQuickly(RoleMemberV roleMemberParent, List<String> eleIdList);

	/**
	 * 
	 * @Title: findByFdId 
	 * @param idList
	 * @return
	 */
	public List<RoleMemberV> findByFdId(List<String> idList);
	
}
