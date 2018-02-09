//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_inquiryprocesshistorysrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SB_WF_InquiryProcessHistorySrvResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_InquiryProcessHistorySrvResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SERVICE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SERVICE_MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_AUDIT_RECORD_COLLECTION" type="{http://csb.fmc.vispractice.com/SB_WF_InquiryProcessHistorySrv}Wf_Audit_Record_Collection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_InquiryProcessHistorySrvResponse", propOrder = {
    "serviceflag",
    "servicemessage",
    "wfauditrecordcollection"
})
public class SBWFInquiryProcessHistorySrvResponse {

    @XmlElement(name = "SERVICE_FLAG", required = true, nillable = true)
    protected String serviceflag;
    @XmlElement(name = "SERVICE_MESSAGE", required = true, nillable = true)
    protected String servicemessage;
    @XmlElement(name = "WF_AUDIT_RECORD_COLLECTION", required = true)
    protected WfAuditRecordCollection wfauditrecordcollection;

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
     * 获取wfauditrecordcollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WfAuditRecordCollection }
     *     
     */
    public WfAuditRecordCollection getWFAUDITRECORDCOLLECTION() {
        return wfauditrecordcollection;
    }

    /**
     * 设置wfauditrecordcollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WfAuditRecordCollection }
     *     
     */
    public void setWFAUDITRECORDCOLLECTION(WfAuditRecordCollection value) {
        this.wfauditrecordcollection = value;
    }

}
