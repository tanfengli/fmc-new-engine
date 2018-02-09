//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.msgheader;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>MsgHeader complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="MsgHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SOURCE_SYSTEM_ID" type="{http://csb.fmc.vispractice.com/MsgHeader}less_than_30"/>
 *         &lt;element name="SOURCE_SYSTEM_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USER_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="USER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SUBMIT_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="PAGE_SIZE" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CURRENT_PAGE" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TOTAL_RECORD" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PROVINCE_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ENVIRONMENT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EXTEND_PARAM_COLLECTION" type="{http://csb.fmc.vispractice.com/MsgHeader}Extend_Param_Collection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MsgHeader", propOrder = {
    "sourcesystemid",
    "sourcesystemname",
    "userid",
    "username",
    "submitdate",
    "pagesize",
    "currentpage",
    "totalrecord",
    "provincecode",
    "environmentname",
    "extendparamcollection"
})
public class MsgHeader {

    @XmlElement(name = "SOURCE_SYSTEM_ID", required = true)
    protected String sourcesystemid;
    @XmlElement(name = "SOURCE_SYSTEM_NAME", required = true)
    protected String sourcesystemname;
    @XmlElement(name = "USER_ID", required = true)
    protected String userid;
    @XmlElement(name = "USER_NAME", required = true)
    protected String username;
    @XmlElement(name = "SUBMIT_DATE", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submitdate;
    @XmlElement(name = "PAGE_SIZE", required = true, defaultValue = "10", nillable = true)
    protected BigDecimal pagesize;
    @XmlElement(name = "CURRENT_PAGE", required = true, defaultValue = "1", nillable = true)
    protected BigDecimal currentpage;
    @XmlElement(name = "TOTAL_RECORD", required = true, nillable = true)
    protected BigDecimal totalrecord;
    @XmlElement(name = "PROVINCE_CODE", required = true, nillable = true)
    protected String provincecode;
    @XmlElement(name = "ENVIRONMENT_NAME", required = true, nillable = true)
    protected String environmentname;
    @XmlElement(name = "EXTEND_PARAM_COLLECTION", required = true)
    protected ExtendParamCollection extendparamcollection;

    /**
     * 获取sourcesystemid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOURCESYSTEMID() {
        return sourcesystemid;
    }

    /**
     * 设置sourcesystemid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOURCESYSTEMID(String value) {
        this.sourcesystemid = value;
    }

    /**
     * 获取sourcesystemname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOURCESYSTEMNAME() {
        return sourcesystemname;
    }

    /**
     * 设置sourcesystemname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOURCESYSTEMNAME(String value) {
        this.sourcesystemname = value;
    }

    /**
     * 获取userid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERID() {
        return userid;
    }

    /**
     * 设置userid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERID(String value) {
        this.userid = value;
    }

    /**
     * 获取username属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERNAME() {
        return username;
    }

    /**
     * 设置username属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERNAME(String value) {
        this.username = value;
    }

    /**
     * 获取submitdate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSUBMITDATE() {
        return submitdate;
    }

    /**
     * 设置submitdate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSUBMITDATE(XMLGregorianCalendar value) {
        this.submitdate = value;
    }

    /**
     * 获取pagesize属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPAGESIZE() {
        return pagesize;
    }

    /**
     * 设置pagesize属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPAGESIZE(BigDecimal value) {
        this.pagesize = value;
    }

    /**
     * 获取currentpage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCURRENTPAGE() {
        return currentpage;
    }

    /**
     * 设置currentpage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCURRENTPAGE(BigDecimal value) {
        this.currentpage = value;
    }

    /**
     * 获取totalrecord属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTOTALRECORD() {
        return totalrecord;
    }

    /**
     * 设置totalrecord属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTOTALRECORD(BigDecimal value) {
        this.totalrecord = value;
    }

    /**
     * 获取provincecode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROVINCECODE() {
        return provincecode;
    }

    /**
     * 设置provincecode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROVINCECODE(String value) {
        this.provincecode = value;
    }

    /**
     * 获取environmentname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENVIRONMENTNAME() {
        return environmentname;
    }

    /**
     * 设置environmentname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENVIRONMENTNAME(String value) {
        this.environmentname = value;
    }

    /**
     * 获取extendparamcollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ExtendParamCollection }
     *     
     */
    public ExtendParamCollection getEXTENDPARAMCOLLECTION() {
        return extendparamcollection;
    }

    /**
     * 设置extendparamcollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendParamCollection }
     *     
     */
    public void setEXTENDPARAMCOLLECTION(ExtendParamCollection value) {
        this.extendparamcollection = value;
    }

}
