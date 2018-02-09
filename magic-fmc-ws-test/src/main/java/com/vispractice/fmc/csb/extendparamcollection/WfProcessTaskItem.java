
package com.vispractice.fmc.csb.extendparamcollection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Wf_Process_Task_Item complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Wf_Process_Task_Item"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WF_INSTANCE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_TOKEN_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_APPLY_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_TEMPLATE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_BUSI_LINK" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_APPLY_USER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_APPLY_USER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_APPLY_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="WF_APPLY_STATUS" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_APPLY_SUBJECT" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_APPLY_URGENT_LEVEL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_CURRENT_NODE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_WAIT_AUDIT_TIP" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_OP_Collection" type="{http://csb.fmc.vispractice.com/ExtendParamCollection}Wf_Op_Collection"/&gt;
 *         &lt;element name="WF_NODE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR3" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR4" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR5" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR6" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR7" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR8" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR9" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR10" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR11" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR12" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR13" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR14" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR15" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR16" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR17" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR18" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR19" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_ATTR20" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * ��ȡwfinstanceid���Ե�ֵ��
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
     * ����wfinstanceid���Ե�ֵ��
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
     * ��ȡwftokenid���Ե�ֵ��
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
     * ����wftokenid���Ե�ֵ��
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
     * ��ȡwfapplycode���Ե�ֵ��
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
     * ����wfapplycode���Ե�ֵ��
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
     * ��ȡwftemplateid���Ե�ֵ��
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
     * ����wftemplateid���Ե�ֵ��
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
     * ��ȡwfbusilink���Ե�ֵ��
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
     * ����wfbusilink���Ե�ֵ��
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
     * ��ȡwfapplyuserno���Ե�ֵ��
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
     * ����wfapplyuserno���Ե�ֵ��
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
     * ��ȡwfapplyusername���Ե�ֵ��
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
     * ����wfapplyusername���Ե�ֵ��
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
     * ��ȡwfapplydate���Ե�ֵ��
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
     * ����wfapplydate���Ե�ֵ��
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
     * ��ȡwfapplystatus���Ե�ֵ��
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
     * ����wfapplystatus���Ե�ֵ��
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
     * ��ȡwfapplysubject���Ե�ֵ��
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
     * ����wfapplysubject���Ե�ֵ��
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
     * ��ȡwfapplyurgentlevel���Ե�ֵ��
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
     * ����wfapplyurgentlevel���Ե�ֵ��
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
     * ��ȡwfdescription���Ե�ֵ��
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
     * ����wfdescription���Ե�ֵ��
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
     * ��ȡwfcurrentnodename���Ե�ֵ��
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
     * ����wfcurrentnodename���Ե�ֵ��
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
     * ��ȡwfwaitaudittip���Ե�ֵ��
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
     * ����wfwaitaudittip���Ե�ֵ��
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
     * ��ȡwfopCollection���Ե�ֵ��
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
     * ����wfopCollection���Ե�ֵ��
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
     * ��ȡwfnodeid���Ե�ֵ��
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
     * ����wfnodeid���Ե�ֵ��
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
     * ��ȡwfattr1���Ե�ֵ��
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
     * ����wfattr1���Ե�ֵ��
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
     * ��ȡwfattr2���Ե�ֵ��
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
     * ����wfattr2���Ե�ֵ��
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
     * ��ȡwfattr3���Ե�ֵ��
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
     * ����wfattr3���Ե�ֵ��
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
     * ��ȡwfattr4���Ե�ֵ��
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
     * ����wfattr4���Ե�ֵ��
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
     * ��ȡwfattr5���Ե�ֵ��
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
     * ����wfattr5���Ե�ֵ��
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
     * ��ȡwfattr6���Ե�ֵ��
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
     * ����wfattr6���Ե�ֵ��
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
     * ��ȡwfattr7���Ե�ֵ��
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
     * ����wfattr7���Ե�ֵ��
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
     * ��ȡwfattr8���Ե�ֵ��
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
     * ����wfattr8���Ե�ֵ��
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
     * ��ȡwfattr9���Ե�ֵ��
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
     * ����wfattr9���Ե�ֵ��
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
     * ��ȡwfattr10���Ե�ֵ��
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
     * ����wfattr10���Ե�ֵ��
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
     * ��ȡwfattr11���Ե�ֵ��
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
     * ����wfattr11���Ե�ֵ��
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
     * ��ȡwfattr12���Ե�ֵ��
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
     * ����wfattr12���Ե�ֵ��
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
     * ��ȡwfattr13���Ե�ֵ��
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
     * ����wfattr13���Ե�ֵ��
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
     * ��ȡwfattr14���Ե�ֵ��
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
     * ����wfattr14���Ե�ֵ��
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
     * ��ȡwfattr15���Ե�ֵ��
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
     * ����wfattr15���Ե�ֵ��
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
     * ��ȡwfattr16���Ե�ֵ��
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
     * ����wfattr16���Ե�ֵ��
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
     * ��ȡwfattr17���Ե�ֵ��
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
     * ����wfattr17���Ե�ֵ��
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
     * ��ȡwfattr18���Ե�ֵ��
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
     * ����wfattr18���Ե�ֵ��
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
     * ��ȡwfattr19���Ե�ֵ��
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
     * ����wfattr19���Ե�ֵ��
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
     * ��ȡwfattr20���Ե�ֵ��
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
     * ����wfattr20���Ե�ֵ��
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
