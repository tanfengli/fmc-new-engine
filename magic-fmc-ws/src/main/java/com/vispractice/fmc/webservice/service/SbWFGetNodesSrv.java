package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_getnodessrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_getnodessrv.ProcessResponse;

/**
 * 流程节点信息查询服务
 * @author ZhouYanyao
 */
public interface SbWFGetNodesSrv {
	/**
	 * 流程节点信息查询服务
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFGetNodesSrv(ProcessRequest processRequest);
}
