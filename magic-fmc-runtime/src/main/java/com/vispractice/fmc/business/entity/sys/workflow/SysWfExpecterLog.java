package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the SYS_WF_EXPECTER_LOG database table.
 * 
 */
@Entity
@Table(name="SYS_WF_EXPECTER_LOG")
@NamedQuery(name="SysWfExpecterLog.findAll", query="SELECT s FROM SysWfExpecterLog s")
public class SysWfExpecterLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name="FD_ID")
	private String fdId;

	@Column(name="DOC_CREATE_TIME")
	private Date docCreateTime;

	@Column(name="FD_ACCER_ID")
	private String fdAccerId;

	@Column(name="FD_ACCREDIT_INFOJSON")
	private String fdAccreditInfojson;

	@Column(name="FD_EXPECTER_ID")
	private String fdExpecterId;

	@Column(name="FD_IDENTITY_FLAG")
	private String fdIdentityFlag;

	@Column(name="FD_NODE_ID")
	private String fdNodeId;

	@Column(name="FD_WF_NODE_ID")
	private String fdWfNodeId;

	@Column(name="FD_WF_PROCESS_ID")
	private String fdWfProcessId;

	public SysWfExpecterLog() {
	}

	public String getFdId() {
		return this.fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public Date getDocCreateTime() {
		return this.docCreateTime;
	}

	public void setDocCreateTime(Date docCreateTime) {
		this.docCreateTime = docCreateTime;
	}

	public String getFdAccerId() {
		return this.fdAccerId;
	}

	public void setFdAccerId(String fdAccerId) {
		this.fdAccerId = fdAccerId;
	}

	public String getFdAccreditInfojson() {
		return this.fdAccreditInfojson;
	}

	public void setFdAccreditInfojson(String fdAccreditInfojson) {
		this.fdAccreditInfojson = fdAccreditInfojson;
	}

	public String getFdExpecterId() {
		return this.fdExpecterId;
	}

	public void setFdExpecterId(String fdExpecterId) {
		this.fdExpecterId = fdExpecterId;
	}

	public String getFdIdentityFlag() {
		return this.fdIdentityFlag;
	}

	public void setFdIdentityFlag(String fdIdentityFlag) {
		this.fdIdentityFlag = fdIdentityFlag;
	}

	public String getFdNodeId() {
		return this.fdNodeId;
	}

	public void setFdNodeId(String fdNodeId) {
		this.fdNodeId = fdNodeId;
	}

	public String getFdWfNodeId() {
		return this.fdWfNodeId;
	}

	public void setFdWfNodeId(String fdWfNodeId) {
		this.fdWfNodeId = fdWfNodeId;
	}

	public String getFdWfProcessId() {
		return this.fdWfProcessId;
	}

	public void setFdWfProcessId(String fdWfProcessId) {
		this.fdWfProcessId = fdWfProcessId;
	}

}