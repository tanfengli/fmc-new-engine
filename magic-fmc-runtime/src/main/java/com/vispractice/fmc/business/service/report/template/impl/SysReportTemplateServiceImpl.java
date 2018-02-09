package com.vispractice.fmc.business.service.report.template.impl;

import java.util.Date;
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

import com.vispractice.fmc.business.entity.report.template.SysReportTemplate;
import com.vispractice.fmc.business.entity.report.template.repositories.SysReportTemplateRepository;
import com.vispractice.fmc.business.service.report.template.ISysReportTemplateService;

@Transactional
@Service
public class SysReportTemplateServiceImpl implements ISysReportTemplateService {
	@Autowired
	private SysReportTemplateRepository sysReportTemplateRepository;
	
	/**
	 *	按模板统计报表 
	 */
	@Override
	public Page<SysReportTemplate> searchSysReportTemplate(SysReportTemplate SysReportTemplate,Pageable pageable) {
		Specification<SysReportTemplate> spec = buildQuery(SysReportTemplate,pageable);
		Page<SysReportTemplate> sysReportTemplates = sysReportTemplateRepository.findAll(spec,pageable);
		
		return sysReportTemplates;
	}
	
	@Override
	public void importSysReportTemplate(Date fdStartDate,Date fdEndDate) {
		sysReportTemplateRepository.importSysReportTemplate(fdStartDate,fdEndDate);
	}



	@Override
	public void exportSysReportTemplate(SysReportTemplate sysReportTemplate) {
	}
	
	
	/**
	 * 报表查询条件
	 */
	public Specification<SysReportTemplate> buildQuery(final SysReportTemplate sysReportTemplate,Pageable pageable) {
		Specification<SysReportTemplate> spec = new Specification<SysReportTemplate>() {
			@Override
			public Predicate toPredicate(Root<SysReportTemplate> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();

				//模板类别名称
				if (StringUtils.isNotBlank(sysReportTemplate.getFdCategoryName())) {
					expressions.add(cb.like(root.<String> get("fdCategoryName"),"%" + sysReportTemplate.getFdCategoryName() + "%"));
				}

				//模板名称
				if (StringUtils.isNotBlank(sysReportTemplate.getFdTemplateName())) {
					expressions.add(cb.like(root.<String> get("fdTemplateName"),"%" + sysReportTemplate.getFdTemplateName() + "%"));
				}
				
				//统计日期
				if (sysReportTemplate.getFdStartDate() != null) {
					expressions.add(cb.greaterThanOrEqualTo(root.<Date> get("fdStartDate"),sysReportTemplate.getFdStartDate()));
				}
				
				if (null != sysReportTemplate.getFdEndDate()) {
					expressions.add(cb.lessThanOrEqualTo(root.<Date> get("fdEndDate"),sysReportTemplate.getFdEndDate()));
				}
				
				return predicate;
			}
		};
		
		return spec;
	}



	
}
