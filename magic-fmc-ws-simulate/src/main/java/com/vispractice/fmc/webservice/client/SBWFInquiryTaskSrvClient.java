package com.vispractice.fmc.webservice.client;

import javax.xml.namespace.QName;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvRequest;
import com.vispractice.fmc.csb.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvResponse;
import com.vispractice.fmc.webservice.processsrv.sb_wf_inquirytasksrv.SBWFInquiryTaskSrv;
import com.vispractice.fmc.webservice.processsrv.sb_wf_inquirytasksrv.SBWFInquiryTaskSrvService;

import java.net.URL;

public final class SBWFInquiryTaskSrvClient {
	private static final QName SERVICE_NAME = new QName("http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv","SBWFInquiryTaskSrvService");

	public static SBWFInquiryTaskSrvResponse call(SBWFInquiryTaskSrvRequest request) {
		URL wsdlURL = SBWFInquiryTaskSrvService.WSDL_LOCATION;

		SBWFInquiryTaskSrvService ss = new SBWFInquiryTaskSrvService(wsdlURL,SERVICE_NAME);
		SBWFInquiryTaskSrv port = ss.getSBWFInquiryTaskSrvSoap11();

		ProcessRequest processRequest = new ProcessRequest();
		processRequest.setRequest(request);
		ProcessResponse _process__return = port.process(processRequest);

		return _process__return.getReturn();
	}
}
