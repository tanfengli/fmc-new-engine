package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_inquiryprocesshistorysrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesshistorysrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFInquiryProcessHistroySrv
 * 描  述：单据审批历史记录查询接口
 * 完成日期：2017年5月22日 上午9:51:39
 * 编码作者：ZhouYanyao
 */
public interface SbWFInquiryProcessHistorySrv {
	/**
	 * 获取审批历史记录信息
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse inquiryProcessHistory(ProcessRequest processRequest);
}
