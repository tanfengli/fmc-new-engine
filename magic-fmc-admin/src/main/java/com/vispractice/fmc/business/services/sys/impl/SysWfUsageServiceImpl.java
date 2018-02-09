package com.vispractice.fmc.business.services.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfUsage;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfUsageRepository;
import com.vispractice.fmc.business.services.sys.ISysWfUsageService;

@Service
@Transactional
public class SysWfUsageServiceImpl implements ISysWfUsageService {

	@Autowired
	private SysWfUsageRepository sysWfUsageRepository;
	
	@Override
	public Page<SysWfUsage> findBySearch(final SysWfUsage sysWfUsage, Pageable pageable) {
		
		Specification<SysWfUsage> spec = new Specification<SysWfUsage>() {

			@Override
			public Predicate toPredicate(Root<SysWfUsage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(null != sysWfUsage){ 
					if (StringUtils.isNotBlank(sysWfUsage.getFdUsageType()))
						list.add(cb.equal(root.get("fdUsageType"), sysWfUsage.getFdUsageType()));
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}

		};
		return sysWfUsageRepository.findAll(spec, pageable);

	}

	@Override
	public Page<SysWfUsage> findAll(SysWfUsage sysWfUsage, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysWfUsage> example = Example.of(sysWfUsage, matcher); 
		return sysWfUsageRepository.findAll(example,pageable);
	}

	@Override
	public void save(SysWfUsage sysWfUsage) {
		sysWfUsageRepository.save(sysWfUsage);		 
	}

	@Override
	public SysWfUsage get(String id) {
		return sysWfUsageRepository.getOne(id);
	}

	@Override
	public void delete(SysWfUsage sysWfUsage) {
		sysWfUsageRepository.delete(sysWfUsage);		
	}
	
	@Override
	public void deleteById(String fdId) {
		sysWfUsageRepository.delete(fdId);		
	}

	@Override
	public int findByFdUsageTypeAndFdIsSysSetup(String fdUsageType,
			String fdIsSysSetup,String fdId) { 
		return sysWfUsageRepository.findByFdUsageTypeAndFdIsSysSetup(fdUsageType,fdIsSysSetup,fdId);
	}
	
	
	@Override
	public Map<String,List<SysWfUsage>> noPageFind(){
		List<SysWfUsage> list = sysWfUsageRepository.findByFdActiveFlag(CommonConstant.AVAILABLE_FLAG);
		Map<String,List<SysWfUsage>> map = new HashMap<String,List<SysWfUsage>>();
		for(SysWfUsage a : list){
			String type = a.getFdUsageType();
			//审批类型为空时跳过
			if(null==type)
				continue;
			//根据审批类型分类
			if(map.containsKey(typeTran(type))){
				map.get(typeTran(type)).add(a);
			}else{
				List<SysWfUsage> list_type = new ArrayList<SysWfUsage>();
				list_type.add(a);
				map.put(typeTran(type),list_type);
			}
		}
		
		return map;
	}
	
	/**
	 * 
	 * 审批类型编码转换
	 * @Title: typeTran 
	 * @param type
	 * @return
	 */
	private String typeTran(String type){
		switch(type){
		case ("1"):
			return SysWfUsage.REJECT;
		case ("2"):
			return SysWfUsage.TRANFOR;
		case ("3"):
			return SysWfUsage.PASS;
		case ("4"):
			return SysWfUsage.COMMUNICATE;
		case ("5"):
			return SysWfUsage.ABANDON;
		default :
			return type;
		}
	}
}