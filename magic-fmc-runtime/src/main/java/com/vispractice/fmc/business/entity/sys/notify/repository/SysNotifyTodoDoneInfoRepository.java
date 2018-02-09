package com.vispractice.fmc.business.entity.sys.notify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodoDoneInfo;

/**
 * 名  称：已办通知持久层服务
 * 描  述：SysNotifyTodoDoneInfoRepository.java
 * 完成日期：2017年7月14日 下午5:39:57
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysNotifyTodoDoneInfoRepository extends JpaRepository<SysNotifyTodoDoneInfo,String>,JpaSpecificationExecutor<SysNotifyTodoDoneInfo> {

	/**
	 * 根据待办id删除
	 * @Title: deleteByFdTodoId 
	 * @param fdTodoIdList
	 */
	@Modifying
	@Query("delete from SysNotifyTodoDoneInfo t where t.fdTodoid in ?1")
	public void deleteAllByFdTodoid(List<String> fdTodoIdList);
	
}
