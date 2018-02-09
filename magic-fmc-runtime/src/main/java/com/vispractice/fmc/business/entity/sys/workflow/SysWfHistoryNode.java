package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the SYS_WF_HISTORY_NODE database table.
 * 
 */
@Data
@Entity
@Table(name="SYS_WF_HISTORY_NODE")
@NamedQuery(name="SysWfHistoryNode.findAll", query="SELECT s FROM SysWfHistoryNode s")
public class SysWfHistoryNode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="FD_ID")
	private String fdId;

	@Column(name="FD_DELAY_TIME")
	private String fdDelayTime;

	@Column(name="FD_FACT_NODE_ID")
	private String fdFactNodeId;

	@Column(name="FD_FACT_NODE_NAME")
	private String fdFactNodeName;

	@Column(name="FD_FINISH_DATE")
	private Date fdFinishDate;

	@Column(name="FD_HANDLE_DURATION")
	private String fdHandleDuration;

	@Column(name="FD_IS_DELAY")
	private String fdIsDelay;

	@Column(name="FD_NODE_ID")
	private String fdNodeId;

	@Column(name="FD_NODE_TYPE")
	private String fdNodeType;

	@Column(name="FD_ORDER")
	private Long fdOrder;

	@Column(name="FD_PROCESS_ID")
	private String fdProcessId;

	@Column(name="FD_ROUTE_TYPE")
	private String fdRouteType;
	
	@Column(name="FD_START_DATE")
	private Date fdStartDate;

	@Column(name="FD_TARGET_ID")
	private String fdTargetId;

	@Column(name="FD_TARGET_MODEL_ID")
	private String fdTargetModelId;

	@Column(name="FD_TARGET_NAME")
	private String fdTargetName;

	@Column(name="FD_TOKEN_HIERARCHY_ID")
	private String fdTokenHierarchyId;

}