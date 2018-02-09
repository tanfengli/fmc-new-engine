package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.OANodeType;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ResultDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractNodeDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.ILineDetail;

/**
 * 机器人节点配置描述
 * 
 * @since 2009.08.07
 * @see
 */
public class OARobotNodeDetail extends AbstractNodeDetail implements OAConstant {
	/**
	 * 唯一标识
	 */
	private String unid = null;
	/**
	 * 机器人配置内容
	 */
	private String content = null;
	
	/**
	 * @return 唯一标识
	 */
	public String getUnid() {
		return unid;
	}
	
	/**
	 * @param Unid 设置唯一标识
	 */
	public void setUnid(String category) {
		this.unid = category;
	}

	/**
	 * @return 机器人配置内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content 设置机器人配置内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	public NodeDescriptor parseNodeDescriptor() {
		NodeDescriptor nodeDescriptor = super.parseNodeDescriptor();
		nodeDescriptor.setParent(OAConstant.ROBOT_NODE_PARENT);
		if(loadOutLines().isEmpty()){
			return nodeDescriptor;
		}
		ILineDetail line = loadOutLines().get(0);
		ResultDescriptor resultDescriptor = new ResultDescriptor();
		resultDescriptor.setTarget(line.getEndNodeId());
		nodeDescriptor.addResultDescriptor(resultDescriptor);
		return nodeDescriptor;
	}

	@Override
	public String getType() {
		return OANodeType.ROBOT_NODE;
	}
}
