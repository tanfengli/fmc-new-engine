package com.vispractice.fmc.business.service.callback.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entity.callback.SysCallBackInfo;
import com.vispractice.fmc.business.entity.callback.repository.SysCallBackInfoRepository;
import com.vispractice.fmc.business.service.callback.ISysCallBackInfoService;
@Service
@Transactional(readOnly=true)
public class SysCallBackInfoServiceImpl implements ISysCallBackInfoService {

	@Autowired
	private SysCallBackInfoRepository sysCallBackInfoRepository;
	
	@Override
	public void save(SysCallBackInfo sysCallBackInfo) {
		sysCallBackInfoRepository.save(sysCallBackInfo);
		
	}

	@Override
	public void delete(SysCallBackInfo sysCallBackInfo) {
		 
		sysCallBackInfoRepository.delete(sysCallBackInfo);
	}

	@Override
	public void deleteSysCallBackInfoByInstanceId(String fdId) {
		sysCallBackInfoRepository.deleteSysCallBackInfoByInstanceId(fdId);
		
	}

	@Override
	public List<SysCallBackInfo> querySysCallBackInfoList() {
		return sysCallBackInfoRepository.querySysCallBackInfoList();
	}

	@Override
	public List<SysCallBackInfo> findAll() {
		return sysCallBackInfoRepository.findAll();
	}

}
