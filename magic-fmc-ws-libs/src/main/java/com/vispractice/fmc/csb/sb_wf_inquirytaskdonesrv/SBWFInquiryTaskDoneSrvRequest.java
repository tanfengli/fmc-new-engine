//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_inquirytaskdonesrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.vispractice.fmc.csb.msgheader.MsgHeader;


/**
 * <p>SB_WF_InquiryTaskDoneSrvRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_InquiryTaskDoneSrvRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MsgHeader" type="{http://csb.fmc.vispractice.com/MsgHeader}MsgHeader"/>
 *         &lt;element name="WF_TASK_USER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_USER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_TEMPLATE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_START_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="WF_APPLY_END_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="WF_APPLY_TITLE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_REASON" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_CONTENT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_URGENT_LEVEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_InquiryTaskDoneSrvRequest", propOrder = {
    "msgHeader",
    "wftaskuserno",
    "wfapplycode",
    "wfapplyuserno",
    "wftemplateid",
    "wfapplystartdate",
    "wfapplyenddate",
    "wfapplytitle",
    "wfapplyreason",
    "wfapplycontent",
    "wfapplyurgentlevel"
})
public class SBWFInquiryTaskDoneSrvRequest {

    @XmlElement(name = "MsgHeader", required = true)
    protected MsgHeader msgHeader;
    @XmlElement(name = "WF_TASK_USER_NO", required = true, nillable = true)
    protected String wftaskuserno;
    @XmlElement(name = "WF_APPLY_CODE", required = true, nillable = true)
    protected String wfapplycode;
    @XmlElement(name = "WF_APPLY_USER_NO", required = true, nillable = true)
    protected String wfapplyuserno;
    @XmlElement(name = "WF_TEMPLATE_ID", required = true, nillable = true)
    protected String wftemplateid;
    @XmlElement(name = "WF_APPLY_START_DATE", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar wfapplystartdate;
    @XmlElement(name = "WF_APPLY_END_DATE", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar wfapplyenddate;
    @XmlElement(name = "WF_APPLY_TITLE", required = true, nillable = true)
    protected String wfapplytitle;
    @XmlElement(name = "WF_APPLY_REASON", required = true, nillable = true)
    protected String wfapplyreason;
    @XmlElement(name = "WF_APPLY_CONTENT", required = true, nillable = true)
    protected String wfapplycontent;
    @XmlElement(name = "WF_APPLY_URGENT_LEVEL", required = true, nillable = true)
    protected String wfapplyurgentlevel;

    /**
     * 获取msgHeader属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MsgHeader }
     *     
     */
    public MsgHeader getMsgHeader() {
        return msgHeader;
    }

    /**
     * 设置msgHeader属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MsgHeader }
     *     
     */
    public void setMsgHeader(MsgHeader value) {
        this.msgHeader = value;
    }

    /**
     * 获取wftaskuserno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFTASKUSERNO() {
        return wftaskuserno;
    }

    /**
     * 设置wftaskuserno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFTASKUSERNO(String value) {
        this.wftaskuserno = value;
    }

    /**
     * 获取wfapplycode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAPPLYCODE() {
        return wfapplycode;
    }

    /**
     * 设置wfapplycode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAPPLYCODE(String value) {
        this.wfapplycode = value;
    }

    /**
     * 获取wfapplyuserno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAPPLYUSERNO() {
        return wfapplyuserno;
    }

    /**
     * 设置wfapplyuserno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAPPLYUSERNO(String value) {
        this.wfapplyuserno = value;
    }

    /**
     * 获取wftemplateid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFTEMPLATEID() {
        return wftemplateid;
    }

    /**
     * 设置wftemplateid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFTEMPLATEID(String value) {
        this.wftemplateid = value;
    }

    /**
     * 获取wfapplystartdate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWFAPPLYSTARTDATE() {
        return wfapplystartdate;
    }

    /**
     * 设置wfapplystartdate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWFAPPLYSTARTDATE(XMLGregorianCalendar value) {
        this.wfapplystartdate = value;
    }

    /**
     * 获取wfapplyenddate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWFAPPLYENDDATE() {
        return wfapplyenddate;
    }

    /**
     * 设置wfapplyenddate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWFAPPLYENDDATE(XMLGregorianCalendar value) {
        this.wfapplyenddate = value;
    }

    /**
     * 获取wfapplytitle属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAPPLYTITLE() {
        return wfapplytitle;
    }

    /**
     * 设置wfapplytitle属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAPPLYTITLE(String value) {
        this.wfapplytitle = value;
    }

    /**
     * 获取wfapplyreason属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAPPLYREASON() {
        return wfapplyreason;
    }

    /**
     * 设置wfapplyreason属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAPPLYREASON(String value) {
        this.wfapplyreason = value;
    }

    /**
     * 获取wfapplycontent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAPPLYCONTENT() {
        return wfapplycontent;
    }

    /**
     * 设置wfapplycontent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAPPLYCONTENT(String value) {
        this.wfapplycontent = value;
    }

    /**
     * 获取wfapplyurgentlevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAPPLYURGENTLEVEL() {
        return wfapplyurgentlevel;
    }

    /**
     * 设置wfapplyurgentlevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAPPLYURGENTLEVEL(String value) {
        this.wfapplyurgentlevel = value;
    }

}
