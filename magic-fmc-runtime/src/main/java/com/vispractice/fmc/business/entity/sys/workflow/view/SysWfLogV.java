package com.vispractice.fmc.business.entity.sys.workflow.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "wf_log_v")
public class SysWfLogV implements Serializable,Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "fd_name")
	private String fdName;
	
	@Column(name="fd_handler_id")
	private String fdHandlerId;

	@Column(name = "fd_fact_node_id")
	private String fdFactNodeId;

	@Column(name = "fd_fact_node_name")
	private String fdFactNodeName;

	@Column(name = "fd_action_id")
	private String fdActionId;

	@Column(name = "fd_action_name")
	private String fdActionName;

	@Column(name = "fd_action_info")
	private String fdActionInfo;

	@Column(name = "fd_audit_note")
	private String fdAuditNote;

	@Column(name = "fd_notify_type")
	private String fdNotifyType;

	@Column(name = "fd_create_date")
	private Date fdCreateDate;
	
	@Column(name="fd_identity_flag")
	private String fdIdentityFlag;
	
	/**
	 * 是否隐藏沟通意见标识
	 */
	@Transient
	private String isHideFlag;

	public SysWfLogV() {
	}
	
	public SysWfLogV(String fdName,String fdFactNodeId,String fdFactNodeName) {
		this.setFdName(fdName);
		this.setFdFactNodeId(fdFactNodeId);
		this.setFdFactNodeName(fdFactNodeName);
	}

	public SysWfLogV(String fdName) {
		this.setFdName(fdName);
	}

	@Override
	public Object clone() {
		SysWfLogV o = null;
		try {
			o = (SysWfLogV) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}
