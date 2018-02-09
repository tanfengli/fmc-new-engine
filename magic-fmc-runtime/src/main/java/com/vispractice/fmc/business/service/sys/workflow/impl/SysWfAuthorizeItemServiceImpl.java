package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorizeItem;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuthorizeItemRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfAuthorizeItemService;

@Service
@Transactional
public class SysWfAuthorizeItemServiceImpl implements ISysWfAuthorizeItemService {

	@Autowired
	private SysWfAuthorizeItemRepository authorizeItemRepository;

	@Override
	public void save(SysWfAuthorizeItem authorizeItem) {
		authorizeItemRepository.insertOne(authorizeItem.getFdAuthorizeId(), authorizeItem.getFdAuthorizeOrgId());
	}

	@Override
	public void deleteByFdAuthorizeId(String fdAuthorizeId) {
		authorizeItemRepository.deleteByFdAuthorizeId(fdAuthorizeId);
	}

	@Override
	public List<SysWfAuthorizeItem> findItemByFdAuthoirzeId(String fdAuthoirzeId) {
		return authorizeItemRepository.findByFdAuthorizeId(fdAuthoirzeId);
	}
	
}