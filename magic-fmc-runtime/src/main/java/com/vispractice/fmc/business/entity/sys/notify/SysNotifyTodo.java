package com.vispractice.fmc.business.entity.sys.notify;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


import lombok.Data;

/**
 * 
 * 编 号： 名 称：通知表 描 述：SysNotifyTodo.java 
 * 完成日期：2017年7月14日 下午5:27:12
 * 编码作者："LaiJiashen"
 */

@Entity
@Data
@Table(name = "SYS_NOTIFY_TODO")
public class SysNotifyTodo {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_APP_NAME")
	private String fdAppName;

	@Column(name = "FD_MODEL_NAME")
	private String fdModelName;

	@Column(name = "FD_MODEL_ID")
	private String fdModelId;

	@Column(name = "FD_KEY")
	private String fdKey;

	/**
	 * 节点标识
	 */
	@Column(name = "FD_PARAMETER1")
	private String fdParameter1;

	/**
	 * 工作项标识
	 */
	@Column(name = "FD_PARAMETER2")
	private String fdParameter2;

	@Column(name = "FD_CREATE_TIME")
	private Date fdCreateTime;

	@Column(name = "FD_SUBJECT")
	private String fdSubject;

	@Column(name = "FD_TYPE")
	private Long fdType;

	@Column(name = "FD_LINK")
	private String fdLink;

	@Column(name = "FD_BUNDLE")
	private String fdBundle;

	@Column(name = "FD_REPLACE_TEXT")
	private String fdReplaceText;

	@Column(name = "FD_MD5")
	private String fdMd5;

}
