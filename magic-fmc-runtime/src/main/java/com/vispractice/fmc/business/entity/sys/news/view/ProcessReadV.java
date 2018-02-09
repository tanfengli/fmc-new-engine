package com.vispractice.fmc.business.entity.sys.news.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "process_read_v")
public class ProcessReadV implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 唯一标识
	 */
	@Id
	@Column(name = "fd_id")
	private String fdId;
	
	/**
	 * 代办ID
	 */
	@Column(name = "todo_id")
	private String todoId;
	
	/**
	 * 是否已阅
	 */
	@Column(name = "is_read")
	private Long isRead;
	
	/**
	 * 待阅消息
	 */
	@Column(name = "fd_subject")
	private String fdSubject;
	
	/**
	 * 流程实例标识
	 */
	@Column(name = "wf_instance_id")
	private String wfInstanceId;
	
	/**
	 * 业务系统标识
	 */
	@Column(name = "source_system_id")
	private String sourceSystemId;
	
	/**
	 * 业务系统名称
	 */
	@Column(name = "source_system_name")
	private String sourceSystemName;
	
	/**
	 * 待阅者编码
	 */
	@Column(name = "read_user_no")
	private String readUserNo;
	
	/**
	 * 待阅者名称
	 */
	@Column(name = "read_user_name")
	private String readUserName;
	
	/**
	 * 申请单号
	 */
	@Column(name = "apply_code")
	private String applyCode;
	
	/**
	 * 申请人编码
	 */
	@Column(name = "apply_user_no")
	private String applyUserNo;
	
	/**
	 * 申请人名称
	 */
	@Column(name = "apply_user_name")
	private String applyUserName;
	
	/**
	 * 模板标识
	 */
	@Column(name = "wf_template_id")
	private String wfTemplateId;
	
	/**
	 * 开始日期
	 */
	@Column(name = "start_apply_date")
	private Date startApplyDate;
	
	/**
	 * 申请日期
	 */
	@Column(name = "apply_date")
	private Date applyDate;
	
	/**
	 * 结束日期
	 */
	@Column(name = "end_apply_date")
	private Date endApplyDate;
	
	/**
	 * 申请主题
	 */
	@Column(name = "apply_subject")
	private String applySubject;
	
	/**
	 * 申请原因
	 */
	@Column(name = "apply_reason")
	private String applyReason;
	
	/**
	 * 申请内容
	 */
	@Column(name = "apply_content")
	private String applyContent;
	
	/**
	 * 单据级别
	 */
	@Column(name = "urgent_level")
	private String urgentLevel;
	
	/**
	 * 申请状态
	 */
	@Column(name = "apply_status")
	private String applyStatus;
	
	/**
	 * 描述信息
	 */
	@Column(name = "wf_description")
	private String wfDescription;
	
	/**
	 * 单据连接地址
	 */
	@Column(name = "wf_busi_url")
	private String wfBusiLink;
	
	/**
	 * 节点名称
	 */
	@Column(name = "node_names")
	private String nodeNames;
	
	/**
	 * 当前处理人名称
	 */
	@Column(name = "deal_name")
	private String dealName;
	
	/**
	 * 扩展字段1
	 */
	@Column(name = "attr1")
	private String attr1;
	
	/**
	 * 扩展字段2
	 */
	@Column(name = "attr2")
	private String attr2;
	
	/**
	 * 扩展字段3
	 */
	@Column(name = "attr3")
	private String attr3;
	
	/**
	 * 扩展字段4
	 */
	@Column(name = "attr4")
	private String attr4;
	
	/**
	 * 扩展字段5
	 */
	@Column(name = "attr5")
	private String attr5;
	
	/**
	 * 扩展字段6
	 */
	@Column(name = "attr6")
	private String attr6;
	
	/**
	 * 扩展字段7
	 */
	@Column(name = "attr7")
	private String attr7;
	
	/**
	 * 扩展字段8
	 */
	@Column(name = "attr8")
	private String attr8;
	
	/**
	 * 扩展字段9
	 */
	@Column(name = "attr9")
	private String attr9;
	
	/**
	 * 扩展字段10
	 */
	@Column(name = "attr10")
	private String attr10;
	
	/**
	 * 扩展字段11
	 */
	@Column(name = "attr11")
	private String attr11;
	
	/**
	 * 扩展字段12
	 */
	@Column(name = "attr12")
	private String attr12;
	
	/**
	 * 扩展字段13
	 */
	@Column(name = "attr13")
	private String attr13;
	
	/**
	 * 扩展字段14
	 */
	@Column(name = "attr14")
	private String attr14;
	
	/**
	 * 扩展字段15
	 */
	@Column(name = "attr15")
	private String attr15;
	
	/**
	 * 扩展字段16
	 */
	@Column(name = "attr16")
	private String attr16;
	
	/**
	 * 扩展字段17
	 */
	@Column(name = "attr17")
	private String attr17;
	
	/**
	 * 扩展字段18
	 */
	@Column(name = "attr18")
	private String attr18;
	
	/**
	 * 扩展字段19
	 */
	@Column(name = "attr19")
	private String attr19;
	
	/**
	 * 扩展字段20
	 */
	@Column(name = "attr20")
	private String attr20;

	
}
