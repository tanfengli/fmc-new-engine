package com.vispractice.fmc.business.entity.sys.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.WfMonitorCalendarMain;
/**
 * 
 * 编  号：
 * 名  称：工作日历持久化接口
 * 描  述：WfMonitorCalendarMainRepository.java
 * 完成日期：2017年12月5日 下午3:13:50
 * 编码作者："LaiJiashen"
 */

@Repository
public interface WfMonitorCalendarMainRepository extends JpaRepository<WfMonitorCalendarMain,String>,JpaSpecificationExecutor<WfMonitorCalendarMain>{

	/**
	 * 根据启用状态获取日历
	 * @Title: findByFdEnabled 
	 * @param availableFlag
	 * @return
	 */
	public List<WfMonitorCalendarMain> findByFdEnabled(String availableFlag);

}
