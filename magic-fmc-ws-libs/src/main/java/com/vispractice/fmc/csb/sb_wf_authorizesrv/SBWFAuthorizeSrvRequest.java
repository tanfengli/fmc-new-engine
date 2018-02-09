//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_authorizesrv;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.vispractice.fmc.csb.msgheader.MsgHeader;


/**
 * <p>SB_WF_AuthorizeSrvRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_AuthorizeSrvRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MsgHeader" type="{http://csb.fmc.vispractice.com/MsgHeader}MsgHeader"/>
 *         &lt;element name="WF_AUTHORIZE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_AUTHORIZE_TYPE" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_AUTHORIZER_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_AUTHORIZED_PERSON_NO" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_START_TIME" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="WF_END_TIME" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="WF_AUTHORIZE_SCOPE_COLLECTION" type="{http://csb.fmc.vispractice.com/SB_WF_AuthorizeSrv}Wf_Authorize_Scope_Collection"/>
 *         &lt;element name="WF_IS_EXPIRE_DELETED" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="WF_AUTHORIZE_EXTEND_COLLECTION" type="{http://csb.fmc.vispractice.com/SB_WF_AuthorizeSrv}Wf_Authorize_Extend_Collection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_AuthorizeSrvRequest", propOrder = {
    "msgHeader",
    "wfauthorizeid",
    "wfauthorizetype",
    "wfauthorizerno",
    "wfauthorizedpersonno",
    "wfstarttime",
    "wfendtime",
    "wfauthorizescopecollection",
    "wfisexpiredeleted",
    "wfauthorizeextendcollection"
})
public class SBWFAuthorizeSrvRequest {

    @XmlElement(name = "MsgHeader", required = true)
    protected MsgHeader msgHeader;
    @XmlElement(name = "WF_AUTHORIZE_ID", required = true, nillable = true)
    protected String wfauthorizeid;
    @XmlElement(name = "WF_AUTHORIZE_TYPE", required = true, nillable = true)
    protected BigInteger wfauthorizetype;
    @XmlElement(name = "WF_AUTHORIZER_NO", required = true, nillable = true)
    protected String wfauthorizerno;
    @XmlElement(name = "WF_AUTHORIZED_PERSON_NO", required = true, nillable = true)
    protected String wfauthorizedpersonno;
    @XmlElement(name = "WF_START_TIME", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar wfstarttime;
    @XmlElement(name = "WF_END_TIME", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar wfendtime;
    @XmlElement(name = "WF_AUTHORIZE_SCOPE_COLLECTION", required = true)
    protected WfAuthorizeScopeCollection wfauthorizescopecollection;
    @XmlElement(name = "WF_IS_EXPIRE_DELETED", required = true, nillable = true)
    protected BigInteger wfisexpiredeleted;
    @XmlElement(name = "WF_AUTHORIZE_EXTEND_COLLECTION", required = true)
    protected WfAuthorizeExtendCollection wfauthorizeextendcollection;

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
     * 获取wfauthorizeid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAUTHORIZEID() {
        return wfauthorizeid;
    }

    /**
     * 设置wfauthorizeid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAUTHORIZEID(String value) {
        this.wfauthorizeid = value;
    }

    /**
     * 获取wfauthorizetype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFAUTHORIZETYPE() {
        return wfauthorizetype;
    }

    /**
     * 设置wfauthorizetype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFAUTHORIZETYPE(BigInteger value) {
        this.wfauthorizetype = value;
    }

    /**
     * 获取wfauthorizerno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAUTHORIZERNO() {
        return wfauthorizerno;
    }

    /**
     * 设置wfauthorizerno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAUTHORIZERNO(String value) {
        this.wfauthorizerno = value;
    }

    /**
     * 获取wfauthorizedpersonno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFAUTHORIZEDPERSONNO() {
        return wfauthorizedpersonno;
    }

    /**
     * 设置wfauthorizedpersonno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFAUTHORIZEDPERSONNO(String value) {
        this.wfauthorizedpersonno = value;
    }

    /**
     * 获取wfstarttime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWFSTARTTIME() {
        return wfstarttime;
    }

    /**
     * 设置wfstarttime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWFSTARTTIME(XMLGregorianCalendar value) {
        this.wfstarttime = value;
    }

    /**
     * 获取wfendtime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWFENDTIME() {
        return wfendtime;
    }

    /**
     * 设置wfendtime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWFENDTIME(XMLGregorianCalendar value) {
        this.wfendtime = value;
    }

    /**
     * 获取wfauthorizescopecollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WfAuthorizeScopeCollection }
     *     
     */
    public WfAuthorizeScopeCollection getWFAUTHORIZESCOPECOLLECTION() {
        return wfauthorizescopecollection;
    }

    /**
     * 设置wfauthorizescopecollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WfAuthorizeScopeCollection }
     *     
     */
    public void setWFAUTHORIZESCOPECOLLECTION(WfAuthorizeScopeCollection value) {
        this.wfauthorizescopecollection = value;
    }

    /**
     * 获取wfisexpiredeleted属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFISEXPIREDELETED() {
        return wfisexpiredeleted;
    }

    /**
     * 设置wfisexpiredeleted属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFISEXPIREDELETED(BigInteger value) {
        this.wfisexpiredeleted = value;
    }

    /**
     * 获取wfauthorizeextendcollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WfAuthorizeExtendCollection }
     *     
     */
    public WfAuthorizeExtendCollection getWFAUTHORIZEEXTENDCOLLECTION() {
        return wfauthorizeextendcollection;
    }

    /**
     * 设置wfauthorizeextendcollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WfAuthorizeExtendCollection }
     *     
     */
    public void setWFAUTHORIZEEXTENDCOLLECTION(WfAuthorizeExtendCollection value) {
        this.wfauthorizeextendcollection = value;
    }

}
