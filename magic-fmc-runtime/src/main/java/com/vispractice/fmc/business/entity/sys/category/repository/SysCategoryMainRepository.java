package com.vispractice.fmc.business.entity.sys.category.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.category.SysCategoryMain;

@Repository
public interface SysCategoryMainRepository extends PagingAndSortingRepository<SysCategoryMain,String> {
	/**
	 * 根据名称获取流程模板类别
	 * @param fdName
	 * @return
	 */
	public SysCategoryMain findByFdName(String fdName);
	
	/**
	 * 删除流程模板类别
	 * @param fdId
	 */
	public void deleteByFdId(String fdId);

	/**
	 * 实现流程:根据分类模板的层级获取其所有下级 
	 * @param hierarchyId
	 * @return
	 */
	@Query("from SysCategoryMain a where a.fdHierarchyId like ?1%")
	public List<SysCategoryMain> findByFdHierarchyIdLike(String hierarchyId);
	
	/**
	 * 获取当前分类及其所有下级的列表
	 * @param fdId
	 * @return
	 */
	@Query("select a.fdId from SysCategoryMain a where a.fdHierarchyId like %?1% and a.fdId!=?1")
	public List<String> findIdsByFdId(String fdId);
	
	/**
	 * 获取当前分类的所有下级
	 * @param fdId
	 * @return
	 */
	@Query("from SysCategoryMain a where a.fdHierarchyId like %?1% and a.fdId!=?1")
	public List<SysCategoryMain> findAllChildrenByFdId(String fdId);

	/**
	 * 实现流程: 获取流程分类的根节点 
	 * @return
	 */
	@Query("from SysCategoryMain a where a.fdParentId is null")
	public List<SysCategoryMain> findRootElements();

	public List<SysCategoryMain> findByFdParentId(String parentId);

	public Page<SysCategoryMain> findAll(Specification<SysCategoryMain> spec,Pageable pageable);
	
	/**
	 * 更新模板分类的是否叶子标识
	 * @param fdId 
	 * @param isLeaf 
	 */
	@Modifying
	@Query("update SysCategoryMain set fdIsLeaf = ?2 where fdId = ?1")
	public void updateFdIsLeafByFdId(String fdId,Long isLeaf);
}
