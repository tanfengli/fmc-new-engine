package com.vispractice.fmc.business.entity.sys.notify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodo;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
/**
 * 名  称：通知表持久层服务
 * 描  述：SysNotifyTodoRepository.java
 * 完成日期：2017年7月14日 下午5:35:02
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysNotifyTodoRepository extends JpaRepository<SysNotifyTodo,String>,JpaSpecificationExecutor<SysNotifyTodo> {
	/**
	 * 获取TODO列表
	 * @Title: findByFdKeyAndFdParameter1AndFdParameter2 
	 * @param key 流程实例ID
	 * @param param1 节点fdId
	 * @param param2 工作项fdId
	 * @return
	 */
	public List<SysNotifyTodo> findByFdKeyAndFdParameter1AndFdParameter2(String key,String param1,String param2);
	
	@Query("select a from SysNotifyTodo a,SysNotifyTodotarget b,SysOrgElement c where a.fdId=b.fdTodoid and b.fdOrgid=c.fdId and a.fdModelId=?2 and c.fdNo=?1")
	public List<SysNotifyTodo> findByUserNoAndWfInstance(String userNo,String wfInstance);
	
	/**
	 * 根据流程id列表获取通知id列表
	 * @Title: findFdIdByProcessId 
	 * @param processIdList
	 * @return
	 */
	@Query("select a.fdId from SysNotifyTodo a where a.fdKey in ?1")
	public List<String> findFdIdByProcessId(List<String> processIdList);
	
	/**
	 * 根据流程id删除通知信息
	 * @Title: deleteByFdKey 
	 * @param processIdList
	 */
	@Modifying
	@Query("delete from SysNotifyTodo t where t.fdKey in ?1")
	public void deleteAllByFdKey(List<String> processIdList);
	
	/**
	 * 根据待办id获取待办人员
	 * @Title: findTodoTargetElements 
	 * @param todoId
	 * @return
	 */
	@Query("select e from SysNotifyTodo n,SysNotifyTodotarget t,SysOrgElement e where n.fdId=t.fdTodoid and t.fdOrgid=e.fdId and n.fdId=?1")
	public List<SysOrgElement> findTodoTargetElements(String todoId);
	
}
