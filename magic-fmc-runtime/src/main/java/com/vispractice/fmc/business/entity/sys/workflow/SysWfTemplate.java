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
@Table(name="SYS_WF_TEMPLATE")
public class SysWfTemplate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 引用默认模板
	 */
	public static final int DEFAULT_TEMPLATE = 1;
	
	/**
	 * 引用其它模板
	 */
	public static final int OTHER_TEMPLATE = 2; 
	
	/**
	 * 自定义
	 */
	public static final int DEFINE_TEMPLATE = 3;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="FD_ID")
	private String fdId;

	@Column(name="FD_COMMON_ID")
	private String fdCommonId;

	@Column(name="FD_CREATE_TIME")
	private Date fdCreateTime;

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

	@Column(name="FD_NODE_NUM")
	private Integer fdNodeNum;

	@Column(name="FD_TYPE")
	private Integer fdType;

	@Column(name="FD_VERSION")
	private String fdVersion;

}