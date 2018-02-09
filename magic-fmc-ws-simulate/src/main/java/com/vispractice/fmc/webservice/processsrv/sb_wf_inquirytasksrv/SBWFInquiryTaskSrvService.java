package com.vispractice.fmc.webservice.processsrv.sb_wf_inquirytasksrv;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import com.vispractice.fmc.webservice.support.SimulateHelper;

import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.12
 * 2017-09-06T15:19:34.276+08:00
 * Generated source version: 3.1.12
 * 
 */
@WebServiceClient(name = "SBWFInquiryTaskSrvService", 
                  wsdlLocation = "http://10.204.115.52:8080/ws/sbWFInquiryTaskSrv/sbWFInquiryTaskSrv.wsdl",
                  targetNamespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv") 
public class SBWFInquiryTaskSrvService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv", "SBWFInquiryTaskSrvService");
    public final static QName SBWFInquiryTaskSrvSoap11 = new QName("http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv", "SBWFInquiryTaskSrvSoap11");
    static {
        URL url = null;
        try {
            url = new URL(SimulateHelper.getURI()+"/ws/sbWFInquiryTaskSrv/sbWFInquiryTaskSrv.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SBWFInquiryTaskSrvService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.204.115.52:8080/ws/sbWFInquiryTaskSrv/sbWFInquiryTaskSrv.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SBWFInquiryTaskSrvService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBWFInquiryTaskSrvService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBWFInquiryTaskSrvService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public SBWFInquiryTaskSrvService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public SBWFInquiryTaskSrvService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public SBWFInquiryTaskSrvService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns SBWFInquiryTaskSrv
     */
    @WebEndpoint(name = "SBWFInquiryTaskSrvSoap11")
    public SBWFInquiryTaskSrv getSBWFInquiryTaskSrvSoap11() {
        return super.getPort(SBWFInquiryTaskSrvSoap11, SBWFInquiryTaskSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBWFInquiryTaskSrv
     */
    @WebEndpoint(name = "SBWFInquiryTaskSrvSoap11")
    public SBWFInquiryTaskSrv getSBWFInquiryTaskSrvSoap11(WebServiceFeature... features) {
        return super.getPort(SBWFInquiryTaskSrvSoap11, SBWFInquiryTaskSrv.class, features);
    }

}
