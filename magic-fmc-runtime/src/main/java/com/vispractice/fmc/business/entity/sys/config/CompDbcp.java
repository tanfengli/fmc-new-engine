package com.vispractice.fmc.business.entity.sys.config;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="COMP_DBCP")
@JsonIgnoreProperties(ignoreUnknown=true)
@NamedQuery(name="CompDbcp.findAll", query="SELECT s FROM CompDbcp s")
public class CompDbcp  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="FD_TYPE")
	private String fdType;
	
	@Column(name="FD_DRIVER")
	private String fdDriver;

	@Column(name="FD_URL")
	private String fdUrl;
	
	@Column(name="FD_USERNAME")
	private String fdUsername;
	
	@Column(name="FD_PASSWORD")
	private String fdPassword;
	
	@Column(name="FD_DESCRIPTION")
	private String fdDescription;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdName() {
		return fdName;
	}

	public void setFdName(String fdName) {
		this.fdName = fdName;
	}

	public String getFdType() {
		return fdType;
	}

	public void setFdType(String fdType) {
		this.fdType = fdType;
	}


	public String getFdDriver() {
		return fdDriver;
	}

	public void setFdDriver(String fdDriver) {
		this.fdDriver = fdDriver;
	}

	public String getFdUrl() {
		return fdUrl;
	}

	public void setFdUrl(String fdUrl) {
		this.fdUrl = fdUrl;
	}

	public String getFdUsername() {
		return fdUsername;
	}

	public void setFdUsername(String fdUsername) {
		this.fdUsername = fdUsername;
	}

	public String getFdPassword() {
		return fdPassword;
	}

	public void setFdPassword(String fdPassword) {
		this.fdPassword = fdPassword;
	}

	public String getFdDescription() {
		return fdDescription;
	}

	public void setFdDescription(String fdDescription) {
		this.fdDescription = fdDescription;
	}


	
	
	

	
	
	
	
}
