package com.vispractice.fmc.business.entities.sys;

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
@Table(name="sys_notify_setting")
@JsonIgnoreProperties(ignoreUnknown=true)
@NamedQuery(name="SysNotifySetting.findAll", query="SELECT s FROM SysNotifySetting s")
public class SysNotifySetting implements Serializable{
 
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_MODEL_NAME")
	private String fdModelName;
	
	@Column(name="FD_MODEL_ID")
	private String fdModelId;
	
	@Column(name="FD_KEY")
	private String fdKey;
	
	@Column(name="FD_NOTIFY_TYPE")
	private String fdNotifyType;
	
	@Column(name="FD_SUBJECT")
	private String fdSubject;
	
	@Column(name="FD_CONTENT")
	private String fdContent;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdModelName() {
		return fdModelName;
	}

	public void setFdModelName(String fdModelName) {
		this.fdModelName = fdModelName;
	}

	public String getFdModelId() {
		return fdModelId;
	}

	public void setFdModelId(String fdModelId) {
		this.fdModelId = fdModelId;
	}

	public String getFdKey() {
		return fdKey;
	}

	public void setFdKey(String fdKey) {
		this.fdKey = fdKey;
	}

	public String getFdNotifyType() {
		return fdNotifyType;
	}

	public void setFdNotifyType(String fdNotifyType) {
		this.fdNotifyType = fdNotifyType;
	}

	public String getFdSubject() {
		return fdSubject;
	}

	public void setFdSubject(String fdSubject) {
		this.fdSubject = fdSubject;
	}

	public String getFdContent() {
		return fdContent;
	}

	public void setFdContent(String fdContent) {
		this.fdContent = fdContent;
	} 
}
