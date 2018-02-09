package com.vispractice.fmc.business.entities.sys.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Role_Line_Member_V")
@NamedQuery(name = "RoleLineMemberV.findAll", query = "SELECT p FROM RoleLineMemberV p")
public class RoleLineMemberV implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_ORDER")
	private String fdOrder;

	@Column(name = "MEMBER_NAME")
	private String memberName;

	@Column(name = "PARENT_NAME")
	private String parentName;

	@Column(name = "fd_role_line_conf_id")
	private String fdRoelLineConfId;

	// 类型名称
	@Column(name = "conf_name")
	private String confName;

	@Override
	public Object clone() {
		RoleLineMemberV o = null;
		try {
			o = (RoleLineMemberV) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public String getFdRoelLineConfId() {
		return fdRoelLineConfId;
	}

	public void setFdRoelLineConfId(String fdRoelLineConfId) {
		this.fdRoelLineConfId = fdRoelLineConfId;
	}

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

	public String getFdOrder() {
		return fdOrder;
	}

	public void setFdOrder(String fdOrder) {
		this.fdOrder = fdOrder;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}

}
