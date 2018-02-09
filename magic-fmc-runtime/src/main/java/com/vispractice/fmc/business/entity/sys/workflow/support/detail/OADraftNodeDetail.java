package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.OANodeType;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ResultDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractNodeDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.ILineDetail;

public class OADraftNodeDetail extends AbstractNodeDetail {
	protected boolean canModifyFlow;

	protected String canModifyHandlerNodeIds;

	protected String mustModifyHandlerNodeIds;

	/**
	 * 流程驳回多少天后通知起草人
	 */
	protected int rejectNotifyDraft;

	public boolean isCanModifyFlow() {
		return canModifyFlow;
	}

	public void setCanModifyFlow(boolean canModifyFlow) {
		this.canModifyFlow = canModifyFlow;
	}

	public String getCanModifyHandlerNodeIds() {
		return canModifyHandlerNodeIds;
	}

	public void setCanModifyHandlerNodeIds(String canModifyHandlerNodeIds) {
		this.canModifyHandlerNodeIds = canModifyHandlerNodeIds;
	}

	public String getMustModifyHandlerNodeIds() {
		return mustModifyHandlerNodeIds;
	}

	public void setMustModifyHandlerNodeIds(String mustModifyHandlerNodeIds) {
		this.mustModifyHandlerNodeIds = mustModifyHandlerNodeIds;
	}

	public NodeDescriptor parseNodeDescriptor() {
		NodeDescriptor nodeDescriptor = super.parseNodeDescriptor();
		nodeDescriptor.setParent(OAConstant.DRAFT_NODE_PARENT);
		if (loadOutLines().isEmpty()) {
			return nodeDescriptor;
		}
		ILineDetail line = loadOutLines().get(0);
		ResultDescriptor resultDescriptor = new ResultDescriptor();
		resultDescriptor.setTarget(line.getEndNodeId());
		nodeDescriptor.addResultDescriptor(resultDescriptor);
		return nodeDescriptor;
	}

	public int getRejectNotifyDraft() {
		return rejectNotifyDraft;
	}

	public void setRejectNotifyDraft(int rejectNotifyDraft) {
		this.rejectNotifyDraft = rejectNotifyDraft;
	}

	@Override
	public String getType() {
		return OANodeType.DRAFT_NODE;
	}
}
