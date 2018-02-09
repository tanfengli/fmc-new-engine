package com.vispractice.fmc.webservice.client;

import java.net.URL;
import javax.xml.namespace.QName;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.SBWFStartProcessSrvRequest;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.SBWFStartProcessSrvResponse;
import com.vispractice.fmc.webservice.processsrv.sb_wf_startprocesssrv.SBWFStartProcessSrv;
import com.vispractice.fmc.webservice.processsrv.sb_wf_startprocesssrv.SBWFStartProcessSrvService;

public final class SBWFStartProcessSrvClient {
    private static final QName SERVICE_NAME = new QName("http://csb.fmc.vispractice.com/SB_WF_StartProcessSrv","SBWFStartProcessSrvService");

    private SBWFStartProcessSrvClient() {
    }

    public static SBWFStartProcessSrvResponse call(SBWFStartProcessSrvRequest sbWFStartProcessSrvRequest) {
        URL wsdlURL = SBWFStartProcessSrvService.WSDL_LOCATION;

        SBWFStartProcessSrvService ss = new SBWFStartProcessSrvService(wsdlURL,SERVICE_NAME);
        SBWFStartProcessSrv port = ss.getSBWFStartProcessSrvSoap11();  
       
        ProcessRequest processRequest = new ProcessRequest();
        processRequest.setRequest(sbWFStartProcessSrvRequest);
        ProcessResponse _process__return = port.process(processRequest);

    	return _process__return.getReturn();
    }
}
