package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 引擎事件实体对象 
 */
@Data
@Entity
@Table(name="SYS_WF_EVENT")
@NamedQuery(name="SysWfEvent.findAll",query="SELECT s FROM SysWfEvent s")
public class SysWfEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 流程事件Action类型
	 */
	public static final int ACTION_CREATENODE = 1;

	public static final int ACTION_DOACTION = 2;
	
	public static final int ACTION_DOTEST = 3;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name="FD_ID")
	private String fdId;

	@Column(name="DOC_CREATE_TIME")
	private Date docCreateTime;

	@Column(name="FD_ACTION")
	private int fdAction;

	@Column(name="FD_NODE_ID")
	private String fdNodeId;

	@Lob
	@Column(name="FD_PARAMETER")
	private String fdParameter;

	@Column(name="FD_PRIORITY")
	private Long fdPriority = 0L;

	@Column(name="FD_PROCESS_ID")
	private String fdProcessId;

	@Column(name="FD_USER_ID")
	private String fdUserId;

}