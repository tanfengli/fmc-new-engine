package com.vispractice.fmc.business.entity.sys.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.view.WfMonitorCalendarV;
/**
 * 
 * 编  号：
 * 名  称：工作日历视图持久化接口
 * 描  述：WfMonitorCalendarVRepository.java
 * 完成日期：2017年12月5日 下午5:46:14
 * 编码作者："LaiJiashen"
 */
@Repository
public interface WfMonitorCalendarVRepository extends JpaRepository<WfMonitorCalendarV,String>,JpaSpecificationExecutor<WfMonitorCalendarV>{

}
