package com.vispractice.fmc.business.entity.sys.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.WfMonitorCalendarSetting;
/**
 * 
 * 编  号：
 * 名  称：工作日历日期设置持久化接口
 * 描  述：WfMonitorCalendarSettingRepository.java
 * 完成日期：2017年12月5日 下午3:14:13
 * 编码作者："LaiJiashen"
 */
@Repository
public interface WfMonitorCalendarSettingRepository extends JpaRepository<WfMonitorCalendarSetting,String>,JpaSpecificationExecutor<WfMonitorCalendarSetting>{

	/**
	 * 根据日历id，年，月，获取日期类型实体
	 * @param fdCalendarId 日历主表id
	 * @param fdYear 年份
	 * @param fdMonth 月份
	 * @return 
	 */
	public List<WfMonitorCalendarSetting> findByFdCalendarIdAndFdYearAndFdMonthOrderByFdDay(String fdCalendarId,String fdYear,String fdMonth);
	
	/**
	 * 根据日历id获取工作日日历
	 * @param fdCalendarId
	 * @return
	 */
	public List<WfMonitorCalendarSetting> findByFdCalendarId(String fdCalendarId);
	
	/**
	 * 根据日历主表id删除日历设置
	 * @Title: deleteByFdCalendarId 
	 * @param fdCalendarId
	 */
	public void deleteByFdCalendarId(String fdCalendarId);
	
}
