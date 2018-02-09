package com.vispractice.fmc.business.entity.sys.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 名  称：角色线成员默认成员表
 * 描  述：SysOrgRoleLineDefaultRole.java
 * 完成日期：2017年8月7日 下午5:48:22
 * 编码作者："LaiJiashen"
 */
@Data
@Entity
@Table(name="sys_org_role_line_default_role")
public class SysOrgRoleLineDefaultRole {
	
	@Id
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_ROLE_LINE_CONF_ID")
	private String fdRoleLineConfId;
	
	@Column(name="FD_PERSON_ID")
	private String fdPersonId;
	
	@Column(name="FD_POST_ID")
	private String fdPostId;
	
	public SysOrgRoleLineDefaultRole() {
		super();
	}

	public SysOrgRoleLineDefaultRole(String fdId,String fdRoleLineConfId, String fdPersonId) {
		super();
		this.fdId = fdId;
		this.fdRoleLineConfId = fdRoleLineConfId;
		this.fdPersonId = fdPersonId;
	}

}
