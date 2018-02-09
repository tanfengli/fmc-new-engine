//package com.vispractice.fmc.web.utils.simulator;
//
//import java.net.URL;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import lombok.extern.slf4j.Slf4j;
//
//import com.vispractice.fmc.csb.msgheader.MsgHeader;
//import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.ProcessRequest;
//import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.ProcessResponse;
//import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrv;
//import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvRequest;
//import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvService;
//import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.WfProcessTaskItem;
//
//
//@Slf4j
//public class WorkflowTaskSimulateUtil {
//	
//	private static final String  url = "/ws/sbWFInquiryTaskSrv/sbWFInquiryTaskSrv.wsdl";
//	private static WorkflowTaskSimulateUtil instance;
//	public static WorkflowTaskSimulateUtil getInstance() {
//		if (instance == null) {
//			synchronized (WorkflowTaskSimulateUtil.class) {
//				if (instance == null) {
//					instance = new WorkflowTaskSimulateUtil();
//				}
//			}
//		}
//		return instance;
//	}
//	 
//	
//	public List<WfProcessTaskItem> start(String http,Map<String,String> rst){
//		try {
////			Map<String,String> rst = new HashMap<String, String>();
//			SBWFInquiryTaskSrvRequest request = initData(rst);
//			ProcessRequest process_request = new ProcessRequest();
//			process_request.setRequest(request);
//			
//			SBWFInquiryTaskSrvService  ss = new SBWFInquiryTaskSrvService(new URL(http+ url));
//			SBWFInquiryTaskSrv port = ss.getSBWFInquiryTaskSrvSoap11();
//			ProcessResponse _process__return = port.process(process_request);
//			log.info("process.result=" + _process__return.getReturn().getSERVICEMESSAGE());
//			
//			return _process__return.getReturn().getWFPROCESSTASKCOLLECTION().getWFPROCESSTASKITEM();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	private  SBWFInquiryTaskSrvRequest initData(Map<String,String> param) {
//		SBWFInquiryTaskSrvRequest request = new SBWFInquiryTaskSrvRequest();
//		MsgHeader header = new MsgHeader();
//		header.setSOURCESYSTEMID("FSSC-Eas");
//		header.setSOURCESYSTEMNAME("电子报账系统");
//		header.setUSERID(param.get("userNo"));
//		header.setUSERNAME(param.get("userName"));
//		request.setMsgHeader(header);
//		
//		request.setWFTASKUSERNO(param.get("userNo"));
//		request.setWFAPPLYCODE(param.get("applyCode"));
//		request.setWFTEMPLATEID(param.get("templateId"));
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
//}
