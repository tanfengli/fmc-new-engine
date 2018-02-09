package com.vispractice.fmc.business.entity.sys.workflow.beans.parameter;

public class WorkitemData {
	public static String WORKITEM_DATA_NODE = "<workitem-data/>";

	/*
	 * 操作类型
	 */
	private String operationType;

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	/*
	 * 驳回或跳转到的节点
	 */
	private String jumpToNodeId;

	public String getJumpToNodeId() {
		return jumpToNodeId;
	}

	public void setJumpToNodeId(String jumpToNodeId) {
		this.jumpToNodeId = jumpToNodeId;
	}

	/*
	 * 选择岗位或个人
	 */
	private String toOtherHandlerIds;

	public String getToOtherHandlerIds() {
		return toOtherHandlerIds;
	}

	public void setToOtherHandlerIds(String toOtherHandlerIds) {
		this.toOtherHandlerIds = toOtherHandlerIds;
	}

	/*
	 * 驳回的节点通过后直接返回本节点
	 */
	private String refusePassedToThisNode;

	public String getRefusePassedToThisNode() {
		return refusePassedToThisNode;
	}

	public void setRefusePassedToThisNode(String refusePassedToThisNode) {
		this.refusePassedToThisNode = refusePassedToThisNode;
	}

	/*
	 * 处理意见
	 */
	private String auditNode;

	public String getAuditNode() {
		return auditNode;
	}

	public void setAuditNode(String auditNode) {
		this.auditNode = auditNode;
	}

	/*
	 * 通知方式
	 */
	private String notifyType;

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	/*
	 * 通知选项,流程结束后通知我
	 */
	private String notifyOnFinish;

	public String getNotifyOnFinish() {
		return notifyOnFinish;
	}

	public void setNotifyOnFinish(String notifyOnFinish) {
		this.notifyOnFinish = notifyOnFinish;
	}
}
