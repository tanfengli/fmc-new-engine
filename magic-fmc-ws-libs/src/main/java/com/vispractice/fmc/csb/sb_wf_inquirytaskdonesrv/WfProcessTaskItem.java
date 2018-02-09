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


/**
 * <p>Wf_Process_Task_Item complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Process_Task_Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_INSTANCE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_TOKEN_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_USER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_USER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_TEMPLATE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_BUSI_LINK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="WF_APPLY_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_SUBJECT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_URGENT_LEVEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_CURRENT_NODE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_WAIT_AUDIT_TIP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Process_Task_Item", propOrder = {
    "wfinstanceid",
    "wftokenid",
    "wfapplycode",
    "wfapplyuserno",
    "wfapplyusername",
    "wftemplateid",
    "wfbusilink",
    "wfapplydate",
    "wfapplystatus",
    "wfapplysubject",
    "wfapplyurgentlevel",
    "wfdescription",
    "wfcurrentnodename",
    "wfwaitaudittip"
})
public class WfProcessTaskItem {

    @XmlElement(name = "WF_INSTANCE_ID", required = true, nillable = true)
    protected String wfinstanceid;
    @XmlElement(name = "WF_TOKEN_ID", required = true, nillable = true)
    protected String wftokenid;
    @XmlElement(name = "WF_APPLY_CODE", required = true, nillable = true)
    protected String wfapplycode;
    @XmlElement(name = "WF_APPLY_USER_NO", required = true, nillable = true)
    protected String wfapplyuserno;
    @XmlElement(name = "WF_APPLY_USER_NAME", required = true, nillable = true)
    protected String wfapplyusername;
    @XmlElement(name = "WF_TEMPLATE_ID", required = true, nillable = true)
    protected String wftemplateid;
    @XmlElement(name = "WF_BUSI_LINK", required = true, nillable = true)
    protected String wfbusilink;
    @XmlElement(name = "WF_APPLY_DATE", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar wfapplydate;
    @XmlElement(name = "WF_APPLY_STATUS", required = true, nillable = true)
    protected String wfapplystatus;
    @XmlElement(name = "WF_APPLY_SUBJECT", required = true, nillable = true)
    protected String wfapplysubject;
    @XmlElement(name = "WF_APPLY_URGENT_LEVEL", required = true, nillable = true)
    protected String wfapplyurgentlevel;
    @XmlElement(name = "WF_DESCRIPTION", required = true, nillable = true)
    protected String wfdescription;
    @XmlElement(name = "WF_CURRENT_NODE_NAME", required = true, nillable = true)
    protected String wfcurrentnodename;
    @XmlElement(name = "WF_WAIT_AUDIT_TIP", required = true, nillable = true)
    protected String wfwaitaudittip;

    /**
     * 获取wfinstanceid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFINSTANCEID() {
        return wfinstanceid;
    }

    /**
     * 设置wfinstanceid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFINSTANCEID(String value) {
        this.wfinstanceid = value;
    }

    /**
     * 获取wftokenid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFTOKENID() {
        return wftokenid;
    }

    /**
     * 设置wftokenid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFTOKENID(String value) {
        this.wftokenid = value;
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
     * 获取wfapplyusername属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAPPLYUSERNAME() {
        return wfapplyusername;
    }

    /**
     * 设置wfapplyusername属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAPPLYUSERNAME(String value) {
        this.wfapplyusername = value;
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
     * 获取wfbusilink属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFBUSILINK() {
        return wfbusilink;
    }

    /**
     * 设置wfbusilink属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFBUSILINK(String value) {
        this.wfbusilink = value;
    }

    /**
     * 获取wfapplydate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWFAPPLYDATE() {
        return wfapplydate;
    }

    /**
     * 设置wfapplydate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWFAPPLYDATE(XMLGregorianCalendar value) {
        this.wfapplydate = value;
    }

    /**
     * 获取wfapplystatus属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAPPLYSTATUS() {
        return wfapplystatus;
    }

    /**
     * 设置wfapplystatus属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAPPLYSTATUS(String value) {
        this.wfapplystatus = value;
    }

    /**
     * 获取wfapplysubject属性的值。
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
     * 设置wfapplysubject属性的值。
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

    /**
     * 获取wfdescription属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFDESCRIPTION() {
        return wfdescription;
    }

    /**
     * 设置wfdescription属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFDESCRIPTION(String value) {
        this.wfdescription = value;
    }

    /**
     * 获取wfcurrentnodename属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFCURRENTNODENAME() {
        return wfcurrentnodename;
    }

    /**
     * 设置wfcurrentnodename属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFCURRENTNODENAME(String value) {
        this.wfcurrentnodename = value;
    }

    /**
     * 获取wfwaitaudittip属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFWAITAUDITTIP() {
        return wfwaitaudittip;
    }

    /**
     * 设置wfwaitaudittip属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFWAITAUDITTIP(String value) {
        this.wfwaitaudittip = value;
    }

}
