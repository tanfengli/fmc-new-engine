package com.vispractice.fmc.webservice.client;

import java.net.URL;

import javax.xml.namespace.QName;

import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ProcessResponse;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.SBWFInquiryApplySrvRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.SBWFInquiryApplySrvResponse;
import com.vispractice.fmc.webservice.processsrv.sb_wf_inquiryapplysrv.SBWFInquiryApplySrv;
import com.vispractice.fmc.webservice.processsrv.sb_wf_inquiryapplysrv.SBWFInquiryApplySrvService;

public final class SBWFInquiryApplySrvClient {
    private static final QName SERVICE_NAME = new QName("http://csb.fmc.vispractice.com/SB_WF_InquiryApplySrv", "SBWFInquiryApplySrvService");

    public static SBWFInquiryApplySrvResponse call(SBWFInquiryApplySrvRequest request) {
    	URL wsdlURL = SBWFInquiryApplySrvService.WSDL_LOCATION;
      
        SBWFInquiryApplySrvService ss = new SBWFInquiryApplySrvService(wsdlURL,SERVICE_NAME);
        SBWFInquiryApplySrv port = ss.getSBWFInquiryApplySrvSoap11();  
        ProcessRequest _process_processRequest = new ProcessRequest();
        _process_processRequest.setRequest(request);
        ProcessResponse _process__return = port.process(_process_processRequest);
        
        return _process__return.getReturn();
    }
}
