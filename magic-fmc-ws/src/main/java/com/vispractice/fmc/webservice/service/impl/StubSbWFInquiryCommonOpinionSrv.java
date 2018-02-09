package com.vispractice.fmc.webservice.service.impl;

import org.springframework.stereotype.Service;

import com.vispractice.fmc.csb.sb_wf_inquirycommonopinionsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirycommonopinionsrv.ProcessResponse;
import com.vispractice.fmc.webservice.service.SbWFInquiryCommonOpinionSrv;

/**
 * 编  号：
 * 名  称：StubSbWFInquiryCommonOpinionSrv
 * 描  述：StubSbWFInquiryCommonOpinionSrv.java
 * 完成日期：2017年6月23日 上午11:20:52
 * 编码作者：ZhouYanyao
 */
@Service
public class StubSbWFInquiryCommonOpinionSrv implements SbWFInquiryCommonOpinionSrv{
	@Override
	public ProcessResponse inquiryCommonOpinion(ProcessRequest processRequest) {
		return null;
	}
}
