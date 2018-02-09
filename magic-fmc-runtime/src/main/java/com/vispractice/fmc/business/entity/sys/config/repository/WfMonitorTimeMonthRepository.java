package com.vispractice.fmc.business.entity.sys.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.WfMonitorTimeMonth;
/**
 * 
 * 编  号：
 * 名  称：WfMonitorTimeWorkVRepository
 * 描  述：WfMonitorTimeWorkVRepository.java
 * 完成日期：2017年12月7日 下午4:16:30
 * 编码作者："LaiJiashen"
 */
@Repository
public interface WfMonitorTimeMonthRepository extends JpaRepository<WfMonitorTimeMonth,String>,JpaSpecificationExecutor<WfMonitorTimeMonth>{

	@Modifying
	@Query(value="delete from Wf_Monitor_Time_Month m where m.fd_time_id=?1",nativeQuery=true)
	public void deleteByFdTimeId(String fdTimeId);
	
	@Modifying
	@Query(value="insert into Wf_Monitor_Time_Month m (m.fd_time_id,m.fd_month) values(?1,?2)",nativeQuery=true)
	public void insertOne(String fdTimeId,String month);
	
}
