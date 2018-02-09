/**
 * 
 */
package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OANodeType;
import com.vispractice.fmc.business.entity.sys.workflow.support.subprocess.configuration.StartNodeConfiguration;
import com.vispractice.fmc.business.entity.sys.workflow.support.subprocess.configuration.StartNodeConfiguration.NotifyType;

/**
 * 启动子流程定义类
 * 
 * @author 傅游翔
 * 
 */
public class OAStartSubProcessNodeDetail extends OAAbstractSubProcessNodeDetail {

	@Override
	protected String getNodeParentString() {
		return START_SUBPROCESS_NODE_PARENT;
	}

	public boolean isNotifyPrivilegerOnError() {
		StartNodeConfiguration config = new StartNodeConfiguration(
				getConfigContent());
		return config.getOnErrorNotify().contains(NotifyType.PRIVILEGER);
	}

	public boolean isNotifyDraftsmanOnError() {
		StartNodeConfiguration config = new StartNodeConfiguration(
				getConfigContent());
		return config.getOnErrorNotify().contains(NotifyType.DRAFTSMAN);
	}

	private boolean synchronizeRight = false;

	public boolean isSynchronizeRight() {
		return synchronizeRight;
	}

	public void setSynchronizeRight(boolean synchronizeRight) {
		this.synchronizeRight = synchronizeRight;
	}

	@Override
	public String getType() {
		return OANodeType.START_SUB_PROCESS_NODE;
	}
}
