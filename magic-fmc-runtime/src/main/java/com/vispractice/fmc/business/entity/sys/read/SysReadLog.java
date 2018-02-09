package com.vispractice.fmc.business.entity.sys.read;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * 编  号：
 * 名  称：SysReadLog
 * 描  述：阅读日志实体
 * 完成日期：2017年10月18日 下午4:18:01
 * 编码作者："LaiJiashen"
 */
@Data
@Entity
@Table(name = "sys_read_log")
public class SysReadLog implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 阅读类型 0：发布后阅读
	 */
	public static final int READ_LOG_TYPE_PUBLISH = 0;
	/**
	 * 阅读类型 1：流程流转过程中阅读
	 */
	public static final int READ_LOG_TYPE_PROCESS = 1;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_READ_TIME")
	private Date fdReadTime;

	@Column(name = "FD_READER_CLIENT_IP")
	private String fdReaderClientIp;

	@Column(name = "FD_IS_NEW_VERSION")
	private Long fdIsNewVersion;

	@Column(name = "FD_KEY")
	private String fdKey;

	@Column(name = "FD_MODEL_NAME")
	private String fdModelName;

	@Column(name = "FD_MODEL_ID")
	private String fdModelId;

	/**
	 * 阅读类型 0：发布后阅读，1：流程流转过程中阅读
	 */
	@Column(name = "FD_READ_TYPE")
	private Long fdReadType;

	@Column(name = "FD_READER_ID")
	private String fdReaderId;

}
