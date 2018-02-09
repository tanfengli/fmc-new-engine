package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
/**
 * The persistent class for the SYS_WF_AUDIT_NOTE database table.
 * 
 */
@Data
@Entity
@Table(name="SYS_WF_AUDIT_NOTE")
@NamedQuery(name="SysWfAuditNote.findAll", query="SELECT s FROM SysWfAuditNote s")
public class SysWfAuditNote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	@Column(name="FD_ID")
	private String fdId;

	@Column(name="CAN_MODIFY_NOTION_POPEDOM")
	private Long canModifyNotionPopedom;

	@Column(name="FD_ACCR_ID")
	private String fdAccrId;

	@Column(name="FD_ACTION_ID")
	private Long fdActionId;

	@Column(name="FD_ACTION_INFO")
	private String fdActionInfo;

	@Column(name="FD_ACTION_NAME")
	private String fdActionName;

	@Column(name="FD_APPROVAL_TYPE_ID")
	private String fdApprovalTypeId;

	@Column(name="FD_AUDIT_NOTE")
	private String fdAuditNote;

	@Column(name="FD_CREATE_DATE")
	private Date fdCreateDate;

	@Column(name="FD_EXPECTER_ID")
	private String fdExpecterId;

	@Column(name="FD_FACT_NODE_ID")
	private String fdFactNodeId;

	@Column(name="FD_FACT_NODE_NAME")
	private String fdFactNodeName;

	@Column(name="FD_HANDLER_ID")
	private String fdHandlerId;

	@Column(name="FD_IDENTITY_FLAG")
	private String fdIdentityFlag;

	@Column(name="FD_IDENTITY_ID")
	private String fdIdentityId;

	@Column(name="FD_IDENTITY_NAME")
	private String fdIdentityName;

	@Column(name="FD_NODE_ID")
	private String fdNodeId;

	@Column(name="FD_NOTIFY_TYPE")
	private String fdNotifyType;

	@Column(name="FD_ORDER")
	private Long fdOrder;

	@Column(name="FD_PARENT_TOKEN_ID")
	private String fdParentTokenId;

	@Column(name="FD_PROCESS_ID")
	private String fdProcessId;

	@Column(name="FD_TOKEN_ID")
	private String fdTokenId;

	@Column(name="FD_WORKITEM_ID")
	private String fdWorkitemId;

	@Column(name="NODE_CAN_VIEW_CUR_NODE_IDS")
	private String nodeCanViewCurNodeIds;

	@Column(name="NODE_CAN_VIEW_CUR_NODE_NAMES")
	private String nodeCanViewCurNodeNames;

	@Column(name="OTHER_CAN_VIEW_CUR_NODE_IDS")
	private String otherCanViewCurNodeIds;

	@Column(name="OTHER_CAN_VIEW_CUR_NODE_NAMES")
	private String otherCanViewCurNodeNames;

}