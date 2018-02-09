package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
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
@Table(name = "sys_wf_node")
public class SysWfNode implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 节点状态
	 */
	public static final String ACTIVATED = "20";

	public static final String COMPLETED = "30";
	
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "fd_fact_node_id")
	private String fdFactNodeId;

	@Column(name = "fd_fact_node_name")
	private String fdFactNodeName;

	@Column(name = "fd_is_delay")
	private String fdIsDelay;

	@Column(name = "fd_status")
	private String fdStatus;

	@Column(name = "fd_target_id")
	private String fdTargetId;

	@Column(name = "fd_target_name")
	private String fdTargetName;

	@Column(name = "fd_route_type")
	private String fdRouteType;

	@Column(name = "fd_target_model_id")
	private String fdTargetModelId;

	@Column(name = "fd_day_of_notify")
	private Long fdDayOfNotity;

	@Column(name = "fd_day_of_pass")
	private Long fdDayOfPass;

	@Column(name = "fd_tran_notify_draft")
	private Long fdTranNotityDraft;

	@Column(name = "fd_tran_notify_private")
	private Long fdTranNotityPrivate;

	@Column(name = "fd_reject_notify_draft")
	private Long fdRejectNotityDraft;

	@Column(name = "fd_start_date")
	private Date fdStartDate;

	@Column(name = "fd_node_type")
	private String fdNodeType;

	@Column(name = "fd_parameter")
	private String fdParameter;

	@Column(name = "fd_process_id")
	private String fdProcessId;
	
	@Transient
	private String fdHandlerIds;
	
	@Transient
	private String fdHandlerNames;
}
