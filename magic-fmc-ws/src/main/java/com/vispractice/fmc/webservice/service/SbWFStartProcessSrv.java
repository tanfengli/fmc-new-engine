package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessResponse;;

/**
 * 编  号：
 * 名  称：SbWFStartProcessSrv
 * 描  述：启动流程接口服务
 * 完成日期：2017年5月22日 上午9:51:39
 * 编码作者：ZhouYanyao
 */
public interface SbWFStartProcessSrv {
	/**
	 * 启动流程
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse startProcess(ProcessRequest ProcessRequest);
}
