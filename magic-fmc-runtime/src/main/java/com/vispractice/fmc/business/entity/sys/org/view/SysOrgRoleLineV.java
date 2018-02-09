package com.vispractice.fmc.business.entity.sys.org.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name="sys_org_role_line_v")
public class SysOrgRoleLineV {
	
	
	@Id
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="FD_ORDER")
	private Long fdOrder;
	
	@Column(name="FD_ORG_TYPE")
	private String fdOrgType;
	

	@Column(name="FD_CREATE_TIME")
	private Date fdCreateTime;
	
	@Column(name="FD_MEMBER_ID")
	private String fdMemberId;
	
	@Column(name="FD_HIERARCHY_ID")
	private String fdHierarchyId;
	
	@Column(name="FD_ROLE_LINE_CONF_ID")
	private String fdRoleLineConfId;
	
	@Column(name="FD_PARENT_ID")
	private String fdParentId;
	
	//拼接Name
	@Column(name="NEW_NAME")
	private String newName;
	
	//成员name
	@Column(name="MEMBER_NAME")
	private String memberName;

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
	
	public String getFdOrgType() {
		return fdOrgType;
	}

	public void setFdOrgType(String fdOrgType) {
		this.fdOrgType = fdOrgType;
	}


	public Long getFdOrder() {
		return fdOrder;
	}

	public void setFdOrder(Long fdOrder) {
		this.fdOrder = fdOrder;
	}

	public Date getFdCreateTime() {
		return fdCreateTime;
	}

	public void setFdCreateTime(Date fdCreateTime) {
		this.fdCreateTime = fdCreateTime;
	}

	public String getFdMemberId() {
		return fdMemberId;
	}

	public void setFdMemberId(String fdMemberId) {
		this.fdMemberId = fdMemberId;
	}

	public String getFdHierarchyId() {
		return fdHierarchyId;
	}

	public void setFdHierarchyId(String fdHierarchyId) {
		this.fdHierarchyId = fdHierarchyId;
	}

	public String getFdRoleLineConfId() {
		return fdRoleLineConfId;
	}

	public void setFdRoleLineConfId(String fdRoleLineConfId) {
		this.fdRoleLineConfId = fdRoleLineConfId;
	}

	public String getFdParentId() {
		return fdParentId;
	}

	public void setFdParentId(String fdParentId) {
		this.fdParentId = fdParentId;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
	
	
	
}
