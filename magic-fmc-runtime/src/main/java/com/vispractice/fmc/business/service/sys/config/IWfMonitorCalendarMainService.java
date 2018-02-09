package com.vispractice.fmc.business.service.sys.config;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.base.domain.WfMonitorCalendarForm;
import com.vispractice.fmc.business.entity.sys.config.WfMonitorCalendarMain;
import com.vispractice.fmc.business.entity.sys.config.view.WfMonitorCalendarV;

/**
 * 
 * 编  号：
 * 名  称：工作日历服务接口
 * 描  述：WfMonitorCalendarMainService.java
 * 完成日期：2017年12月5日 下午4:00:10
 * 编码作者："LaiJiashen"
 */
public interface IWfMonitorCalendarMainService {

	/**
	 * 获取日历
	 * @Title: getCalendarView 
	 * @param calendarId
	 * @param myYear
	 * @param week1
	 * @param week2
	 * @return
	 */
	public WfMonitorCalendarForm getCalendarView(String calendarId, String myYear,
			String week1, String week2);

	/**
	 * 根据条件查询所有工作日历
	 * @param wfMonitorCalendarV
	 * @param pageable
	 * @return
	 */
	public Page<WfMonitorCalendarV> findAll(WfMonitorCalendarV wfMonitorCalendarV,
			Pageable pageable);

	/**
	 * 获取启用状态的日历
	 * @Title: getEnabled 
	 * @param fdId
	 * @return
	 */
	public List<WfMonitorCalendarMain> getEnabled(String fdId);

	/**
	 * 保存工作日历主表及非工作日信息
	 * @Title: saveCalendarAndDays 
	 * @param wfMonitorCalendarForm
	 * @param creatorId 
	 */
	public void saveCalendarAndDays(WfMonitorCalendarForm wfMonitorCalendarForm, String creatorId);
	
	/**
	 * 根据日历id删除工作日历
	 * @Title: deleteByfdId 
	 * @param calendarId
	 */
	public void deleteByFdId(String calendarId);

}
