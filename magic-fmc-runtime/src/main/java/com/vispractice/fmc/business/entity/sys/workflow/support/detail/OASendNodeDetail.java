package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OAConstant;
import com.vispractice.fmc.business.base.constrant.OANodeType;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ResultDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractNodeDetail;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.ILineDetail;

public class OASendNodeDetail extends AbstractNodeDetail implements OAConstant {
	protected String optHandlerIds;

	protected String optHandlerNames;

	protected int optHandlerCalType;

	protected boolean useOptHandlerOnly;

	protected boolean ignoreOnHandlerEmpty;

	public boolean isIgnoreOnHandlerEmpty() {
		return ignoreOnHandlerEmpty;
	}

	public void setIgnoreOnHandlerEmpty(boolean ignoreOnHandlerEmpty) {
		this.ignoreOnHandlerEmpty = ignoreOnHandlerEmpty;
	}

	public int getOptHandlerCalType() {
		return optHandlerCalType;
	}

	public void setOptHandlerCalType(int optHandlerCalType) {
		this.optHandlerCalType = optHandlerCalType;
	}

	public String getOptHandlerIds() {
		return optHandlerIds;
	}

	public void setOptHandlerIds(String optHandlerIds) {
		this.optHandlerIds = optHandlerIds;
	}

	public String getOptHandlerNames() {
		return optHandlerNames;
	}

	public void setOptHandlerNames(String optHandlerNames) {
		this.optHandlerNames = optHandlerNames;
	}

	public boolean isUseOptHandlerOnly() {
		return useOptHandlerOnly;
	}

	public void setUseOptHandlerOnly(boolean useOptHandlerOnly) {
		this.useOptHandlerOnly = useOptHandlerOnly;
	}

	public NodeDescriptor parseNodeDescriptor() {
		NodeDescriptor nodeDescriptor = super.parseNodeDescriptor();
		nodeDescriptor.setParent(OAConstant.SEND_NODE_PARENT);
		if (loadOutLines().isEmpty()) {
			return nodeDescriptor;
		}
		ILineDetail line = loadOutLines().get(0);
		ResultDescriptor resultDescriptor = new ResultDescriptor();
		resultDescriptor.setTarget(line.getEndNodeId());
		nodeDescriptor.addResultDescriptor(resultDescriptor);
		return nodeDescriptor;
	}

	/**
	 * 默认处理人选择类型
	 */
	protected String handlerSelectType;

	public void setHandlerSelectType(String handlerSelectType) {
		this.handlerSelectType = handlerSelectType;
	}

	public String getHandlerSelectType() {
		return handlerSelectType;
	}

	/**
	 * 是否已经初始化，运算过当前节点审批人
	 */
	protected boolean hasInitSysOrgElment = false;

	/**
	 * @return 是否已经初始化，运算过当前节点审批人
	 */
	public boolean isHasInitSysOrgElment() {
		return hasInitSysOrgElment;
	}

	/**
	 * @param 是否已经初始化
	 *            ，运算过当前节点审批人
	 */
	public void setHasInitSysOrgElment(boolean hasInitSysOrgElment) {
		this.hasInitSysOrgElment = hasInitSysOrgElment;
	}

	@Override
	public String getType() {
		return OANodeType.SEND_NODE;
	}

}
