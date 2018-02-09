package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_inquiryprocesslogsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryprocesslogsrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFInquiryProcessLogSrv
 * 描  述：查询审批日志服务接口
 * 完成日期：2017年5月22日 上午9:51:39
 * 编码作者：ZhouYanyao"
 */
public interface SbWFInquiryProcessLogSrv {
	/**
	 * 获取审批日志记录信息
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse inquiryProcessLog(ProcessRequest processRequest);
}
