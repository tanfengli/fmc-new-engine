package com.vispractice.fmc.business.entity.sys.workflow;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "sys_wf_workitem")
public class SysWfWorkitem {
	public static final String ACTIVATED = "20";

	public static final String COMPLETED = "30";

	public static final String SUSPENDED = "40";

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "fd_id")
	private String fdId;

	@Column(name = "fd_action_id")
	private Long fdActionId;

	@Column(name = "fd_action_name")
	private String fdActionName;

	@Column(name = "fd_parameter")
	private String fdParameter;

	@Column(name = "fd_status")
	private String fdStatus;

	@Column(name = "fd_start_date")
	private Date fdStartDate;

	@Column(name = "fd_notify_on_finish")
	private Boolean fdNotifyOnFinish;

	@Column(name = "fd_notify_type")
	private String fdNotifyType;

	@Column(name = "fd_relation_workitem_id")
	private String fdRelationWorkitemId;

	@Column(name = "fd_node_id")
	private String fdNodeId;

	@Column(name = "fd_handler_id")
	private String fdHandlerId;

	@Column(name = "fd_expected_id")
	private String fdExpectedId;

	@Column(name = "fd_accer_id")
	private String fdAccerId;

	@Column(name = "fd_source_workitem_id")
	private String fdSourceWorkitemId;

	@Column(name = "fd_is_done")
	private Long fdIsDone;
	
	@Transient
	private String fdNodeNo;
	
	@Transient
	private String fdNodeName;
	
	@Transient
	private String fdExpectedName;
}
