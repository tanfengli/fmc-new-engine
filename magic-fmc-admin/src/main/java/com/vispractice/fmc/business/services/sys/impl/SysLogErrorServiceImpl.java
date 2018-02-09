package com.vispractice.fmc.business.services.sys.impl;

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

import com.vispractice.fmc.business.entities.sys.SysLogError;
import com.vispractice.fmc.business.entities.sys.repositories.SysLogErrorRepository;
import com.vispractice.fmc.business.services.sys.ISysLogErrorService;
 

@Service
@Transactional
public class SysLogErrorServiceImpl implements ISysLogErrorService {

	@Autowired
	private SysLogErrorRepository errorRepository;

	@Override
	public Page<SysLogError> findBySearch(final SysLogError SysLogError, Pageable pageable) {
		
		Specification<SysLogError> spec = new Specification<SysLogError>() {

			@Override
			public Predicate toPredicate(Root<SysLogError> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(null != SysLogError){ 
					if (null!=SysLogError.getFdCreateTime())
						list.add(cb.equal(root.get("fdCreateTime"), SysLogError.getFdCreateTime()));
					if (StringUtils.isNotBlank(SysLogError.getFdOperator()))
					    list.add(cb.like(root.<String>get("fdOperator"), "%"+SysLogError.getFdOperator()+"%"));
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}

		};
		return errorRepository.findAll(spec, pageable);

	}

	@Override
	public Page<SysLogError> findAll(SysLogError sysLogError, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysLogError> example = Example.of(sysLogError, matcher); 
		return errorRepository.findAll(example,pageable);
	}

	@Override
	public void save(SysLogError sysLogError) {
		errorRepository.save(sysLogError);		 
	}

	@Override
	public SysLogError get(String id) {
		return errorRepository.getOne(id);
	}

	@Override
	public void delete(SysLogError sysLogError) {
		errorRepository.delete(sysLogError);		
	}

}