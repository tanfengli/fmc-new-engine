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
 * 结束并发分支节点
 * 
 * @author 傅游翔
 * 
 */
public class OAJoinNodeDetail extends AbstractNodeDetail {

	/**
	 * 聚合类型
	 */
	private String joinType;

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	/**
	 * 公式聚合文本
	 */
	private String formulaJoinTypeText;

	/**
	 * 公式聚合公式
	 */
	private String formulaJoinTypeValue;

	public String getFormulaJoinTypeText() {
		return formulaJoinTypeText;
	}

	public void setFormulaJoinTypeText(String formulaJoinTypeText) {
		this.formulaJoinTypeText = formulaJoinTypeText;
	}

	public String getFormulaJoinTypeValue() {
		return formulaJoinTypeValue;
	}

	public void setFormulaJoinTypeValue(String formulaJoinTypeValue) {
		this.formulaJoinTypeValue = formulaJoinTypeValue;
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
		nodeDescriptor.setParent(OAConstant.JOIN_NODE_PARENT);
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
		return OANodeType.JOIN_NODE;
	}
}
