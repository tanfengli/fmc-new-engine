//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_authorizesrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_Authorize_Extend_Item complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Authorize_Extend_Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FD_BOE_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FD_BUSS_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FD_DEPT_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MIN_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MAX_AMOUNT" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Authorize_Extend_Item", propOrder = {
    "fdboetype",
    "fdbusstype",
    "fddeptid",
    "minamount",
    "maxamount"
})
public class WfAuthorizeExtendItem {

    @XmlElement(name = "FD_BOE_TYPE", required = true, nillable = true)
    protected String fdboetype;
    @XmlElement(name = "FD_BUSS_TYPE", required = true, nillable = true)
    protected String fdbusstype;
    @XmlElement(name = "FD_DEPT_ID", required = true, nillable = true)
    protected String fddeptid;
    @XmlElement(name = "MIN_AMOUNT", required = true, nillable = true)
    protected String minamount;
    @XmlElement(name = "MAX_AMOUNT", required = true, nillable = true)
    protected String maxamount;

    /**
     * 获取fdboetype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFDBOETYPE() {
        return fdboetype;
    }

    /**
     * 设置fdboetype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFDBOETYPE(String value) {
        this.fdboetype = value;
    }

    /**
     * 获取fdbusstype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFDBUSSTYPE() {
        return fdbusstype;
    }

    /**
     * 设置fdbusstype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFDBUSSTYPE(String value) {
        this.fdbusstype = value;
    }

    /**
     * 获取fddeptid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFDDEPTID() {
        return fddeptid;
    }

    /**
     * 设置fddeptid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFDDEPTID(String value) {
        this.fddeptid = value;
    }

    /**
     * 获取minamount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMINAMOUNT() {
        return minamount;
    }

    /**
     * 设置minamount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMINAMOUNT(String value) {
        this.minamount = value;
    }

    /**
     * 获取maxamount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMAXAMOUNT() {
        return maxamount;
    }

    /**
     * 设置maxamount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMAXAMOUNT(String value) {
        this.maxamount = value;
    }

}
