package com.vispractice.fmc.business.service.sys.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.config.WfMonitorTimeWork;
import com.vispractice.fmc.business.entity.sys.config.view.WfMonitorTimeWorkV;

/**
 * 
 * 编  号：
 * 名  称：工作时间服务接口
 * 描  述：WfMonitorCalendarMainService.java
 * 完成日期：2017年12月5日 下午4:00:10
 * 编码作者："LaiJiashen"
 */
public interface IWfMonitorTimeWorkService {	
	/**
	 * 根据条件查询所有工作时间
	 * @param wfMonitorCalendarV
	 * @param pageable
	 * @return
	 */
	public Page<WfMonitorTimeWorkV> findAll(WfMonitorTimeWorkV wfMonitorCalendarV,Pageable pageable);

	/**
	 * 保存
	 * @Title: save 
	 * @param wfMonitorTimeWork
	 */
	public void save(WfMonitorTimeWork wfMonitorTimeWork);

	/**
	 * 删除一条 记录
	 * @Title: deleteOne 
	 * @param fdId
	 */
	public void deleteOne(String fdId);

}
