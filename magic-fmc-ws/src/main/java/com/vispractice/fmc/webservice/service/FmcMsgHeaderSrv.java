package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.csb.msgheader.MsgHeader;

/**
 * 编  号：
 * 名  称：FmcMesHeader
 * 描  述：共用字段MsgHeader校验服务
 * 完成日期：2017年5月16日 上午9:59:23
 * 编码作者：ZhouYanyao
 */
public interface FmcMsgHeaderSrv {
	/**
	 * 验证消息头部信息合理性 
	 * @param header
	 * @return
	 */
	public SysBusiSys validateSysData(MsgHeader header,String sysWfInterfaceCode) throws WorkflowException;
}
