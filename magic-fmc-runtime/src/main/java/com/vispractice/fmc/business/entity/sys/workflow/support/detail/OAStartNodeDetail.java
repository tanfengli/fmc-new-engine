package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.OANodeType;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ResultDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractNodeDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.ILineDetail;

public class OAStartNodeDetail extends AbstractNodeDetail {
	public NodeDescriptor parseNodeDescriptor() {
		NodeDescriptor nodeDescriptor = super.parseNodeDescriptor();
		nodeDescriptor.setParent(OAConstant.START_NODE_PARENT);
		if(loadOutLines().isEmpty()){
			return nodeDescriptor;
		}
		ILineDetail line =  loadOutLines().get(0);
		ResultDescriptor resultDescriptor = new ResultDescriptor();
		resultDescriptor.setTarget(line.getEndNodeId());
		nodeDescriptor.addResultDescriptor(resultDescriptor);
		return nodeDescriptor;
	}

	@Override
	public String getType() {
		return OANodeType.START_NODE;
	}

}
