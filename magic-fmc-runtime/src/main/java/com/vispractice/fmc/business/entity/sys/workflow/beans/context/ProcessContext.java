package com.vispractice.fmc.business.entity.sys.workflow.beans.context;

import lombok.Data;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;

@Data
public class ProcessContext {
	/**
	 * 当前操作人
	 */
	private SysOrgPerson currentUser;
	
	/**
	 * 流程实例业务信息
	 */
	private SysNewsMain sysNewsMain;
	
	/**
	 * 流程实例信息
	 */
	private SysWfProcess sysWfProcess;
	
	/**
	 * 当前工作项参数
	 */
	private String fdWorkItemParameter = "<workitem-param></workitem-param>";

	/**
	 * 当前处理流程节点
	 */
	private String fdHandleNodeId;

	/**
	 * 当前处理工作项
	 */
	private String fdHandleWorkItemId;
	
	/**
	 * 人工决策下一节点
	 */
	private String futureNodeId;
	
	/**
	 * 人工决策下一节点处理人编号
	 */
	private String futureHandIds;
	
	/**
	 * 人工决策下一节点处理人名称
	 */
	private String futureHandNames;
	
	/**
	 * 审批意见
	 */
	private String approvalOpinion;
	
	/**
	 * 是否运行流程标记
	 */
	private String canStartProcess = "true";

	/**
	 * 处理信息
	 */
	private String fdProcessorInfoXML = null;
}
