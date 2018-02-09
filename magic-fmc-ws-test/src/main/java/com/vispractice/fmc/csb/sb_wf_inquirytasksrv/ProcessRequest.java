
package com.vispractice.fmc.csb.sb_wf_inquirytasksrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv}SB_WF_InquiryTaskSrvRequest" minOccurs="0" form="qualified"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request"
})
@XmlRootElement(name = "processRequest", namespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv")
public class ProcessRequest {

    @XmlElement(namespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv")
    protected SBWFInquiryTaskSrvRequest request;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link SBWFInquiryTaskSrvRequest }
     *     
     */
    public SBWFInquiryTaskSrvRequest getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBWFInquiryTaskSrvRequest }
     *     
     */
    public void setRequest(SBWFInquiryTaskSrvRequest value) {
        this.request = value;
    }

}
