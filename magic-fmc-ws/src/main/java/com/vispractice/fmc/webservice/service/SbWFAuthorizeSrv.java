package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_authorizesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_authorizesrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFAuthorizeSrv
 * 描  述：流程授权接口服务
 * 完成日期：2017年5月22日 上午9:51:39
 * 编码作者：ZhouYanyao
 */
public interface SbWFAuthorizeSrv {
	/**
	 * 启动流程
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFAuthorize(ProcessRequest processRequest);
}
