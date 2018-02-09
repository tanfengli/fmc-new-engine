package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * The persistent class for the SYS_WF_HISTORY_WORKITEM database table.
 * 
 */
@Data
@Entity
@Table(name = "SYS_WF_HISTORY_WORKITEM")
@NamedQuery(name = "SysWfHistoryWorkitem.findAll", query = "SELECT s FROM SysWfHistoryWorkitem s")
public class SysWfHistoryWorkitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_ACTION_ID")
	private Long fdActionId;

	@Column(name = "FD_ACTION_NAME")
	private String fdActionName;

	@Column(name = "FD_EXPECTED_ID")
	private String fdExpectedId;

	@Column(name = "FD_FINISH_DATE")
	private Date fdFinishDate;

	@Column(name = "FD_HANDLER_ID")
	private String fdHandlerId;

	@Column(name = "FD_HISTORY_ID")
	private String fdHistoryId;

	@Column(name = "FD_NODE_ID")
	private String fdNodeId;

	@Column(name = "FD_NOTIFY_ON_FINISH")
	private Boolean fdNotifyOnFinish;

	@Column(name = "FD_NOTIFY_TYPE")
	private String fdNotifyType;

	@Column(name = "FD_ORDER")
	private Long fdOrder;

	@Column(name = "FD_SOURCE_WORKITEM_ID")
	private String fdSourceWorkitemId;

	@Column(name = "FD_START_DATE")
	private Date fdStartDate;

	@Column(name = "FD_WORKITEM_ID")
	private String fdWorkitemId;

}