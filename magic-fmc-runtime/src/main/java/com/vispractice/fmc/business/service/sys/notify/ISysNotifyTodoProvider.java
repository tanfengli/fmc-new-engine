package com.vispractice.fmc.business.service.sys.notify;

import com.vispractice.fmc.business.base.domain.SbWFApprovalForm;
import com.vispractice.fmc.business.entity.sys.workflow.beans.context.ProcessContext;

/**
 * 名  称：待办,已办通知处理接口
 * 描  述：SysNotifyTodoProviderService.java
 * 完成日期：2017年7月14日 下午6:36:06
 * 编码作者：ZhouYanyao
 */
public interface ISysNotifyTodoProvider {
	/**
	 * 清除相关代办信息
	 * @param sbWFApprovalForm
	 * @param processContext
	 */
	public void clearSysNotifyTodo(SbWFApprovalForm sbWFApprovalForm,ProcessContext processContext);
}
