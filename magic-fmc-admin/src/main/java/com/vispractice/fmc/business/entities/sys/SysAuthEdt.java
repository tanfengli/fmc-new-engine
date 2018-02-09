package com.vispractice.fmc.business.entities.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="sys_auth_edt")
public class SysAuthEdt {
	
	@Id
	@Column(name="FD_ORGELEMENTID")
	private String fdOrgelementid;
	
	@Column(name="FD_ROLEID")
	private String fdRoleid;

	public String getFdOrgelementid() {
		return fdOrgelementid;
	}

	public void setFdOrgelementid(String fdOrgelementid) {
		this.fdOrgelementid = fdOrgelementid;
	}

	public String getFdRoleid() {
		return fdRoleid;
	}

	public void setFdRoleid(String fdRoleid) {
		this.fdRoleid = fdRoleid;
	}
	
	
	
	
	
}
