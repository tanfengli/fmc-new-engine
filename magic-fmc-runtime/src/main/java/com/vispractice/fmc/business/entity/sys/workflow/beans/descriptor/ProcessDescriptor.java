package com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor;

import com.vispractice.fmc.business.base.utils.BeanReaderWriter;

public class ProcessDescriptor extends AbstractProcessDescriptor {

	public static String WORKFLOW_DESCRIPTOR_NODE = "<process/>";

	// 取得初始节点
	private NodeDescriptor initialNodeDescriptor;
	
	private String id;

	private String name;
	
	private String parent;
	
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	// 取得结束节点
	private NodeDescriptor finallyNodeDescriptor;
	
	// 取得节点描述对象
	public NodeDescriptor getNodeDescriptor(String nodeId) {
		if (nodeDescriptors == null || nodeId == null)
			return null;
		for (int i = 0; i < nodeDescriptors.size(); i++) {
			NodeDescriptor nodeDescriptor = (NodeDescriptor) nodeDescriptors.get(i);
			if (nodeId.equals(nodeDescriptor.getId())) {
				return nodeDescriptor;
			}
		}
		if (nodeId.equals(getInitialNodeDescriptor().getId())) {
			return getInitialNodeDescriptor();
		}
		if (nodeId.equals(getFinallyNodeDescriptor().getId())) {
			return getFinallyNodeDescriptor();
		}
		return null;
	}
	
	// 根据节点类型取得节点描述对象
	public NodeDescriptor getNodeDescriptorByType(String parent) {
		if (nodeDescriptors == null || parent == null)
			return null;
		for (int i = 0; i < nodeDescriptors.size(); i++) {
			NodeDescriptor nodeDescriptor = (NodeDescriptor) nodeDescriptors.get(i);
			if (parent.equals(nodeDescriptor.getParent())) {
				return nodeDescriptor;
			}
		}
		if (parent.equals(getInitialNodeDescriptor().getParent())) {
			return getInitialNodeDescriptor();
		}
		if (parent.equals(getFinallyNodeDescriptor().getParent())) {
			return getFinallyNodeDescriptor();
		}
		return null;
	}

	public NodeDescriptor getInitialNodeDescriptor() {
		return initialNodeDescriptor;
	}

	public void setInitialNodeDescriptor(NodeDescriptor initialNodeDescriptor) {
		this.initialNodeDescriptor = initialNodeDescriptor;
	}

	public NodeDescriptor getFinallyNodeDescriptor() {
		return finallyNodeDescriptor;
	}

	public void setFinallyNodeDescriptor(NodeDescriptor finallyNodeDescriptor) {
		this.finallyNodeDescriptor = finallyNodeDescriptor;
	}
	
	private static BeanReaderWriter beanReaderWriter = new BeanReaderWriter(
			ProcessDescriptor.class);

	public String toString() {
		return beanReaderWriter.write(this);
	}

	public synchronized static ProcessDescriptor parse(String xml)
			throws Exception {
		return beanReaderWriter.parse(xml, WORKFLOW_DESCRIPTOR_NODE);
	}

}
