package com.vispractice.fmc.webservice.processsrv.sb_wf_inquiryapplysrv;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import com.vispractice.fmc.webservice.support.SimulateHelper;

import javax.xml.ws.Service;

@WebServiceClient(name = "SBWFInquiryApplySrvService", 
                  wsdlLocation = "http://10.204.115.52:8080/ws/sbWFInquiryApplySrv/sbWFInquiryApplySrv.wsdl",
                  targetNamespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryApplySrv") 
public class SBWFInquiryApplySrvService extends Service {
    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://csb.fmc.vispractice.com/SB_WF_InquiryApplySrv", "SBWFInquiryApplySrvService");
    public final static QName SBWFInquiryApplySrvSoap11 = new QName("http://csb.fmc.vispractice.com/SB_WF_InquiryApplySrv", "SBWFInquiryApplySrvSoap11");
    static {
        URL url = null;
        try {
            url = new URL(SimulateHelper.getURI()+"/ws/sbWFInquiryApplySrv/sbWFInquiryApplySrv.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SBWFInquiryApplySrvService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.204.115.52:8080/ws/sbWFInquiryApplySrv/sbWFInquiryApplySrv.wsdl");
        }
        
        WSDL_LOCATION = url;
    }

    public SBWFInquiryApplySrvService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBWFInquiryApplySrvService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBWFInquiryApplySrvService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public SBWFInquiryApplySrvService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public SBWFInquiryApplySrvService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public SBWFInquiryApplySrvService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    @WebEndpoint(name = "SBWFInquiryApplySrvSoap11")
    public SBWFInquiryApplySrv getSBWFInquiryApplySrvSoap11() {
        return super.getPort(SBWFInquiryApplySrvSoap11, SBWFInquiryApplySrv.class);
    }

    @WebEndpoint(name = "SBWFInquiryApplySrvSoap11")
    public SBWFInquiryApplySrv getSBWFInquiryApplySrvSoap11(WebServiceFeature... features) {
        return super.getPort(SBWFInquiryApplySrvSoap11, SBWFInquiryApplySrv.class, features);
    }
}
