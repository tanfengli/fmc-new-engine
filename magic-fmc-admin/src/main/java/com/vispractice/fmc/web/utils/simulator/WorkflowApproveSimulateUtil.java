//package com.vispractice.fmc.web.utils.simulator;
//
//import java.net.URL;
//import java.util.Map;
//import java.util.Random;
//
//import lombok.extern.slf4j.Slf4j;
//
//import com.vispractice.fmc.business.entity.aboutmyself.VCmsTask;
//import com.vispractice.fmc.csb.extendparamcollection.WfProcessTaskItem;
//import com.vispractice.fmc.csb.msgheader.MsgHeader;
//import com.vispractice.fmc.csb.sb_wf_approvalsrv.ProcessRequest;
//import com.vispractice.fmc.csb.sb_wf_approvalsrv.ProcessResponse;
//import com.vispractice.fmc.csb.sb_wf_approvalsrv.SBWFApprovalSrv;
//import com.vispractice.fmc.csb.sb_wf_approvalsrv.SBWFApprovalSrvRequest;
//import com.vispractice.fmc.csb.sb_wf_approvalsrv.SBWFApprovalSrvService;
//
//
//@Slf4j
//public class WorkflowApproveSimulateUtil {
//	
//	private static final String  url = "/ws/sbWFApprovalSrv/sbWFApprovalSrv.wsdl";
//	private static WorkflowApproveSimulateUtil instance;
//	public static WorkflowApproveSimulateUtil getInstance() {
//		if (instance == null) {
//			synchronized (WorkflowApproveSimulateUtil.class) {
//				if (instance == null) {
//					instance = new WorkflowApproveSimulateUtil();
//				}
//			}
//		}
//		return instance;
//	}
//	 
//	
//	public void start(String http,VCmsTask task){
//		try {
//			SBWFApprovalSrvRequest request = initData(task);
//			ProcessRequest process_request = new ProcessRequest();
//			process_request.setRequest(request);
//			SBWFApprovalSrvService ss = new SBWFApprovalSrvService(new URL(http+ url));
//			SBWFApprovalSrv port = ss.getSBWFApprovalSrvSoap11();
//			ProcessResponse _process__return = port.process(process_request);
//			log.info("process.result=" + _process__return.getReturn().getSERVICEMESSAGE());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public void start(String http,Map<String,String> task){
//		try {
//			SBWFApprovalSrvRequest request = initData(task);
//			ProcessRequest process_request = new ProcessRequest();
//			process_request.setRequest(request);
//			SBWFApprovalSrvService ss = new SBWFApprovalSrvService(new URL(http+ url));
//			SBWFApprovalSrv port = ss.getSBWFApprovalSrvSoap11();
//			ProcessResponse _process__return = port.process(process_request);
//			log.info("process.result=" + _process__return.getReturn().getSERVICEMESSAGE());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	public  static void testApprove(String http) {
//
//		try {
//			SBWFApprovalSrvService ss = new SBWFApprovalSrvService(new URL(http+url));
//			SBWFApprovalSrv port = ss.getSBWFApprovalSrvSoap11();
//			log.info("Invoking approve process...");
//			ProcessRequest process_request = new ProcessRequest();
////			List<Map<String,String>> rst = UtilTest.queryWf("");
////			for(Map<String,String> param:rst){
////				String status = param.get("status");
////			}
////			SBWFApprovalSrvRequest request = initData(param);
////			process_request.setRequest(request);
////			ProcessResponse _process__return = port.process(process_request);
////			log.info("process.result=" + _process__return.getReturn().getSERVICEMESSAGE());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private static SBWFApprovalSrvRequest initData(VCmsTask task) {
//		SBWFApprovalSrvRequest request = new SBWFApprovalSrvRequest();
//		MsgHeader header = new MsgHeader();
//		header.setSOURCESYSTEMID("FSSC-Eas");
//		header.setSOURCESYSTEMNAME("电子报账系统");
//		header.setUSERID(task.getTaskUserNo());
//		header.setUSERNAME(task.getTaskUserName());
//		request.setMsgHeader(header);
//		
//		request.setWFAUDITERUSERNO(task.getTaskUserNo());
//		request.setWFINSTANCEID(task.getWfInstanceId());
//		request.setWFOPINIONCON("测试审批");
//		request.setWFPASSEDTOTHIS("");
////		request.setWFPROCESSVARCOLLECTION(value);
//		request.setWFRESULT("101");
//		request.setWFROUTEID("");
//		request.setWFTRANSFERUSERNO("");
//		/**
//		  * 
//		  */
//		// request.setMsgHeader(null);
//		// request.setWFAPPLYCODE(value);
//		// request.setLINKURL(value);
//		// request.setURGENTLEVEL(value);
//		// request.setWFAPPLYCONTENT(value);
//		// request.setWFAPPLYDATE(value);
//		// request.setWFAPPLYREASON(value);
//		// request.setWFAPPLYSUBJECT(value);
//		// request.setWFAPPLYUSERNO(value);
//		// request.setWFDESCRIPTION(value);
//		// request.setWFPROCESSVARCOLLECTION(value);
//		// request.setWFTEMPLATEID(value);
//		return request;
//	}
//	
//	private static SBWFApprovalSrvRequest initData(Map<String,String> param) {
//		SBWFApprovalSrvRequest request = new SBWFApprovalSrvRequest();
//		MsgHeader header = new MsgHeader();
//		header.setSOURCESYSTEMID("FSSC-Eas");
//		header.setSOURCESYSTEMNAME("电子报账系统");
//		header.setUSERID(param.get("userNo"));
//		header.setUSERNAME(param.get("userName"));
//		request.setMsgHeader(header);
//		
//		request.setWFAUDITERUSERNO(param.get("userNo"));
//		request.setWFINSTANCEID(param.get("wfInstanceId"));
//		request.setWFOPINIONCON("测试审批");
//		request.setWFPASSEDTOTHIS("");
////		request.setWFPROCESSVARCOLLECTION(value);
//		request.setWFRESULT("101");
//		request.setWFROUTEID("");
//		request.setWFTRANSFERUSERNO("");
//		return request;
//	}
////	@Test
//	public static String initResult(){
////		while(true){
//			String[] rst = {"101","102"};
//			Random ran = new Random();
//			int index = ran.nextInt(2);
//			System.out.println(rst[index]);
////		}
//		
//		return rst[index];
//	}
//	
//	
//}
