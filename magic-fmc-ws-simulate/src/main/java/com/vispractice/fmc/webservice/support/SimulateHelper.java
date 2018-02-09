package com.vispractice.fmc.webservice.support;

import java.math.BigDecimal;

import com.vispractice.fmc.csb.msgheader.MsgHeader;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvRequest;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.SBWFNodeApprovalSrvRequest;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.SBWFStartProcessSrvRequest;

public class SimulateHelper {
	public static int QUIRY_PROCESS_TASK = 10;
	public static int START_PROCESS_TASK = 20;
	public static int NODE_APPROVAL_TASK0 = 30;
	public static int NODE_APPROVAL_TASK1 = 31;
	
	public static String getURI(){
		return "http://10.204.115.52:8080";
	}
	/**
	 * 构造报文头部信息
	 * @return
	 */
	public static MsgHeader getMsgHeader() {
		return getMsgHeader("yaoxue", "姚雪");
	}
	
	public static MsgHeader getMsgHeader(String userId, String username) {
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setSOURCESYSTEMID("FSSC-Eas");
		msgHeader.setSOURCESYSTEMNAME("模拟测试");
		msgHeader.setUSERID(userId);
		msgHeader.setUSERNAME(username);
		msgHeader.setCURRENTPAGE(new BigDecimal(1));
		msgHeader.setPAGESIZE(new BigDecimal(10));
		
		return msgHeader;
	}
	
	public static SBWFInquiryTaskSrvRequest getInquiryTaskSrvRequest(){
		return getInquiryTaskSrvRequest("xuegx", "薛桂喜");
	}
	
	public static SBWFInquiryTaskSrvRequest getInquiryTaskSrvRequest(String userId, String usernae){
		SBWFInquiryTaskSrvRequest request = new SBWFInquiryTaskSrvRequest();
		request.setMsgHeader(SimulateHelper.getMsgHeader("xuegx", usernae));
		request.setWFTASKUSERNO(userId);
		return request;
	}
	
	public static SBWFStartProcessSrvRequest getStartProcessSrvRequsst(int number){

		SBWFStartProcessSrvRequest request = new SBWFStartProcessSrvRequest();
		request.setMsgHeader(SimulateHelper.getMsgHeader());
		
		String applyCode = "sim2017092901-" + String.valueOf(number);
		request.setWFAPPLYCODE(applyCode);
		request.setWFAPPLYUSERNO(request.getMsgHeader().getUSERID());
		request.setWFAPPLYSUBJECT("流程模拟测试");
		request.setWFTEMPLATEID("8a4cf3b85e3c9f2d015e3cf907310013");
		request.setLINKURL("http://localhost:8080/fa-web/task/view?code={0}");

		return request;
	}
	
	public static SBWFNodeApprovalSrvRequest  getSBWFNodeApprovalSrvRequest(String userno, String username, String pId, String nId, String result){
		SBWFNodeApprovalSrvRequest request = new SBWFNodeApprovalSrvRequest();
		
		request.setMsgHeader(SimulateHelper.getMsgHeader(userno, username));
		request.setWFAUDITERUSERNO(request.getMsgHeader().getUSERID());
		request.setWFINSTANCEID(pId);
//		request.setWFTOKENID(tId);
		request.setWFNODEID(nId);
		request.setWFRESULT(result);
		request.setWFOPINIONCON(result);
		return request;
	}
}
