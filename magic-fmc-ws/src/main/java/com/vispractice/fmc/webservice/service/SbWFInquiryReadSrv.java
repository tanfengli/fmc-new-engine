package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_inquiryreadsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryreadsrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFInquiryReadSrv
 * 描  述：流程待阅查询服务
 * 完成日期：2017年5月22日 上午9:51:39
 * 编码作者：ZhouYanyao
 */
public interface SbWFInquiryReadSrv {
	/**
	 * 流程待阅查询服务
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWfInquiryReadSrv(ProcessRequest processRequest);
}
