package com.vispractice.fmc.business.base.domain;

import java.util.List;

import lombok.Data;

import com.vispractice.fmc.business.entity.sys.config.WfMonitorCalendarMain;
import com.vispractice.fmc.business.entity.sys.config.WfMonitorCalendarSetting;

/**
 * 
 * 编  号：
 * 名  称：工作日历form
 * 描  述：WfMonitorCalendarForm.java
 * 完成日期：2017年12月5日 下午4:04:20
 * 编码作者："LaiJiashen"
 */
@Data
public class WfMonitorCalendarForm {

	private WfMonitorCalendarMain wfMonitorCalendarMain;
	
	/**
	 * xx年每月第一天为星期几
	 */
	private List<Integer> firstDateList;
	
	/**
	 * XX年每月有多少天
	 */
	private List<Integer> daysPerMonList;
	
	/**
	 * 日历列表
	 */
	private List<List<WfMonitorCalendarSetting>> calendarList;
	
}
