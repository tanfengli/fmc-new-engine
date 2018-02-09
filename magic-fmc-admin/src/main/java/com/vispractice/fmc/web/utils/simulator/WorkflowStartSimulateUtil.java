//package com.vispractice.fmc.web.utils.simulator;
//
//import java.net.URL;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.xml.datatype.DatatypeConfigurationException;
//import javax.xml.datatype.DatatypeFactory;
//import javax.xml.datatype.XMLGregorianCalendar;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.vispractice.fmc.business.service.sys.facade.IProcessFacade;
//import com.vispractice.fmc.csb.extendparamcollection.WfProcessVarCollection;
//import com.vispractice.fmc.csb.msgheader.MsgHeader;
//import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessRequest;
//import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessResponse;
//import com.vispractice.fmc.csb.sb_wf_startprocesssrv.SBWFStartProcessSrv;
//import com.vispractice.fmc.csb.sb_wf_startprocesssrv.SBWFStartProcessSrvRequest;
//import com.vispractice.fmc.csb.sb_wf_startprocesssrv.SBWFStartProcessSrvService;
//@Slf4j
//public class WorkflowStartSimulateUtil {
//
//	private static final String url ="/ws/sbWFStartProcessSrv/sbWFStartProcessSrv.wsdl";
//	/**
//	 * 流程服务接口
//	 */
//	@Autowired
//	private IProcessFacade processFacade;
//	
//	
//	private static WorkflowStartSimulateUtil instance;
//	public static WorkflowStartSimulateUtil getInstance() {
//		if (instance == null) {
//			synchronized (WorkflowStartSimulateUtil.class) {
//				if (instance == null) {
//					instance = new WorkflowStartSimulateUtil();
//				}
//			}
//		}
//		return instance;
//	}
//	public Map<String, String>  start(String http){
//    	try {
//    		Map<String, String> map = new HashMap<String, String>();
//			SBWFStartProcessSrvService ss = new SBWFStartProcessSrvService(new URL(http+url));
//			SBWFStartProcessSrv port = ss.getSBWFStartProcessSrvSoap11();
//			log.info("Invoking process...");
//			ProcessRequest process_request = new ProcessRequest();
//			SBWFStartProcessSrvRequest request = initData(null);
//			process_request.setRequest(request);
//			ProcessResponse _process__return = port.process(process_request);
//			log.info("process.result="
//					+ _process__return.getReturn().getWFINSTANCEID()+"---"+request.getWFAPPLYCODE());
//			map.put("applyCode", request.getWFAPPLYCODE());
//			map.put("wfInstanceId", _process__return.getReturn().getWFINSTANCEID());
//			return map;
//    	} catch (Exception e) {
//			e.printStackTrace();
//		}
//		 return null;
//    }
//	
//	private  SBWFStartProcessSrvRequest initData(String applyCode) {
//		SBWFStartProcessSrvRequest request = new SBWFStartProcessSrvRequest();
//		 /**
//		  * 
//		  */
//		MsgHeader header = new MsgHeader();
//		header.setSOURCESYSTEMID("FSSC-Eas");
//		header.setSOURCESYSTEMNAME("系统后台管理");
//		header.setUSERID("yaoxue");
//		header.setUSERNAME("姚雪");
//		
//		request.setMsgHeader(header);
//		if(applyCode==null){
//			request.setWFAPPLYCODE(initApplyCode());
//		}else{
//			request.setWFAPPLYCODE(applyCode);
//		}
//		
//		
//		request.setLINKURL("http://10.204.115.56:8080/fa-web/task/view?code={0}");
//		request.setURGENTLEVEL("1");
////		request.setWFAPPLYCONTENT("");
////		request.setWFAPPLYDATE(dateToXmlDate());
////		request.setWFAPPLYREASON("");
//		request.setWFAPPLYSUBJECT("test0001");
//		request.setWFAPPLYUSERNO("yaoxue");
////		request.setWFDESCRIPTION("");
//		request.setWFPROCESSVARCOLLECTION(new WfProcessVarCollection());
//		request.setWFTEMPLATEID("8a4cf3b85e3c9f2d015e3cf907310013");
//		return request;
//	}
//  
//	
//	public XMLGregorianCalendar dateToXmlDate(){  
//        Calendar cal = Calendar.getInstance();  
//        DatatypeFactory dtf = null;  
//         try {  
//            dtf = DatatypeFactory.newInstance();  
//        } catch (DatatypeConfigurationException e) {  
//        }  
//        XMLGregorianCalendar dateType = dtf.newXMLGregorianCalendar();  
//        dateType.setYear(cal.get(Calendar.YEAR));  
//        //由于Calendar.MONTH取值范围为0~11,需要加1  
//        dateType.setMonth(cal.get(Calendar.MONTH)+1);  
//        dateType.setDay(cal.get(Calendar.DAY_OF_MONTH));  
//        dateType.setHour(cal.get(Calendar.HOUR_OF_DAY));  
//        dateType.setMinute(cal.get(Calendar.MINUTE));  
//        dateType.setSecond(cal.get(Calendar.SECOND));  
//        return dateType;  
//    }    
//    
//	
//	public  String  initApplyCode(){
//		String pre ="TEST";
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmss");
//		Date date  =  Calendar.getInstance().getTime();
//		return pre+sdf.format(date);
//	}
//}
