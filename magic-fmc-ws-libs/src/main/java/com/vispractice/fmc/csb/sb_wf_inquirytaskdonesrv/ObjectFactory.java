//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv
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
     * Create an instance of {@link SBWFInquiryTaskDoneSrvRequest }
     * 
     */
    public SBWFInquiryTaskDoneSrvRequest createSBWFInquiryTaskDoneSrvRequest() {
        return new SBWFInquiryTaskDoneSrvRequest();
    }

    /**
     * Create an instance of {@link ProcessResponse }
     * 
     */
    public ProcessResponse createProcessResponse() {
        return new ProcessResponse();
    }

    /**
     * Create an instance of {@link SBWFInquiryTaskDoneSrvResponse }
     * 
     */
    public SBWFInquiryTaskDoneSrvResponse createSBWFInquiryTaskDoneSrvResponse() {
        return new SBWFInquiryTaskDoneSrvResponse();
    }

    /**
     * Create an instance of {@link WfProcessTaskCollection }
     * 
     */
    public WfProcessTaskCollection createWfProcessTaskCollection() {
        return new WfProcessTaskCollection();
    }

    /**
     * Create an instance of {@link WfProcessTaskItem }
     * 
     */
    public WfProcessTaskItem createWfProcessTaskItem() {
        return new WfProcessTaskItem();
    }

}
