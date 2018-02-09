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
import com.vispractice.fmc.csb.msgheader.MsgHeader;


/**
 * <p>SB_WF_PersonImportSrvRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_PersonImportSrvRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MsgHeader" type="{http://csb.fmc.vispractice.com/MsgHeader}MsgHeader"/>
 *         &lt;element name="WF_PERSON_COLLECTION" type="{http://csb.fmc.vispractice.com/SB_WF_PersonImportSrv}Wf_Person_Collection"/>
 *         &lt;element name="WF_IS_ROLLBACK_ALL" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_PersonImportSrvRequest", propOrder = {
    "msgHeader",
    "wfpersoncollection",
    "wfisrollbackall"
})
public class SBWFPersonImportSrvRequest {

    @XmlElement(name = "MsgHeader", required = true)
    protected MsgHeader msgHeader;
    @XmlElement(name = "WF_PERSON_COLLECTION", required = true)
    protected WfPersonCollection wfpersoncollection;
    @XmlElement(name = "WF_IS_ROLLBACK_ALL", required = true, nillable = true)
    protected BigInteger wfisrollbackall;

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
     * 获取wfpersoncollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link WfPersonCollection }
     *     
     */
    public WfPersonCollection getWFPERSONCOLLECTION() {
        return wfpersoncollection;
    }

    /**
     * 设置wfpersoncollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link WfPersonCollection }
     *     
     */
    public void setWFPERSONCOLLECTION(WfPersonCollection value) {
        this.wfpersoncollection = value;
    }

    /**
     * 获取wfisrollbackall属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWFISROLLBACKALL() {
        return wfisrollbackall;
    }

    /**
     * 设置wfisrollbackall属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWFISROLLBACKALL(BigInteger value) {
        this.wfisrollbackall = value;
    }

}
