package com.vispractice.fmc.business.service.sys.org;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgGroupCate;
import com.vispractice.fmc.business.entity.sys.org.SysOrgGroupElement;

public interface ISysOrgGroupService {

	/**
	 * 删除群组分类
	 * @param sysOrgGroupCate
	 * @return
	 */
	public Boolean delete(SysOrgGroupCate sysOrgGroupCate);

	/**
	 * 获取群组分类根节点
	 * @return
	 */
	public List<SysOrgGroupCate> findRootElements();

	/**
	 * 获取群组分类子节点
	 * @param parentId
	 * @return
	 */
	public List<SysOrgGroupCate> findSubElements(String parentId);

	/**
	 * 保存群组分类
	 * @param sysOrgGroupCate
	 * @return
	 */
	public SysOrgGroupCate save(SysOrgGroupCate sysOrgGroupCate);

	/**
	 * 分页查询
	 * @param SysOrgGroupCate
	 * @param pageable
	 * @return
	 */
	public Page<SysOrgGroupCate> findBySearch(SysOrgGroupCate SysOrgGroupCate, Pageable pageable);

	/**
	 * 根据id删除群组分类
	 * @param fdId
	 */
	public void deleteByFdId(String fdId);
	
	/**
	 * 得到指定群组成员
	 * @param groupId
	 * @return
	 */
	public List<SysOrgElement> findOrgElementsByGroupId(String groupId);

}
