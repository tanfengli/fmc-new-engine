package com.vispractice.fmc.business.service.sys.workflow;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfExceptionLog;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfExceptionLogV;

public interface ISysWfExceptionLogService {
	
	/**
	 * 保存日志
	 * @param log
	 */
	public void saveExceptionLog(SysWfExceptionLog log);
	
	/**
	 * 依据流程编号查找错误日志
	 * @param processId
	 * @return
	 */
	public SysWfExceptionLog findExceptionLogByProcessId(String processId);
	
	/**
	 * 流程异常日志视图分页查询
	 * @param sysWfExceptionLogV
	 * @param pageable 分页参数
	 * @return 
	 */
	public Page<SysWfExceptionLogV> findBySearch(SysWfExceptionLogV sysWfExceptionLogV, Pageable pageable);
	
}
