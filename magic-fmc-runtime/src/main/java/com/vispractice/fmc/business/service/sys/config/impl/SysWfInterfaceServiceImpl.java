package com.vispractice.fmc.business.service.sys.config.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.entity.sys.config.SysWfInterface;
import com.vispractice.fmc.business.entity.sys.config.repository.SysWfInterfaceRepository;
import com.vispractice.fmc.business.service.sys.config.ISysBusiSysService;
import com.vispractice.fmc.business.service.sys.config.ISysWfInterfaceService;

@Service
@Transactional
public class SysWfInterfaceServiceImpl implements ISysWfInterfaceService {
	@Autowired
	private SysWfInterfaceRepository sysWfInterfaceRepository;
	
	@Autowired
	private ISysBusiSysService sysBusiSysService;
	
	@Override
	public List<SysWfInterface> findAllElements() {
		return sysWfInterfaceRepository.findAll();
	}
	
	@Override
	public List<SysWfInterface> getRootElements() {
		List<SysWfInterface> sysWfInterfaces = new ArrayList<SysWfInterface>();
		SysWfInterface sysWfInterface = new SysWfInterface();
		sysWfInterface.setFdId("0");
		sysWfInterface.setFdName("流程接口");
		sysWfInterface.setFdIsLeaf(0L);
		sysWfInterfaces.add(sysWfInterface);
		
		return sysWfInterfaces;
	}

	@Override
	public List<SysWfInterface> getSubElements(String fdParentId) {
		if ("0".equals(fdParentId)) {
			return sysWfInterfaceRepository.findRootElements();
		} else {
			return sysWfInterfaceRepository.findByFdParentId(fdParentId);
		}
	}
	
	@Override
	public List<SysWfInterface> findByBusiAndType(String fdBusiId,String fdType) {
		return sysWfInterfaceRepository.findByBusiAndType(fdBusiId,fdType);
	}

	@Override
	public Page<SysWfInterface> searchInterface(SysWfInterface sysWfInterface,Pageable pageable) {
		Specification<SysWfInterface> spec = buildQuery(sysWfInterface,pageable);
		Page<SysWfInterface> sysWfInterfaces = sysWfInterfaceRepository.findAll(spec,pageable);
		
		return sysWfInterfaces;
	}
	
	/**
	 * 单据查询条件
	 */
	public Specification<SysWfInterface> buildQuery(final SysWfInterface sysWfInterface,Pageable pageable) {
		Specification<SysWfInterface> spec = new Specification<SysWfInterface>() {
			@Override
			public Predicate toPredicate(Root<SysWfInterface> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();

				// 接口名称
				if (StringUtils.isNotBlank(sysWfInterface.getFdName())) {
					expressions.add(cb.like(root.<String> get("fdName"),"%" + sysWfInterface.getFdName() + "%"));
				}

				return predicate;
			}
		};
		
		return spec;
	}
	
	/**
	 * 保存接口信息
	 */
	@Override
	public void saveInterface(SysWfInterface sysWfInterface) {
		if (StringUtil.isEmpty(sysWfInterface.getFdIsLeaf())) {
			sysWfInterface.setFdIsLeaf(Long.parseLong("1"));
		}
		
		sysWfInterfaceRepository.save(sysWfInterface);
	}

	@Override
	public void deleteInterface(String fdId) {
		sysWfInterfaceRepository.delete(fdId);
	}

	@Override
	public List<SysWfInterface> findInterface() {
		return sysWfInterfaceRepository.findInterface();
	}

	
	/**
	 * 查看接口授权信息
	 */
	@Override
	public SysWfInterface findByBusiCodeAndInterfaceCode(String sysBusiCode,String sysWfInterfaceCode) {
		SysWfInterface sysWfInterface = sysWfInterfaceRepository.findByBusiCodeAndInterfaceCode(sysBusiCode,sysWfInterfaceCode);
		
		return sysWfInterface;
	}
}
