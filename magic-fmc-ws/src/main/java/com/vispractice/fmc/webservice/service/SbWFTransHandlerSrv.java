package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_transhandlersrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_transhandlersrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFTransHandlerSrv
 * 描  述：转办/转交接口服务
 * 完成日期：2017年5月22日 上午9:51:39
 * 编码作者：ZhouYanyao
 */
public interface SbWFTransHandlerSrv {
	/**
	 * 转办/转交接口服务
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFTransHandler(ProcessRequest processRequest);
}
