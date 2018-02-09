package com.vispractice.fmc.business.entity.sys.news.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;

@Repository
public interface SysNewsTemplateRepository extends JpaRepository<SysNewsTemplate,String>,JpaSpecificationExecutor<SysNewsTemplate> {
	/**
	 * 根据流程模板类别删除流程模板
	 * @param fdId
	 */
	public void deleteByFdCategoryId(String fdId);

	/**
	 * 根据模板类型获取流程模板
	 * @param parentId
	 * @return
	 */
	public List<SysNewsTemplate> findByFdCategoryId(String parentId);
	
	/**
	 * 根据模板类型获取流程模板,并根据模板前缀排序
	 * @param parentId
	 * @return
	 */
	public List<SysNewsTemplate> findByFdCategoryIdAndFdStatusOrderByFdNumberPrefix(String parentId,String fdStatus);
	
	/**
	 * 根据流程标识获取流程模板
	 * @param fdId
	 * @return
	 */
	public SysNewsTemplate findByFdId(String fdId);
	
	/**
	 * 根据流程名称获取流程模板
	 * @param fdName
	 * @return
	 */
	public List<SysNewsTemplate> findByFdName(String fdName);
	
	/**
	 * 根据流程前缀获取流程模板列表
	 * @param prefix
	 * @return
	 */
	public List<SysNewsTemplate> findByFdNumberPrefix(String prefix);
	
	/**
	 * 根据分类ID列表获取所有模板
	 * @param cateId
	 * @return
	 */
	@Query("from SysNewsTemplate a where a.fdCategoryId in ?1")
	public List<SysNewsTemplate> findAllByCateIds(List<String> cateIds);
}
