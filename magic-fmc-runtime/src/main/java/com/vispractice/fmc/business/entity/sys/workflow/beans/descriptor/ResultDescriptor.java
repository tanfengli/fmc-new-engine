package com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor;

public class ResultDescriptor {
	/*
	 * 目标节点ID
	 */
	private String target;
	
	/*
	 * 分支条件
	 */
	private String condition;
	
	/*
	 * 条件执行优先级
	 */
	private int priority;
	
	@SuppressWarnings("unused")
	private NodeDescriptor nodeDescriptor;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setNodeDescriptor(NodeDescriptor nodeDescriptor) {
		this.nodeDescriptor = nodeDescriptor;
	}
}
