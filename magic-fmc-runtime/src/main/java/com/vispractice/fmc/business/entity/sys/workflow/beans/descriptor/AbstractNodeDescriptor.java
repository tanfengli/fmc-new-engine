package com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor;

public class AbstractNodeDescriptor {
	/*
	 * 节点ID
	 */
	private String id;
	
	/*
	 * 节点名
	 */
	private String name;

	private ActionDescriptor actionDescriptor;

	@SuppressWarnings("unused")
	private AbstractProcessDescriptor abstractWorkflowDescriptor;

	public void setAbstractWorkflowDescriptor(
			AbstractProcessDescriptor abstractWorkflowDescriptor) {
		this.abstractWorkflowDescriptor = abstractWorkflowDescriptor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActionDescriptor getActionDescriptor() {
		return actionDescriptor;
	}

	public void setActionDescriptor(ActionDescriptor actionDescriptor) {
		this.actionDescriptor = actionDescriptor;
	}
}
