//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_inquiryapplysrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Wf_Process_Apply_Item complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Process_Apply_Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_INSTANCE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_TEMPLATE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_BUSI_LINK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="WF_APPLY_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_SUBJECT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_URGENT_LEVEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_CURRENT_NODE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_WAIT_AUDIT_TIP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_OP_COLLECTION" type="{http://csb.fmc.vispractice.com/SB_WF_InquiryApplySrv}Wf_Op_Collection"/>
 *         &lt;element name="WF_ATTR1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR3" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR4" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR5" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR6" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR7" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR8" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR9" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR10" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR11" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR12" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR13" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR14" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR15" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR16" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR17" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR18" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR19" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ATTR20" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Process_Apply_Item", propOrder = {
    "wfinstanceid",
    "wfapplycode",
    "wftemplateid",
    "wfbusilink",
    "wfapplydate",
    "wfapplystatus",
    "wfapplysubject",
    "wfapplyurgentlevel",
    "wfdescription",
    "wfcurrentnodename",
    "wfwaitaudittip",
    "wfopcollection",
    "wfattr1",
    "wfattr2",
    "wfattr3",
    "wfattr4",
    "wfattr5",
    "wfattr6",
    "wfattr7",
    "wfattr8",
    "wfattr9",
    "wfattr10",
    "wfattr11",
    "wfattr12",
    "wfattr13",
    "wfattr14",
    "wfattr15",
    "wfattr16",
    "wfattr17",
    "wfattr18",
    "wfattr19",
    "wfattr20"
})
public class WfProcessApplyItem {

    @XmlElement(name = "WF_INSTANCE_ID", required = true, nillable = true)
    protected String wfinstanceid;
    @XmlElement(name = "WF_APPLY_CODE", required = true, nillable = true)
    protected String wfapplycode;
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
    @XmlElement(name = "WF_OP_COLLECTION", required = true)
    protected WfOpCollection wfopcollection;
    @XmlElement(name = "WF_ATTR1", required = true, nillable = true)
    protected String wfattr1;
    @XmlElement(name = "WF_ATTR2", required = true, nillable = true)
    protected String wfattr2;
    @XmlElement(name = "WF_ATTR3", required = true, nillable = true)
    protected String wfattr3;
    @XmlElement(name = "WF_ATTR4", required = true, nillable = true)
    protected String wfattr4;
    @XmlElement(name = "WF_ATTR5", required = true, nillable = true)
    protected String wfattr5;
    @XmlElement(name = "WF_ATTR6", required = true, nillable = true)
    protected String wfattr6;
    @XmlElement(name = "WF_ATTR7", required = true, nillable = true)
    protected String wfattr7;
    @XmlElement(name = "WF_ATTR8", required = true, nillable = true)
    protected String wfattr8;
    @XmlElement(name = "WF_ATTR9", required = true, nillable = true)
    protected String wfattr9;
    @XmlElement(name = "WF_ATTR10", required = true, nillable = true)
    protected String wfattr10;
    @XmlElement(name = "WF_ATTR11", required = true, nillable = true)
    protected String wfattr11;
    @XmlElement(name = "WF_ATTR12", required = true, nillable = true)
    protected String wfattr12;
    @XmlElement(name = "WF_ATTR13", required = true, nillable = true)
    protected String wfattr13;
    @XmlElement(name = "WF_ATTR14", required = true, nillable = true)
    protected String wfattr14;
    @XmlElement(name = "WF_ATTR15", required = true, nillable = true)
    protected String wfattr15;
    @XmlElement(name = "WF_ATTR16", required = true, nillable = true)
    protected String wfattr16;
    @XmlElement(name = "WF_ATTR17", required = true, nillable = true)
    protected String wfattr17;
    @XmlElement(name = "WF_ATTR18", required = true, nillable = true)
    protected String wfattr18;
    @XmlElement(name = "WF_ATTR19", required = true, nillable = true)
    protected String wfattr19;
    @XmlElement(name = "WF_ATTR20", required = true, nillable = true)
    protected String wfattr20;

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

    /**
     * 获取wfopcollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WfOpCollection }
     *     
     */
    public WfOpCollection getWFOPCOLLECTION() {
        return wfopcollection;
    }

    /**
     * 设置wfopcollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WfOpCollection }
     *     
     */
    public void setWFOPCOLLECTION(WfOpCollection value) {
        this.wfopcollection = value;
    }

    /**
     * 获取wfattr1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR1() {
        return wfattr1;
    }

    /**
     * 设置wfattr1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR1(String value) {
        this.wfattr1 = value;
    }

    /**
     * 获取wfattr2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR2() {
        return wfattr2;
    }

    /**
     * 设置wfattr2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR2(String value) {
        this.wfattr2 = value;
    }

    /**
     * 获取wfattr3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR3() {
        return wfattr3;
    }

    /**
     * 设置wfattr3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR3(String value) {
        this.wfattr3 = value;
    }

    /**
     * 获取wfattr4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR4() {
        return wfattr4;
    }

    /**
     * 设置wfattr4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR4(String value) {
        this.wfattr4 = value;
    }

    /**
     * 获取wfattr5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR5() {
        return wfattr5;
    }

    /**
     * 设置wfattr5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR5(String value) {
        this.wfattr5 = value;
    }

    /**
     * 获取wfattr6属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR6() {
        return wfattr6;
    }

    /**
     * 设置wfattr6属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR6(String value) {
        this.wfattr6 = value;
    }

    /**
     * 获取wfattr7属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR7() {
        return wfattr7;
    }

    /**
     * 设置wfattr7属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR7(String value) {
        this.wfattr7 = value;
    }

    /**
     * 获取wfattr8属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR8() {
        return wfattr8;
    }

    /**
     * 设置wfattr8属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR8(String value) {
        this.wfattr8 = value;
    }

    /**
     * 获取wfattr9属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR9() {
        return wfattr9;
    }

    /**
     * 设置wfattr9属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR9(String value) {
        this.wfattr9 = value;
    }

    /**
     * 获取wfattr10属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR10() {
        return wfattr10;
    }

    /**
     * 设置wfattr10属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR10(String value) {
        this.wfattr10 = value;
    }

    /**
     * 获取wfattr11属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR11() {
        return wfattr11;
    }

    /**
     * 设置wfattr11属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR11(String value) {
        this.wfattr11 = value;
    }

    /**
     * 获取wfattr12属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR12() {
        return wfattr12;
    }

    /**
     * 设置wfattr12属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR12(String value) {
        this.wfattr12 = value;
    }

    /**
     * 获取wfattr13属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR13() {
        return wfattr13;
    }

    /**
     * 设置wfattr13属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR13(String value) {
        this.wfattr13 = value;
    }

    /**
     * 获取wfattr14属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR14() {
        return wfattr14;
    }

    /**
     * 设置wfattr14属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR14(String value) {
        this.wfattr14 = value;
    }

    /**
     * 获取wfattr15属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR15() {
        return wfattr15;
    }

    /**
     * 设置wfattr15属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR15(String value) {
        this.wfattr15 = value;
    }

    /**
     * 获取wfattr16属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR16() {
        return wfattr16;
    }

    /**
     * 设置wfattr16属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR16(String value) {
        this.wfattr16 = value;
    }

    /**
     * 获取wfattr17属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR17() {
        return wfattr17;
    }

    /**
     * 设置wfattr17属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR17(String value) {
        this.wfattr17 = value;
    }

    /**
     * 获取wfattr18属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR18() {
        return wfattr18;
    }

    /**
     * 设置wfattr18属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR18(String value) {
        this.wfattr18 = value;
    }

    /**
     * 获取wfattr19属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR19() {
        return wfattr19;
    }

    /**
     * 设置wfattr19属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR19(String value) {
        this.wfattr19 = value;
    }

    /**
     * 获取wfattr20属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFATTR20() {
        return wfattr20;
    }

    /**
     * 设置wfattr20属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFATTR20(String value) {
        this.wfattr20 = value;
    }

}
