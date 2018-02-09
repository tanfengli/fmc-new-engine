package com.vispractice.fmc.business.service.sys.workflow;

import java.util.Date;
import java.util.List;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorize;

public interface ISysWfAuthorizeService {
	
	/**
	 * 获取授权处理记录
	 * @param time
	 * @param fdTemplateId
	 * @return
	 * @throws WorkflowException
	 */
	public List<SysWfAuthorize> getHandlerAuthorize(Date time, String fdTemplateId, String elementId) throws WorkflowException;

	
	/**
	 * 获取授权阅读记录
	 * @param fdTemplateId
	 * @param elementId
	 * @return
	 * @throws WorkflowException
	 */
	public List<SysWfAuthorize> getReaderAuthorize(String fdTemplateId, String elementId) throws WorkflowException;
	
}
