package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfOperMain;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfOperMainRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfOperationsRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfOperMainService;
 
@Service
@Transactional
public class SysWfOperMainServiceImpl implements ISysWfOperMainService {

	@Autowired
	private SysWfOperMainRepository operMainRepository;
	
	@Autowired
	private SysWfOperationsRepository operationsRepository;

	@Override
	public Page<SysWfOperMain> findBySearch(final SysWfOperMain SysWfOperMain, Pageable pageable) {
		
		Specification<SysWfOperMain> spec = new Specification<SysWfOperMain>() {

			@Override
			public Predicate toPredicate(Root<SysWfOperMain> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(null != SysWfOperMain){ 
					if (StringUtils.isNotBlank(SysWfOperMain.getFdName())) 
					    list.add(cb.like(root.<String>get("fdName"), "%"+SysWfOperMain.getFdName()+"%"));
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}

		};
		return operMainRepository.findAll(spec, pageable);

	}

	@Override
	public Page<SysWfOperMain> findAll(SysWfOperMain SysWfOperMain, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysWfOperMain> example = Example.of(SysWfOperMain, matcher); 
		return operMainRepository.findAll(example,pageable);
	}

	@Override
	public SysWfOperMain save(SysWfOperMain SysWfOperMain) {
		return operMainRepository.save(SysWfOperMain);		 
	}

	@Override
	public SysWfOperMain getById(String id) {
		return operMainRepository.findOne(id);
	}

	@Override
	public void deleteByFdId(String fdId) {
		//主表删除，删除详表
		operMainRepository.deleteByFdId(fdId);		
		
		operationsRepository.deleteByFdOperatorId(fdId);
	}

	@Override
	public int findByFdNodeTypeAndFdIsDefault(String fdNodeType,
			String fdIsDefault,String fdId) { 
		return operMainRepository.findByFdNodeTypeAndFdIsDefault(fdNodeType, fdIsDefault,fdId);
	}

	@Override
	public List<SysWfOperMain> findList() {
		return operMainRepository.findList();
	}
}