package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;

@Repository
public interface SysWfNodeRepository extends JpaRepository<SysWfNode,String>,JpaSpecificationExecutor<SysWfNode> {
	/**
	 * 根据令牌获取当前节点信息
	 * @param wfInstance
	 * @param tokenId
	 * @return
	 */
	@Query("select a from SysWfNode a,SysWfToken b where a.fdId=b.fdNodeInstanceId and b.fdProcessId=?1 and b.fdId=?2")
	public SysWfNode getWfNodeByTokenId(String wfInstance,String tokenId);
	
	/**
	 * 根据流程实例获取节点信息
	 * @param fdId
	 * @return
	 */
	//@Query("select a from SysWfNode a where a.fdProcessId=?1 and a.fdNodeType<>'oaJoinNode'")
	@Query("select a from SysWfNode a where a.fdProcessId=?1")
	public List<SysWfNode> findByFdProcessId(String fdId);
	
	/**
	 * 根据流程id获取所有当前节点
	 * @param fdProcessId
	 * @return List<String>
	 */
	public List<SysWfNode> findAllByFdProcessId(String fdProcessId);
	
	/**
	 * 根据流程实例和人员信息获取节点信息
	 * @param wfInstance
	 * @param userNo
	 * @return
	 */
	@Query("select a from SysWfNode a,SysNotifyTodo b,SysNotifyTodotarget c,SysOrgElement d where a.fdId=b.fdParameter1 and b.fdId=c.fdTodoid and c.fdOrgid=d.fdId and a.fdProcessId=?1 and d.fdNo=?2 and b.fdType=1")
	public SysWfNode getSysWfNodeByWfInstanceAndUserNo(String wfInstance,String userNo);
	
	/**
	 * 实现流程:通过单据id和用户no获取对应处理节点<br/>
	 * @Title: getWfNodeByBillIdAndUserNo 
	 * @param billId 单据ID
	 * @param userNo 用户编号
	 * @return	SysWfNode实体
	 */
	@Query("select a from SysWfNode a,SysWfWorkitem b,SysOrgElement c where a.fdId=b.fdNodeId and b.fdExpectedId=c.fdId and a.fdProcessId=?1 and c.fdNo=?2")
	public SysWfNode getWfNodeByBillIdAndUserNo(String billId,String userNo);
	
	/**
	 * 
	 * 实现流程: 通过流程id和处理节点no获取对应处理节点<br/>
	 * @param processId 流程ID
	 * @param factNodeId 处理节点ID
	 * @return
	 */
	public SysWfNode findByFdProcessIdAndFdFactNodeId(String processId,String factNodeId);
	
	/**
	 * 根据实例id列表获取流程节点id列表
	 * @Title: findFdIdListByProcessId 
	 * @param fdProcessIdList
	 * @return
	 */
	@Query("select n.fdId from SysWfNode n where n.fdProcessId in ?1")
	public List<String> findFdIdListByProcessId(List<String> fdProcessIdList);
	
	/**
	 * 根据实例id删除
	 * @Title: deleteByFdProcessId 
	 * @param fdProcessIdList
	 */
	@Modifying
	@Query("delete from SysWfNode n where n.fdProcessId in ?1")
	public void deleteAllByFdProcessId(List<String> fdProcessIdList);
	
	@Modifying
	@Query("delete from SysWfNode n where n.fdProcessId = ?1")
	public void deleteAllByFdProcessId(String fdProcessId);
}
