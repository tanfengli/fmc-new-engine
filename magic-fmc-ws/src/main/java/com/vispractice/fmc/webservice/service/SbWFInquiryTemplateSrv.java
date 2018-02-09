package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_inquirytemplatesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytemplatesrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFInquiryTemplateSrv
 * 描  述：流程模板查询服务接口
 * 完成日期：2017年6月23日 下午2:45:59
 * 编码作者：ZhouYanyao
 */
public interface SbWFInquiryTemplateSrv {
	/**
	 * 查询流程模板
	 * @Title: inquiryTemplate 
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse inquiryTemplate(ProcessRequest processRequest);
}