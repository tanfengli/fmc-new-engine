package com.vispractice.fmc.business.service.report.node.impl;

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

import com.vispractice.fmc.business.entity.report.node.SysReportNode;
import com.vispractice.fmc.business.entity.report.node.repository.SysReportNodeRepository;
import com.vispractice.fmc.business.service.report.node.ISysReportNodeService;
/**
 * 编  号：
 * 名  称：SysReportNodeServiceImpl
 * 描  述：SysReportNodeServiceImpl.java
 * 完成日期：2017年12月20日 上午10:44:55
 * 编码作者："LaiJiashen"
 */
@Service
@Transactional(readOnly=true)
public class SysReportNodeServiceImpl implements ISysReportNodeService {
	
	@Autowired
	private SysReportNodeRepository sysReportNodeRepository;

	@Override
	public Page<SysReportNode> searchSysReportNode(final SysReportNode sysReportNode, Pageable pageable) {
		Specification<SysReportNode> spec = buildQuery(sysReportNode,pageable);
		Page<SysReportNode> sysReportTemplates = sysReportNodeRepository.findAll(spec,pageable);
		
		return sysReportTemplates;
	}

	/**
	 * 报表查询条件
	 */
	private Specification<SysReportNode> buildQuery(final SysReportNode sysReportNode, Pageable pageable) {
		
		Specification<SysReportNode> spec = new Specification<SysReportNode>() {
			@Override
			public Predicate toPredicate(Root<SysReportNode> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();

				//节点名称
				if (StringUtils.isNotBlank(sysReportNode.getFdFactNodeName())) {
					expressions.add(cb.like(root.<String> get("fdFactNodeName"),"%" + sysReportNode.getFdFactNodeName() + "%"));
				}

				//模板名称
				if (StringUtils.isNotBlank(sysReportNode.getFdTemplateName())) {
					expressions.add(cb.like(root.<String> get("fdTemplateName"),"%" + sysReportNode.getFdTemplateName() + "%"));
				}
				
				//超期
				if (StringUtils.isNotBlank(sysReportNode.getFdOverdueNumber())) {
					expressions.add(cb.greaterThan(root.<String> get("fdOverdueNumber"),sysReportNode.getFdOverdueNumber()));
				}
				
				return predicate;
			}
		};
		
		return spec;
	}

	@Override
	public void importSysReportNode(Date fdStartDate,Date fdEndDate) {
		sysReportNodeRepository.importSysReportNode(fdStartDate,fdEndDate);
	}

	@Override
	public void exportSysReportNode(SysReportNode sysReportNode) {
	}

}
