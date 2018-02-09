package com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor;

import java.util.ArrayList;
import java.util.List;

public class NodeDescriptor extends AbstractNodeDescriptor {
	/*
	 * 节点所引用全局的服务名(parent所指全局描述的节点ID)
	 */
	private String parent;
	
	/*
	 * 合并节点时所指向的源节点ID
	 */
	private String sourceId;
	
	@SuppressWarnings("unused")
	private ProcessDescriptor workflowDescriptor;

	private List<ResultDescriptor> resultDescriptors = new ArrayList<ResultDescriptor>();

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public List<ResultDescriptor> getResultDescriptors() {
		return resultDescriptors;
	}

	public void setResultDescriptor(List<ResultDescriptor> resultDescriptors) {
		this.resultDescriptors = resultDescriptors;
	}

	public void addResultDescriptor(ResultDescriptor resultDescriptor) {
		resultDescriptor.setNodeDescriptor(this);
		resultDescriptors.add(resultDescriptor);
	}
	
	public String getNextTargetNodeIds(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<resultDescriptors.size();i++){
			if(i>0){
				sb.append(";");
			}
			sb.append(resultDescriptors.get(i).getTarget());
		}
		return sb.toString();
	}
	
	public void setWorkflowDescriptor(ProcessDescriptor workflowDescriptor) {
		this.workflowDescriptor = workflowDescriptor;
	}
}
