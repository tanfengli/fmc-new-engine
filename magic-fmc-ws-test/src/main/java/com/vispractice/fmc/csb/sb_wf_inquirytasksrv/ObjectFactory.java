
package com.vispractice.fmc.csb.sb_wf_inquirytasksrv;

import javax.xml.bind.annotation.XmlRegistry;

import com.vispractice.fmc.csb.msgheader.ExtendParam;
import com.vispractice.fmc.csb.msgheader.ExtendParamCollection;
import com.vispractice.fmc.csb.msgheader.MsgHeader;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vispractice.fmc.csb.sb_wf_inquirytasksrv package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vispractice.fmc.csb.sb_wf_inquirytasksrv
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WfProcessTaskItem }
     * 
     */
    public WfProcessTaskItem createWfProcessTaskItem() {
        return new WfProcessTaskItem();
    }

    /**
     * Create an instance of {@link SBWFInquiryTaskSrvRequest }
     * 
     */
    public SBWFInquiryTaskSrvRequest createSBWFInquiryTaskSrvRequest() {
        return new SBWFInquiryTaskSrvRequest();
    }

    /**
     * Create an instance of {@link ProcessResponse }
     * 
     */
    public ProcessResponse createProcessResponse() {
        return new ProcessResponse();
    }

    /**
     * Create an instance of {@link MsgHeader }
     * 
     */
    public MsgHeader createMsgHeader() {
        return new MsgHeader();
    }

    /**
     * Create an instance of {@link ExtendParam }
     * 
     */
    public ExtendParam createExtendParam() {
        return new ExtendParam();
    }

    /**
     * Create an instance of {@link ExtendParamCollection }
     * 
     */
    public ExtendParamCollection createExtendParamCollection() {
        return new ExtendParamCollection();
    }

    /**
     * Create an instance of {@link WfOpItem }
     * 
     */
    public WfOpItem createWfOpItem() {
        return new WfOpItem();
    }

    /**
     * Create an instance of {@link WfOpCollection }
     * 
     */
    public WfOpCollection createWfOpCollection() {
        return new WfOpCollection();
    }

    /**
     * Create an instance of {@link ProcessRequest }
     * 
     */
    public ProcessRequest createProcessRequest() {
        return new ProcessRequest();
    }

    /**
     * Create an instance of {@link SBWFInquiryTaskSrvResponse }
     * 
     */
    public SBWFInquiryTaskSrvResponse createSBWFInquiryTaskSrvResponse() {
        return new SBWFInquiryTaskSrvResponse();
    }

    /**
     * Create an instance of {@link WfProcessVar }
     * 
     */
    public WfProcessVar createWfProcessVar() {
        return new WfProcessVar();
    }

    /**
     * Create an instance of {@link WfProcessVarCollection }
     * 
     */
    public WfProcessVarCollection createWfProcessVarCollection() {
        return new WfProcessVarCollection();
    }

    /**
     * Create an instance of {@link WfProcessTaskCollection }
     * 
     */
    public WfProcessTaskCollection createWfProcessTaskCollection() {
        return new WfProcessTaskCollection();
    }

}
