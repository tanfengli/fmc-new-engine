package com.vispractice.fmc.business.entity.sys.config.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sys_busi_sys_v")
@NamedQuery(name = "SysBusiSysV.findAll", query = "SELECT p FROM SysBusiSysV p")
public class SysBusiSysV implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_CODE")
	private String fdCode;

	@Column(name = "FD_ORDER")
	private String fdOrder;

	@Column(name = "FD_CREATED_DATE")
	private Date fdCreatedDate;

	@Column(name = "FD_CREATED_BY")
	private String fdCreatedBy;

	@Column(name = "FD_ENABLED")
	private String fdEnabled;

	@Column(name = "CREATED_NAME")
	private String createdName;

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

	public String getFdCode() {
		return fdCode;
	}

	public void setFdCode(String fdCode) {
		this.fdCode = fdCode;
	}

	public String getFdOrder() {
		return fdOrder;
	}

	public void setFdOrder(String fdOrder) {
		this.fdOrder = fdOrder;
	}

	public Date getFdCreatedDate() {
		return fdCreatedDate;
	}

	public void setFdCreatedDate(Date fdCreatedDate) {
		this.fdCreatedDate = fdCreatedDate;
	}

	public String getFdCreatedBy() {
		return fdCreatedBy;
	}

	public void setFdCreatedBy(String fdCreatedBy) {
		this.fdCreatedBy = fdCreatedBy;
	}

	public String getFdEnabled() {
		return fdEnabled;
	}

	public void setFdEnabled(String fdEnabled) {
		this.fdEnabled = fdEnabled;
	}

	public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
}
