package com.vispractice.fmc.business.entity.sys.workflow.view;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 
 * 编  号：
 * 名  称：SysWfExceptionLogV
 * 描  述：流程异常日志视图
 * 完成日期：2017年8月22日 下午6:02:30
 * 编码作者：LaiJiashen
 */
@Data
@Entity
@Table(name="SYS_WF_EXCEPTION_LOG_V")
public class SysWfExceptionLogV {

	@Id
	@Column(name="FD_ID")
	private String fdId;
	
	/*
	 * 异常原因
	 */
	@Column(name="FD_REASON")
	private String fdReason;
	
	/*
	 * 流程实例ID
	 */
	@Column(name="FD_PROCESS_ID")
	private String fdProcessId;
	
	/*
	 * 当前节点ID
	 */
	@Column(name="FD_FACT_NODE_ID")
	private String fdFactNodeId;
	
	/*
	 * 当前节点名称
	 */
	@Column(name="FD_FACT_NODE_NAME")
	private String fdFactNodeName;
	
	/*
	 * 当前处理人ID
	 */
	@Column(name="FD_HANDLER_ID")
	private String fdHandlerId;
	
	/*
	 * 当前处理人名称
	 */
	@Column(name="FD_HANDLER_NAME")
	private String fdHandlerName;
	
	/*
	 * 创建日期
	 */
	@Column(name="FD_CREATE_TIME")
	private Timestamp fdCreateTime;
	
	/*
	 * 异常详情
	 */
	@Column(name="FD_EXCEPTION")
	private String fdException;
	
	/*
	 * 异常信息
	 */
	@Column(name="FD_MESSAGE")
	private String fdMessage;
	
	/*
	 * 单据主题
	 */
	@Column(name="FD_TITLE")
	private String fdTitle;
	
	/*
	 * 报账人
	 */
	@Column(name="FD_DOC_CREATOR_NAME")
	private String fdDocCreatorName;
	
	/*
	 * 单据编号
	 */
	@Column(name="FD_APPLY_CODE")
	private String fdApplyCode;
	
	/*
	 * 流程状态
	 */
	@Column(name="FD_STATUS")
	private String fdStatus;
	
	/*
	 * 模板ID
	 */
	@Column(name="FD_TEMPLATE_ID")
	private String fdTemplateId;
	
	/*
	 * 模板名称
	 */
	@Column(name="FD_TEMPLATE_NAME")
	private String fdTemplateName;
	
	/*
	 * 业务系统ID
	 */
	@Column(name="FD_BUSI_SYS_ID")
	private String fdBusiSysId;
	
	/*
	 * 业务系统名称
	 */
	@Column(name="FD_BUSI_SYS_NAME")
	private String fdBusiSysName;
	
}
