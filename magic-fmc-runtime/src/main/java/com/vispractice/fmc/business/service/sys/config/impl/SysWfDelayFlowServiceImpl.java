package com.vispractice.fmc.business.service.sys.config.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.config.SysWfDelayFlow;
import com.vispractice.fmc.business.entity.sys.config.repository.SysWfDelayFlowRepository;
import com.vispractice.fmc.business.entity.sys.config.repository.SysWfDelayVRepository;
import com.vispractice.fmc.business.entity.sys.config.view.SysWfDelayV;
import com.vispractice.fmc.business.service.sys.config.ISysWfDelayFlowService;

/**
 * 
 * 编  号：
 * 名  称：SysWfDelayFlowServiceImpl
 * 描  述：SysWfDelayFlowServiceImpl.java
 * 完成日期：2017年12月7日 下午7:59:37
 * 编码作者："LaiJiashen"
 */
@Service
@Transactional
public class SysWfDelayFlowServiceImpl implements ISysWfDelayFlowService{

	@Autowired
	private SysWfDelayFlowRepository sysWfDelayFlowRepository;
	
	@Autowired
	private SysWfDelayVRepository sysWfDelayVRepository;
	
	@Override
	public Page<SysWfDelayV> findAll(final SysWfDelayV sysWfDelayV, Pageable pageable) {
		Specification<SysWfDelayV> spec = new Specification<SysWfDelayV>() {
			@Override
			public Predicate toPredicate(Root<SysWfDelayV> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();

				// 工作日历名称
				if (StringUtils.isNotBlank(sysWfDelayV.getFdTemplateName())) {
					expressions.add(cb.like(root.<String> get("fdTemplateName"),"%" + sysWfDelayV.getFdTemplateName() + "%"));
				}
				

				return predicate;
			}
		};
		
		Page<SysWfDelayV> page = sysWfDelayVRepository.findAll(spec, pageable);
		return page;
	}

	@Override
	public void save(SysWfDelayFlow sysWfDelayFlow) {
		sysWfDelayFlowRepository.save(sysWfDelayFlow);
	}

	@Override
	public void deleteOne(String fdId) {
		sysWfDelayFlowRepository.delete(fdId);
		
	}

}
