//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_inquiryprocesslogsrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Wf_Process_Log complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Process_Log">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_NODE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_NODE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_OP_USER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_OP_USER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_OP_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_OP_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_OP_INFO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_OP_time" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Process_Log", propOrder = {
    "wfnodeid",
    "wfnodename",
    "wfopuserno",
    "wfopusername",
    "wfopcode",
    "wfopname",
    "wfopinfo",
    "wfopTime"
})
public class WfProcessLog {

    @XmlElement(name = "WF_NODE_ID", required = true, nillable = true)
    protected String wfnodeid;
    @XmlElement(name = "WF_NODE_NAME", required = true, nillable = true)
    protected String wfnodename;
    @XmlElement(name = "WF_OP_USER_NO", required = true, nillable = true)
    protected String wfopuserno;
    @XmlElement(name = "WF_OP_USER_NAME", required = true, nillable = true)
    protected String wfopusername;
    @XmlElement(name = "WF_OP_CODE", required = true, nillable = true)
    protected String wfopcode;
    @XmlElement(name = "WF_OP_NAME", required = true, nillable = true)
    protected String wfopname;
    @XmlElement(name = "WF_OP_INFO", required = true, nillable = true)
    protected String wfopinfo;
    @XmlElement(name = "WF_OP_time", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar wfopTime;

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
     * 获取wfopuserno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFOPUSERNO() {
        return wfopuserno;
    }

    /**
     * 设置wfopuserno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFOPUSERNO(String value) {
        this.wfopuserno = value;
    }

    /**
     * 获取wfopusername属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFOPUSERNAME() {
        return wfopusername;
    }

    /**
     * 设置wfopusername属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFOPUSERNAME(String value) {
        this.wfopusername = value;
    }

    /**
     * 获取wfopcode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFOPCODE() {
        return wfopcode;
    }

    /**
     * 设置wfopcode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFOPCODE(String value) {
        this.wfopcode = value;
    }

    /**
     * 获取wfopname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFOPNAME() {
        return wfopname;
    }

    /**
     * 设置wfopname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFOPNAME(String value) {
        this.wfopname = value;
    }

    /**
     * 获取wfopinfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFOPINFO() {
        return wfopinfo;
    }

    /**
     * 设置wfopinfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFOPINFO(String value) {
        this.wfopinfo = value;
    }

    /**
     * 获取wfopTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWFOPTime() {
        return wfopTime;
    }

    /**
     * 设置wfopTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWFOPTime(XMLGregorianCalendar value) {
        this.wfopTime = value;
    }

}
