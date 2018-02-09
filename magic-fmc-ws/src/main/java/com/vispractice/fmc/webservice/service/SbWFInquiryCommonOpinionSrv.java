package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_inquirycommonopinionsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirycommonopinionsrv.ProcessResponse;

/**
 * 
 * 编  号：
 * 名  称：SbWFInquiryCommonOpinionSrv
 * 描  述：常用审批意见查询服务接口
 * 完成日期：2017年6月23日 上午11:18:16
 * 编码作者："LaiJiashen"
 */
public interface SbWFInquiryCommonOpinionSrv {

	/**
	 * 
	 * 查询常用审批意见
	 * @Title: inquiryCommonOpinion 
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse inquiryCommonOpinion(ProcessRequest processRequest);
	
}
