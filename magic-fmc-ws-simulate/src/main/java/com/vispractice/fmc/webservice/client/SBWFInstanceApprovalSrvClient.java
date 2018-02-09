
package com.vispractice.fmc.webservice.client;

import java.net.URL;
import javax.xml.namespace.QName;
import com.vispractice.fmc.csb.sb_wf_instance_approvalsrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_instance_approvalsrv.SBWFInstanceApprovalSrvRequest;
import com.vispractice.fmc.webservice.processsrv.sb_wf_instance_approvalsrv.SBWFInstanceApprovalSrv;
import com.vispractice.fmc.webservice.processsrv.sb_wf_instance_approvalsrv.SBWFInstanceApprovalSrvService;

public final class SBWFInstanceApprovalSrvClient {
    private static final QName SERVICE_NAME = new QName("http://csb.fmc.vispractice.com/SB_WF_Instance_ApprovalSrv", "SBWFInstanceApprovalSrvService");

    public static void call(SBWFInstanceApprovalSrvRequest request) throws java.lang.Exception {
        URL wsdlURL = SBWFInstanceApprovalSrvService.WSDL_LOCATION;
        SBWFInstanceApprovalSrvService ss = new SBWFInstanceApprovalSrvService(wsdlURL,SERVICE_NAME);
        SBWFInstanceApprovalSrv port = ss.getSBWFInstanceApprovalSrvSoap11();  

        ProcessRequest _process_processRequest = new ProcessRequest();
        _process_processRequest.setRequest(request);
        port.process(_process_processRequest);
    }
}
