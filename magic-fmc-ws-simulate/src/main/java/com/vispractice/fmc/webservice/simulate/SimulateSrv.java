package com.vispractice.fmc.webservice.simulate;

import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvResponse;

public interface SimulateSrv {
	
	/**
	 * 批量查看待办单据
	 * @param params
	 * @return
	 */
	public SBWFInquiryTaskSrvResponse inquiryTaskSrv();
	
	/**
	 * 批量提交单据
	 * @param params
	 * @param number
	 */
	public void startProcessSrv(int number);
	
	/**
	 * 节点审批
	 * @param params
	 * @param records
	 * @return
	 */
	public void nodeApprovalSrv(String userno, String username);

	
	/**
	 * 批量查看我的申请
	 * @param params
	 * @param users
	 */
//	public void inquiryApplySrv();
	

}
