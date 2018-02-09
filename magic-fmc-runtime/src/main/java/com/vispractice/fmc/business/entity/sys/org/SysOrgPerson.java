package com.vispractice.fmc.business.entity.sys.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

/**
 * The persistent class for the SYS_ORG_PERSON database table.
 */
@Entity
@Data
@Table(name = "SYS_ORG_PERSON")
@NamedQuery(name = "SysOrgPerson.findAll", query = "SELECT s FROM SysOrgPerson s")
public class SysOrgPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_ATTENDANCE_CARD_NUMBER")
	private String fdAttendanceCardNumber;

	@Column(name = "FD_CARD_NO")
	private String fdCardNo;

	@Column(name = "FD_DEFAULT_LANG")
	private String fdDefaultLang;

	@Column(name = "FD_EMAIL")
	private String fdEmail;

	@Column(name = "FD_INIT_PASSWORD")
	private String fdInitPassword;

	@Column(name = "FD_LOGIN_NAME")
	private String fdLoginName;

	@Column(name = "FD_MOBILE_NO")
	private String fdMobileNo;

	@Column(name = "FD_PASSWORD")
	private String fdPassword;

	@Column(name = "FD_RTX_NO")
	private String fdRtxNo;

	@Column(name = "FD_WORK_PHONE")
	private String fdWorkPhone;

}