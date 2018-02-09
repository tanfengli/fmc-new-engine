package com.vispractice.fmc.business.entity.sys.authority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * 编  号：
 * 名  称：UserAuthorityV
 * 描  述：用户权限查询视图
 * 完成日期：2017年10月13日 下午5:41:36
 * 编码作者："LaiJiashen"
 */
@Entity
@Data
@Table(name = "USER_AUTHORITY_V")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuthorityV {
	
	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "USER_ORG_ID")
	private String userOrgId;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Column(name = "MENU_AUTH_NAME")
	private String menuAuthName;

}
