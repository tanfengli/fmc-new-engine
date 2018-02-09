package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuditNote;

public interface ISysWfAuditNoteService {
	
	public void saveNote(SysWfAuditNote note);

	/**
	 * 查找流程所有审批记录
	 * @param id
	 * @return List
	 */
	public List<SysWfAuditNote> findAllByFdProcessId(String id);
	
	/**
	 * 查找流程人工审批记录
	 * @param id
	 * @return List
	 */
	public List<SysWfAuditNote> findManualNoteByFdProcessId(String id);


	/**
	 * 查找处理人上一节点审批记录
	 * @param processId
	 * @param FactNodeId
	 * @param handleId
	 * @return
	 */
	public List<SysWfAuditNote> findPrevNodeAuditNote(String processId, String FactNodeId, String handleId);
	
	/**
	 * 根据实例id删除
	 * @Title: deleteByFdProcessId 
	 * @param fdProcessIdList
	 */
	public void deleteAllByFdProcessId(List<String> fdProcessIdList);
}
