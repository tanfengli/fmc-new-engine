/**
 * 
 */
package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.OANodeType;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ResultDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractNodeDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.ILineDetail;

/**
 * 并行启动节点
 * 
 * @author 傅游翔
 * 
 */
public class OASplitNodeDetail extends AbstractNodeDetail {

	/**
	 * 并行启动类型
	 */
	private String splitType;

	public String getSplitType() {
		return splitType;
	}

	public void setSplitType(String splitType) {
		this.splitType = splitType;
	}

	/**
	 * 关联节点ID
	 */
	private String relatedNodeIds;

	public String getRelatedNodeIds() {
		return relatedNodeIds;
	}

	public void setRelatedNodeIds(String relatedNodeIds) {
		this.relatedNodeIds = relatedNodeIds;
	}

	public NodeDescriptor parseNodeDescriptor() {
		NodeDescriptor nodeDescriptor = super.parseNodeDescriptor();
		nodeDescriptor.setParent(OAConstant.SPLIT_NODE_PARENT);
		if (loadOutLines().isEmpty()) {
			return nodeDescriptor;
		}
		for (int i = 0; i < loadOutLines().size(); i++) {
			ILineDetail line = loadOutLines().get(i);
			ResultDescriptor resultDescriptor = new ResultDescriptor();
			resultDescriptor.setPriority(line.getPriority());
			resultDescriptor.setCondition(line.getCondition());
			resultDescriptor.setTarget(line.getEndNodeId());
			nodeDescriptor.addResultDescriptor(resultDescriptor);
		}
		return nodeDescriptor;
	}

	@Override
	public String getType() {
		return OANodeType.SPLIT_NODE;
	}
}
