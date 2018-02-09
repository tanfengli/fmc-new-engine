package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.ProcessDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.beans.detail.AbstractProcessDetail;

public class OAProcessDetail extends AbstractProcessDetail {
	protected boolean rejectReturn;

	protected String notifyType;

	protected boolean notifyOnFinish;

	// 增加流程结束后是否通知起草人选项 add by limh 2010年10月19日
	protected boolean notifyDraftOnFinish = true;

	protected String privilegerIds;

	protected String privilegerNames;

	protected int dayOfNotifyPrivileger;

	public int getDayOfNotifyPrivileger() {
		return dayOfNotifyPrivileger;
	}

	public void setDayOfNotifyPrivileger(int dayOfNotifyPrivileger) {
		this.dayOfNotifyPrivileger = dayOfNotifyPrivileger;
	}

	public boolean isNotifyOnFinish() {
		return notifyOnFinish;
	}

	public void setNotifyOnFinish(boolean notifyOnFinish) {
		this.notifyOnFinish = notifyOnFinish;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public boolean isRejectReturn() {
		return rejectReturn;
	}

	public void setRejectReturn(boolean rejectReturn) {
		this.rejectReturn = rejectReturn;
	}

	public String getPrivilegerIds() {
		return privilegerIds;
	}

	public void setPrivilegerIds(String privilegerIds) {
		this.privilegerIds = privilegerIds;
	}

	public String getPrivilegerNames() {
		return privilegerNames;
	}

	public void setPrivilegerNames(String privilegerNames) {
		this.privilegerNames = privilegerNames;
	}

	/**
	 * 重新计算处理人选项
	 * 
	 * @since 9.0 2011-07
	 */
	protected boolean recalculateHandler = true;

	/**
	 * @return recalculateHandler
	 */
	public boolean isRecalculateHandler() {
		return recalculateHandler;
	}

	/**
	 * @param recalculateHandler
	 *            要设置的 recalculateHandler
	 */
	public void setRecalculateHandler(boolean recalculateHandler) {
		this.recalculateHandler = recalculateHandler;
	}

	@Override
	public ProcessDescriptor parseProcessDescriptor() throws Exception {
		ProcessDescriptor wfDescriptor = super.parseProcessDescriptor();
		wfDescriptor.setParent("oa");
		for (int i = 0; i < getNodes().size(); i++) {
			if (getNodes().get(i) instanceof OAStartNodeDetail) {
				wfDescriptor.setInitialNodeDescriptor(getNodes().get(i)
						.parseNodeDescriptor());
			} else if (getNodes().get(i) instanceof OAEndNodeDetail) {
				wfDescriptor.setFinallyNodeDescriptor(getNodes().get(i)
						.parseNodeDescriptor());
			} else {
				wfDescriptor.addNodeDescriptor(getNodes().get(i)
						.parseNodeDescriptor());
			}
		}
		return wfDescriptor;
	}

	/**
	 * @return notifyDraftOnFinish
	 */
	public boolean isNotifyDraftOnFinish() {
		return notifyDraftOnFinish;
	}

	/**
	 * @param notifyDraftOnFinish
	 *            要设置的 notifyDraftOnFinish
	 */
	public void setNotifyDraftOnFinish(boolean notifyDraftOnFinish) {
		this.notifyDraftOnFinish = notifyDraftOnFinish;
	}
}
