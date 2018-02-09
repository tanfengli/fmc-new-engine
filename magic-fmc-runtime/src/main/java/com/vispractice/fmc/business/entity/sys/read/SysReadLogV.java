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
 * 名  称：SysReadLogV
 * 描  述：阅读日志视图
 * 完成日期：2017年10月18日 下午4:18:01
 * 编码作者：LaiJiashen
 */
@Data
@Entity
@Table(name = "sys_read_log_v")
public class SysReadLogV implements Serializable {
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

	@Column(name = "FD_MODEL_ID")
	private String fdModelId;

	/**
	 * 阅读类型 0：发布后阅读，1：流程流转过程中阅读
	 */
	@Column(name = "FD_READ_TYPE")
	private Long fdReadType;

	@Column(name = "FD_READER_ID")
	private String fdReaderId;

	@Column(name = "FD_READER_NAME")
	private String fdReaderName;
	
	@Column(name = "FD_READER_DEPT_ID")
	private String fdReaderDeptId;

	@Column(name = "FD_READER_DEPT_NAME")
	private String fdReaderDeptName;
	
}
