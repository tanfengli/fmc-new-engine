package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;

@Repository
public interface SysWfWorkitemRepository extends JpaRepository<SysWfWorkitem,String>,JpaSpecificationExecutor<SysWfWorkitem> {
	/**
	 * 实现流程:通过节点获取工作项 
	 * @param fdId
	 * @return
	 */
	public List<SysWfWorkitem> findByFdNodeId(String nodeId);
	
	
	/**
	 * 根据节点统计工作事项数目
	 * @param fdNodeId
	 * @return
	 */
	public Long countByFdNodeId(String fdNodeId);
	
	/**
	 * 根据令牌和用户查找工作事项
	 * @param wfTokenId
	 * @param userId
	 * @return
	 */
	@Query("select a from SysWfWorkitem a,SysWfToken b,SysNotifyTodo c,SysNotifyTodotarget d,SysOrgElement e where a.fdId=c.fdParameter2 and c.fdParameter1=b.fdNodeInstanceId and c.fdId=d.fdTodoid and d.fdOrgid=e.fdId and b.fdId = ?1 and e.fdNo = ?2")
	public SysWfWorkitem findByTokenIdAndUserNo(String wfTokenId,String userNo);
	
	/**
	 * 节点和用户获取工作事项
	 * @param nodeId
	 * @param userId
	 * @return
	 */
	@Query("select a from SysWfWorkitem a,SysNotifyTodo b,SysNotifyTodotarget c where a.fdId=b.fdParameter2 and b.fdId=c.fdTodoid and c.fdOrgid=?2 and b.fdParameter1 = ?1 and a.fdIsDone = 0")
	public List<SysWfWorkitem> findByNodeIdAndUserId(String nodeId,String userId);
	
	/**
	 * 根据节点编码获取工作事项
	 * @param nodeId
	 * @param userId
	 * @param wfInstanceId
	 * @return
	 */
	@Query("select a from SysWfWorkitem a,SysNotifyTodo b,SysNotifyTodotarget c,SysWfNode d where a.fdId=b.fdParameter2 and b.fdId=c.fdTodoid and d.fdId = a.fdNodeId and d.fdProcessId = ?3 and c.fdOrgid = ?2 and d.fdFactNodeId = ?1 and a.fdIsDone = 0")
	public List<SysWfWorkitem> findByNodeNoAndUserId(String nodeId,String userId,String wfInstanceId);
	
	/**
	 * 根据节点编码获取工作事项
	 * @param nodeId
	 * @param userId
	 * @param wfInstanceId
	 * @return
	 */
	@Query("select a from SysWfWorkitem a,SysNotifyTodo b,SysNotifyTodotarget c,SysWfNode d,SysOrgElement e where e.fdId=c.fdOrgid and a.fdId=b.fdParameter2 and b.fdId=c.fdTodoid and d.fdId = a.fdNodeId and d.fdProcessId = ?3 and e.fdNo = ?2 and d.fdFactNodeId = ?1")
	public List<SysWfWorkitem> findByNodeNoAndUserNo(String nodeId,String userNo,String wfInstanceId);
	
	/**
	 * 根据工作事项和人员获取关联工作事项
	 * @param relationWorkitemId
	 * @param expectedId
	 * @return
	 */
	public SysWfWorkitem findByFdRelationWorkitemIdAndFdExpectedId(String relationWorkitemId,String expectedId);
	
	/**
	 * 通过关联工作事项获取关联工作事项列表
	 * @param fdRelationWorkitemId
	 * @return
	 */
	public List<SysWfWorkitem> findByFdRelationWorkitemId(String fdRelationWorkitemId);
	
	/**
	 * 根据当前节点获取根源流程工作事项
	 * @param nodeId
	 * @return
	 */
	@Query("from SysWfWorkitem w where w.fdNodeId=?1 and w.fdRelationWorkitemId is null")
	public SysWfWorkitem getRootWorkitem(String nodeId);
	
	/**
	 * 根据节点工作事项
	 * @param fdNodeIdList
	 */
	@Modifying
	@Query("delete from SysWfWorkitem w where w.fdNodeId in ?1")
	public void deleteAllByFdNodeId(List<String> fdNodeIdList);
	
	/**
	 * 根据节点ID删除
	 * @param fdNodeId
	 */
	@Modifying
	public void deleteByFdNodeId(String fdNodeId);
	
}
