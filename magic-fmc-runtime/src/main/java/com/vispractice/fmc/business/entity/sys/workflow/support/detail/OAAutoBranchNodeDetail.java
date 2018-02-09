package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.OANodeType;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ResultDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractNodeDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.ILineDetail;

public class OAAutoBranchNodeDetail extends AbstractNodeDetail {
	
	public NodeDescriptor parseNodeDescriptor() {
		NodeDescriptor nodeDescriptor = super.parseNodeDescriptor();
		nodeDescriptor.setParent(OAConstant.AUTO_BRANCH_NODE_PARENT);
		if(loadOutLines().isEmpty()){
			return nodeDescriptor;
		}
		for(int i=0;i<loadOutLines().size();i++){
			ILineDetail line =  loadOutLines().get(i);
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
		return OANodeType.AUTO_BRANCH_NODE;
	}

}
