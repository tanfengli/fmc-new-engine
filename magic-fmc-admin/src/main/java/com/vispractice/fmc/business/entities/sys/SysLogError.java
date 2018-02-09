package com.vispractice.fmc.business.entities.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
 
/**
 * 错误日志
 * 编  号：<br/>
 * 名  称：SysLogError<br/>
 * 描  述：<br/>
 * 完成日期：2017年1月11日 下午4:09:31<br/>
 * 编码作者：Administrator<br/>
 */
@Entity
@Table(name="sys_log_error")
@NamedQuery(name="SysLogError.findAll", query="SELECT s FROM SysLogError s")
public class SysLogError implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	 
	@Column(name="FD_CREATE_TIME")
	private Date fdCreateTime;
	
	@Column(name="FD_IP")
	private String fdIp;
	 			
	@Column(name="FD_OPERATOR")
	private String fdOperator;
	
	@Column(name="FD_OPERATOR_ID")
	private String fdOperatorId;
	 	
	@Column(name="FD_URL")
	private String fdUrl;
	
	@Column(name="FD_ERROR_INFO")
	private String fdErrorInfo;

	@Column(name="FD_METHOD")
	private String fdMethod;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public Date getFdCreateTime() {
		return fdCreateTime;
	}

	public void setFdCreateTime(Date fdCreateTime) {
		this.fdCreateTime = fdCreateTime;
	}

	public String getFdIp() {
		return fdIp;
	}

	public void setFdIp(String fdIp) {
		this.fdIp = fdIp;
	}

	public String getFdOperator() {
		return fdOperator;
	}

	public void setFdOperator(String fdOperator) {
		this.fdOperator = fdOperator;
	}

	public String getFdOperatorId() {
		return fdOperatorId;
	}

	public void setFdOperatorId(String fdOperatorId) {
		this.fdOperatorId = fdOperatorId;
	}

	public String getFdUrl() {
		return fdUrl;
	}

	public void setFdUrl(String fdUrl) {
		this.fdUrl = fdUrl;
	}

	public String getFdErrorInfo() {
		return fdErrorInfo;
	}

	public void setFdErrorInfo(String fdErrorInfo) {
		this.fdErrorInfo = fdErrorInfo;
	}

	public String getFdMethod() {
		return fdMethod;
	}

	public void setFdMethod(String fdMethod) {
		this.fdMethod = fdMethod;
	}
}
