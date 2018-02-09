package com.vispractice.fmc.business.entity.sys.workflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 工作项关联的被授权人信息 实体
 * 
 * @author LaiJiaShen
 */
@Entity
@Table(name="SYS_WF_BYACCREDIT")
public class SysWfByaccredit {
	
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	private String fdId;
	
	@Column(name="FD_WORKITEM_ID")
	private String fdWorkitemId;
	
	@Column(name="FD_ACCR_ID")
	private String fdAccrId;
	
	@Column(name="FD_BYACCR_ID")
	private String fdByaccrId;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdWorkitemId() {
		return fdWorkitemId;
	}

	public void setFdWorkitemId(String fdWorkitemId) {
		this.fdWorkitemId = fdWorkitemId;
	}

	public String getFdAccrId() {
		return fdAccrId;
	}

	public void setFdAccrId(String fdAccrId) {
		this.fdAccrId = fdAccrId;
	}

	public String getFdByaccrId() {
		return fdByaccrId;
	}

	public void setFdByaccrId(String fdByaccrId) {
		this.fdByaccrId = fdByaccrId;
	}
	
	

}
