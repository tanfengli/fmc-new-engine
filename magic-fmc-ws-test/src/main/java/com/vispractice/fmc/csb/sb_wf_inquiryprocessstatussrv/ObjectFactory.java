
package com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProcessRequest }
     * 
     */
    public ProcessRequest createProcessRequest() {
        return new ProcessRequest();
    }

    /**
     * Create an instance of {@link SBWFInquiryProcessStatusSrvRequest }
     * 
     */
    public SBWFInquiryProcessStatusSrvRequest createSBWFInquiryProcessStatusSrvRequest() {
        return new SBWFInquiryProcessStatusSrvRequest();
    }

    /**
     * Create an instance of {@link ProcessResponse }
     * 
     */
    public ProcessResponse createProcessResponse() {
        return new ProcessResponse();
    }

    /**
     * Create an instance of {@link SBWFInquiryProcessStatusSrvResponse }
     * 
     */
    public SBWFInquiryProcessStatusSrvResponse createSBWFInquiryProcessStatusSrvResponse() {
        return new SBWFInquiryProcessStatusSrvResponse();
    }

    /**
     * Create an instance of {@link WfStatusCollection }
     * 
     */
    public WfStatusCollection createWfStatusCollection() {
        return new WfStatusCollection();
    }

    /**
     * Create an instance of {@link WfStatusItem }
     * 
     */
    public WfStatusItem createWfStatusItem() {
        return new WfStatusItem();
    }

}
