package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SYS_WF_WORKITEM_DATA database table.
 * 
 */
@Entity
@Table(name="SYS_WF_WORKITEM_DATA")
@NamedQuery(name="SysWfWorkitemData.findAll", query="SELECT s FROM SysWfWorkitemData s")
public class SysWfWorkitemData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="FD_ID")
	private String fdId;

	@Lob
	@Column(name="FD_DATA")
	private String fdData;

	@Column(name="FD_USER_ID")
	private String fdUserId;

	@Column(name="FD_WORKITEM_ID")
	private String fdWorkitemId;

	public SysWfWorkitemData() {
	}

	public String getFdId() {
		return this.fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdData() {
		return this.fdData;
	}

	public void setFdData(String fdData) {
		this.fdData = fdData;
	}

	public String getFdUserId() {
		return this.fdUserId;
	}

	public void setFdUserId(String fdUserId) {
		this.fdUserId = fdUserId;
	}

	public String getFdWorkitemId() {
		return this.fdWorkitemId;
	}

	public void setFdWorkitemId(String fdWorkitemId) {
		this.fdWorkitemId = fdWorkitemId;
	}

}