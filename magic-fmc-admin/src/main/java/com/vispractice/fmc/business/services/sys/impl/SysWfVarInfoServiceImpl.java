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

import com.vispractice.fmc.business.entity.sys.workflow.SysWfVarInfo;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfVarInfoRepository;
import com.vispractice.fmc.business.services.sys.ISysWfVarInfoService;

@Service
@Transactional
public class SysWfVarInfoServiceImpl implements ISysWfVarInfoService {

	@Autowired
	private SysWfVarInfoRepository varInfoRepository;

	@Override
	public Page<SysWfVarInfo> findBySearch(final SysWfVarInfo sysWfVarInfo, Pageable pageable) {
		
		Specification<SysWfVarInfo> spec = new Specification<SysWfVarInfo>() {

			@Override
			public Predicate toPredicate(Root<SysWfVarInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(null != sysWfVarInfo){ 
					if (StringUtils.isNotBlank(sysWfVarInfo.getVarUseType()))
						list.add(cb.equal(root.get("varUseType"), sysWfVarInfo.getVarUseType()));
					if (StringUtils.isNotBlank(sysWfVarInfo.getVarName()))
						list.add(cb.equal(root.get("varName"), sysWfVarInfo.getVarName()));
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}

		};
		return varInfoRepository.findAll(spec, pageable);

	}

	@Override
	public Page<SysWfVarInfo> findAll(SysWfVarInfo sysWfVarInfo, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysWfVarInfo> example = Example.of(sysWfVarInfo, matcher); 
		return varInfoRepository.findAll(example,pageable);
	}

	@Override
	public void save(SysWfVarInfo sysWfVarInfo) {
		varInfoRepository.save(sysWfVarInfo);		 
	}

	@Override
	public SysWfVarInfo get(Long id) {
		return varInfoRepository.getOne(id);
	}

	@Override
	public void delete(SysWfVarInfo sysWfVarInfo) {
		varInfoRepository.delete(sysWfVarInfo);		
	}

	@Override
	public int findByVarName(String varName,String fdId) { 
		return varInfoRepository.findByVarName(varName,fdId);
	}

	@Override
	public int findByVarCode(String varCode,String fdId) {
		return varInfoRepository.findByVarCode(varCode,fdId);
	}

	@Override
	public List<SysWfVarInfo> findAll() {
		return varInfoRepository.findAll();
	}

}