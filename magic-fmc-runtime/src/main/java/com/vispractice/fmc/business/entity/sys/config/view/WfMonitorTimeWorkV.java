package com.vispractice.fmc.business.entity.sys.config.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


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
@Entity(name="wf_monitor_time_work_v")
public class WfMonitorTimeWorkV {
	
	
	@Id
	@Column(name = "FD_ID")
	private String fdId;
	
	@Column(name = "FD_NAME")
	private String fdName;
	
	@Column(name = "FD_TIME_START")
	private String fdTimeStart;
	
	@Column(name = "FD_TIME_END")
	private String fdTimeEnd;
	
	@Column(name = "FD_NOON_START")
	private String fdNoonStart;
	
	@Column(name = "FD_NOON_END")
	private String fdNoonEnd;
	
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

	@Column(name = "FD_CALENDAR_ID")
	private String fdCalendarId;
	
	@Column(name = "FD_CALENDAR_NAME")
	private String fdCalendarName;
	
	@Column(name = "FD_MONTHS")
	private String fdMonths;
	
	@Column(name = "FD_CREATOR_NAME")
	private String fdCreatorName;
	
}
