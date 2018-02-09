//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_inquiryprocesshistorysrv;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Wf_Audit_Record complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Audit_Record">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_NODE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_NODE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_AUDIT_USER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_AUDIT_USER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_AUDIT_MIND" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_AUDIT_RESULT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_AUDIT_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="WF_AUDIT_SPACING_INTERVAL" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Audit_Record", propOrder = {
    "wfnodeid",
    "wfnodename",
    "wfaudituserno",
    "wfauditusername",
    "wfauditmind",
    "wfauditresult",
    "wfauditdate",
    "wfauditspacinginterval"
})
public class WfAuditRecord {

    @XmlElement(name = "WF_NODE_ID", required = true, nillable = true)
    protected String wfnodeid;
    @XmlElement(name = "WF_NODE_NAME", required = true, nillable = true)
    protected String wfnodename;
    @XmlElement(name = "WF_AUDIT_USER_NO", required = true, nillable = true)
    protected String wfaudituserno;
    @XmlElement(name = "WF_AUDIT_USER_NAME", required = true, nillable = true)
    protected String wfauditusername;
    @XmlElement(name = "WF_AUDIT_MIND", required = true, nillable = true)
    protected String wfauditmind;
    @XmlElement(name = "WF_AUDIT_RESULT", required = true, nillable = true)
    protected String wfauditresult;
    @XmlElement(name = "WF_AUDIT_DATE", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar wfauditdate;
    @XmlElement(name = "WF_AUDIT_SPACING_INTERVAL", required = true, nillable = true)
    protected BigDecimal wfauditspacinginterval;

    /**
     * 获取wfnodeid属性的值。
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
     * 设置wfnodeid属性的值。
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
     * 获取wfnodename属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFNODENAME() {
        return wfnodename;
    }

    /**
     * 设置wfnodename属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFNODENAME(String value) {
        this.wfnodename = value;
    }

    /**
     * 获取wfaudituserno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAUDITUSERNO() {
        return wfaudituserno;
    }

    /**
     * 设置wfaudituserno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAUDITUSERNO(String value) {
        this.wfaudituserno = value;
    }

    /**
     * 获取wfauditusername属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAUDITUSERNAME() {
        return wfauditusername;
    }

    /**
     * 设置wfauditusername属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAUDITUSERNAME(String value) {
        this.wfauditusername = value;
    }

    /**
     * 获取wfauditmind属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAUDITMIND() {
        return wfauditmind;
    }

    /**
     * 设置wfauditmind属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAUDITMIND(String value) {
        this.wfauditmind = value;
    }

    /**
     * 获取wfauditresult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAUDITRESULT() {
        return wfauditresult;
    }

    /**
     * 设置wfauditresult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAUDITRESULT(String value) {
        this.wfauditresult = value;
    }

    /**
     * 获取wfauditdate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWFAUDITDATE() {
        return wfauditdate;
    }

    /**
     * 设置wfauditdate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWFAUDITDATE(XMLGregorianCalendar value) {
        this.wfauditdate = value;
    }

    /**
     * 获取wfauditspacinginterval属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWFAUDITSPACINGINTERVAL() {
        return wfauditspacinginterval;
    }

    /**
     * 设置wfauditspacinginterval属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWFAUDITSPACINGINTERVAL(BigDecimal value) {
        this.wfauditspacinginterval = value;
    }

}
