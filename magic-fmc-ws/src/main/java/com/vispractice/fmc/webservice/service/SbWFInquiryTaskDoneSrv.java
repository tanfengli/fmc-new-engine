package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFInquiryTaskDoneSrv
 * 描  述：查询已办服务
 * 完成日期：2017年5月18日 下午4:03:03
 * 编码作者：ZhouYanyao
 */
public interface SbWFInquiryTaskDoneSrv {
	/**
	 * 单据已办接口查询服务
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse inquiryTaskDone(ProcessRequest processRequest);
}
