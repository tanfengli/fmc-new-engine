
package com.vispractice.fmc.webservice.client;

import java.net.URL;

import javax.xml.namespace.QName;

import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.SBWFNodeApprovalSrvRequest;
import com.vispractice.fmc.csb.sb_wf_node_approvalsrv.SBWFNodeApprovalSrvResponse;
import com.vispractice.fmc.webservice.processsrv.sb_wf_node_approvalsrv.SBWFNodeApprovalSrv;
import com.vispractice.fmc.webservice.processsrv.sb_wf_node_approvalsrv.SBWFNodeApprovalSrvService;

public final class SBWFNodeApprovalSrvClient {
	private static final QName SERVICE_NAME = new QName("http://csb.fmc.vispractice.com/SB_WF_Node_ApprovalSrv","SBWFNodeApprovalSrvService");

	public static SBWFNodeApprovalSrvResponse call(SBWFNodeApprovalSrvRequest request) {
		URL wsdlURL = SBWFNodeApprovalSrvService.WSDL_LOCATION;

		SBWFNodeApprovalSrvService ss = new SBWFNodeApprovalSrvService(wsdlURL,SERVICE_NAME);
		SBWFNodeApprovalSrv port = ss.getSBWFNodeApprovalSrvSoap11();

		ProcessRequest processRequest = new ProcessRequest();
		processRequest.setRequest(request);
		ProcessResponse response = port.process(processRequest);
		
		if(response.getReturn().getSERVICEFLAG().equals("N")){
			System.out.println(response.getReturn().getSERVICEMESSAGE());
		}
		
		return response.getReturn();
	}
}
