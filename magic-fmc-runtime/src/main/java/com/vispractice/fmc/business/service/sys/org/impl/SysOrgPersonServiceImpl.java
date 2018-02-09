package com.vispractice.fmc.business.service.sys.org.impl;

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

import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgPersonRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPersonService;

@Service 
@Transactional(readOnly = true)
public class SysOrgPersonServiceImpl implements ISysOrgPersonService {
	/**
	 * 人员数据层服务
	 */
	@Autowired
	private SysOrgPersonRepository sysOrgPersonRepository;

	/**
	 * 根据信息查找人员信息
	 */
	@Override
	public Page<SysOrgPerson> findBySearch(final SysOrgPerson sysOrgPerson,Pageable pageable) {
		Specification<SysOrgPerson> spec = new Specification<SysOrgPerson>() {
			@Override
			public Predicate toPredicate(Root<SysOrgPerson> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(null != sysOrgPerson){
					if(StringUtils.isNotBlank(sysOrgPerson.getFdId())){
						list.add(cb.equal(root.get("fdId"),sysOrgPerson.getFdId()));
					}
					
					if(StringUtils.isNotBlank(sysOrgPerson.getFdLoginName())){
						list.add(cb.equal(root.get("fdLoginName"),sysOrgPerson.getFdLoginName()));
					}
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}
		};
		
		return sysOrgPersonRepository.findAll(spec,pageable);
	}
	
	/**
	 * 模糊查找人员信息
	 */
	@Override
	public Page<SysOrgPerson> findByExample(SysOrgPerson sysOrgPerson,Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysOrgPerson> example = Example.of(sysOrgPerson,matcher); 
		
		return sysOrgPersonRepository.findAll(example,pageable);
	}

	/**
	 * 保存人员信息
	 */
	@Override
	@Transactional
	public void save(SysOrgPerson sysOrgPerson) {
		sysOrgPersonRepository.save(sysOrgPerson);		
	}
 
	/**
	 * 获取人员信息
	 */
	@Override
	public SysOrgPerson get(String id) {
		return sysOrgPersonRepository.findOne(id);
	}

	/**
	 * 删除人员信息
	 */
	@Override
	@Transactional
	public void delete(String id) {
		sysOrgPersonRepository.delete(id);		
	}
	
	/**
	 * 根据登录名查找人员信息
	 */
	@Override
	public SysOrgPerson getAvailablePersonByLoginName(String loginName) {
		SysOrgPerson sysOrgPerson = sysOrgPersonRepository.getAvailablePersonByLoginName(loginName);
		
		return sysOrgPerson;
	}
	
}