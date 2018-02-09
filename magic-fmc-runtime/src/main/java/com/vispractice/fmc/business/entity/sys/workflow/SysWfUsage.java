package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable; 
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue; 
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="sys_wf_usage")
@NamedQuery(name="SysWfUsage.findAll", query="SELECT s FROM SysWfUsage s")
public class SysWfUsage implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	/**
	 * 审批类型：通过
	 */
	public final static String PASS="pass";
	/**
	 * 审批类型：驳回
	 */
	public final static String REJECT="reject";
	/**
	 * 审批类型：转办
	 */
	public final static String TRANFOR="tranfor";
	/**
	 * 审批类型：沟通
	 */
	public final static String COMMUNICATE="communicate";
	/**
	 * 审批类型：废弃
	 */
	public final static String ABANDON="abandon";

	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_USAGE_CONTENT")
	private String fdUsageContent; 
	
	@Column(name="FD_IS_SYS_SETUP")
	private String fdIsSysSetup;
	 	
	@Column(name="FD_CREATE_TIME")
	private Date fdCreateTime; 
	
	@Column(name="FD_CREATOR_ID")
	private String fdCreatorId; 

	@Column(name="FD_USAGE_TYPE")
	private String fdUsageType;

	@Column(name="FD_ACTIVE_FLAG")
	private String fdActiveFlag;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdUsageContent() {
		return fdUsageContent;
	}

	public void setFdUsageContent(String fdUsageContent) {
		this.fdUsageContent = fdUsageContent;
	}
 
	public Date getFdCreateTime() {
		return fdCreateTime;
	}

	public void setFdCreateTime(Date fdCreateTime) {
		this.fdCreateTime = fdCreateTime;
	}

	public String getFdCreatorId() {
		return fdCreatorId;
	}

	public void setFdCreatorId(String fdCreatorId) {
		this.fdCreatorId = fdCreatorId;
	}

	public String getFdUsageType() {
		return fdUsageType;
	}

	public void setFdUsageType(String fdUsageType) {
		this.fdUsageType = fdUsageType;
	}

	public String getFdIsSysSetup() {
		return fdIsSysSetup;
	}

	public void setFdIsSysSetup(String fdIsSysSetup) {
		this.fdIsSysSetup = fdIsSysSetup;
	}

	public String getFdActiveFlag() {
		return fdActiveFlag;
	}

	public void setFdActiveFlag(String fdActiveFlag) {
		this.fdActiveFlag = fdActiveFlag;
	} 
  
}
