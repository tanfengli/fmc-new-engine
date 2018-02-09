package com.vispractice.fmc.business.service.category;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.base.domain.SysOrgElementForm;
import com.vispractice.fmc.business.entity.sys.category.SysCategoryMain;
import com.vispractice.fmc.business.entity.sys.category.view.CategoryMain;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;

public interface ICategoryMainService {
	
	/**
	 * 
	 * 实现流程:读取根路径<br/>
	 * @Title: getRoot 
	 * @return List
	 */
	public List<SysCategoryMain> findRootElements();
	/**
	 * 
	 * 实现流程:读取单层下级节点<br/>
	 * @return
	 */
	public List<SysCategoryMain> findSubElements(String parentId);
	/**
	 * 
	 * 实现流程:读取所有递归下级<br/>
	 * @return
	 */
	public Page<SysCategoryMain> findHierarchyElements(SysOrgElementForm sysOrgElementForm,Pageable pageable);
	
	/**
	 * 
	 * 根据ID删除模板分类
	 * @Title: deleteByFdId 
	 * @param fdId
	 * @return
	 */
	public Boolean deleteByFdId(String fdId);
	
	/**
	 * 
	 * 模板分类分页复杂查询
	 * @Title: findBySearch 
	 * @param sysCategoryMain
	 * @param pageable
	 * @return
	 */
	Page<CategoryMain> findBySearch(CategoryMain sysCategoryMain,Pageable pageable);
	
	public List<SysOrgElement> findEditorElement(String fdCategoryId);
	public List<SysOrgElement> findReaderElement(String fdCategoryId);
	public SysCategoryMain findByFdId(String fdId);
	public SysCategoryMain save(SysCategoryMain sysCategoryMain, String id);
	
	 
	public List<SysCategoryMain> findAll();
	
	/**
	 * 获取模板分类的下级及其中的模板
	 * @Title: findTemplateSubElements 
	 * @param parentId
	 * @return SysCategoryMain的列表: 
	 * 根据fdHierarchyId是否为null判断是模板还是分类（为null是为模板）
	 */
	public List<SysCategoryMain> findTemplateSubElements(String parentId);
	
	/**
	 * 获取已经选中的模板
	 * @param fdTemplateIds
	 * @return
	 */
	public List<SysCategoryMain> findCheckedElements(List<String> fdTemplateIds);
	
	/**
	 * 根据分类id获取某一分类下的所有子孙分类
	 * @param cateId
	 * @return
	 */
	public List<SysCategoryMain> findByAncestors(String cateId);
	
	/**
	 * 获取当前分类的所有子孙级分类及其模板
	 * @Title: getAllLevel 
	 * @param wfCateId
	 * @return Map,key:1为分类,key:2为模板
	 * @throws Exception
	 */
	public Map<String, Object> getAllLevel(String wfCateId) throws Exception;
	
	/**
	 * 
	 * 获取当前分类的所有子孙级分类及其模板
	 * @Title: getAllLevel 
	 * @param wfCateId
	 * @return Map,key:1为分类,key:2为模板
	 * @throws Exception
	 */
	public Map<String, Object> getSingleLevel(String wfCateId) throws Exception;
	
	/**
	 * 模板分类组件根节点
	 * @Title: findRootCategorys 
	 * @return
	 */
	public List<SysCategoryMain> findRootCategorys();
	
	/**
	 * 模板组件分类子节点
	 * @Title: findSubCategorys 
	 * @param parentId
	 * @return
	 */
	public List<SysCategoryMain> findSubCategorys(String parentId);

	 
	
}
