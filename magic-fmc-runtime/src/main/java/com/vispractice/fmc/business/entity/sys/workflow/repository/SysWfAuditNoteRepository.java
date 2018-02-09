package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuditNote;

@Repository
public interface SysWfAuditNoteRepository
		extends JpaRepository<SysWfAuditNote, String>, JpaSpecificationExecutor<SysWfAuditNote> {
	
	/**
	 * 查找流程所有审批记录
	 * @param id
	 * @return
	 */
	public List<SysWfAuditNote> findByFdProcessIdOrderByFdCreateDateAsc(String processId);
	
	/**
	 * 查找流程所有审批记录
	 * @param id
	 * @return
	 */
	public List<SysWfAuditNote> findByFdProcessIdAndFdActionIdIsNotNullOrderByFdCreateDateAsc(String processId);
	
	@Query("select note from SysWfAuditNote note where note.fdProcessId = ?1 and note.fdFactNodeId = ?2 and fdHandlerId = ?3 order by fdCreateDate desc")
	public List<SysWfAuditNote> findPrevNodeAuditNote(String processId, String FactNodeId, String handleId);
	
	/**
	 * 根据实例id删除
	 * @Title: deleteByFdProcessId 
	 * @param fdProcessIdList
	 */
	@Modifying
	@Query("delete from SysWfAuditNote n where n.fdProcessId in ?1")
	public void deleteAllByFdProcessId(List<String> fdProcessIdList);

}
