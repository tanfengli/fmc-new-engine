package com.vispractice.fmc.business.entity.sys.org;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "SYS_ORG_ROLE_LINE")
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedQuery(name = "SysOrgRoleLine.findAll", query = "SELECT s FROM SysOrgRoleLine s")
public class SysOrgRoleLine implements Serializable, Comparable<SysOrgRoleLine>, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_ORDER")
	private Long fdOrder;

	@Column(name = "FD_CREATE_TIME")
	private Date fdCreateTime;

	@Column(name = "FD_MEMBER_ID")
	private String fdMemberId;

	@Column(name = "FD_HIERARCHY_ID")
	private String fdHierarchyId;

	@Column(name = "FD_ROLE_LINE_CONF_ID")
	private String fdRoleLineConfId;

	@Column(name = "FD_PARENT_ID")
	private String fdParentId;
	
	@Column(name="FD_IS_LEAF")
	private Long fdIsLeaf;
	
	@Column(name="FD_IS_AVAILABLE")
	private Long fdIsAvailable;

	// 拼接Name
	@Transient
	private String newName;

	// 成员name
	@Transient
	private String memberName;
	
	@Transient
	private List<SysOrgRoleLine> hbmChildren;
	
	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name = "FD_PARENT_ID", insertable = false, updatable = false)
	private SysOrgRoleLine hbmParent;
	
	public void setFdHierarchyId(String fdHierarchyId){
		this.fdHierarchyId = fdHierarchyId;
	}
	

	@Override
	public Object clone() {
		SysOrgRoleLine o = null;
		try {
			o = (SysOrgRoleLine) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public int compareTo(SysOrgRoleLine o) {

		return o.getFdHierarchyId().length() - this.getFdHierarchyId().length();
	}

}
