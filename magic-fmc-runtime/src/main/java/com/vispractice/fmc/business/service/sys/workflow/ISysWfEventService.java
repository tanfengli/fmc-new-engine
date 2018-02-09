/******************************************************************
 *
 *    Package:     com.vispractice.fmc.business.service.sys.workflow
 *
 *    Filename:    ISysWfEventService.java
 *
 *    Description: TODO(用一句话描述该文件做什么)
 *
 *    @author:     sky
 *
 *    @version:    1.0.0
 *
 *    Create at:   May 11, 2017
 *
 *
 *****************************************************************/
package com.vispractice.fmc.business.service.sys.workflow;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfEvent;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;

/**
 * 
 * @ClassName ISysWfEventService 流程代处理事件服务接口
 * @author sky
 * @Date May 15, 2017
 * @version 1.0.0
 */
public interface ISysWfEventService {

	/**
	 * 增加引擎代办事件
	 * 
	 * @param fdProcessId
	 *            流程号
	 * @param fdNodeId
	 *            当前节点
	 * @param fdUserId
	 *            处理人
	 * @param fdParameter
	 *            流程参数
	 * @param fdPriority
	 *            优先级 
	 */
	public void addEvent(ProcessContext context, String fdNodeId, String fdUserId, Long fdPriority)
			throws WorkflowException;
	
	public void addEvent(SysWfEvent sysWfEvent)throws WorkflowException;
	
	
	/**
	 * 
	 * 删除指定ID的事件
	 * 
	 * @param eventId
	 *            事件ID
	 */
	public void delete(SysWfEvent event) throws WorkflowException;
	
	/**
	 * 依据Id取流程事件
	 * @param fdId ID号
	 * @return 事件实例
	 */
	public SysWfEvent getEventById(String fdId);
	
	
}
