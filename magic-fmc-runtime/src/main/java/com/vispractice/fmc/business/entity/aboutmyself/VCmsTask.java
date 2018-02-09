package com.vispractice.fmc.business.entity.aboutmyself;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "v_cms_task")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VCmsTask {
	/**
	 * 唯一标识
	 */
	@Id
	@Column(name = "FD_ID")
	private String fdId;
	
	/**
	 * 流程实例标识
	 */
	@Column(name = "WF_INSTANCE_ID")
	private String wfInstanceId;
	
	/**
	 * 单据号
	 */
	@Column(name = "APPLY_CODE")
	private String applyCode;

	/**
	 * 业务系统标识
	 */
	@Column(name = "FD_NEWS_SOURCE")
	private String sourceSystemId;

	/**
	 * 单据状态
	 */
	@Column(name = "DOC_STATUS")
	private String wfStatus;

	/**
	 * 待审人编号
	 */
	@Column(name = "TASK_USER_NO")
	private String taskUserNo;
	
	/**
	 * 待审人名称
	 */
	@Column(name = "TASK_USER_NAME")
	private String taskUserName;

	/**
	 * 申请人编号
	 */
	@Column(name = "APP_USER_NO")
	private String appUserNo;

	/**
	 * 申请人名称
	 */
	@Column(name = "APP_USER_NAME")
	private String appUserName;

	/**
	 * 申请日期
	 */
	@Column(name = "APP_DATE")
	private Date appDate;
	
	/**
	 * 发布日期
	 */
	@Column(name = "PUBLISH_DATE")
	private Date publishDate;
	
	/**
	 * 验证信息
	 */
	@Column(name = "FD_TOKEN_ID")
	private String fdTokenId;

	/**
	 * 申请主题
	 */
	@Column(name = "APP_TITLE")
	private String appTitle;

	/**
	 * 申请原因
	 */
	@Column(name = "APPLY_REASON")
	private String applyReason;

	/**
	 * 重要级别
	 */
	@Column(name = "URGENT_LEVEL")
	private String urgentLevel;
	
	/**
	 * 业务系统连接地址
	 */
	@Column(name="WF_BUSI_LINK")
	private String wfBusiLink;

	/**
	 * 模板标识
	 */
	@Column(name = "FD_TEMPLATE_ID")
	private String fdTemplateId;

	/**
	 * 流程描述
	 */
	@Column(name = "FD_DESCRIPTION")
	private String fdDescription;

	/**
	 * 单据内容
	 */
	@Column(name = "DOC_CONTENT")
	private String docContent;

	/**
	 * 节点标识
	 */
	@Column(name = "NODE_ID")
	private String nodeId;
	
	/**
	 * 节点名称
	 */
	@Column(name = "NODE_NAME")
	private String nodeName;
	
	/**
	 * 操作类型名称
	 */
	@Column(name = "OPERATOR_NAMES")
	private String oprNames;
	
	
	@Column(name="ATTR1")
	private String attr1;

	@Column(name="ATTR10")
	private String attr10;

	@Column(name="ATTR11")
	private String attr11;

	@Column(name="ATTR12")
	private String attr12;

	@Column(name="ATTR13")
	private String attr13;

	@Column(name="ATTR14")
	private String attr14;

	@Column(name="ATTR15")
	private String attr15;

	@Column(name="ATTR16")
	private String attr16;

	@Column(name="ATTR17")
	private String attr17;

	@Column(name="ATTR18")
	private String attr18;

	@Column(name="ATTR19")
	private String attr19;

	@Column(name="ATTR2")
	private String attr2;

	@Column(name="ATTR20")
	private String attr20;

	@Column(name="ATTR3")
	private String attr3;

	@Column(name="ATTR4")
	private String attr4;

	@Column(name="ATTR5")
	private String attr5;

	@Column(name="ATTR6")
	private String attr6;

	@Column(name="ATTR7")
	private String attr7;

	@Column(name="ATTR8")
	private String attr8;

	@Column(name="ATTR9")
	private String attr9;

}
