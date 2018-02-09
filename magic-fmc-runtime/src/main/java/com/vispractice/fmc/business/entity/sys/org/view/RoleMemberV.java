package com.vispractice.fmc.business.entity.sys.org.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
/**
 * 
 * 名  称：角色线成员视图（带路径）
 * 描  述：RoleMemberV.java
 * 完成日期：2017年7月25日 下午6:29:34
 * 编码作者："LaiJiashen"
 */

@Data
@Entity
@Table(name="ROLE_MEMBER_V")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RoleMemberV {
	
	@Id
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="CONF_ID")
	private String confId;
	
	@Column(name="CONF_NAME")
	private String confName;
	
	@Column(name="FD_ORG_TYPE")
	private Long fdOrgType;
	
	@Column(name="IS_AVAILABLE")
	private String isAvailable;
	
	@Column(name="PATH_NAME")
	private String pathName;
	
	@Column(name="PARENT_ID")
	private String parentId;
	
	@Column(name="PARENT_NAME")
	private String parentName;
	
	@Column(name="MEMBER_ID")
	private String memberId;
	
	@Column(name="MEMBER_NAME")
	private String memberName;
	
	@Column(name="HIERARCHY_ID")
	private String hierarchyId;
	
	@Column(name="FD_IS_LEAF")
	private Long fdIsLeaf;
	
	@Column(name="FD_IS_AVAILABLE")
	private Long fdIsAvailable;
	
	/**
	 * 是否是角色线成员，"0"即不是
	 */
	@Transient
	private String isRoleMember;
	
	@Transient
	private String newParentId;
	
	@Transient
	private String newConfId;
	
	@Transient
	private String isParentRoleMember;
	
	@Transient
	private String searchMemberName;
	
	public String getIsParent(){
		return null==this.fdIsLeaf||this.fdIsLeaf==0?"true":"false";
	};

}
