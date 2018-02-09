package com.vispractice.fmc.webservice.service;

import com.vispractice.fmc.csb.sb_wf_nextnodessrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_nextnodessrv.ProcessResponse;

/**
 * 查询下一审批节点服务
 * @author ZhouYanyao
 */
public interface SbWNextNodesSrv {
	/**
	 * 流程节点信息查询服务
	 * @param processRequest
	 * @return
	 */
	public ProcessResponse sbWFNextNodesSrv(ProcessRequest processRequest);
}
