package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.vispractice.fmc.business.base.constrant.BaseTreeConstant;
import com.vispractice.fmc.business.base.utils.IDGenerator;

import lombok.Data;

/**
 * The persistent class for the SYS_WF_PROCESS database table.
 * 
 */
@Data
@Entity
@Table(name = "SYS_WF_PROCESS")
@NamedQuery(name = "SysWfProcess.findAll", query = "SELECT s FROM SysWfProcess s")
public class SysWfProcess implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 流程创建
	 */
	public static final String CREATED = "10";
	/**
	 * 流程中
	 */
	public static final String ACTIVATED = "20";
	/**
	 * 流程出错
	 */
	public static final String ERROR = "21";
	/**
	 * 流程结束
	 */
	public static final String COMPLETED = "30";
	
	@Id
	@GeneratedValue(generator = "assigned")
	@GenericGenerator(name = "assigned", strategy = "assigned")
	@Column(name = "FD_ID")
	private String fdId;

//	@Version
	@Column(name = "FD_VERSION")
	private Long fdVersion = 1L;

	@Column(name = "FD_CREATE_TIME")
	private Date fdCreateTime;

	@Column(name = "FD_CREATOR_ID")
	private String fdCreatorId;

	@Column(name = "FD_DAY_NOTIFY_DRAFTER")
	private Long fdDayNotifyDrafter;

	@Column(name = "FD_DAY_NOTIFY_PRIVILEGER")
	private Long fdDayNotifyPrivileger;

	@Column(name = "FD_DELAY_TIME")
	private String fdDelayTime;

	@Column(name = "FD_DESCRIPTION")
	private String fdDescription;

	@Lob
	@Column(name = "FD_DESCRIPTOR")
	private String fdDescriptor;

	@Lob
	@Column(name = "FD_DETAIL")
	private String fdDetail;

	@Column(name = "FD_HANDLE_DURATION")
	private String fdHandleDuration;

	@Column(name = "FD_HIERARCHY_ID")
	private String fdHierarchyId = BaseTreeConstant.HIERARCHY_ID_SPLIT + getFdId()
			+ BaseTreeConstant.HIERARCHY_ID_SPLIT;

	@Column(name = "FD_IS_DELAY")
	private String fdIsDelay;

	@Column(name = "FD_KEY")
	private String fdKey;

	@Column(name = "FD_LAST_HANDLE_TIME")
	private Date fdLastHandleTime;

	@Column(name = "FD_MODEL_ID")
	private String fdModelId;

	@Column(name = "FD_MODEL_NAME")
	private String fdModelName;

	@Column(name = "FD_NAME")
	private String fdName;

	@Lob
	@Column(name = "FD_ORI_DETAIL")
	private String fdOriDetail;

	@Lob
	@Column(name = "FD_PARAMETER")
	private String fdParameter;

	@Column(name = "FD_PARENT_NODE_FDID")
	private String fdParentNodeFdid;

	@Column(name = "FD_PARENT_NODEID")
	private String fdParentNodeid;

	@Column(name = "FD_PARENTID")
	private String fdParentid;

	@Column(name = "FD_PRIVILEGER_IDS")
	private String fdPrivilegerIds;

	@Column(name = "FD_STATUS")
	private String fdStatus;

	@Column(name = "FD_SUB_STATUS")
	private Long fdSubStatus;

	@Column(name = "FD_TEMPLATE_ID")
	private String fdTemplateId;

}