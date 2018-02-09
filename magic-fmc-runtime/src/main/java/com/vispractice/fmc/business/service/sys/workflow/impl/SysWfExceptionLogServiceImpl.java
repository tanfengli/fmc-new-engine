package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfExceptionLog;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfExceptionLogRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfExceptionLogVRepository;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfExceptionLogV;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfExceptionLogService;

@Service
@Transactional
public class SysWfExceptionLogServiceImpl implements ISysWfExceptionLogService {

	@Autowired
	private SysWfExceptionLogRepository sysWfExceptionLogRepository;
	
	@Autowired
	private SysWfExceptionLogVRepository sysWfExceptionLogVRepository;
	
	@Override
	public void saveExceptionLog(SysWfExceptionLog log) {
		sysWfExceptionLogRepository.save(log);
	}
	
	@Override
	public Page<SysWfExceptionLogV> findBySearch(final SysWfExceptionLogV sysWfExceptionLogV,Pageable pageable) {
		Specification<SysWfExceptionLogV> spec = new Specification<SysWfExceptionLogV>() {
			@Override
			public Predicate toPredicate(Root<SysWfExceptionLogV> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if (null != sysWfExceptionLogV) { 
					if (StringUtils.isNotBlank(sysWfExceptionLogV.getFdTitle()))
						list.add(cb.equal(root.get("fdTitle"), sysWfExceptionLogV.getFdTitle())); 
					
					if (StringUtils.isNotBlank(sysWfExceptionLogV.getFdApplyCode()))
						list.add(cb.equal(root.get("fdApplyCode"), sysWfExceptionLogV.getFdApplyCode())); 
					
					if (StringUtils.isNotBlank(sysWfExceptionLogV.getFdFactNodeName()))
						list.add(cb.equal(root.get("fdFactNodeName"), sysWfExceptionLogV.getFdFactNodeName())); 
					
					if (StringUtils.isNotBlank(sysWfExceptionLogV.getFdHandlerName()))
						list.add(cb.equal(root.get("fdHandlerName"), sysWfExceptionLogV.getFdHandlerName())); 
				}
				Predicate[] p = new Predicate[list.size()];
				
				
				Order order = new OrderImpl(root.get("fdCreateTime"),false);
				query.orderBy(order);
				return cb.and(list.toArray(p));
			}
		};
		
		return sysWfExceptionLogVRepository.findAll(spec,pageable);
	}

	@Override
	public SysWfExceptionLog findExceptionLogByProcessId(String processId) {
		return sysWfExceptionLogRepository.findByFdProcessId(processId);
	}

}
