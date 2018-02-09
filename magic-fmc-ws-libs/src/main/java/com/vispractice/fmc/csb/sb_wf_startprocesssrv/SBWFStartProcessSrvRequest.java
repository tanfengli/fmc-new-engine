//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_startprocesssrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVarCollection;
import com.vispractice.fmc.csb.msgheader.MsgHeader;


/**
 * <p>SB_WF_StartProcessSrvRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_StartProcessSrvRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MsgHeader" type="{http://csb.fmc.vispractice.com/MsgHeader}MsgHeader"/>
 *         &lt;element name="WF_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_TEMPLATE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_USER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_SUBJECT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_REASON" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_CONTENT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="URGENT_LEVEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LINK_URL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_PROCESS_VAR_COLLECTION" type="{http://csb.fmc.vispractice.com/ExtendParamCollection}Wf_Process_Var_Collection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_StartProcessSrvRequest", propOrder = {
    "msgHeader",
    "wfdescription",
    "wftemplateid",
    "wfapplyuserno",
    "wfapplycode",
    "wfapplysubject",
    "wfapplyreason",
    "wfapplycontent",
    "wfapplydate",
    "urgentlevel",
    "linkurl",
    "wfprocessvarcollection"
})
public class SBWFStartProcessSrvRequest {

    @XmlElement(name = "MsgHeader", required = true)
    protected MsgHeader msgHeader;
    @XmlElement(name = "WF_DESCRIPTION", required = true, nillable = true)
    protected String wfdescription;
    @XmlElement(name = "WF_TEMPLATE_ID", required = true, nillable = true)
    protected String wftemplateid;
    @XmlElement(name = "WF_APPLY_USER_NO", required = true, nillable = true)
    protected String wfapplyuserno;
    @XmlElement(name = "WF_APPLY_CODE", required = true, nillable = true)
    protected String wfapplycode;
    @XmlElement(name = "WF_APPLY_SUBJECT", required = true, nillable = true)
    protected String wfapplysubject;
    @XmlElement(name = "WF_APPLY_REASON", required = true, nillable = true)
    protected String wfapplyreason;
    @XmlElement(name = "WF_APPLY_CONTENT", required = true, nillable = true)
    protected String wfapplycontent;
    @XmlElement(name = "WF_APPLY_DATE", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar wfapplydate;
    @XmlElement(name = "URGENT_LEVEL", required = true, nillable = true)
    protected String urgentlevel;
    @XmlElement(name = "LINK_URL", required = true, nillable = true)
    protected String linkurl;
    @XmlElement(name = "WF_PROCESS_VAR_COLLECTION", required = true)
    protected WfProcessVarCollection wfprocessvarcollection;

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
     * 获取urgentlevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURGENTLEVEL() {
        return urgentlevel;
    }

    /**
     * 设置urgentlevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURGENTLEVEL(String value) {
        this.urgentlevel = value;
    }

    /**
     * 获取linkurl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLINKURL() {
        return linkurl;
    }

    /**
     * 设置linkurl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLINKURL(String value) {
        this.linkurl = value;
    }

    /**
     * 获取wfprocessvarcollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WfProcessVarCollection }
     *     
     */
    public WfProcessVarCollection getWFPROCESSVARCOLLECTION() {
        return wfprocessvarcollection;
    }

    /**
     * 设置wfprocessvarcollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WfProcessVarCollection }
     *     
     */
    public void setWFPROCESSVARCOLLECTION(WfProcessVarCollection value) {
        this.wfprocessvarcollection = value;
    }

}
