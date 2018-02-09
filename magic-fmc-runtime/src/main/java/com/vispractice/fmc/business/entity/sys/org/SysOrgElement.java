package com.vispractice.fmc.business.entity.sys.org;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;

/**
 * The persistent class for the SYS_ORG_ELEMENT database table.
 */
@Entity
@Data
@Table(name = "SYS_ORG_ELEMENT")
@NamedQuery(name = "SysOrgElement.findAll", query = "SELECT s FROM SysOrgElement s")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysOrgElement implements Serializable, Cloneable, SysOrgConstant {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 组织架构管理员
	 */
	public static final String admin = "admin";

	@Id
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_ALTER_TIME")
	private Date fdAlterTime;

	@Column(name = "FD_CALENDAR_ID")
	private String fdCalendarId;

	@Column(name = "FD_CATEID")
	private String fdCateid;

	@Column(name = "FD_CREATE_TIME")
	private Date fdCreateTime;

	@Column(name = "FD_FLAG_DELETED")
	private Long fdFlagDeleted;

	@Column(name = "FD_HIERARCHY_ID")
	private String fdHierarchyId;

	@Column(name = "FD_IMPORT_INFO")
	private String fdImportInfo;

	@Column(name = "FD_IS_ABANDON")
	private Long fdIsAbandon;

	@Column(name = "FD_IS_AVAILABLE")
	private Long fdIsAvailable;

	@Column(name = "FD_IS_LEAF")
	private Long fdIsLeaf;

	@Column(name = "FD_IS_BUSINESS")
	private String fdIsBusiness;

	@Column(name = "FD_KEYWORD")
	private String fdKeyword;

	@Column(name = "FD_LDAP_DN")
	private String fdLdapDn;

	@Column(name = "FD_MEMO")
	private String fdMemo;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_NAME_PINYIN")
	private String fdNamePinyin;

	@Column(name = "FD_NO")
	private String fdNo;

	@Column(name = "FD_ORDER")
	private Long fdOrder;

	@Column(name = "FD_ORG_TYPE")
	private Long fdOrgType;

	@Column(name = "FD_PARENTID")
	private String fdParentid;

	@Column(name = "FD_PARENTORGID")
	private String fdParentorgid;

	@Column(name = "FD_SUPER_LEADERID")
	private String fdSuperLeaderid;

	@Column(name = "FD_THIS_LEADERID")
	private String fdThisLeaderid;

	/**
	 * 机构信息
	 */
	@Transient
	private String fdParentorgName; // 上级机构

	@Transient
	private String fdSuperLeaderName;// 上级领导

	/**
	 * 岗位信息
	 */
	@Transient
	private String fdPersonId;

	@Transient
	private String fdPersonName;// 岗位员工信息列表

	@Transient
	private List<SysOrgElement> editorArray;

	@Transient
	private String fdDeptName;// 所在部门

	@Transient
	private String fdThisLeaderName;// 岗位领导

	/**
	 * 人员信息
	 */
	@Transient
	private String fdEmail; // 邮件地址

	@Transient
	private String fdMobileNo;// 手机号码

	@Transient
	private String fdWorkPhone;// 办公电话

	@Transient
	private String fdLoginName;// 登录名

	@Transient
	private String fdDefaultLang;// 默认语言
	// 所属岗位
	
	@Transient
	private String fdPostIds;// 所属岗位id
	
	@Transient
	private String fdPostNames;// 所属岗位name

	@Transient
	public String getIsParent(){
		return null == this.fdIsLeaf || this.fdIsLeaf == 1L?"false":"true";
	};

	@Transient
	private List<SysOrgRoleConf> sysOrgRoleConfs;

	@Override
	public Object clone() {
		SysOrgElement o = null;
		try {
			o = (SysOrgElement) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	
	

}