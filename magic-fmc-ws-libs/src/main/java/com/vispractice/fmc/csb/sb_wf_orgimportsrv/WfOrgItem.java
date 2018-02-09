//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_orgimportsrv;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_Org_Item complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Org_Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_ORG_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ORG_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ORG_TYPE" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_IMPORT_TYPE" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_ORG_PARENT_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_ORG_ORDER" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_ORG_IS_AVAILABLE" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_ORG_IS_BUSINESS" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_ORG_MEMO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Org_Item", propOrder = {
    "wforgno",
    "wforgname",
    "wforgtype",
    "wfimporttype",
    "wforgparentno",
    "wforgorder",
    "wforgisavailable",
    "wforgisbusiness",
    "wforgmemo"
})
public class WfOrgItem {

    @XmlElement(name = "WF_ORG_NO", required = true, nillable = true)
    protected String wforgno;
    @XmlElement(name = "WF_ORG_NAME", required = true, nillable = true)
    protected String wforgname;
    @XmlElement(name = "WF_ORG_TYPE", required = true, nillable = true)
    protected BigInteger wforgtype;
    @XmlElement(name = "WF_IMPORT_TYPE", required = true, nillable = true)
    protected BigInteger wfimporttype;
    @XmlElement(name = "WF_ORG_PARENT_NO", required = true, nillable = true)
    protected String wforgparentno;
    @XmlElement(name = "WF_ORG_ORDER", required = true, nillable = true)
    protected BigInteger wforgorder;
    @XmlElement(name = "WF_ORG_IS_AVAILABLE", required = true, nillable = true)
    protected BigInteger wforgisavailable;
    @XmlElement(name = "WF_ORG_IS_BUSINESS", required = true, nillable = true)
    protected BigInteger wforgisbusiness;
    @XmlElement(name = "WF_ORG_MEMO", required = true, nillable = true)
    protected String wforgmemo;

    /**
     * 获取wforgno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFORGNO() {
        return wforgno;
    }

    /**
     * 设置wforgno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFORGNO(String value) {
        this.wforgno = value;
    }

    /**
     * 获取wforgname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFORGNAME() {
        return wforgname;
    }

    /**
     * 设置wforgname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFORGNAME(String value) {
        this.wforgname = value;
    }

    /**
     * 获取wforgtype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFORGTYPE() {
        return wforgtype;
    }

    /**
     * 设置wforgtype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFORGTYPE(BigInteger value) {
        this.wforgtype = value;
    }

    /**
     * 获取wfimporttype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFIMPORTTYPE() {
        return wfimporttype;
    }

    /**
     * 设置wfimporttype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFIMPORTTYPE(BigInteger value) {
        this.wfimporttype = value;
    }

    /**
     * 获取wforgparentno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFORGPARENTNO() {
        return wforgparentno;
    }

    /**
     * 设置wforgparentno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFORGPARENTNO(String value) {
        this.wforgparentno = value;
    }

    /**
     * 获取wforgorder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFORGORDER() {
        return wforgorder;
    }

    /**
     * 设置wforgorder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFORGORDER(BigInteger value) {
        this.wforgorder = value;
    }

    /**
     * 获取wforgisavailable属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFORGISAVAILABLE() {
        return wforgisavailable;
    }

    /**
     * 设置wforgisavailable属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFORGISAVAILABLE(BigInteger value) {
        this.wforgisavailable = value;
    }

    /**
     * 获取wforgisbusiness属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFORGISBUSINESS() {
        return wforgisbusiness;
    }

    /**
     * 设置wforgisbusiness属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFORGISBUSINESS(BigInteger value) {
        this.wforgisbusiness = value;
    }

    /**
     * 获取wforgmemo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFORGMEMO() {
        return wforgmemo;
    }

    /**
     * 设置wforgmemo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFORGMEMO(String value) {
        this.wforgmemo = value;
    }

}
