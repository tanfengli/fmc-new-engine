package com.vispractice.fmc.webservice.processsrv.sb_wf_inquiryapplysrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ObjectFactory;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_inquiryapplysrv.ProcessResponse;

@WebService(targetNamespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryApplySrv", name = "SBWFInquiryApplySrv")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBWFInquiryApplySrv {

    @WebResult(name = "processResponse", targetNamespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryApplySrv", partName = "processResponse")
    @WebMethod
    public ProcessResponse process(
        @WebParam(partName = "processRequest", name = "processRequest", targetNamespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryApplySrv")
        ProcessRequest processRequest
    );
}
