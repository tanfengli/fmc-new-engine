//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_instance_approvalsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.vispractice.fmc.csb.extendparamcollection.WfProcessVarCollection;
import com.vispractice.fmc.csb.msgheader.MsgHeader;


/**
 * <p>SB_WF_Instance_ApprovalSrvRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_Instance_ApprovalSrvRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MsgHeader" type="{http://csb.fmc.vispractice.com/MsgHeader}MsgHeader"/>
 *         &lt;element name="WF_INSTANCE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_AUDITER_USER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_RESULT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_OPINION_CON" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ROUTE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_TRANSFER_USER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_PASSED_TO_THIS" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "SB_WF_Instance_ApprovalSrvRequest", propOrder = {
    "msgHeader",
    "wfinstanceid",
    "wfauditeruserno",
    "wfresult",
    "wfopinioncon",
    "wfrouteid",
    "wftransferuserno",
    "wfpassedtothis",
    "wfprocessvarcollection"
})
public class SBWFInstanceApprovalSrvRequest {

    @XmlElement(name = "MsgHeader", required = true)
    protected MsgHeader msgHeader;
    @XmlElement(name = "WF_INSTANCE_ID", required = true, nillable = true)
    protected String wfinstanceid;
    @XmlElement(name = "WF_AUDITER_USER_NO", required = true, nillable = true)
    protected String wfauditeruserno;
    @XmlElement(name = "WF_RESULT", required = true, nillable = true)
    protected String wfresult;
    @XmlElement(name = "WF_OPINION_CON", required = true, nillable = true)
    protected String wfopinioncon;
    @XmlElement(name = "WF_ROUTE_ID", required = true, nillable = true)
    protected String wfrouteid;
    @XmlElement(name = "WF_TRANSFER_USER_NO", required = true, nillable = true)
    protected String wftransferuserno;
    @XmlElement(name = "WF_PASSED_TO_THIS", required = true, nillable = true)
    protected String wfpassedtothis;
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
     * 获取wfauditeruserno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAUDITERUSERNO() {
        return wfauditeruserno;
    }

    /**
     * 设置wfauditeruserno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAUDITERUSERNO(String value) {
        this.wfauditeruserno = value;
    }

    /**
     * 获取wfresult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFRESULT() {
        return wfresult;
    }

    /**
     * 设置wfresult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFRESULT(String value) {
        this.wfresult = value;
    }

    /**
     * 获取wfopinioncon属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFOPINIONCON() {
        return wfopinioncon;
    }

    /**
     * 设置wfopinioncon属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFOPINIONCON(String value) {
        this.wfopinioncon = value;
    }

    /**
     * 获取wfrouteid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFROUTEID() {
        return wfrouteid;
    }

    /**
     * 设置wfrouteid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFROUTEID(String value) {
        this.wfrouteid = value;
    }

    /**
     * 获取wftransferuserno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFTRANSFERUSERNO() {
        return wftransferuserno;
    }

    /**
     * 设置wftransferuserno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFTRANSFERUSERNO(String value) {
        this.wftransferuserno = value;
    }

    /**
     * 获取wfpassedtothis属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPASSEDTOTHIS() {
        return wfpassedtothis;
    }

    /**
     * 设置wfpassedtothis属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPASSEDTOTHIS(String value) {
        this.wfpassedtothis = value;
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
