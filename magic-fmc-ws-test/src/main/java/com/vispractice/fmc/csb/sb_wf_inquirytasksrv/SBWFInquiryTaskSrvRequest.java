
package com.vispractice.fmc.csb.sb_wf_inquirytasksrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.vispractice.fmc.csb.msgheader.MsgHeader;


/**
 * <p>Java class for SB_WF_InquiryTaskSrvRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SB_WF_InquiryTaskSrvRequest">
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
 *         &lt;element name="WF_APPLY_SUBJECT" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "SB_WF_InquiryTaskSrvRequest", namespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv", propOrder = {
    "msgHeader",
    "wftaskuserno",
    "wfapplycode",
    "wfapplyuserno",
    "wftemplateid",
    "wfapplystartdate",
    "wfapplyenddate",
    "wfapplysubject",
    "wfapplyreason",
    "wfapplycontent",
    "wfapplyurgentlevel"
})
public class SBWFInquiryTaskSrvRequest {

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
    @XmlElement(name = "WF_APPLY_SUBJECT", required = true, nillable = true)
    protected String wfapplysubject;
    @XmlElement(name = "WF_APPLY_REASON", required = true, nillable = true)
    protected String wfapplyreason;
    @XmlElement(name = "WF_APPLY_CONTENT", required = true, nillable = true)
    protected String wfapplycontent;
    @XmlElement(name = "WF_APPLY_URGENT_LEVEL", required = true, nillable = true)
    protected String wfapplyurgentlevel;

    /**
     * Gets the value of the msgHeader property.
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
     * Sets the value of the msgHeader property.
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
     * Gets the value of the wftaskuserno property.
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
     * Sets the value of the wftaskuserno property.
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
     * Gets the value of the wfapplycode property.
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
     * Sets the value of the wfapplycode property.
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
     * Gets the value of the wfapplyuserno property.
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
     * Sets the value of the wfapplyuserno property.
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
     * Gets the value of the wftemplateid property.
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
     * Sets the value of the wftemplateid property.
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
     * Gets the value of the wfapplystartdate property.
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
     * Sets the value of the wfapplystartdate property.
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
     * Gets the value of the wfapplyenddate property.
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
     * Sets the value of the wfapplyenddate property.
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
     * Gets the value of the wfapplysubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAPPLYSUBJECT() {
        return wfapplysubject;
    }

    /**
     * Sets the value of the wfapplysubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAPPLYSUBJECT(String value) {
        this.wfapplysubject = value;
    }

    /**
     * Gets the value of the wfapplyreason property.
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
     * Sets the value of the wfapplyreason property.
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
     * Gets the value of the wfapplycontent property.
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
     * Sets the value of the wfapplycontent property.
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
     * Gets the value of the wfapplyurgentlevel property.
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
     * Sets the value of the wfapplyurgentlevel property.
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
