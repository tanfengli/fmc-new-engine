package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFInquiryApplySrv
 * 描  述：查询我的申请服务接口
 * 完成日期：2017年5月19日 上午9:51:39
 * 编码作者：ZhouYanyao
 */
public interface SbWFInquiryApplySrv {
	/**
	 * 查看申请单据信息
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse inquiryApply(ProcessRequest processRequest);
}
