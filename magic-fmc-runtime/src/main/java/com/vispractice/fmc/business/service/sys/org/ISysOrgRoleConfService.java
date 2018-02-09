package com.vispractice.fmc.business.service.sys.org;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;

/**
 * 
 * 编  号：<br/>
 * 名  称：ISysOrgRoleConfService<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月20日 下午5:15:09<br/>
 * 编码作者："LaiJiashen"<br/>
 */
public interface ISysOrgRoleConfService {

	
	/**
	 * 查询
	 */
	public Page<SysOrgRoleConf> findAll(SysOrgRoleConf sysOrgRoleConf, Pageable pageable);
	
	
	/**
	 * 保存
	 * @return 
	 */
	public Boolean save(SysOrgRoleConf sysOrgRoleConf);
	
	/***
	 * 查询有效角色线
	 */
	public List<SysOrgRoleConf> findIsAvailable();

	/**
	 * 分页查询
	 * @param sysOrgRoleConf
	 * @param pageable
	 * @return
	 */
	public Page<SysOrgRoleConf> findBySearch(SysOrgRoleConf sysOrgRoleConf,
			Pageable pageable);

	/**
	 * 获取角色线根节点
	 * @return
	 */
	public List<SysOrgRoleConf> findRootElements();

	/**
	 * 获取角色线子节点
	 * @param parentId
	 * @return
	 */
	public List<SysOrgRoleConf> findSubElements(String parentId);

	/**
	 * 通过角色线ID获取角色
	 * @param fdId
	 * @return
	 */
	public List<SysOrgElement> getRoleByConfId(String fdId);

	/**
	 * 
	 * 复制角色线
	 * @param fdId 被复制的角色线ID
	 * @throws Exception 
	 */
	public void updateCopy(String fdId) throws Exception;

	/**
	 * 
	 * 获取form
	 * @param fdId
	 * @return
	 */
	public SysOrgRoleConf findForm(String fdId);


	/**
	 * 
	 * 获取id获取角色线
	 * @Title: getOne 
	 * @param fdId 角色线fdid
	 * @return
	 */
	public SysOrgRoleConf getOne(String fdId);
	
	
	
	
	
}
