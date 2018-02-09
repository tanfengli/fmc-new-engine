package com.vispractice.fmc.business.service.sys.config.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entity.sys.config.SysParam;
import com.vispractice.fmc.business.entity.sys.config.repository.SysParamRepository;
import com.vispractice.fmc.business.service.sys.config.ISysParamService;

@Service
@Transactional
public class SysParamServiceImpl implements ISysParamService{

	@Autowired
	private SysParamRepository sysParamRepository;
	
	@Override
	public List<SysParam> findByFdCodeIn(List<String> fdCodeList) {
		return sysParamRepository.findByFdCodeIn(fdCodeList);
	}

	@Override
	public void updateFdValueByFdCode(String fdCode, String fdValue) {
		sysParamRepository.updateFdValueByFdCode(fdCode, fdValue);
	}

}
