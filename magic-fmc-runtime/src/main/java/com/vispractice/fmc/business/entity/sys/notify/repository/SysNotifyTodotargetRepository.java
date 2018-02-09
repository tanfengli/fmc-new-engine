package com.vispractice.fmc.business.entity.sys.notify.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodotarget;

/**
 * 名  称：SysNotifyTodotargetRepository
 * 描  述：SysNotifyTodotargetRepository.java
 * 完成日期：2017年7月14日 下午6:13:06
 * 编码作者：LaiJiashen
 */
@Repository
public interface SysNotifyTodotargetRepository extends JpaRepository<SysNotifyTodotarget,String>,JpaSpecificationExecutor<SysNotifyTodotarget> {
	/**
	 * 批量删除待办信息
	 * @param todoId
	 * @param orgIds
	 */
	@Modifying
	@Query("delete from SysNotifyTodotarget where fd_todoid = ?1 and fd_orgid in ?2")
	public void deleteTodoTarget(String todoId,List<String> orgIds);
	
	/**
	 * 删除待办信息
	 * @param todoId
	 * @param orgIds
	 */
	@Modifying
	@Query("delete from SysNotifyTodotarget where fd_todoid=?1 and fd_orgid = ?2")
	public void deleteTodoTarget(String todoId,String orgIds);
	
	/**
	 * 获取代办目标
	 * @Title: findByfdTodoidAndfdOrgid 
	 * @param todoId 待办标识
	 * @param orgIds 目标用户标识
	 * @return
	 */
	public SysNotifyTodotarget findByFdTodoidAndFdOrgid(String todoId,String orgIds);
	
	/**
	 * 根据流程实例找待办信息
	 * @param wfInstanceId
	 * @return
	 */
	@Query("select b from SysNotifyTodo a,SysNotifyTodotarget b where a.fdId = b.fdTodoid and a.fdModelId = ?1")
	public List<SysNotifyTodotarget> findByWfInstanceId(String wfInstanceId);
	
	/**
	 * 根据待办id删除
	 * @Title: deleteByFdTodoId 
	 * @param fdTodoIdList
	 */
	@Modifying
	public void deleteAllByFdTodoidIn(List<String> fdTodoIdList);
	
}
