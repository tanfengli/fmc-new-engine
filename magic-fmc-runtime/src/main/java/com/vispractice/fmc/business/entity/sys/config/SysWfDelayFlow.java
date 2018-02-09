package com.vispractice.fmc.business.entity.sys.config;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * 编  号：
 * 名  称：工作日历时间设置表
 * 描  述：WfMonitorCalendarMain.java
 * 完成日期：2017年12月5日 下午3:02:35
 * 编码作者："LaiJiashen"
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@Entity(name="sys_wf_delay_flow")
public class SysWfDelayFlow {
	
	@Id
	@Column(name = "FD_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String fdId;
	
	@Column(name = "FD_TEMPLATE_ID")
	private String fdTemplateId;
	
	@Column(name = "FD_DELAY_TIME")
	private String fdDelayTime;
	
	@Column(name = "FD_ENABLED")
	private String fdEnabled;
	
	@Column(name = "FD_CREATED_BY")
	private String fdCreatedBy;
	
	@Column(name = "FD_CREATED_DATE")
	private Date fdCreatedDate;
	
	@Column(name = "FD_LAST_UPDATED_BY")
	private String fdLastUpdatedBy;
	
	@Column(name = "FD_LAST_UPDATED_DATE")
	private String fdLastUpdatedDate;

}
