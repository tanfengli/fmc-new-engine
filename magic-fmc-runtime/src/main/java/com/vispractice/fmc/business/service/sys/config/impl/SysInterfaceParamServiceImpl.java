package com.vispractice.fmc.business.service.sys.config.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.config.SysInterfaceParam;
import com.vispractice.fmc.business.entity.sys.config.repository.SysInterfaceParamRepository;
import com.vispractice.fmc.business.service.sys.config.ISysInterfaceParamService;

@Service
@Transactional
public class SysInterfaceParamServiceImpl implements ISysInterfaceParamService {
	@Autowired
	private SysInterfaceParamRepository sysInterfaceParamRepository;

	@Override
	public void saveSysInterfaceParams(List<SysInterfaceParam> sysInterfaceParams,String fdInterfaceId) {
		sysInterfaceParamRepository.deleteByFdInterfaceId(fdInterfaceId);
	
		for (SysInterfaceParam sysInterfaceParam : sysInterfaceParams) {
			sysInterfaceParam.setFdInterfaceId(fdInterfaceId);
			sysInterfaceParamRepository.save(sysInterfaceParam);
		}
	
	}

	@Override
	public List<SysInterfaceParam> findSysInterfaceParam(String fdInterfaceId) {
		return sysInterfaceParamRepository.findByFdInterfaceId(fdInterfaceId);
	}

	@Override
	public SysInterfaceParam findByFdInterfaceIdAndFdName(
			String interfaceId, String fdName) {
		return sysInterfaceParamRepository.findByFdInterfaceIdAndFdName(interfaceId, fdName);
	}
}
