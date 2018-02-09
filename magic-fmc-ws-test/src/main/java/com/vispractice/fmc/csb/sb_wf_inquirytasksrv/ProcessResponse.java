
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
 *         &lt;element name="return" type="{http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv}SB_WF_InquiryTaskSrvResponse" minOccurs="0" form="qualified"/>
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
    "_return"
})
@XmlRootElement(name = "processResponse", namespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv")
public class ProcessResponse {

    @XmlElement(name = "return", namespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv")
    protected SBWFInquiryTaskSrvResponse _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link SBWFInquiryTaskSrvResponse }
     *     
     */
    public SBWFInquiryTaskSrvResponse getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link SBWFInquiryTaskSrvResponse }
     *     
     */
    public void setReturn(SBWFInquiryTaskSrvResponse value) {
        this._return = value;
    }

}
