package com.vispractice.fmc.business.entity.sys.org.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.SysOrgGroupCate;

/**
 * 
 * 编 号：<br/>
 * 名 称：SysOrgGroupCate<br/>
 * 描 述：群组分类实体 完成日期：2016年12月14日 下午2:53:31<br/>
 * 编码作者："LaiJiashen"<br/>
 */

@Repository
public interface SysOrgGroupCateRepository
		extends JpaRepository<SysOrgGroupCate, String>, JpaSpecificationExecutor<SysOrgGroupCate> {

	List<SysOrgGroupCate> findByFdId(String fdId);

	void deleteByfdId(String fdId);
	
	/**
	 * 根据分类名称查询
	 * @param fdName
	 * @return
	 */
	public List<SysOrgGroupCate> findByFdName(String fdName);

	@Modifying
	@Query("update SysOrgGroupCate c set c.fdName =?1,c.fdAlterTime=?2 where fdId=?3 ")
	void update(String fdName, Date time, String fdId);

	@Query("from SysOrgGroupCate a where a.fdParentId is null")
	List<SysOrgGroupCate> findRootElements();

	List<SysOrgGroupCate> findByFdParentId(String parentId);
	
	/**
	 * 更新群组分类的是否叶子标识
	 * @param fdId 
	 * @param isLeaf 
	 */
	@Modifying
	@Query("update SysOrgGroupCate set fdIsLeaf = ?2 where fdId = ?1")
	public void updateFdIsLeafByFdId(String fdId,Long isLeaf);

}
