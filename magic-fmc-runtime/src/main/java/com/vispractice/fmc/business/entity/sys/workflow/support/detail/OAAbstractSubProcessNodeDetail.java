/**
 * 
 */
package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ResultDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractNodeDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.ILineDetail;

/**
 * 子流程定义类
 * 
 * @author 傅游翔 2011-04
 * 
 */
public abstract class OAAbstractSubProcessNodeDetail extends AbstractNodeDetail
		implements OAConstant {

	/**
	 * 子流程节点配置内容，采用JSON格式序列化
	 */
	protected String configContent;

	/**
	 * 子流程节点配置内容
	 * 
	 * @return
	 */
	public String getConfigContent() {
		return configContent;
	}

	/**
	 * 子流程节点配置内容
	 * 
	 * @param configContent
	 */
	public void setConfigContent(String configContent) {
		this.configContent = configContent;
	}

	abstract protected String getNodeParentString();

	@Override
	public NodeDescriptor parseNodeDescriptor() {
		NodeDescriptor nodeDescriptor = super.parseNodeDescriptor();
		nodeDescriptor.setParent(getNodeParentString());
		if (loadOutLines().isEmpty()) {
			return nodeDescriptor;
		}
		ILineDetail line = loadOutLines().get(0);
		ResultDescriptor resultDescriptor = new ResultDescriptor();
		resultDescriptor.setTarget(line.getEndNodeId());
		nodeDescriptor.addResultDescriptor(resultDescriptor);
		return nodeDescriptor;
	}
}
