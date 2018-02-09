package com.vispractice.fmc.business.services.setting.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entities.setting.repositories.RoleCateVRepository;
import com.vispractice.fmc.business.entities.setting.view.RoleCateV;
import com.vispractice.fmc.business.entities.sys.SysAuthCategory;
import com.vispractice.fmc.business.entities.sys.SysAuthRole;
import com.vispractice.fmc.business.entities.sys.repositories.SysAuthCategoryRepository;
import com.vispractice.fmc.business.entities.sys.repositories.SysAuthRoleRepository;
import com.vispractice.fmc.business.services.setting.IRoleCateVService;

@Service
@Transactional
public class RoleCateVServiceImpl implements IRoleCateVService {

	@Autowired
	private RoleCateVRepository roleCateVRepository;

	@Autowired
	private SysAuthCategoryRepository sysAuthCategoryRepository;
	
	@Autowired
	private SysAuthRoleRepository sysAuthRoleRepository;
	

	@Override
	public Page<RoleCateV> findByExample(RoleCateV roleCate, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreNullValues();
		Example<RoleCateV> example = Example.of(roleCate, matcher);

		return roleCateVRepository.findAll(example, pageable);
	}
	
	
	@Override
	public Page<RoleCateV> findBySearch(final RoleCateV roleCate, Pageable pageable) {
		
		Specification<RoleCateV> spec = new Specification<RoleCateV>() {

			@Override
			public Predicate toPredicate(Root<RoleCateV> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(null != roleCate){
					if (StringUtils.isNotBlank(roleCate.getFdName())) {
						list.add(cb.equal(root.get("fdName"), roleCate.getFdName()));
					}
					
					if (StringUtils.isNotBlank(roleCate.getCreatorName())) {
						list.add(cb.equal(root.get("creatorName"), roleCate.getCreatorName()));
					}
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}
		};
		
		return roleCateVRepository.findAll(spec,pageable);
	}

	@Override
	public void save(SysAuthCategory authCate) {
		sysAuthCategoryRepository.save(authCate);
	}
	
	@Override
	public SysAuthCategory findOne(String fdId){
		return sysAuthCategoryRepository.findOne(fdId);
	}

	@Override
	public void deleteByFdId(RoleCateV roleCate) {
		// 删除分类下子记录
		List <SysAuthRole> roleList = sysAuthRoleRepository.findByFdCategoryId(roleCate.getFdId());
		
		for(SysAuthRole role : roleList){
			sysAuthRoleRepository.delete(role);
		}
		
		sysAuthCategoryRepository.delete(roleCate.getFdId());
	}
	
	@Override
	public List<RoleCateV> getIdAndName() {
		return roleCateVRepository.findAll();
	}
}
