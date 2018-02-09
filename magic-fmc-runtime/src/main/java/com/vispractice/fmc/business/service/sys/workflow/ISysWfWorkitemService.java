package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;

import org.springframework.data.domain.Example;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfByaccredit;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;

/**
 * 编  号：
 * 名  称：ISysWfWorkitemService
 * 描  述：流程工作事项服务接口
 * 完成日期：2017年11月24日 下午5:53:53
 * 编码作者：ZhouYanyao
 */
public interface ISysWfWorkitemService {
	/**
	 * 根据节点ID获取沟通范围
	 * @param nodeId
	 * @return
	 */
	public List<SysOrgElement> getCommunicateScope(String nodeId);

	/**
	 * 根绝主键获取工作事项
	 * @param fdId
	 * @return
	 */
	public SysWfWorkitem findByFdId(String fdId);
	
	/**
	 * 依据例子查找实例	
	 * @param workitem
	 * @return
	 */
	public SysWfWorkitem findByExample(Example<SysWfWorkitem> example);
	
	/**
	 * 保存工作项
	 * @param item
	 */
	public void save(SysWfWorkitem item);
	
	/**
	 * 保存工作项列表
	 * @Title: save 
	 * @param itemList
	 */
	public void saveWfItemList(List<SysWfWorkitem> itemList);
	
	/**
	 * 删除工作项
	 * @param fdId
	 * @return
	 */
	public void remove(SysWfWorkitem sysWfWorkitem);
	
	/**
	 * @param fdId
	 * @return
	 */
	public void removeByFdNodeId(SysWfNode node);
	
	/**
	 * 生成历史工作项信息
	 * @param hisWorkitem
	 */
	public void saveHistoryWorkItem(SysWfWorkitem item, SysWfHistoryWorkitem hisWorkitem);
	
	/**
	 * 获取指定节点工作项数量
	 * @param fdNodeId
	 * @return
	 */
	public Long countByFdNodeId(String fdNodeId);
	
	public List<SysWfHistoryWorkitem> findByFdHistoryId(String historyId);
	
	/**
	 * 根据流程节点ID获取工作事项
	 * @param wfNodeId
	 * @return
	 */
	public List<SysWfWorkitem> findByNodeId(String wfNodeId);
	
	/**
	 * 保存授权项
	 * @param wfByaccreditList
	 */
	public void saveWfByaccreditList(List<SysWfByaccredit> wfByaccreditList);
	

	/**
	 * 获取当前节点所有预期处理人和实际处理人
	 * @param node
	 * @return
	 */
	public List<String> getNodeAllExpecterAndHandler(SysWfNode node);
	
	/**
	 * 根据节点和人获取工作事项
	 * @param nodeId
	 * @param userId
	 * @return
	 */
	public List<SysWfWorkitem> findByNodeIdAndUserId(String nodeId,String userId);
	
	/**
	 * 根据节点和人获取工作事项
	 * @param nodeNo
	 * @param userId
	 * @param wfInstanceId
	 * @return
	 */
	public List<SysWfWorkitem> findByNodeNoAndUserId(String nodeNo,String userId,String wfInstanceId);
	
	/**
	 * 获取关联工作事项
	 * @param fdRelationWorkitemId
	 * @return
	 */
	public List<SysWfWorkitem> findByFdRelationWorkitemId(String fdRelationWorkitemId);
	
	/**
	 * 找出相关工作事项
	 * @param relationWorkitemId
	 * @param expectedId
	 * @return
	 */
	public SysWfWorkitem findByFdRelationWorkitemIdAndFdExpectedId(String relationWorkitemId,String expectedId);
	
	/**
	 * 锁定工作项
	 * @param workitem
	 */
	public void trylockHandlerWorkitem(SysWfWorkitem workitem) ;
	
	/**
	 * 解锁工作项
	 * @param workitem
	 */
	public void unlockHandlerWorkitem(String workitemId) ;
	
	/**
	 * 解锁工作项
	 * @param workitem
	 */
	public void unlockHandlerWorkitem(SysWfWorkitem workitem) ;
}
