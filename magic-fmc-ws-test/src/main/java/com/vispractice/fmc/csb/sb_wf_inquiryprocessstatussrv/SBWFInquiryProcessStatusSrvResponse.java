
package com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SB_WF_InquiryProcessStatusSrvResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_InquiryProcessStatusSrvResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SERVICE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SERVICE_MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_STATUS_COLLECTION" type="{http://csb.fmc.vispractice.com/SB_WF_InquiryProcessStatusSrv}Wf_Status_Collection"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_InquiryProcessStatusSrvResponse", propOrder = {
    "serviceflag",
    "servicemessage",
    "wfstatuscollection"
})
public class SBWFInquiryProcessStatusSrvResponse {

    @XmlElement(name = "SERVICE_FLAG", required = true, nillable = true)
    protected String serviceflag;
    @XmlElement(name = "SERVICE_MESSAGE", required = true, nillable = true)
    protected String servicemessage;
    @XmlElement(name = "WF_STATUS_COLLECTION", required = true)
    protected WfStatusCollection wfstatuscollection;

    /**
     * 获取serviceflag属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICEFLAG() {
        return serviceflag;
    }

    /**
     * 设置serviceflag属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICEFLAG(String value) {
        this.serviceflag = value;
    }

    /**
     * 获取servicemessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICEMESSAGE() {
        return servicemessage;
    }

    /**
     * 设置servicemessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICEMESSAGE(String value) {
        this.servicemessage = value;
    }

    /**
     * 获取wfstatuscollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WfStatusCollection }
     *     
     */
    public WfStatusCollection getWFSTATUSCOLLECTION() {
        return wfstatuscollection;
    }

    /**
     * 设置wfstatuscollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WfStatusCollection }
     *     
     */
    public void setWFSTATUSCOLLECTION(WfStatusCollection value) {
        this.wfstatuscollection = value;
    }

}
