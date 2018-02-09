package com.vispractice.fmc.business.entity.sys.news.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "process_log_v")
public class ProcessLogV implements Serializable {
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
	 * 节点标识
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
	@Column(name="wf_op_user_no")
	private String wfOpUserNo;
	
	/**
	 * 审批人名称
	 */
	@Column(name="wf_op_user_name")
	private String wfOpUserName;
	
	/**
	 * 操作编码
	 */
	@Column(name="wf_op_code")
	private String wfOpCode;
	
	/**
	 * 操作名称
	 */
	@Column(name="wf_op_name")
	private String wfOpName;
	
	/**
	 * 操作信息
	 */
	@Column(name="wf_op_info")
	private String wfOpInfo;
	
	
	/**
	 * 操作日期
	 */
	@Column(name="wf_op_time")
	private Date wfOpTime;
	
	
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

	public String getWfOpUserNo() {
		return wfOpUserNo;
	}

	public void setWfOpUserNo(String wfOpUserNo) {
		this.wfOpUserNo = wfOpUserNo;
	}

	public String getWfOpUserName() {
		return wfOpUserName;
	}

	public void setWfOpUserName(String wfOpUserName) {
		this.wfOpUserName = wfOpUserName;
	}

	public String getWfOpCode() {
		return wfOpCode;
	}

	public void setWfOpCode(String wfOpCode) {
		this.wfOpCode = wfOpCode;
	}

	public String getWfOpName() {
		return wfOpName;
	}

	public void setWfOpName(String wfOpName) {
		this.wfOpName = wfOpName;
	}

	public String getWfOpInfo() {
		return wfOpInfo;
	}

	public void setWfOpInfo(String wfOpInfo) {
		this.wfOpInfo = wfOpInfo;
	}

	public Date getWfOpTime() {
		return wfOpTime;
	}

	public void setWfOpTime(Date wfOpTime) {
		this.wfOpTime = wfOpTime;
	}
}
