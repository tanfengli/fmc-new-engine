package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.OANodeType;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractNodeDetail;

public class OAEndNodeDetail extends AbstractNodeDetail {
	
	public NodeDescriptor parseNodeDescriptor() {
		NodeDescriptor nodeDescriptor = super.parseNodeDescriptor();
		nodeDescriptor.setParent(OAConstant.END_NODE_PARENT);
		return nodeDescriptor;
	}

	@Override
	public String getType() {
		return OANodeType.END_NODE;
	}

}
