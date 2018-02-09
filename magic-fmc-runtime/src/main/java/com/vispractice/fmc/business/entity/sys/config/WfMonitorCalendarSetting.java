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
 * 名  称：工作日历日期设置
 * 描  述：WfMonitorCalendarSetting.java
 * 完成日期：2017年12月5日 下午3:08:43
 * 编码作者："LaiJiashen"
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@Entity(name="wf_monitor_calendar_setting")
public class WfMonitorCalendarSetting {
	
	@Id
	@Column(name = "FD_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String fdId;
	
	@Column(name = "FD_CALENDAR_ID")
	private String fdCalendarId;
	
	@Column(name = "FD_YEAR")
	private String fdYear;
	
	@Column(name = "FD_MONTH")
	private String fdMonth;
	
	@Column(name = "FD_DAY")
	private String fdDay;
	
	@Column(name = "FD_TYPE")
	private String fdType;
	
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
