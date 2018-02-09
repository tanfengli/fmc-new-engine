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

import com.vispractice.fmc.business.entity.sys.config.WfMonitorTimeWork;
import com.vispractice.fmc.business.entity.sys.config.repository.WfMonitorTimeMonthRepository;
import com.vispractice.fmc.business.entity.sys.config.repository.WfMonitorTimeWorkRepository;
import com.vispractice.fmc.business.entity.sys.config.repository.WfMonitorTimeWorkVRepository;
import com.vispractice.fmc.business.entity.sys.config.view.WfMonitorTimeWorkV;
import com.vispractice.fmc.business.service.sys.config.IWfMonitorTimeWorkService;

/**
 * 
 * 编  号：
 * 名  称：WfMonitorTimeWorkServiceImpl
 * 描  述：WfMonitorTimeWorkServiceImpl.java
 * 完成日期：2017年12月7日 下午4:23:10
 * 编码作者："LaiJiashen"
 */
@Service
@Transactional
public class WfMonitorTimeWorkServiceImpl implements IWfMonitorTimeWorkService{

	@Autowired
	private WfMonitorTimeWorkVRepository wfMonitorTimeWorkVRepository;
	
	@Autowired
	private WfMonitorTimeWorkRepository wfMonitorTimeWorkRepository;
	
	@Autowired
	private WfMonitorTimeMonthRepository wfMonitorTimeMonthRepository;
	
	@Override
	public Page<WfMonitorTimeWorkV> findAll(final WfMonitorTimeWorkV wfMonitorTimeWorkV, Pageable pageable) {
		Specification<WfMonitorTimeWorkV> spec = new Specification<WfMonitorTimeWorkV>() {
			@Override
			public Predicate toPredicate(Root<WfMonitorTimeWorkV> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();

				// 工作日历名称
				if (StringUtils.isNotBlank(wfMonitorTimeWorkV.getFdName())) {
					expressions.add(cb.like(root.<String> get("fdName"),"%" + wfMonitorTimeWorkV.getFdName() + "%"));
				}
				
				// 工作日历名称
				if (StringUtils.isNotBlank(wfMonitorTimeWorkV.getFdCalendarName())) {
					expressions.add(cb.like(root.<String> get("fdCalendarName"),"%" + wfMonitorTimeWorkV.getFdCalendarName() + "%"));
				}

				return predicate;
			}
		};
		
		Page<WfMonitorTimeWorkV> page = wfMonitorTimeWorkVRepository.findAll(spec, pageable);
		return page;
	}

	@Override
	public void save(WfMonitorTimeWork wfMonitorTimeWork) {
		
		if (StringUtils.isNotEmpty(wfMonitorTimeWork.getFdId())) {
			wfMonitorTimeMonthRepository.deleteByFdTimeId(wfMonitorTimeWork.getFdId());
		}
		wfMonitorTimeWorkRepository.save(wfMonitorTimeWork);
		for (String month : wfMonitorTimeWork.getFdMonthList()) {
			wfMonitorTimeMonthRepository.insertOne(wfMonitorTimeWork.getFdId(), month);
		}
		
	}

	@Override
	public void deleteOne(String fdId) {
		wfMonitorTimeMonthRepository.deleteByFdTimeId(fdId);
		wfMonitorTimeWorkRepository.delete(fdId);
		
	}

}
