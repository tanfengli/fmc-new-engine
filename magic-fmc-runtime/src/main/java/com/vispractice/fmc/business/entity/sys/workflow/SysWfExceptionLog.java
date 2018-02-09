package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;


/**
 * 
 * 编  号：
 * 名  称：SysWfExceptionLog
 * 描  述：流程异常日志实体
 * 完成日期：2017年8月22日 下午5:50:04
 * 编码作者："LaiJiashen"
 */
@SuppressWarnings("serial")
@Data
@Entity
@Table(name="SYS_WF_EXCEPTION_LOG")
public class SysWfExceptionLog implements Serializable{
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_REASON")
	private String fdReason;
	
	@Column(name="FD_PROCESS_ID")
	private String fdProcessId;
	
	@Column(name="FD_FACT_NODE_ID")
	private String fdFactNodeId;
	
	@Column(name="FD_FACT_NODE_NAME")
	private String fdFactNodeName;
	
	@Column(name="FD_HANDLER_ID")
	private String fdHandlerId;
	
	@Column(name="FD_HANDLER_NAME")
	private String fdHandlerName;
	
	@Column(name="FD_CREATE_TIME")
	private Date fdCreateTime;
	
	@Column(name="FD_EXCEPTION")
	private String fdException;
	
	@Column(name="FD_MESSAGE")
	private String fdMessage;
	
}
