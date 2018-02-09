package com.vispractice.fmc.business.service.aboutmyself.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.aboutmyself.AuditBillV;
import com.vispractice.fmc.business.entity.aboutmyself.repository.AuditBillVRepository;
import com.vispractice.fmc.business.service.aboutmyself.IAuditBillVService;

@Service
@Transactional(readOnly=true)
public class AuditBillVServiceImpl implements IAuditBillVService {
	@Autowired
	private AuditBillVRepository auditBillVRepository;
	
	/**
	 * 分页查询已办接口数据
	 * @throws WorkflowException 
	 */
	@Override
	public Page<AuditBillV> searchTask(AuditBillV auditBillV,Pageable pageable) throws WorkflowException {
		Specification<AuditBillV> spec = buildQuery(auditBillV,pageable);
		Page<AuditBillV> page = auditBillVRepository.findAll(spec,pageable);

		return page;
	}

	/**
	 * 已办单据查询条件
	 */
	private Specification<AuditBillV> buildQuery(final AuditBillV auditBillV,Pageable pageable) {
		Specification<AuditBillV> spec = new Specification<AuditBillV>() {
			@Override
			public Predicate toPredicate(Root<AuditBillV> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				//提交人编号
				if (null != auditBillV) {
					if (StringUtils.isNotBlank(auditBillV.getTaskUserNo())) {
						list.add(cb.equal(root.get("taskUserNo"),auditBillV.getTaskUserNo()));
					}
				}
				
				//申请主题
				if (StringUtils.isNotBlank(auditBillV.getDocSubject())) {
					list.add(cb.like(root.<String>get("docSubject"),"%" + auditBillV.getDocSubject() + "%"));
				}
				
				//申请单号
				if (StringUtils.isNotBlank(auditBillV.getApplyCode())) {
					list.add(cb.like(root.<String>get("applyCode"),"%" + auditBillV.getApplyCode() + "%"));
				}
				
				//节点名称
				if (StringUtils.isNotBlank(auditBillV.getNodeName())) {
					list.add(cb.like(root.<String>get("nodeName"),"%" + auditBillV.getNodeName() + "%"));
				}
				
				//处理人名称
				if (StringUtils.isNotBlank(auditBillV.getDealName())) {
					list.add(cb.like(root.<String>get("dealName"),"%" + auditBillV.getDealName() + "%"));
				}
				
				// 单据状态
				if (StringUtils.isNotBlank(auditBillV.getDocStatus())) {
					list.add(cb.equal(root.<String> get("docStatus"),auditBillV.getDocStatus()));
				}
				
				// 开始时间
				if (null != auditBillV.getDocCreateTime()) {
					list.add(cb.greaterThanOrEqualTo(root.<Date> get("docCreateTime"),auditBillV.getDocCreateTime()));
				}
				
				// 结束时间
				if (null != auditBillV.getDocPublishTime()) {
					list.add(cb.lessThanOrEqualTo(root.<Date> get("docCreateTime"),auditBillV.getDocPublishTime()));
				}
				
				// 申请原因
				if (StringUtils.isNotBlank(auditBillV.getFdDescription())) {
					list.add(cb.like(root.<String> get("fdDescription"),"%" + auditBillV.getFdDescription() + "%"));
				}
				
				// 申请内容
				if (StringUtils.isNotBlank(auditBillV.getDocContent())) {
					list.add(cb.like(root.<String> get("docContent"),"%" + auditBillV.getDocContent() + "%"));
				}
				
				// 紧急程度
				if (null!=auditBillV.getFdImportance()) {
					list.add(cb.equal(root.<Long> get("fdImportance"),auditBillV.getFdImportance()));
				}

				Predicate[] p = new Predicate[list.size()];

				return cb.and(list.toArray(p));
			}
		};
		
		return spec;
	}
}
