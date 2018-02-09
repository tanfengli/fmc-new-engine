package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_inquiryrejectnodesrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryrejectnodesrv.ProcessResponse;

/**
 * 编  号：
 * 名  称：SbWFInquiryRejectNodeSrv
 * 描  述：查询驳回节点服务接口
 * 完成日期：2017年5月22日 上午9:51:39
 * 编码作者：ZhouYanyao
 */
public interface SbWFInquiryRejectNodeSrv {
	/**
	 * 获取驳回节点记录信息
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFInquiryRejectNode(ProcessRequest processRequest);
}
