package com.vispractice.fmc.business.service.sys.news;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.news.view.DocumentSubmmitedV;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessHistoryV;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessLogV;

public interface ISysNewsMainService {
	/**
	 * 实现流程:单据提交
	 * @Title:save 
	 * @param sysNewsMain
	 * @param localUser
	 * @return
	 */
	public void submitDocument(SysNewsMain sysNewsMain) throws WorkflowException;
	
	/**
	 * 依据业务系统编码及业务编号查找流程实例
	 * @param wfTemplateId
	 * @param docCreateId
	 * @return
	 */
	public SysNewsMain findByBusiSysIdAndApplCode(String BusiSysId,String applyCode);

	/**
	 * 实现流程:通过主键(fdId)获取SysNewsMain
	 * @Title:getByFdId 
	 * @param fdId
	 * @return
	 */
	public SysNewsMain getByFdId(String fdId);

	/**
	 * 分页查询我申请单据
	 * @param docSubV
	 * @param pageable
	 * @return
	 */
	public Page<DocumentSubmmitedV> searchDocumentSubmmitedV(DocumentSubmmitedV documentSubmmitedV,Pageable pageable);
	
	/**
	 * 实现流程:通过流程实例查出审批记录
	 * @param processHistoryV
	 * @param pageable
	 * @return
	 */
	public List<ProcessHistoryV> findProcessHistory(String wfInstanceId);
	
	/**
	 * 实现流程:通过流程实例查出审批日志记录
	 * @param processHistoryV
	 * @param pageable
	 * @return
	 */
	public List<ProcessLogV> findProcessLog(String wfInstanceId);
	
	/**
	 * 根据人员和流程实例获取单据信息
	 * @param userNo
	 * @param wfInstanceId
	 * @return
	 */
	public SysNewsMain findByUserNoAndWfinstanceId(String userNo,String wfInstanceId);

	/**
	 * 
	 * 删除流程实例及其相关信息
	 * @Title: deleteProcessInstance 
	 * @param fdIdStr
	 */
	public void deleteProcessInstance(String fdIdStr);
	
	
	/**
	 * 更新流程文档状态
	 * @param sysNewsMain
	 */
	public void save(SysNewsMain sysNewsMain);
}
