package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName ComponentLockerMain 
 * @author ZhouYanyao
 */
@Entity
@Table(name = "COMPONENT_LOCKER_MAIN")
public class ComponentLockerMain implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_MODEL_ID")
	private String fdModelId;

	@Column(name = "FD_MODEL_NAME")
	private String fdModelName;

	@Column(name = "FD_LOCKER_OWNER")
	private String fdLockerOwner;

	@Column(name = "FD_LOCKED_TIME")
	private Date fdLockedTime;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdModelId() {
		return fdModelId;
	}

	public void setFdModelId(String fdModelId) {
		this.fdModelId = fdModelId;
	}

	public String getFdModelName() {
		return fdModelName;
	}

	public void setFdModelName(String fdModelName) {
		this.fdModelName = fdModelName;
	}

	public String getFdLockerOwner() {
		return fdLockerOwner;
	}

	public void setFdLockerOwner(String fdLockerOwner) {
		this.fdLockerOwner = fdLockerOwner;
	}

	public Date getFdLockedTime() {
		return fdLockedTime;
	}

	public void setFdLockedTime(Date fdLockedTime) {
		this.fdLockedTime = fdLockedTime;
	}
}