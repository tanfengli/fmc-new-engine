package com.vispractice.fmc.webservice.client;

import java.net.URL;

import javax.xml.namespace.QName;

import com.vispractice.fmc.csb.sb_wf_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_approvalsrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_approvalsrv.SBWFApprovalSrvRequest;
import com.vispractice.fmc.csb.sb_wf_approvalsrv.SBWFApprovalSrvResponse;
import com.vispractice.fmc.webservice.processsrv.sb_wf_approvalsrv.SBWFApprovalSrv;
import com.vispractice.fmc.webservice.processsrv.sb_wf_approvalsrv.SBWFApprovalSrvService;
import com.vispractice.fmc.webservice.processsrv.sb_wf_startprocesssrv.SBWFStartProcessSrvService;

public class SBWFApprovalSrvClient {
	private static final QName SERVICE_NAME = new QName("http://csb.fmc.vispractice.com/SB_WF_ApprovalSrv","SBWFApprovalSrvService");

    public static SBWFApprovalSrvResponse call(SBWFApprovalSrvRequest reuqest) {
    	URL wsdlURL = SBWFStartProcessSrvService.WSDL_LOCATION;

		SBWFApprovalSrvService ss = new SBWFApprovalSrvService(wsdlURL,SERVICE_NAME);
		SBWFApprovalSrv port = ss.getSBWFApprovalSrvSoap11();

		ProcessRequest processRequest = new ProcessRequest();
		processRequest.setRequest(reuqest);
		ProcessResponse _process__return = port.process(processRequest);
		
		return _process__return.getReturn();
    }
}
