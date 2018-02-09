package com.vispractice.fmc.business.service.sys.org;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLine;
import com.vispractice.fmc.business.entity.sys.org.view.RoleMemberV;

public interface ISysOrgRoleLineService {

	/**
	 * 
	 * 实现流程:读取根路径<br/>
	 * 
	 * @Title: getRoot
	 * @return List
	 */
	public List<RoleMemberV> findRootElements(String fdRoleLineConfId);

	/***
	 * 找子节点
	 */
	@SuppressWarnings("rawtypes")
	public List findSubElements(String parentId);

	/**
	 * 删除角色线成员及其下级
	 * 
	 * @param fdId
	 *            角色线成员fdId
	 */
	public boolean deleteByFdId(String fdId);

	/**
	 * 保存
	 * 
	 * @return
	 */
	public SysOrgRoleLine save(SysOrgRoleLine sysOrgRoleLine);

	/**
	 * 
	 * 根据ID获取一个角色线成员
	 * 
	 * @Title: getOne
	 * @param fdId
	 * @return
	 */
	public SysOrgRoleLine getOne(String fdId);

	/**
	 * 
	 * 查找角色线下所有角色线成员或获取当前层级id下的所有成员
	 * 
	 * @Title: findAllSubMembers
	 * @param roleMemberV
	 * @return 角色线id为null时返回当前层级id下的所有成员，否则返回角色线下所有角色线成员
	 * @throws Exception
	 */
	public Page<RoleMemberV> findAllSubMembers(RoleMemberV roleMemberV, Pageable pageable) throws Exception;

	/**
	 * 
	 * 获取角色线成员父节点
	 * 
	 * @param roleMemberV
	 *            角色线成员视图实体
	 * @return 当前角色线成员为根节点时返回其角色线，否则返回其上一级节点。找不到时返回null
	 */
	public RoleMemberV getParentNode(RoleMemberV roleMemberV);

	/**
	 * 移动角色线成员节点
	 * 
	 * @param roleMemberV
	 */
	public void moveNode(RoleMemberV roleMemberV) throws Exception;

	/**
	 * 
	 * 获取一个角色线成员视图实体
	 * 
	 * @param fdId
	 * @return
	 */
	public RoleMemberV getOneMember(String fdId);

}
