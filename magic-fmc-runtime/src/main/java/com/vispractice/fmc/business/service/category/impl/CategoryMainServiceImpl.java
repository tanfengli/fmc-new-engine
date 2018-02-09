package com.vispractice.fmc.business.service.category.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.base.domain.SysOrgElementForm;
import com.vispractice.fmc.business.entity.sys.category.SysCategoryMain;
import com.vispractice.fmc.business.entity.sys.category.SysCategoryMainReader;
import com.vispractice.fmc.business.entity.sys.category.repository.CategoryMainRepository;
import com.vispractice.fmc.business.entity.sys.category.repository.SysCategoryMainEditorRepository;
import com.vispractice.fmc.business.entity.sys.category.repository.SysCategoryMainReaderRepository;
import com.vispractice.fmc.business.entity.sys.category.repository.SysCategoryMainRepository;
import com.vispractice.fmc.business.entity.sys.category.view.CategoryMain;
import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplate;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsTemplateEditorRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsTemplateReaderRepository;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsTemplateRepository;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.service.category.ICategoryMainService;
import com.vispractice.fmc.business.service.sys.news.ISysNewsTemplateService;

@Transactional
@Service
public class CategoryMainServiceImpl implements ICategoryMainService {
	@Autowired
	private SysCategoryMainRepository sysCategoryMainRepository;

	@Autowired
	private SysCategoryMainEditorRepository sysCategoryMainEditorRepository;

	@Autowired
	private SysCategoryMainReaderRepository sysCategoryMainReaderRepository;

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;

	@Autowired
	private SysNewsTemplateRepository sysNewsTemplateRepository;

	@Autowired
	private SysNewsTemplateEditorRepository sysNewsTemplateEditorRepository;

	@Autowired
	private SysNewsTemplateReaderRepository sysNewsTemplateReaderRepository;
	
	@Autowired
	private ISysNewsTemplateService sysNewsTemplateService;
	
	@Autowired
	private CategoryMainRepository categoryMainRepository;
	
	@PersistenceContext
	private EntityManager em;

	/**
	 * 添加
	 */
	@Override
	public SysCategoryMain save(SysCategoryMain sysCategoryMain,String currUserId) {
		Boolean flag = false;
		Date date = new Date(System.currentTimeMillis());
		SysCategoryMain parentCategory = null;
		String parentHierarchyId = "";
		List<SysOrgElement> editorList = sysCategoryMain.getEditorArray();
		List<SysOrgElement> readerList = sysCategoryMain.getReaderArray();
		/************保存SysCayegoryMain *************/
		//新增操作时初始化
		if (StringUtils.isEmpty(sysCategoryMain.getFdId())) {
			sysCategoryMain.setDocCreateId(currUserId);
			sysCategoryMain.setDocCreateTime(date);
			sysCategoryMain.setFdIsLeaf(new Long(CommonConstant.AVAILABLE_FLAG));
			if (null != sysCategoryMain.getFdParentId()) {
				sysCategoryMainRepository.updateFdIsLeafByFdId(sysCategoryMain.getFdParentId(),new Long(CommonConstant.NOT_AVAILABLE_FLAG));
			}
			
			if(null != sysCategoryMain.getFdParentId()){
				parentCategory = sysCategoryMainRepository.findOne(sysCategoryMain.getFdParentId());
			}
			parentHierarchyId = (null==parentCategory) ? "x" : parentCategory.getFdHierarchyId();
			flag = true;
		} else {
			SysCategoryMain categoryMain = sysCategoryMainRepository.findOne(sysCategoryMain.getFdId());
			String fdName = sysCategoryMain.getFdName();
			sysCategoryMain = (SysCategoryMain) categoryMain.clone();
			sysCategoryMain.setFdName(fdName);
		}
		sysCategoryMain.setDocAlterTime(date);
		sysCategoryMain.setDocAlterorId(currUserId);
		sysCategoryMainRepository.save(sysCategoryMain);

		// 新增时保存层级
		if (flag) {
			sysCategoryMain.setFdHierarchyId(parentHierarchyId + sysCategoryMain.getFdId() + "x");
			sysCategoryMainRepository.save(sysCategoryMain);
		}
		/************保存SysCayegoryMain *************/

		/************保存到从表 *************/
		sysCategoryMainEditorRepository.deleteByCategoryId(sysCategoryMain.getFdId());
		if(editorList != null && editorList.size() > 0) {
			for (SysOrgElement editor : editorList) {
				sysCategoryMainEditorRepository.insertOne(sysCategoryMain.getFdId(), editor.getFdId());
			}
		}

		sysCategoryMainReaderRepository.deleteByFdCategoryId(sysCategoryMain.getFdId());
		if(readerList != null && readerList.size() > 0) {
			for (SysOrgElement reader : readerList) {
				SysCategoryMainReader sysCategoryMainReader = new SysCategoryMainReader();
				sysCategoryMainReader.setFdCategoryId(sysCategoryMain.getFdId());
				sysCategoryMainReader.setAuthReaderId(reader.getFdId());
				sysCategoryMainReaderRepository.save(sysCategoryMainReader);
			}
		}
		/************保存到从表 *************/

		return sysCategoryMain;
	}

	/**
	 * 删除
	 */
	@Override
	public Boolean deleteByFdId(String fdId) {
		List<SysCategoryMain> cateList = sysCategoryMainRepository.findByFdParentId(fdId);
		List<SysNewsTemplate> tempList = sysNewsTemplateRepository.findByFdCategoryId(fdId);
		
		//删除节点
		if(cateList.size()>0||tempList.size()>0) {
			return false;
		} else {
			//更新父级叶子节点
			SysCategoryMain sysCategoryMain = sysCategoryMainRepository.findOne(fdId);
			if (null!=sysCategoryMain&&null!=sysCategoryMain.getFdParentId()) {
				List<SysCategoryMain> cateParentList = sysCategoryMainRepository.findByFdParentId(sysCategoryMain.getFdParentId());
				if (null!=cateParentList&&cateParentList.size()==1&&cateParentList.get(0).getFdId().equals(fdId)) {
					sysCategoryMainRepository.updateFdIsLeafByFdId(sysCategoryMain.getFdParentId(), new Long(CommonConstant.AVAILABLE_FLAG));
				}
			}
			
			sysCategoryMainReaderRepository.deleteByFdCategoryId(fdId);
			sysCategoryMainEditorRepository.deleteByCategoryId(fdId);
			sysCategoryMainRepository.deleteByFdId(fdId);
			
			return true;
		}
	}

	/**
	 * 查找可修改者
	 */
	@Override
	public List<SysOrgElement> findEditorElement(String fdCategoryId) {
		return sysOrgElementRepository.findEditorMember(fdCategoryId);
	}

	/**
	 * 查找可阅读者
	 */
	@Override
	public List<SysOrgElement> findReaderElement(String fdCategoryId) {
		return sysOrgElementRepository.findReaderMember(fdCategoryId);
	}

	@Override
	public Page<CategoryMain> findBySearch(final CategoryMain sysCategoryMain,Pageable pageable) {
		Specification<CategoryMain> spec = new Specification<CategoryMain>() {
			@Override
			public Predicate toPredicate(Root<CategoryMain> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if (null != sysCategoryMain) {
					if (StringUtils.isNotBlank(sysCategoryMain.getFdHierarchyId())) {
						list.add(cb.like(root.<String> get("fdHierarchyId"),sysCategoryMain.getFdHierarchyId() + "%"));
					}
				}
				Predicate[] p = new Predicate[list.size()];

				return cb.and(list.toArray(p));
			}

		};
		
		return categoryMainRepository.findAll(spec, pageable);
	}

	@Override
	public List<SysCategoryMain> findRootElements() {
		SysCategoryMain category = new SysCategoryMain();
		category.setFdId("0");
		category.setFdName("模板分类");
		category.setFdIsLeaf(0L);
		
		List<SysCategoryMain> list = new ArrayList<SysCategoryMain>();
		list.add(category);

		return list;
	}

	@Override
	public List<SysCategoryMain> findSubElements(String parentId) {
		if ("0".equals(parentId)) {
			return sysCategoryMainRepository.findRootElements();
		} else {
			return sysCategoryMainRepository.findByFdParentId(parentId);
		}
	}

	@Override
	public List<SysCategoryMain> findRootCategorys() {
		List<SysCategoryMain> list = new ArrayList<SysCategoryMain>();
		list = sysCategoryMainRepository.findRootElements();
		
		return list;
	}

	@Override
	public List<SysCategoryMain> findSubCategorys(String parentId) {
		return sysCategoryMainRepository.findByFdParentId(parentId);
	}
	
	@Override
	public List<SysCategoryMain> findTemplateSubElements(String parentId) {
		//获取下级分类
		List<SysCategoryMain> categoryList = findSubElements(parentId);
		//获取分类下的模板
		List<SysNewsTemplate> templateList = sysNewsTemplateRepository.findByFdCategoryIdAndFdStatusOrderByFdNumberPrefix(parentId,CommonConstant.AVAILABLE_FLAG);
		em.clear();
		for (SysCategoryMain cate : categoryList) {
			cate.setFdIsLeaf(0L);
		}

		for (SysNewsTemplate template : templateList) {
			SysCategoryMain category = new SysCategoryMain();
			category.setFdId(template.getFdId());
			category.setFdName(template.getFdName());
			category.setFdIsLeaf(1L);
			categoryList.add(category);
		}
		
		return categoryList;
	}

	@Override
	public List<SysCategoryMain> findCheckedElements(List<String> fdTemplateIds) {
		List<SysCategoryMain> sysCategoryMains = new ArrayList<SysCategoryMain>();
		for (String fdTemplateId : fdTemplateIds) {
			SysNewsTemplate sysNewsTemplate = sysNewsTemplateRepository.findByFdId(fdTemplateId);
			if (sysNewsTemplate != null) {
				SysCategoryMain sysCategoryMain = new SysCategoryMain();
				sysCategoryMain.setFdId(sysNewsTemplate.getFdId());
				sysCategoryMain.setFdName(sysNewsTemplate.getFdName());
				sysCategoryMain.setFdIsLeaf(1L);
				sysCategoryMains.add(sysCategoryMain);
			}
		}
		
		return sysCategoryMains;
	}
	
	@Override
	public SysCategoryMain findByFdId(String fdId) {
		return sysCategoryMainRepository.findOne(fdId);
	}

	@Override
	public Page<SysCategoryMain> findHierarchyElements(SysOrgElementForm sysOrgElementForm,Pageable pageable) {
		return null;
	}

	/**
	 * 查询所有
	 * @return
	 */
	@Override
	public List<SysCategoryMain> findAll() {
		return (List<SysCategoryMain>) sysCategoryMainRepository.findAll();
	}
	
	@Override
	public List<SysCategoryMain> findByAncestors(String cateId){
		return sysCategoryMainRepository.findByFdHierarchyIdLike(cateId);
	}
	
	/**
	 * 获取当前分类的所有子孙级分类及其模板
	 * @Title: getAllLevel 
	 * @param wfCateId
	 * @return Map,key:1为分类,key:2为模板
	 * @throws Exception
	 */
	@Override
	public Map<String,Object> getAllLevel(String wfCateId) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		//获取所有子孙分类
		List<SysCategoryMain> allCate = this.findByAncestors(wfCateId);
		//获取所有子孙分类下的所有模板
		List<SysNewsTemplate> allTemp = sysNewsTemplateService.findAllByAncestorsCateId(wfCateId);
		Map<String, SysCategoryMain> cateMap = new HashMap<>();
		if (allCate != null && !allCate.isEmpty()) {
			for (Iterator<SysCategoryMain> iter = allCate.iterator(); iter.hasNext();) {
				SysCategoryMain cate = iter.next();
				cateMap.put(cate.getFdId(), cate);
				if (!wfCateId.equals(cate.getFdParentId())) {//非直接子节点
					//把当前分类放到其父分类的子分类下
					cateMap.get(cate.getFdParentId()).getCategoryChildren().add(cate);
					iter.remove();//移除当前节点，即非直接子节点，allCate中剩余的就都是直接子节点了。
				}
			}
		}
		if (allTemp != null && !allTemp.isEmpty()) {
			for (Iterator<SysNewsTemplate> iter = allTemp.iterator(); iter.hasNext();) {
				SysNewsTemplate template = iter.next();
				if (!template.getFdCategoryId().equals(wfCateId)) {//非直接分类下的
					//放到所属的分类中去
					cateMap.get(template.getFdCategoryId()).getAssociateModels().add(template);
					//移除当前节点，即非直接分类下的，集合中剩下的就都是直接分类下的了。
					iter.remove();
				}
			}
		}
		map.put("1",allCate);
		map.put("2",allTemp);
		
		return map;
	}

	/**
	 * 获取当前分类下的下级分类和模板
	 * @Title: getSingleLevel 
	 * @param wfCateId
	 * @return Map，key：1为分类，key：2为模板
	 * @throws Exception
	 */
	@Override
	public Map<String,Object> getSingleLevel(String wfCateId) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		
		//子分类
		List<SysCategoryMain> childCates = sysCategoryMainRepository.findByFdParentId(wfCateId);
		//分类下的所有模板
		List<SysNewsTemplate> newsTemplates = sysNewsTemplateRepository.findByFdCategoryId(wfCateId);
		
		map.put("1",childCates);
		map.put("2",newsTemplates);
		
		return map;
	}
}
