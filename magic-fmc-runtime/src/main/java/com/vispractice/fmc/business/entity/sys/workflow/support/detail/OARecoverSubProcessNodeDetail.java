/**
 * 
 */
package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OANodeType;

/**
 * 回收子流程
 * 
 * 
 */
public class OARecoverSubProcessNodeDetail extends
		OAAbstractSubProcessNodeDetail {

	@Override
	protected String getNodeParentString() {
		return RECOVER_SUBPROCESS_NODE_PARENT;
	}

	@Override
	public String getType() {
		return OANodeType.RECOVER_SUB_PROCESS_NODE;
	}

}
