package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfToken;

public interface SysWfTokenRepository extends JpaRepository<SysWfToken,String> {
	@Query("select a from SysWfToken a,SysWfNode b where a.fdProcessId=b.fdProcessId and a.fdNodeInstanceId=b.fdId and b.fdFactNodeId=?1 and a.fdProcessId=?2")
	SysWfToken findWfTokenByNodeIdAndInstanceId(String nodeId,String wfInstanceId);
	
	@Query("select a from SysWfToken a where a.fdTargetNodeId=?1 and a.fdProcessId=?2")
	SysWfToken findWfTokenByNodeIdAndTargetNodeId(String nodeId,String wfInstanceId);
	
	@Query("select a from SysWfToken a,SysNotifyTodo b,SysNotifyTodotarget c,SysOrgElement d where a.fdProcessId=b.fdModelId and a.fdNodeInstanceId=b.fdParameter1 and b.fdId=c.fdTodoid and c.fdOrgid=d.fdId and d.fdNo=?1 and a.fdProcessId=?2")
	List<SysWfToken> findWfTokenByUserNoAndInstanceId(String userNo,String wfInstanceId);
	
	/**
	 * 查找所有下级token
	 * @param fdParentId
	 * @return
	 */
	List<SysWfToken> findTokenListByFdParentId(String fdParentId);
	
	List<SysWfToken> findTokenListByFdProcessIdAndFdFromNodeId(String processId, String fromNodeId);
	
	/**
	 * 删除所有下级token
	 * @param fdParentId
	 */
	void deleteAllByFdParentId(String fdParentId);
	
	/**
	 * 根据流程id删除令牌
	 * @Title: deleteByFdProcessId 
	 * @param fdProcessIdList
	 */
	@Modifying
	@Query("delete from SysWfToken t where t.fdProcessId in ?1")
	public void deleteAllByFdProcessId(List<String> fdProcessIdList);
	
	@Query("select a from SysWfToken a,SysWfNode b where a.fdProcessId=b.fdProcessId and a.fdNodeInstanceId=b.fdId and b.fdFactNodeId=?1 and a.fdProcessId=?2  and a.fdFromNodeId=?3")
	SysWfToken findWfTokenByNodeIdAndInstanceIdAndFromNodeId(String nodeId,String wfInstanceId,String fromNodeId);

	List<SysWfToken> findTokenListByFdProcessId(String fdProcessId);
}
