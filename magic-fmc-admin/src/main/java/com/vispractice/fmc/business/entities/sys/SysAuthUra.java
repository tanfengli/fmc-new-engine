package com.vispractice.fmc.business.entities.sys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="SYS_AUTH_URA")
public class SysAuthUra  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_ROLEID")
	private String fdRoleid;
	
	@Column(name="FD_AREAID")
	private String fdAreaid;
	
	@Column(name="FD_ORGELEMENTID")
	private String fdOrgelementid;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdRoleid() {
		return fdRoleid;
	}

	public void setFdRoleid(String fdRoleid) {
		this.fdRoleid = fdRoleid;
	}

	public String getFdAreaid() {
		return fdAreaid;
	}

	public void setFdAreaid(String fdAreaid) {
		this.fdAreaid = fdAreaid;
	}

	public String getFdOrgelementid() {
		return fdOrgelementid;
	}

	public void setFdOrgelementid(String fdOrgelementid) {
		this.fdOrgelementid = fdOrgelementid;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
