package com.vispractice.fmc.business.entity.sys.config;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * 编  号：
 * 名  称：工作日历时间月份表
 * 描  述：WfMonitorCalendarMain.java
 * 完成日期：2017年12月5日 下午3:02:35
 * 编码作者："LaiJiashen"
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@Entity(name="wf_monitor_time_month")
public class WfMonitorTimeMonth {
	
	@Id
	@Column(name = "FD_TIME_ID")
	private String fdTimeId;
	
	@Column(name = "FD_MONTH")
	private String fdMonth;
	
}
