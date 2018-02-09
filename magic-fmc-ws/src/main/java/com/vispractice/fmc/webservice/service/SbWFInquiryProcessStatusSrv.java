package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFInquiryProcessStatusSrv
 * 描  述：查询流程状态服务接口
 * 完成日期：2017年5月15日 下午6:21:39
 * 编码作者：ZhouYanyao
 */
public interface SbWFInquiryProcessStatusSrv {
	/**
	 * 查询流程状态接口
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse inquiryProcessStatus(ProcessRequest processRequest);
}
