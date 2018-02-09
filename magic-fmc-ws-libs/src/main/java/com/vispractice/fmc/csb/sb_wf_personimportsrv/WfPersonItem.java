//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_personimportsrv;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_Person_Item complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Person_Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_PERSON_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_PERSON_LOGIN_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_PERSON_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_IMPORT_TYPE" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_PERSON_ORG_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_PERSON_ORDER" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_PERSON_IS_AVAILABLE" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_PERSON_IS_BUSINESS" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_PERSON_EMAIL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_PERSON_MOBILE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_PERSON_PHONE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_PERSON_MEMO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_PERSON_POST_COLLECTION" type="{http://csb.fmc.vispractice.com/SB_WF_PersonImportSrv}Wf_Person_Post_Collection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Person_Item", propOrder = {
    "wfpersonno",
    "wfpersonloginname",
    "wfpersonname",
    "wfimporttype",
    "wfpersonorgno",
    "wfpersonorder",
    "wfpersonisavailable",
    "wfpersonisbusiness",
    "wfpersonemail",
    "wfpersonmobile",
    "wfpersonphone",
    "wfpersonmemo",
    "wfpersonpostcollection"
})
public class WfPersonItem {

    @XmlElement(name = "WF_PERSON_NO", required = true, nillable = true)
    protected String wfpersonno;
    @XmlElement(name = "WF_PERSON_LOGIN_NAME", required = true, nillable = true)
    protected String wfpersonloginname;
    @XmlElement(name = "WF_PERSON_NAME", required = true, nillable = true)
    protected String wfpersonname;
    @XmlElement(name = "WF_IMPORT_TYPE", required = true, nillable = true)
    protected BigInteger wfimporttype;
    @XmlElement(name = "WF_PERSON_ORG_NO", required = true, nillable = true)
    protected String wfpersonorgno;
    @XmlElement(name = "WF_PERSON_ORDER", required = true, nillable = true)
    protected BigInteger wfpersonorder;
    @XmlElement(name = "WF_PERSON_IS_AVAILABLE", required = true, nillable = true)
    protected BigInteger wfpersonisavailable;
    @XmlElement(name = "WF_PERSON_IS_BUSINESS", required = true, nillable = true)
    protected BigInteger wfpersonisbusiness;
    @XmlElement(name = "WF_PERSON_EMAIL", required = true, nillable = true)
    protected String wfpersonemail;
    @XmlElement(name = "WF_PERSON_MOBILE", required = true, nillable = true)
    protected String wfpersonmobile;
    @XmlElement(name = "WF_PERSON_PHONE", required = true, nillable = true)
    protected String wfpersonphone;
    @XmlElement(name = "WF_PERSON_MEMO", required = true, nillable = true)
    protected String wfpersonmemo;
    @XmlElement(name = "WF_PERSON_POST_COLLECTION", required = true)
    protected WfPersonPostCollection wfpersonpostcollection;

    /**
     * 获取wfpersonno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPERSONNO() {
        return wfpersonno;
    }

    /**
     * 设置wfpersonno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPERSONNO(String value) {
        this.wfpersonno = value;
    }

    /**
     * 获取wfpersonloginname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPERSONLOGINNAME() {
        return wfpersonloginname;
    }

    /**
     * 设置wfpersonloginname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPERSONLOGINNAME(String value) {
        this.wfpersonloginname = value;
    }

    /**
     * 获取wfpersonname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPERSONNAME() {
        return wfpersonname;
    }

    /**
     * 设置wfpersonname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPERSONNAME(String value) {
        this.wfpersonname = value;
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
     * 获取wfpersonorgno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPERSONORGNO() {
        return wfpersonorgno;
    }

    /**
     * 设置wfpersonorgno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPERSONORGNO(String value) {
        this.wfpersonorgno = value;
    }

    /**
     * 获取wfpersonorder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFPERSONORDER() {
        return wfpersonorder;
    }

    /**
     * 设置wfpersonorder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFPERSONORDER(BigInteger value) {
        this.wfpersonorder = value;
    }

    /**
     * 获取wfpersonisavailable属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFPERSONISAVAILABLE() {
        return wfpersonisavailable;
    }

    /**
     * 设置wfpersonisavailable属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFPERSONISAVAILABLE(BigInteger value) {
        this.wfpersonisavailable = value;
    }

    /**
     * 获取wfpersonisbusiness属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFPERSONISBUSINESS() {
        return wfpersonisbusiness;
    }

    /**
     * 设置wfpersonisbusiness属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFPERSONISBUSINESS(BigInteger value) {
        this.wfpersonisbusiness = value;
    }

    /**
     * 获取wfpersonemail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPERSONEMAIL() {
        return wfpersonemail;
    }

    /**
     * 设置wfpersonemail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPERSONEMAIL(String value) {
        this.wfpersonemail = value;
    }

    /**
     * 获取wfpersonmobile属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPERSONMOBILE() {
        return wfpersonmobile;
    }

    /**
     * 设置wfpersonmobile属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPERSONMOBILE(String value) {
        this.wfpersonmobile = value;
    }

    /**
     * 获取wfpersonphone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPERSONPHONE() {
        return wfpersonphone;
    }

    /**
     * 设置wfpersonphone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPERSONPHONE(String value) {
        this.wfpersonphone = value;
    }

    /**
     * 获取wfpersonmemo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFPERSONMEMO() {
        return wfpersonmemo;
    }

    /**
     * 设置wfpersonmemo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFPERSONMEMO(String value) {
        this.wfpersonmemo = value;
    }

    /**
     * 获取wfpersonpostcollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WfPersonPostCollection }
     *     
     */
    public WfPersonPostCollection getWFPERSONPOSTCOLLECTION() {
        return wfpersonpostcollection;
    }

    /**
     * 设置wfpersonpostcollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WfPersonPostCollection }
     *     
     */
    public void setWFPERSONPOSTCOLLECTION(WfPersonPostCollection value) {
        this.wfpersonpostcollection = value;
    }

}
