package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfOperations;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfOperationsRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfOperationsService;
 
@Service
@Transactional
public class SysWfOperationsServiceImpl implements ISysWfOperationsService {
	@Autowired
	private SysWfOperationsRepository operationsRepository;

	@Override
	public Page<SysWfOperations> findBySearch(final SysWfOperations operations, Pageable pageable) {
		Specification<SysWfOperations> spec = new Specification<SysWfOperations>() {
			@Override
			public Predicate toPredicate(Root<SysWfOperations> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}
		};
		
		return operationsRepository.findAll(spec,pageable);
	}

	@Override
	public Page<SysWfOperations> findAll(SysWfOperations SysWfOperations, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysWfOperations> example = Example.of(SysWfOperations, matcher); 
		return operationsRepository.findAll(example,pageable);
	}

	@Override
	public void save(SysWfOperations SysWfOperations) {
		operationsRepository.save(SysWfOperations);		 
	}

	@Override
	public SysWfOperations get(Long id) {
		return operationsRepository.getOne(id);
	}

	@Override
	public void delete(SysWfOperations SysWfOperations) {
		operationsRepository.delete(SysWfOperations);		
	}

	@Override
	public List<SysWfOperations> findByFdOperatorId(String fdOperatorId) {  
		return operationsRepository.findByFdOperatorId(fdOperatorId);
	}

	@Override
	public void deleteByFdOperatorId(String fdOperatorId) {
		operationsRepository.deleteByFdOperatorId(fdOperatorId);
	}
}