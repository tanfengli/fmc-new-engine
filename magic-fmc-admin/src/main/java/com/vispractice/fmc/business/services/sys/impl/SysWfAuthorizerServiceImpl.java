package com.vispractice.fmc.business.services.sys.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorize;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuthorizeRepository;
import com.vispractice.fmc.business.services.sys.ISysWfAuthorizerService;

@Service
@Transactional
public class SysWfAuthorizerServiceImpl implements ISysWfAuthorizerService {

	@Autowired
	private SysWfAuthorizeRepository wfAuthorizeRepository;

	@Override
	public Page<SysWfAuthorize> findAll(SysWfAuthorize sysWfAuthorize, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysWfAuthorize> example = Example.of(sysWfAuthorize, matcher);
		return wfAuthorizeRepository.findAll(example, pageable);
	}

	@Override
	public SysWfAuthorize save(SysWfAuthorize sysWfAuthorize) {
		return wfAuthorizeRepository.save(sysWfAuthorize);
	}

	@Override
	public SysWfAuthorize get(String id) {
		return wfAuthorizeRepository.getOne(id);
	}

	@Override
	public void deleteByFdId(String fdId) {
		wfAuthorizeRepository.deleteByFdId(fdId);
	}

}