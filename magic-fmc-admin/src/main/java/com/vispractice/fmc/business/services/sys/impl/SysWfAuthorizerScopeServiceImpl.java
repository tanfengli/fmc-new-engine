package com.vispractice.fmc.business.services.sys.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorizeScope;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuthorizeScopeRepository;
import com.vispractice.fmc.business.services.sys.ISysWfAuthorizeScopeService;

@Service
@Transactional
public class SysWfAuthorizerScopeServiceImpl implements ISysWfAuthorizeScopeService {

	@Autowired
	private SysWfAuthorizeScopeRepository authorizeScopeRepository;

	@Override
	public Page<SysWfAuthorizeScope> findAll(SysWfAuthorizeScope SysWfAuthorizeScope, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysWfAuthorizeScope> example = Example.of(SysWfAuthorizeScope, matcher);
		return authorizeScopeRepository.findAll(example, pageable);
	}

	@Override
	public void save(SysWfAuthorizeScope SysWfAuthorizeScope) {
		authorizeScopeRepository.save(SysWfAuthorizeScope);
	}

	@Override
	public SysWfAuthorizeScope get(String id) {
		return authorizeScopeRepository.getOne(id);
	}

	@Override
	public void deleteByfdAuthorizeId(String fdId) {
		authorizeScopeRepository.deleteByfdAuthorizeId(fdId);
	}

}