package com.vispractice.fmc.business.service.sys.webservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.webservice.SysWebServiceLog;
import com.vispractice.fmc.business.entity.sys.webservice.repository.SysWebServiceLogRepository;
import com.vispractice.fmc.business.service.sys.webservice.IWebServiceService;

@Transactional
@Service
public class WebServiceServiceImpl implements IWebServiceService {
	/**
	 * 接口调用日志信息
	 */
	@Autowired
	private SysWebServiceLogRepository sysWebServiceLogService;
	
	/**
	 * 保存接口调用日志信息
	 */
	@Override
	public void saveWebServiceLog(SysWebServiceLog sysWebServiceLog) throws WorkflowException {
		sysWebServiceLogService.save(sysWebServiceLog);
	}
}
