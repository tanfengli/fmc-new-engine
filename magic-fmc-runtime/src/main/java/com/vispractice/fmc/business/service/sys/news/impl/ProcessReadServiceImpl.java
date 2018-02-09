package com.vispractice.fmc.business.service.sys.news.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.entity.sys.news.repository.ProcessReadVRepository;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessReadV;
import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodotarget;
import com.vispractice.fmc.business.entity.sys.notify.repository.SysNotifyTodotargetRepository;
import com.vispractice.fmc.business.service.sys.news.IProcessReadService;
import com.vispractice.fmc.business.service.sys.notify.ISysNotifyTodoDoneInfoService;

@Transactional
@Service
public class ProcessReadServiceImpl implements IProcessReadService {
	/**
	 * 待阅服务 
	 */
	@Autowired
	private ProcessReadVRepository processReadVRepository;
	
	/**
	 * 待办目标表
	 */
	@Autowired
	private SysNotifyTodotargetRepository sysNotifyTodotargetRepository;
	
	/**
	 * 已办目标表
	 */
	@Autowired
	private ISysNotifyTodoDoneInfoService sysNotifyTodoDoneInfoService;
	
	/**
	 * 查找待阅信息 
	 */
	@Override
	public Page<ProcessReadV> searchProcessRead(ProcessReadV processReadV,Pageable pageable) {
		Specification<ProcessReadV> queryFilter = this.queryFilter(processReadV,pageable);
		Page<ProcessReadV> result = processReadVRepository.findAll(queryFilter,pageable);
		
		return result;
	}
	
	/**
	 * 构造过滤模糊查询 
	 * @param processReadV
	 * @param pageable
	 * @return
	 */
	public Specification<ProcessReadV> queryFilter(final ProcessReadV processReadV,Pageable pageable) {
		Specification<ProcessReadV> specification = new Specification<ProcessReadV>() {
			@Override
			public Predicate toPredicate(Root<ProcessReadV> root,CriteriaQuery<?> query,CriteriaBuilder criteria) {
				Predicate predicate = criteria.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				
				//待阅用户编号
				if (StringUtils.isNotBlank(processReadV.getReadUserNo())) {
					expressions.add(criteria.equal(root.<String> get("readUserNo"),processReadV.getReadUserNo()));
				}
				//单据主题
				if (StringUtils.isNotBlank(processReadV.getApplySubject())) {
					expressions.add(criteria.like(root.<String> get("applySubject"),"%" + processReadV.getApplySubject() + "%"));
				}
				//单据编号
				if (StringUtils.isNotBlank(processReadV.getApplyCode())) {
					expressions.add(criteria.like(root.<String> get("applyCode"),"%" + processReadV.getApplyCode() + "%"));
				}
				//消息
				if (StringUtils.isNotBlank(processReadV.getFdSubject())) {
					expressions.add(criteria.like(root.<String> get("fdSubject"),"%" + processReadV.getFdSubject() + "%"));
				}
				//是否已阅
				if (null!=processReadV.getIsRead()) {
					expressions.add(criteria.equal(root.<Long> get("isRead"),processReadV.getIsRead()));
				}
				return predicate;
			}
		};
		
		return specification;
	}
	
	/**
	 * 统计总计,已阅,未阅
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	@Override
	public Map count(String userNo) {
		Map map = new HashMap();
		Long unreadLong = processReadVRepository.countNum(userNo, new Long(CommonConstant.NOT_AVAILABLE_FLAG));
		Long readLong = processReadVRepository.countNum(userNo, new Long(CommonConstant.AVAILABLE_FLAG));
		Long allLong = processReadVRepository.countAll(userNo);
		map.put("unread",unreadLong);
		map.put("read",readLong);
		map.put("all",allLong);
		
		return map;
	}
	
	/**
	 * 置成已办
	 */
	@Override
	public void setIsRead(String todoId,String userId) throws Exception {
		try {
			SysNotifyTodotarget sysNotifyTodotarget = sysNotifyTodotargetRepository.findByFdTodoidAndFdOrgid(todoId,userId);
			if (null != sysNotifyTodotarget) {
				sysNotifyTodotargetRepository.deleteTodoTarget(todoId,userId);
				sysNotifyTodoDoneInfoService.addOne(todoId,userId);
			}
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
}
