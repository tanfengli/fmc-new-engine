package com.vispractice.fmc.business.entity.sys.news.view;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "process_history_v")
public class ProcessHistoryV implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 唯一标识
	 */
	@Id
	@Column(name="fd_id")
	private String fdId;
	
	/**
	 * 流程实例标识
	 */
	@Column(name="wf_instance_id")
	private String wfInstanceId;
	
	/**
	 * 节点编号
	 */
	@Column(name="wf_node_id")
	private String wfNodeId;
	
	/**
	 * 节点名称
	 */
	@Column(name="wf_node_name")
	private String wfNodeName;
	
	/**
	 * 审批人编号
	 */
	@Column(name="wf_audit_user_no")
	private String wfAuditUserNo;
	
	/**
	 * 审批人名称
	 */
	@Column(name="wf_audit_user_name")
	private String wfAuditUserName;
	
	/**
	 * 审批意见
	 */
	@Column(name="wf_audit_mind")
	private String wfAuditMind;
	
	/**
	 * 审批结果
	 */
	@Column(name="wf_audit_result")
	private String wfAuditResult;
	
	/**
	 * 审批日期
	 */
	@Column(name="wf_audit_date")
	private Timestamp wfAuditDate;
	
	/**
	 * 审批时差
	 */
	@Column(name="wf_audit_spacing_interval")
	private Long wfAuditSpacingInterval;
	
	public String getFdId() {
		return fdId;
	}
	
	public void setFdId(String fdId) {
		this.fdId = fdId;
	}
	
	public String getWfInstanceId() {
		return wfInstanceId;
	}
	
	public void setWfInstanceId(String wfInstanceId) {
		this.wfInstanceId = wfInstanceId;
	}
	
	public String getWfNodeId() {
		return wfNodeId;
	}
	
	public void setWfNodeId(String wfNodeId) {
		this.wfNodeId = wfNodeId;
	}
	
	public String getWfNodeName() {
		return wfNodeName;
	}
	
	public void setWfNodeName(String wfNodeName) {
		this.wfNodeName = wfNodeName;
	}
	
	public String getWfAuditUserNo() {
		return wfAuditUserNo;
	}
	
	public void setWfAuditUserNo(String wfAuditUserNo) {
		this.wfAuditUserNo = wfAuditUserNo;
	}
	
	public String getWfAuditUserName() {
		return wfAuditUserName;
	}
	
	public void setWfAuditUserName(String wfAuditUserName) {
		this.wfAuditUserName = wfAuditUserName;
	}
	
	public String getWfAuditMind() {
		return wfAuditMind;
	}
	
	public void setWfAuditMind(String wfAuditMind) {
		this.wfAuditMind = wfAuditMind;
	}
	
	public String getWfAuditResult() {
		return wfAuditResult;
	}
	
	public void setWfAuditResult(String wfAuditResult) {
		this.wfAuditResult = wfAuditResult;
	}
	
	
	public Timestamp getWfAuditDate() {
		return wfAuditDate;
	}

	public void setWfAuditDate(Timestamp wfAuditDate) {
		this.wfAuditDate = wfAuditDate;
	}

	public Long getWfAuditSpacingInterval() {
		return wfAuditSpacingInterval;
	}
	
	public void setWfAuditSpacingInterval(Long wfAuditSpacingInterval) {
		this.wfAuditSpacingInterval = wfAuditSpacingInterval;
	}
}
