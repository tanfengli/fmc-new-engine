package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfExpecterLog;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfExpecterLogRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfExpecterLogService;

@Service
@Transactional
public class SysWfExpecterLogServiceImpl implements ISysWfExpecterLogService{

	@Autowired
	private SysWfExpecterLogRepository sysWfExpecterLogRepository;
	
	@Override
	public void saveLog(SysWfExpecterLog log) {
		sysWfExpecterLogRepository.save(log);
	}

	@Override
	public void saveLog(List<SysWfExpecterLog> logs) {
		for (SysWfExpecterLog log : logs) {
			sysWfExpecterLogRepository.save(log);
		}		
	}

}
