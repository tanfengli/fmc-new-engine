package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;
import org.springframework.data.domain.Example;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;

public interface ISysWfNodeService {
	/**
	 * 依据节点id取节点
	 * @return SysWfNode
	 */
	public SysWfNode findByFdId(String nodeId);
	
	/**
	 * 基于例子查找实例
	 * @param example
	 * @return
	 */
	public SysWfNode findByExample(Example<SysWfNode> example);
	
	/**
	 * 依据流程id和节点编号取节点
	 * @return
	 */
	public SysWfNode findByFdProcessIdAndFdFactNodeId(String wfInstanceId,String wfFactNodeId);
	/**
	 * 根据审批令牌获取节点
	 * @return
	 */
	public SysWfNode getWfNodeByTokenId(String wfInstanceId,String wfTokenId);
	
	
	/**
	 * 根据流程实例获取节点
	 * @param fdId
	 * @return
	 */
	public List<SysWfNode> findByFdProcessId(String fdId);

	/**
	 * 通过实例Id和申请人No获取当前处理节点
	 * @Title: getWfNode 
	 * @param processId
	 * @param userNo
	 * @return
	 */
	public SysWfNode getWfNode(String processId,String userNo);

	/**
	 * 根据流程实例和节点获取流程节点
	 * @param instanceId 流程实例
	 * @param nodeId 节点N
	 * @return
	 */
	public SysWfNode getNodeByInstanceIdAndNodeId(String instanceId,String nodeId);

	/**
	 * 根据实例id和处理人id获取流程节点
	 * @Title: getWfnodeByInstanceIdAndProcessorId 
	 * @param instanceId
	 * @param processorId
	 * @return
	 */
	public SysWfNode getWfNodeByInstanceIdAndProcessorId(String instanceId,String processorId);
	
	/**
	 * 保存节点
	 * @param node
	 */
	public void saveNode(SysWfNode node);
	/**
	 * 删除节点
	 * @param node
	 */
	public void delete(SysWfNode node);
	
	/**
	 * 根据流程实例ID删除节点
	 * @param fdProcessId
	 */
	public void deleteAllByFdProcessId(String fdProcessId);
	
	/**
	 * 根据流程实例ID删除节点和工作事项
	 * @param fdProcessId
	 */
	public void deleteAllNodeAndWorkitemByProcessId(String fdProcessId);
	
	/**
	 * 获取当前节点所有预期处理人和实际处理人
	 * 
	 * @param node
	 * @return
	 */
	public List<String> getNodeAllExpecterAndHandler(SysWfNode node);
	
}
