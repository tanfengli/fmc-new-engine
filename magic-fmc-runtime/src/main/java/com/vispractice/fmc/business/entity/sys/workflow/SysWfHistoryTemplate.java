package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="sys_wf_history_template")
public class SysWfHistoryTemplate implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="FD_ID")
	private String fdId;

	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="FD_IS_DEFAULT")
	private String fdIsDefault;
	
	@Column(name="FD_TEMPLATE_TYPE")
	private Long fdTemplateType;
	
	@Column(name="FD_TEMPLATE_ID")
	private String fdTemplateId;
	
	@Column(name="FD_COMMON_ID")
	private String fdCommonId;

	@Column(name="FD_CREATE_TIME")
	private Date fdCreateTime;
	
	@Column(name="FD_MODIFY_TIME")
	private Date fdModifyTime;
	
	@Column(name="FD_MODIFY_ID")
	private String fdModifyId;
	
	@Column(name="FD_CREATOR_ID")
	private String fdCreatorId;

	@Lob
	@Column(name="FD_FLOW_CONTENT")
	private String fdFlowContent;

	@Column(name="FD_KEY")
	private String fdKey;

	@Column(name="FD_MODEL_ID")
	private String fdModelId;

	@Column(name="FD_MODEL_NAME")
	private String fdModelName;

	@Column(name="FD_TYPE")
	private Long fdType;

	@Column(name="FD_VERSION")
	private String fdVersion;
	
	@Column(name="FD_STATUS")
	private String fdStatus;
	
	@Column(name="FD_ENABLE_TIME")
	private Date fdEnableTime;
	
	@Column(name="FD_COMMON_VERSION")
	private String fdCommonVersion;

}