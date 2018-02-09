package com.vispractice.fmc.business.service.sys.org.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRoleService;

@Service
@Transactional
public class SysOrgRoleServiceImpl implements ISysOrgRoleService {

	@Autowired
	private SysOrgRoleRepository orgRoleRepository;

	@Override
	public Page<SysOrgRole> findAll(SysOrgRole orgRole, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreNullValues();
		Example<SysOrgRole> example = Example.of(orgRole, matcher);
		return orgRoleRepository.findAll(example, pageable);
	}

	@Override
	public void save(SysOrgRole orgRole) {
		orgRoleRepository.save(orgRole);
	}

	@Override
	public void delete(SysOrgRole orgRole) {
		orgRoleRepository.delete(orgRole);
	}

	@Override
	public SysOrgRole get(String id) {
		
		SysOrgRole role = orgRoleRepository.getOne(id);
		
		return role;

	}

}