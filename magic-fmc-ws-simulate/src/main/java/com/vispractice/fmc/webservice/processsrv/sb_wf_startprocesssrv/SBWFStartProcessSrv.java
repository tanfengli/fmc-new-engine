package com.vispractice.fmc.webservice.processsrv.sb_wf_startprocesssrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ObjectFactory;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessRequest;
import com.vispractice.fmc.csb.sb_wf_startprocesssrv.ProcessResponse;

/**
 * This class was generated by Apache CXF 3.1.12
 * 2017-09-07T14:04:24.652+08:00
 * Generated source version: 3.1.12
 * 
 */
@WebService(targetNamespace = "http://csb.fmc.vispractice.com/SB_WF_StartProcessSrv", name = "SBWFStartProcessSrv")
@XmlSeeAlso({com.vispractice.fmc.csb.msgheader.ObjectFactory.class, com.vispractice.fmc.csb.extendparamcollection.ObjectFactory.class, ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBWFStartProcessSrv {

    @WebMethod
    @WebResult(name = "processResponse", targetNamespace = "http://csb.fmc.vispractice.com/SB_WF_StartProcessSrv", partName = "processResponse")
    public ProcessResponse process(
        @WebParam(partName = "processRequest", name = "processRequest", targetNamespace = "http://csb.fmc.vispractice.com/SB_WF_StartProcessSrv")
        ProcessRequest processRequest
    );
}
