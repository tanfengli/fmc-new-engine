package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfExpecterLog;

public interface ISysWfExpecterLogService {
	
	/**
	 * 保存单条log
	 * @param log
	 */
	public void saveLog(SysWfExpecterLog log);
	
	/**
	 * 保存多条log
	 * @param log
	 */	
	public void saveLog(List<SysWfExpecterLog> log);
}
