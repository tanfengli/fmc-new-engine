package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.ProcessResponse;

/**
 * 
 * 编  号：
 * 名  称：SbWFInquiryTaskSrv
 * 描  述：查询代办服务
 * 完成日期：2017年5月18日 下午4:30:16
 * 编码作者："LaiJiashen"
 */
public interface SbWFInquiryTaskSrv {
	public ProcessResponse quiryTask(ProcessRequest processRequest );
}
