package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_abandonsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_abandonsrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFStartProcessSrv
 * 描  述：启动流程接口服务
 * 完成日期：2017年5月22日 上午9:51:39
 * 编码作者：ZhouYanyao
 */
public interface SbWFAbandonSrv {
	/**
	 * 启动流程
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFAbandon(ProcessRequest processRequest);
}
