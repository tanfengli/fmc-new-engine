
package com.vispractice.fmc.csb.sb_wf_inquirytasksrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Wf_Process_Task_Item complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Wf_Process_Task_Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_INSTANCE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_TOKEN_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_TEMPLATE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_BUSI_LINK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_USER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_USER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="WF_APPLY_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_SUBJECT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_APPLY_URGENT_LEVEL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_CURRENT_NODE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_WAIT_AUDIT_TIP" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_OP_Collection" type="{http://csb.fmc.vispractice.com/ExtendParamCollection}Wf_Op_Collection"/>
 *         &lt;element name="WF_NODE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "Wf_Process_Task_Item", propOrder = {
    "wfinstanceid",
    "wftokenid",
    "wfapplycode",
    "wftemplateid",
    "wfbusilink",
    "wfapplyuserno",
    "wfapplyusername",
    "wfapplydate",
    "wfapplystatus",
    "wfapplysubject",
    "wfapplyurgentlevel",
    "wfdescription",
    "wfcurrentnodename",
    "wfwaitaudittip",
    "wfopCollection",
    "wfnodeid",
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
public class WfProcessTaskItem {

    @XmlElement(name = "WF_INSTANCE_ID", required = true, nillable = true)
    protected String wfinstanceid;
    @XmlElement(name = "WF_TOKEN_ID", required = true, nillable = true)
    protected String wftokenid;
    @XmlElement(name = "WF_APPLY_CODE", required = true, nillable = true)
    protected String wfapplycode;
    @XmlElement(name = "WF_TEMPLATE_ID", required = true, nillable = true)
    protected String wftemplateid;
    @XmlElement(name = "WF_BUSI_LINK", required = true, nillable = true)
    protected String wfbusilink;
    @XmlElement(name = "WF_APPLY_USER_NO", required = true, nillable = true)
    protected String wfapplyuserno;
    @XmlElement(name = "WF_APPLY_USER_NAME", required = true, nillable = true)
    protected String wfapplyusername;
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
    @XmlElement(name = "WF_OP_Collection", required = true)
    protected WfOpCollection wfopCollection;
    @XmlElement(name = "WF_NODE_ID", required = true, nillable = true)
    protected String wfnodeid;
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
     * Gets the value of the wfinstanceid property.
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
     * Sets the value of the wfinstanceid property.
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
     * Gets the value of the wftokenid property.
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
     * Sets the value of the wftokenid property.
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
     * Gets the value of the wfbusilink property.
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
     * Sets the value of the wfbusilink property.
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
     * Gets the value of the wfapplyusername property.
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
     * Sets the value of the wfapplyusername property.
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
     * Gets the value of the wfapplydate property.
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
     * Sets the value of the wfapplydate property.
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
     * Gets the value of the wfapplystatus property.
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
     * Sets the value of the wfapplystatus property.
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

    /**
     * Gets the value of the wfdescription property.
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
     * Sets the value of the wfdescription property.
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
     * Gets the value of the wfcurrentnodename property.
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
     * Sets the value of the wfcurrentnodename property.
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
     * Gets the value of the wfwaitaudittip property.
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
     * Sets the value of the wfwaitaudittip property.
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
     * Gets the value of the wfopCollection property.
     * 
     * @return
     *     possible object is
     *     {@link WfOpCollection }
     *     
     */
    public WfOpCollection getWFOPCollection() {
        return wfopCollection;
    }

    /**
     * Sets the value of the wfopCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link WfOpCollection }
     *     
     */
    public void setWFOPCollection(WfOpCollection value) {
        this.wfopCollection = value;
    }

    /**
     * Gets the value of the wfnodeid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFNODEID() {
        return wfnodeid;
    }

    /**
     * Sets the value of the wfnodeid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFNODEID(String value) {
        this.wfnodeid = value;
    }

    /**
     * Gets the value of the wfattr1 property.
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
     * Sets the value of the wfattr1 property.
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
     * Gets the value of the wfattr2 property.
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
     * Sets the value of the wfattr2 property.
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
     * Gets the value of the wfattr3 property.
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
     * Sets the value of the wfattr3 property.
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
     * Gets the value of the wfattr4 property.
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
     * Sets the value of the wfattr4 property.
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
     * Gets the value of the wfattr5 property.
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
     * Sets the value of the wfattr5 property.
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
     * Gets the value of the wfattr6 property.
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
     * Sets the value of the wfattr6 property.
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
     * Gets the value of the wfattr7 property.
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
     * Sets the value of the wfattr7 property.
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
     * Gets the value of the wfattr8 property.
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
     * Sets the value of the wfattr8 property.
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
     * Gets the value of the wfattr9 property.
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
     * Sets the value of the wfattr9 property.
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
     * Gets the value of the wfattr10 property.
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
     * Sets the value of the wfattr10 property.
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
     * Gets the value of the wfattr11 property.
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
     * Sets the value of the wfattr11 property.
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
     * Gets the value of the wfattr12 property.
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
     * Sets the value of the wfattr12 property.
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
     * Gets the value of the wfattr13 property.
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
     * Sets the value of the wfattr13 property.
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
     * Gets the value of the wfattr14 property.
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
     * Sets the value of the wfattr14 property.
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
     * Gets the value of the wfattr15 property.
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
     * Sets the value of the wfattr15 property.
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
     * Gets the value of the wfattr16 property.
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
     * Sets the value of the wfattr16 property.
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
     * Gets the value of the wfattr17 property.
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
     * Sets the value of the wfattr17 property.
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
     * Gets the value of the wfattr18 property.
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
     * Sets the value of the wfattr18 property.
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
     * Gets the value of the wfattr19 property.
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
     * Sets the value of the wfattr19 property.
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
     * Gets the value of the wfattr20 property.
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
     * Sets the value of the wfattr20 property.
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
