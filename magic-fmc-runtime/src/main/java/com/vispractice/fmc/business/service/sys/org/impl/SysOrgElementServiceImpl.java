package com.vispractice.fmc.business.service.sys.org.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
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
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.base.domain.SysOrgElementForm;
import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;

/**
 * 
 * 编 号：<br/>
 * 名 称：SysOrgElementServiceImpl<br/>
 * 描 述：组织管理服务实现类<br/>
 * 完成日期：2016年12月14日 下午2:14:09<br/>
 * 编码作者：sky<br/>
 */
@Service
@Transactional(readOnly = true)
public class SysOrgElementServiceImpl implements ISysOrgElementService, CommonConstant {

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;

	@Override
	public List<SysOrgElement> findRootElements() {
		List<Long> fdOrgTypeList = new ArrayList<Long>();
		fdOrgTypeList.add(Long.valueOf(SysOrgConstant.ORG_TYPE_ORG));
		return sysOrgElementRepository.findRootElements(fdOrgTypeList);
	}

	/*
	 * 编号/名称查询条件
	 */
	public Specification<SysOrgElement> likeFdNoOrFdName(final SysOrgElementForm sysOrgElementForm) {
		return new Specification<SysOrgElement>() {
			@Override
			public Predicate toPredicate(Root<SysOrgElement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> list = new ArrayList<Predicate>();

				// 树曾节点查询
				Path<String> fdHierarchyIdPath = root.get("fdHierarchyId");

				list.add(cb.like(fdHierarchyIdPath, sysOrgElementForm.getNodeId() + "%"));

				// 过滤条件查询
				if (StringUtils.isNotBlank(sysOrgElementForm.getFilterName())) {
					Path<String> fdNoPath = root.get("fdNo");

					Path<String> fdNamePath = root.get("fdName");

					String filterName = "%" + sysOrgElementForm.getFilterName() + "%";

					list.add(cb.or(cb.like(fdNoPath, filterName), cb.like(fdNamePath, filterName)));
				}
				// check选项查询
				Path<Long> orgType = root.get("fdOrgType");

				if (sysOrgElementForm.getCheckedValue().size() > 0) {

					list.add(orgType.in(sysOrgElementForm.getCheckedValue()));

				} else {
					list.add(cb.equal(orgType, Long.valueOf(0)));
				}
				
				// 有效性查询
				 Path<Long> fdIsAvailablePath = root.get("fdIsAvailable");
				 if (null!=sysOrgElementForm.getAvailableFlag()&&sysOrgElementForm.getAvailableFlag().size() > 0) {
					list.add(fdIsAvailablePath.in(sysOrgElementForm.getAvailableFlag()));
				}else{
					list.add(cb.equal(fdIsAvailablePath, Long.valueOf(2)));
				}

				// 过滤已废弃成员
				Path<Long> fdIsAbandonPath = root.get("fdIsAbandon");
				list.add(cb.equal(fdIsAbandonPath, Long.valueOf(0)));
				Predicate[] p = new Predicate[list.size()];

				return cb.and(list.toArray(p));
			}
		};
	}

	/*
	 * 成员组件查询条件
	 */
	public Specification<SysOrgElement> likeFdName(final SysOrgElementForm sysOrgElementForm) {
		return new Specification<SysOrgElement>() {
			@Override
			public Predicate toPredicate(Root<SysOrgElement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> list = new ArrayList<Predicate>();

				// if(!sysOrgElementForm.getIfSearch()){
				// //树曾节点查询
				// Path<String> fdParentidPath = root.get("fdParentid");
				//
				// list.add(cb.equal(fdParentidPath,
				// sysOrgElementForm.getNodeId()));
				// }
				// 过滤条件查询
				if (StringUtils.isNotBlank(sysOrgElementForm.getFilterName())) {

					Path<String> fdNoPath = root.get("fdNo");

					Path<String> fdNamePath = root.get("fdName");

					if (sysOrgElementForm.getSearchMode()
							&& StringUtils.isNotBlank(sysOrgElementForm.getFilterName())) {
						// 精确查询
						String filterName = sysOrgElementForm.getFilterName();

						list.add(cb.or(cb.equal(fdNoPath, filterName), cb.equal(fdNamePath, filterName)));

					} else {
						// 模糊查询
						String filterName = "%" + sysOrgElementForm.getFilterName() + "%";

						list.add(cb.or(cb.like(fdNoPath, filterName), cb.like(fdNamePath, filterName)));
					}

				}

				// check选项查询
				Path<Long> orgType = root.get("fdOrgType");

				if (sysOrgElementForm.getCheckedValue().size() > 0) {

					list.add(orgType.in(sysOrgElementForm.getCheckedValue()));

				} else {
					list.add(cb.equal(orgType, Long.valueOf(0)));
				}
				// 有效性查询
				Path<Long> fdIsAvailablePath = root.get("fdIsAvailable");

				list.add(cb.equal(fdIsAvailablePath, "1"));

				Predicate[] p = new Predicate[list.size()];

				return cb.and(list.toArray(p));
			}
		};
	}

	@Override
	public List<SysOrgElement> findSubElements(String parentId) {
		return sysOrgElementRepository.findByFdParentidAndFdIsAvailable(parentId,
				Long.valueOf(CommonConstant.AVAILABLE_FLAG));
	}

	@Override
	public List<SysOrgElement> findSubElements(String parentId, List<Long> fdOrgTypeList) {
		return sysOrgElementRepository.findByFdParentidAndFdOrgTypeInAndFdIsAvailable(parentId,
				fdOrgTypeList, Long.valueOf(CommonConstant.AVAILABLE_FLAG));
	}

	@Override
	public SysOrgElement findByFdNo(String fdNo) {
		return sysOrgElementRepository.findByFdNo(fdNo);
	}

	@Override
	public Page<SysOrgElement> findHierarchyElements(SysOrgElementForm sysOrgElementForm, Pageable pageable) {

		Page<SysOrgElement> page = sysOrgElementRepository.findAll(likeFdNoOrFdName(sysOrgElementForm), pageable);
		// Page<SysOrgElement> page = sysOrgElementRepository
		// .findByFdHierarchyIdLikeAndFdOrgTypeInAndFdIsAvailableOrderByFdNo(
		// sysOrgElementForm.getNodeId() + "%",
		// sysOrgElementForm.getCheckedValue(),
		// CommonConstant.AVAILABLE_FLAG, pageable);
		return page;
	}

	@Override
	public List<SysOrgElement> findFdNameType(SysOrgElementForm sysOrgElementForm) {

		List<SysOrgElement> list = sysOrgElementRepository.findAll(likeFdName(sysOrgElementForm));

		return list;
	}

	@Override
	public List<SysOrgElement> findFdNameElements(SysOrgElementForm sysOrgElementForm) {
		List<SysOrgElement> list = sysOrgElementRepository.findByFdNameLikeAndFdOrgTypeInAndFdIsAvailable(
				"%" + sysOrgElementForm.getFilterName() + "%", sysOrgElementForm.getCheckedValue(),
				CommonConstant.AVAILABLE_FLAG);
		return list;

	}

	@Override
	@Transactional
	public SysOrgElement save(SysOrgElement sysOrgElement) throws RuntimeException{
		if (null == sysOrgElement.getFdId()) {
			sysOrgElement.setFdId(IDGenerator.generateID());
		}
		//更新父级叶子节点字段
		if (null != sysOrgElement.getFdParentid()) {
			SysOrgElement parentElement = sysOrgElementRepository.findOne(sysOrgElement.getFdParentid());
			if (null == parentElement) {
				throw new RuntimeException("获取上级组织架构失败");
			}
			if (parentElement.getFdIsLeaf()==1) {
				sysOrgElementRepository.updateFdIsLeafByFdId(parentElement.getFdId(), 0L);
			}
		}
		return sysOrgElementRepository.save(sysOrgElement);
	}

	@Override
	public SysOrgElement findByFdId(String fdId) {
		return sysOrgElementRepository.findOne(fdId);
	}

	@Override
	public List<SysOrgElement> findByFdIdIn(List<String> fdId) {
		return sysOrgElementRepository.findByFdIdIn(fdId);
	}
	
	@Override
	public List<SysOrgElement> findOrderElementByFdIdIn(List<String> fdIdList) {
		List<SysOrgElement> result = new ArrayList<SysOrgElement>();
		
		for(String handlerId: fdIdList) {
			SysOrgElement element = sysOrgElementRepository.findOne(handlerId);
			if(element != null) {
				result.add(element);
			}
		}
		return result;
	}

	@Override
	public List<SysOrgElement> findElements(String fdParentId,List<Long> fdOrgType) {
		return sysOrgElementRepository.findByFdParentidAndFdOrgTypeIn(fdParentId,fdOrgType);
	}

	@Override
	public List<SysOrgElement> findByName(String fdName, Long fdOrgType) {
		return sysOrgElementRepository.findByFdNameAndFdOrgType(fdName, fdOrgType);
	}

	@Override
	@Transactional
	public boolean deleteByFdId(String fdId) throws RuntimeException{
		if (sysOrgElementRepository.findByFdParentid(fdId).size()>0) {
			throw new RuntimeException("请先删除所有子级");
		}
		SysOrgElement sysOrgElement = sysOrgElementRepository.findOne(fdId);
		if (null!=sysOrgElement.getFdParentid()) {
			List<SysOrgElement> elementList =  sysOrgElementRepository.findByFdParentid(sysOrgElement.getFdParentid());
			if (elementList.size()==1&&elementList.get(0).getFdId().equals(fdId)) {
				sysOrgElementRepository.updateFdIsLeafByFdId(sysOrgElement.getFdParentid(), 1L);
			}
		}
		sysOrgElementRepository.deleteByFdId(fdId);
		return true;
	}

	@Override
	public List<SysOrgElement> findPosts(String fdId) {

		List<SysOrgElement> postList = new ArrayList<SysOrgElement>();

		SysOrgElement element = sysOrgElementRepository.findOne(fdId);
		// HierarchyId: x***x***x***x
		String[] ids = element.getFdHierarchyId().split("x");
		// 获取岗位ids
		List<String> postIdList = new ArrayList<String>();
		String postId = ids[ids.length - 2];
		postIdList.add(postId);// 一人多岗情况暂不考虑

		postList = sysOrgElementRepository.findByFdIdIn(postIdList);

		return postList;

	}

	@Override
	public List<SysOrgElement> findPostPerson(String postId) {
		return sysOrgElementRepository.findPostPerson(postId);
	}

	@Override
	public List<SysOrgElement> findByName(String fdName) {
	 
		return sysOrgElementRepository.findByFdName(fdName);
	}
	
	public SysOrgElement findOrgByFdNo(String fdNo){
		return sysOrgElementRepository.findOrgByFdNo(fdNo);
	}

}
