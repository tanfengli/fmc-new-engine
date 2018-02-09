//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_postimportsrv;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_Post_Item complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Post_Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_POST_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_POST_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_POST_ORG_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_IMPORT_TYPE" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_POST_ORDER" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_POST_IS_AVAILABLE" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_POST_IS_BUSINESS" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_POST_MEMO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_POST_PERSON_COLLECTION" type="{http://csb.fmc.vispractice.com/SB_WF_PostImportSrv}Wf_Post_Person_Collection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Post_Item", propOrder = {
    "wfpostno",
    "wfpostname",
    "wfpostorgno",
    "wfimporttype",
    "wfpostorder",
    "wfpostisavailable",
    "wfpostisbusiness",
    "wfpostmemo",
    "wfpostpersoncollection"
})
public class WfPostItem {

    @XmlElement(name = "WF_POST_NO", required = true, nillable = true)
    protected String wfpostno;
    @XmlElement(name = "WF_POST_NAME", required = true, nillable = true)
    protected String wfpostname;
    @XmlElement(name = "WF_POST_ORG_NO", required = true, nillable = true)
    protected String wfpostorgno;
    @XmlElement(name = "WF_IMPORT_TYPE", required = true, nillable = true)
    protected BigInteger wfimporttype;
    @XmlElement(name = "WF_POST_ORDER", required = true, nillable = true)
    protected BigInteger wfpostorder;
    @XmlElement(name = "WF_POST_IS_AVAILABLE", required = true, nillable = true)
    protected BigInteger wfpostisavailable;
    @XmlElement(name = "WF_POST_IS_BUSINESS", required = true, nillable = true)
    protected BigInteger wfpostisbusiness;
    @XmlElement(name = "WF_POST_MEMO", required = true, nillable = true)
    protected String wfpostmemo;
    @XmlElement(name = "WF_POST_PERSON_COLLECTION", required = true)
    protected WfPostPersonCollection wfpostpersoncollection;

    /**
     * 获取wfpostno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPOSTNO() {
        return wfpostno;
    }

    /**
     * 设置wfpostno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPOSTNO(String value) {
        this.wfpostno = value;
    }

    /**
     * 获取wfpostname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPOSTNAME() {
        return wfpostname;
    }

    /**
     * 设置wfpostname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPOSTNAME(String value) {
        this.wfpostname = value;
    }

    /**
     * 获取wfpostorgno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPOSTORGNO() {
        return wfpostorgno;
    }

    /**
     * 设置wfpostorgno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPOSTORGNO(String value) {
        this.wfpostorgno = value;
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
     * 获取wfpostorder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFPOSTORDER() {
        return wfpostorder;
    }

    /**
     * 设置wfpostorder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFPOSTORDER(BigInteger value) {
        this.wfpostorder = value;
    }

    /**
     * 获取wfpostisavailable属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFPOSTISAVAILABLE() {
        return wfpostisavailable;
    }

    /**
     * 设置wfpostisavailable属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFPOSTISAVAILABLE(BigInteger value) {
        this.wfpostisavailable = value;
    }

    /**
     * 获取wfpostisbusiness属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFPOSTISBUSINESS() {
        return wfpostisbusiness;
    }

    /**
     * 设置wfpostisbusiness属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFPOSTISBUSINESS(BigInteger value) {
        this.wfpostisbusiness = value;
    }

    /**
     * 获取wfpostmemo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPOSTMEMO() {
        return wfpostmemo;
    }

    /**
     * 设置wfpostmemo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPOSTMEMO(String value) {
        this.wfpostmemo = value;
    }

    /**
     * 获取wfpostpersoncollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WfPostPersonCollection }
     *     
     */
    public WfPostPersonCollection getWFPOSTPERSONCOLLECTION() {
        return wfpostpersoncollection;
    }

    /**
     * 设置wfpostpersoncollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WfPostPersonCollection }
     *     
     */
    public void setWFPOSTPERSONCOLLECTION(WfPostPersonCollection value) {
        this.wfpostpersoncollection = value;
    }

}
