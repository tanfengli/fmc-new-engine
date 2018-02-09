package com.vispractice.fmc.business.entity.sys.workflow.beans.parameter;

import lombok.Data;
import com.vispractice.fmc.business.base.utils.BeanReaderWriter;

/**
 * @ClassName WorkitemParameter
 * workItem 参数bean
 * @author sky
 * @Date Jun 7, 2017
 * @version 1.0.0
 */
@Data
public class WorkitemParameter {
	/**
	 * workItem文件标识
	 */
	public static String WORKITEM_PARAMETER_NODE = "<workitem-param />";

	/**
	 * 处理身份
	 */
	private String handlerIdentity;
	
	/**
	 * 工作项标识
	 */
	private String workitemId;
	
	/**
	 * 处理节点
	 */
	private String nodeId;
	
	/**
	 * 当前处理人
	 */
	private String handlerId;
	
	/**
	 * 操作类型
	 */
	private String operationType;
	
	/**
	 * 驳回或跳转到的节点
	 */
	private String jumpToNodeId;
	
	/**
	 * 选择岗位或个人标识
	 */
	private String toOtherHandlerIds;

	/**
	 * 选择岗位或个人名称
	 */
	private String toOtherHandlerNames;
	
	/**
	 * 被转办处理人身份标识,节点中所选择的处理人标识
	 */
	private String reToHandlerId;
	
	/**
	 * 驳回的节点通过后直接返回本节点
	 */
	private String refusePassedToThisNode;
	
	/**
	 * 在特权人修改处理人时,保存修改前处理人名字,用于流程记录中信息
	 */
	private String oldHandlerNames;
	
	/**
	 * 驳回的节点当前处理人的位移
	 */
	private int currHandlerOffset;
	
	/**
	 * 通知选项,流程结束后通知我
	 */
	private String notifyOnFinish;
	
	/**
	 * 处理意见
	 */
	private String auditNode;
	
	/**
	 * 流程停滞多少天后,通知起草人
	 */
	private String dayOfNotifyDrafter = null;
	
	/**
	 * 操作名字集合
	 */
	private String oprNames;
	
	/**
	 * 通知方式
	 */
	private String notifyType;
	
	/**
	 * 关联工作项,用于沟通类、转办类中关联处理人
	 */
	private String relationWorkitemId;
	
	/**
	 * 取消沟通管理工作项
	 */
	private String celRelationWorkitemIds;
	
	/**
	 * 沟通意见和处理人关联标识 
	 */
	private String fdIdentityFlag;
	
	/**
	 * 是否开启沟通意见隐藏 
	 */
	private String isHiddenAuditNote;
	
	/**
	 * 即将流向的节点
	 */
	private String futureNodeId;
	
	/**
	 * 处理事务
	 */
	private String operationItems;
	
	/**
	 * 沟通范围限制,用户标识
	 */
	private String communicateScopeHandlerIds;
	/**
	 * 沟通范围限制,用户名称
	 */
	private String communicateScopeHandlerNames;

	/**
	 * 回收流程节点
	 */
	private String recoverProcessIds;
	
	/**
	 * 回收子流程
	 */
	private String isRecoverPassedSubprocess;
	
	/**
	 * 是否开启多级沟通
	 */
	private String isMutiCommunicate;
	
	/**
	 * 加签功能
	 */
	private String sourceOperType;
	
	private String rootWiParameter;
	
	public String getSourceOperType() {
		return sourceOperType;
	}

	public void setSourceOperType(String sourceOperType) {
		this.sourceOperType = sourceOperType;
	}

	private static BeanReaderWriter beanReaderWriter = new BeanReaderWriter(WorkitemParameter.class);

	public String toString() {
		return beanReaderWriter.write(this);
	}

	public synchronized static WorkitemParameter parse(String xml)
			throws Exception {
		return beanReaderWriter.parse(xml, WORKITEM_PARAMETER_NODE);
	}
}
