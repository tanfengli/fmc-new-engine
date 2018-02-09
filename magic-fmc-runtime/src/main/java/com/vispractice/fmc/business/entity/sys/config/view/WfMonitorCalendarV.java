package com.vispractice.fmc.business.entity.sys.config.view;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


/**
 * 
 * 编  号：
 * 名  称：工作日历视图
 * 描  述：WfMonitorCalendarV.java
 * 完成日期：2017年12月5日 下午5:42:56
 * 编码作者："LaiJiashen"
 */
@Data
@Entity(name="wf_monitor_calendar_v")
public class WfMonitorCalendarV {

	@Id
	@Column(name = "FD_ID")
	private String fdId;
	
	@Column(name = "FD_NAME")
	private String fdName;
	
	@Column(name = "FD_ENABLED")
	private String fdEnabled;
	
	@Column(name = "FD_CREATOR_NAME")
	private String fdCreatorName;
	
	@Column(name = "FD_YEAR")
	private String fdYear;
	
	@Column(name = "FD_SCOPE_IDS")
	private String fdScopeIds;
	
	@Column(name = "FD_SCOPE_NAMES")
	private String fdScopeNames;
	
	@Column(name = "FD_CREATED_BY")
	private String fdCreatedBy;
	
	@Column(name = "FD_CREATED_DATE")
	private Date fdCreatedDate;
	
	@Column(name = "FD_WEEK_START")
	private Long fdWeekStart;
	
	@Column(name = "FD_WEEK_END")
	private String fdWeekEnd;
}
