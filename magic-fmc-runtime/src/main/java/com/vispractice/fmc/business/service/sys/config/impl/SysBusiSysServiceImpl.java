package com.vispractice.fmc.business.service.sys.config.impl;
 
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

import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.business.entity.sys.config.repository.SysBusiSysRepository;
import com.vispractice.fmc.business.entity.sys.config.repository.SysBusiSysVRepository;
import com.vispractice.fmc.business.entity.sys.config.view.SysBusiSysV;
import com.vispractice.fmc.business.service.sys.config.ISysBusiSysService;

/**
 * 业务系统标识记录服务实现
 * @author ZhouYanyao
 */
@Service
@Transactional
public class SysBusiSysServiceImpl implements ISysBusiSysService {
	/**
	 * 业务系统记录数据库层服务
	 */
	@Autowired
	private SysBusiSysRepository busiSysRepository;
	
	/**
	 * 业务系统记录数据库服务
	 */
	@Autowired
	private SysBusiSysVRepository busiSysVRepository;
 
	/**
	 * 分页查找所有业务系统记录
	 */
	@Override
	public Page<SysBusiSysV> findAll(SysBusiSysV SysBusiSys,Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysBusiSysV> example = Example.of(SysBusiSys,matcher); 
		
		return busiSysVRepository.findAll(example,pageable);
	}

	/**
	 * 分页搜索所有业务系统记录
	 */
	@Override
	public Page<SysBusiSysV> findBySearch(final SysBusiSysV sysBusiSysV, Pageable pageable) {
		Specification<SysBusiSysV> spec = new Specification<SysBusiSysV>() {
			@Override
			public Predicate toPredicate(Root<SysBusiSysV> root, CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if (null != sysBusiSysV) { 
					if (StringUtils.isNotBlank(sysBusiSysV.getFdName())) {
						list.add(cb.equal(root.get("fdName"),sysBusiSysV.getFdName())); 
					}
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}
		};
		
		return busiSysVRepository.findAll(spec,pageable);
	}
	
	/**
	 * 保存业务系统记录
	 */
	@Override
	public SysBusiSys save(SysBusiSys SysBusiSys) {
		return busiSysRepository.save(SysBusiSys);		 
	}

	/**
	 * 删除业务系统记录
	 */
	@Override
	public void delete(SysBusiSys SysBusiSys) {
		busiSysRepository.delete(SysBusiSys);		
	}

	/**
	 * 根据标识获取业务系统记录
	 */
	@Override
	public SysBusiSys get(String id) { 
		return busiSysRepository.getOne(id);
	}

	/**
	 * 查找所有业务系统记录
	 */
	@Override
	public List<SysBusiSys> findAll() {
		return busiSysRepository.findAll();
	}
	
	@Override
	public List<SysBusiSys> findByFdEnabled(Long fdEnabled){
		return busiSysRepository.findByFdEnabled(fdEnabled);
	}
	
	/**
	 * 根据业务系统编码获取业务系统记录
	 */
	@Override
	public SysBusiSys getByFdCode(String fdCode) {
		return busiSysRepository.getByFdCode(fdCode);
	} 
}