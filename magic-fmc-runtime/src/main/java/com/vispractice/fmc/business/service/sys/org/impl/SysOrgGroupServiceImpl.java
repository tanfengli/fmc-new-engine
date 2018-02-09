package com.vispractice.fmc.business.service.sys.org.impl;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgGroupCate;
import com.vispractice.fmc.business.entity.sys.org.SysOrgGroupElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgGroupCateRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgGroupElementRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgGroupService;
/**
 * 
 * 编  号：<br/>
 * 名  称：SysOrgGroupCateServiceImpl<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月14日 下午3:18:07<br/>
 * 编码作者："LaiJiashen"<br/>
 */
@Transactional
@Service
public class SysOrgGroupServiceImpl implements ISysOrgGroupService{

	
	@Autowired
	private SysOrgGroupCateRepository sysOrgGroupCateRepository;
	
	@Autowired
	private SysOrgGroupElementRepository sysOrgGroupElementRepository;
	
	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;
	
	private int count = 0;
	
	@Override
	public List<SysOrgGroupCate> findRootElements() {

		return sysOrgGroupCateRepository.findRootElements();
	}
	
	@Override
	public List<SysOrgGroupCate> findSubElements(String parentId) {

		return sysOrgGroupCateRepository.findByFdParentId(parentId);
	}
	
	@Override
	public SysOrgGroupCate save(SysOrgGroupCate sysOrgGroupCate) throws RuntimeException{
		
		Date date = new Date(System.currentTimeMillis());
		
		if(null==sysOrgGroupCate.getFdId()){//添加
			sysOrgGroupCate.setFdCreateTime(date);
			sysOrgGroupCate.setFdIsLeaf(new Long(CommonConstant.AVAILABLE_FLAG));
			if(null!=sysOrgGroupCate.getFdParentId()){
				sysOrgGroupCateRepository.updateFdIsLeafByFdId(sysOrgGroupCate.getFdParentId(), new Long(CommonConstant.NOT_AVAILABLE_FLAG));
			}
		}

		sysOrgGroupCate.setFdAlterTime(date);
		this.beforeSave(sysOrgGroupCate);
		sysOrgGroupCateRepository.save(sysOrgGroupCate);
		return sysOrgGroupCate;
	}
	
	private void beforeSave(SysOrgGroupCate sysOrgGroupCate) {
		// 1.检测是否超过4层
		count = 0;
		if(!this.checkParents(sysOrgGroupCate.getFdParentId())){
			throw new RuntimeException("只允许新增到第四级类别");
		}
		// 2.检测是否重名
		String fdId = sysOrgGroupCate.getFdId();
		String fdName = sysOrgGroupCate.getFdName();
		List<SysOrgGroupCate> cateList = sysOrgGroupCateRepository.findByFdName(fdName);
		boolean isRepeat = false;
		if (StringUtils.isEmpty(fdId)&&cateList.size()>0) {
			isRepeat = true;
		}
		if (cateList.size()>1||cateList.size()==1&&!cateList.get(0).getFdId().equals(sysOrgGroupCate.getFdId())) {
			isRepeat = true;
		}
		if (isRepeat) {
			throw new RuntimeException("名称已存在，无法保存");
		}
	}
	
	/**
	 * 检测节点层次是否超过4层
	 * @param parentId
	 * @return
	 */
	private boolean checkParents(String parentId) {
		if (StringUtils.isEmpty(parentId)) {
			return true;
		}
		count++;
		String nextParentId = sysOrgGroupCateRepository.findOne(parentId).getFdParentId();
		this.checkParents(nextParentId);
		if (count>3) {
			return false;
		}
		return true;
	}
	
	
	@Override
	public Page<SysOrgGroupCate> findBySearch(final SysOrgGroupCate SysOrgGroupCate, Pageable pageable) {
		
		Specification<SysOrgGroupCate> spec = new Specification<SysOrgGroupCate>() {
			@Override
			public Predicate toPredicate(Root<SysOrgGroupCate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(null != SysOrgGroupCate){
					if (StringUtils.isNotBlank(SysOrgGroupCate.getFdId()))
						list.add(cb.equal(root.<String>get("fdId"),SysOrgGroupCate.getFdId()));
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		return sysOrgGroupCateRepository.findAll(spec, pageable);
	}
	
	/**
	 * 删除
	 * */
	@Override
	public void deleteByFdId(String fdId){
		sysOrgGroupCateRepository.deleteByfdId(fdId);
	}

	@Override
	public Boolean delete(SysOrgGroupCate sysOrgGroupCate) {
		
		String fdId = sysOrgGroupCate.getFdId();
		String parentId = sysOrgGroupCate.getFdParentId();
		
		List<SysOrgGroupCate> cateList = sysOrgGroupCateRepository.findByFdParentId(fdId);
		List<SysOrgElement> elemList = sysOrgElementRepository.findByFdCateidAndFdOrgType(fdId,(long) SysOrgConstant.ORG_TYPE_GROUP);
		
		if(cateList.size()>0||elemList.size()>0){
			return false;
		}else{
			//更新父级叶子节点
			if (null!=parentId) {
				List<SysOrgGroupCate> cateList2 = sysOrgGroupCateRepository.findByFdParentId(parentId);
				if (null!=cateList2&&cateList2.size()==1&&cateList2.get(0).getFdId().equals(fdId)) {
					sysOrgGroupCateRepository.updateFdIsLeafByFdId(parentId, new Long(CommonConstant.AVAILABLE_FLAG));
				}
			}
			
			sysOrgGroupCateRepository.deleteByfdId(sysOrgGroupCate.getFdId());
			return true;
		}
	}

	@Override
	public List<SysOrgElement> findOrgElementsByGroupId(String groupId) {
		return sysOrgGroupElementRepository.findOrgElementsByGroupId(groupId);
	}
	
}
