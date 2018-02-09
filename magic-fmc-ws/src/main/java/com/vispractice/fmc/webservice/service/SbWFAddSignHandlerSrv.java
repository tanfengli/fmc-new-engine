package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_addsignhandlersrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_addsignhandlersrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFAddSignHandlerSrv
 * 描  述：加签接口服务
 * 完成日期：2017年5月22日 上午9:51:39
 * 编码作者：ZhouYanyao
 */
public interface SbWFAddSignHandlerSrv {
	/**
	 * 加签接口服务
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFAddSignHandler(ProcessRequest processRequest);
}
