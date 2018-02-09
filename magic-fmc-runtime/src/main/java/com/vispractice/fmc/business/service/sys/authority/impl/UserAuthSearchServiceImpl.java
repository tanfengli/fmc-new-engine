package com.vispractice.fmc.business.service.sys.authority.impl;

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

import com.vispractice.fmc.business.entity.sys.authority.UserAuthorityV;
import com.vispractice.fmc.business.entity.sys.authority.repository.UserAuthorityVRepository;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.service.sys.authority.IUserAuthSearchService;

/**
 * 
 * 编  号：
 * 名  称：UserAuthSearchServiceImpl
 * 描  述：用户权限查询实现类
 * 完成日期：2017年10月13日 上午11:04:54
 * 编码作者："LaiJiashen"
 */
@Service
@Transactional
public class UserAuthSearchServiceImpl implements IUserAuthSearchService{

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;
	
	@Autowired
	private UserAuthorityVRepository userAuthorityVRepository;
	
	/**
	 * 获取有效用户
	 * @return
	 */
	@Override
	public Page<SysOrgElement> findUser(final SysOrgElement element ,Pageable pageable) {
		
		Page<SysOrgElement> page = null;
		
		Specification<SysOrgElement> spec = new Specification<SysOrgElement>() {
			@Override
			public Predicate toPredicate(Root<SysOrgElement> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				//员工名称/编号
				if (StringUtils.isNotBlank(element.getFdName())) {
					Predicate f1 = cb.like(root.<String>get("fdName"), "%"+element.getFdName()+"%");
					expressions.add(f1);
				}
				//员工名称/编号
				if (StringUtils.isNotBlank(element.getFdNo())) {
					Predicate f1 = cb.like(root.<String>get("fdNo"), "%"+element.getFdNo()+"%");
					expressions.add(f1);
				}
				
				Predicate p = cb.equal(root.<Long>get("fdOrgType"), 8L);
				expressions.add(p);
				Predicate a = cb.equal(root.<Long>get("fdIsAvailable"), 1L);
				expressions.add(a);
				return predicate;
			}
		};
		
		page = sysOrgElementRepository.findAll(spec,pageable);
		
		return page;
	}
	
	@Override
	public Page<UserAuthorityV> findUserAuthority(String orgId,Pageable pageable) {
		Page<UserAuthorityV> page = null;
		if (StringUtils.isNotEmpty(orgId)) {
			page = userAuthorityVRepository.findByUserOrgId(orgId, pageable);
		}
		return page;
	}
	
	
}
