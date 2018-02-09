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

import com.vispractice.fmc.business.entities.sys.repositories.SysVarInfoVRepository;
import com.vispractice.fmc.business.entities.sys.view.SysVarInfoV;
import com.vispractice.fmc.business.services.sys.ISysVarInfoVService;
  

@Service
@Transactional
public class SysVarInfoVServiceImpl implements ISysVarInfoVService {

	@Autowired
	private SysVarInfoVRepository infoVRepository;

	@Override
	public Page<SysVarInfoV> findBySearch(final SysVarInfoV infoV, Pageable pageable) {
		
		Specification<SysVarInfoV> spec = new Specification<SysVarInfoV>() {

			@Override
			public Predicate toPredicate(Root<SysVarInfoV> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(null != infoV){ 
					if (StringUtils.isNotBlank(infoV.getVarUseType()))
						list.add(cb.equal(root.get("varUseType"), infoV.getVarUseType()));
					if (StringUtils.isNotBlank(infoV.getVarName())) 
					    list.add(cb.like(root.<String>get("varName"), "%"+infoV.getVarName()+"%"));
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}

		};
		return infoVRepository.findAll(spec, pageable);

	}

	@Override
	public Page<SysVarInfoV> findAll(SysVarInfoV infoV, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysVarInfoV> example = Example.of(infoV, matcher); 
		return infoVRepository.findAll(example,pageable);
	}
 
	@Override
	public SysVarInfoV get(Long id) {
		return infoVRepository.getOne(id);
	}
 
}