package com.vispractice.fmc.webservice.simulate;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvResponse;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.SBWFStartProcessSrvRequest;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.SBWFStartProcessSrvResponse;
import com.vispractice.fmc.webservice.client.SBWFInquiryTaskSrvClient;
import com.vispractice.fmc.webservice.client.SBWFStartProcessSrvClient;
import com.vispractice.fmc.webservice.support.SimulateHelper;

@Service
public class SimulateSrvImpl implements SimulateSrv {

	Logger log = LoggerFactory.getLogger(SimulateSrvImpl.class);
	/**
	 * 批量查看待办单据
	 */
	public SBWFInquiryTaskSrvResponse inquiryTaskSrv() {
		log.info("模拟查询用户待办------------start.");
		
		SBWFInquiryTaskSrvRequest request= SimulateHelper.getInquiryTaskSrvRequest();
		SBWFInquiryTaskSrvResponse response = SBWFInquiryTaskSrvClient.call(request);
		
		 if(response.getSERVICEFLAG().equals("N")){
			 log.info(response.getSERVICEMESSAGE());
		 }else{
			 log.info("查询用户待办数量：" + response.getTOTALRECORD());
		 }
		 log.info("模拟查询用户待办------------end.");
		 return response;
	}

	/**
	 * 启动流程实例
	 * 
	 * @return
	 */
	 @Override
	 public void startProcessSrv(int number) {
		 log.info("启动流程实例------------start.");
		
		 SBWFStartProcessSrvRequest request = SimulateHelper.getStartProcessSrvRequsst(number);
		 SBWFStartProcessSrvResponse response = SBWFStartProcessSrvClient.call(request);
		 
		 if(response.getSERVICEFLAG().equals("N")){
			 log.info(response.getSERVICEMESSAGE());
		 }else{
			 log.info("生成实例流程：" + response.getWFINSTANCEID());
		 }
		 
		 log.info("启动流程实例-----------end.");
	 }

	 /**
	  * 节点审批
	  */
	@Override
	public void nodeApprovalSrv(String userno, String username) {
		Boolean isContinue = true;
		
		while(isContinue){
			SBWFInquiryTaskSrvRequest taskRequest= SimulateHelper.getInquiryTaskSrvRequest(userno, username);
			SBWFInquiryTaskSrvResponse taskResponse = SBWFInquiryTaskSrvClient.call(taskRequest);
			
			log.info("剩余任务数量：" + taskResponse.getTOTALRECORD());
			
			if( taskResponse.getTOTALRECORD().compareTo(new BigDecimal(10)) <= 0 ){
				isContinue = false;
			}
			
			if(taskResponse.getTOTALRECORD().compareTo(new BigDecimal(0)) > 0){
//				List<WfProcessTaskItem> taskItemList = taskResponse.getWFPROCESSTASKCOLLECTION().getWFPROCESSTASKITEM();
//				
//				for(int i=0; i < taskItemList.size(); i++) {
//					WfProcessTaskItem item = taskItemList.get(i);
//					SBWFNodeApprovalSrvRequest request = SimulateHelper.getSBWFNodeApprovalSrvRequest(userno, username, item.getWFINSTANCEID(), item.getWFTOKENID(), "101");
//					log.info("审批开始，流程实例号为" + item.getWFINSTANCEID());
//					SBWFNodeApprovalSrvClient.call(request);
//					log.info("审批 结束，流程实例号为" + item.getWFINSTANCEID());
//				}
			}
			
		}
		
	}
	 
	/**
	 * 批量查看我的申请
	 */
	// @Override
	// public void inquiryApplySrv() {
	// SBWFInquiryApplySrvRequest request = null;
	// List<Map<String,String>> records = null;
	// SBWFInquiryApplySrvResponse response = null;
	// List<WfProcessApplyItem> processApplyItems = null;
	//
	// Map<String,String> record = null;
	// MsgHeader msgHeader = null;
	// long startTime = 0L;
	// long endTime = 0L;
	// int count = 0;
	// int size = 0;
	//
	// log.info("模拟查询我的申请------------start.");
	// count = Integer.parseInt(params.get("count"));
	// startTime = System.currentTimeMillis();
	// do {
	// records = new ArrayList<Map<String,String>>();
	// msgHeader = SimulateHelper.buildHeaderData(params);
	// request = new SBWFInquiryApplySrvRequest();
	// request.setMsgHeader(msgHeader);
	// request.setWFAPPLYUSERNO(msgHeader.getUSERID());
	// response = SBWFInquiryApplySrvClient.call(request);
	// if ("Y".equals(response.getSERVICEFLAG())) {
	// log.info(params.get("userName") + ":查询我的申请成功.");
	// processApplyItems =
	// response.getWFPROCESSAPPLYCOLLECTION().getWFPROCESSAPPLYITEM();
	// for (WfProcessApplyItem processApplyItem : processApplyItems) {
	// record = new HashMap<String,String>();
	// record.put("wfInstanceId",processApplyItem.getWFINSTANCEID());
	// record.put("wfResult","203");
	// record.put("wfOpinionCon","撤回");
	// record.put("wfRouteId","");
	// record.put("wfTransferUserNo","");
	// record.put("wfPassedToThis","");
	// records.add(record);
	// }
	//
	// size += this.approvalSrv(params,records);
	// } else {
	// log.info(params.get("userName") + ":查询我的申请失败.");
	// }
	//
	// endTime = System.currentTimeMillis();
	// log.info("查询我的申请时间间隔:" + (endTime - startTime) + ".");
	// log.info(params.get("userName") + "期待处理我的申请数量:" + count +
	// "条;实际处理我的申请数量:" + size + ".");
	// } while((size < count) && (endTime - startTime) < 30000);
	//
	// log.info("模拟查询我的申请------------end.");
	// }

	/**
	 * 批量审批
	 */
	// public long approvalSrv() {
	// SBWFApprovalSrvRequest request = new SBWFApprovalSrvRequest();
	// SBWFApprovalSrvResponse approvalSrvResponse = null;
	// boolean isSuccess = true;
	// long result = 0L;
	// long startTime = 0L;
	// long endTime = 0L;
	//
	// MsgHeader msgHeader = SimulateHelper.buildHeaderData(params);
	// request.setMsgHeader(msgHeader);
	// for (Map<String,String> record : records) {
	// request.setWFINSTANCEID(record.get("wfInstanceId"));
	// request.setWFAUDITERUSERNO(msgHeader.getUSERID());
	// request.setWFRESULT(record.get("wfResult"));
	// request.setWFOPINIONCON(record.get("wfOpinionCon"));
	// request.setWFROUTEID(record.get("wfRouteId"));
	// request.setWFTRANSFERUSERNO(record.get("wfTransferUserNo"));
	// request.setWFPASSEDTOTHIS(record.get("wfPassedToThis"));
	//
	// approvalSrvResponse = SBWFApprovalSrvClient.call(request);
	// isSuccess = "Y".equals(approvalSrvResponse.getSERVICEFLAG());
	//
	// if (isSuccess) {
	// log.info(msgHeader.getUSERNAME() + ":审批单据成功;单据实例标识:" +
	// record.get("wfInstanceId"));
	// result++;
	// } else {
	// log.info(msgHeader.getUSERNAME() + ":审批单据失败;单据实例标识:" +
	// record.get("wfInstanceId"));
	// }
	//
	// startTime = System.currentTimeMillis();
	// do {
	// endTime = System.currentTimeMillis();
	// } while (!isSuccess && (30000 < (endTime - startTime)));
	// }
	//
	// return result;
	// return 1L;
	// }

}
