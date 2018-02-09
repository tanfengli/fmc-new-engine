package com.vispractice.fmc.business.service.sys.webservice;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.webservice.SysWebServiceLog;


public interface IWebServiceService {
	/**
	 * 保存接口调用日志信息
	 * @param sysWebServiceLog
	 * @throws WorkflowException
	 */
	public void saveWebServiceLog(SysWebServiceLog sysWebServiceLog) throws WorkflowException;
}
