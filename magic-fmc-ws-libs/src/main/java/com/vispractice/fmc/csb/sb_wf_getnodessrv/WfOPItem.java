//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_getnodessrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_OP_Item complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_OP_Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_NODE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_NODE_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_NODE_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_BIZ_INFO_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_OP_Item", propOrder = {
    "wfnodeid",
    "wfnodename",
    "wfnodetype",
    "wfbizinfotype"
})
public class WfOPItem {

    @XmlElement(name = "WF_NODE_ID", required = true, nillable = true)
    protected String wfnodeid;
    @XmlElement(name = "WF_NODE_NAME", required = true, nillable = true)
    protected String wfnodename;
    @XmlElement(name = "WF_NODE_TYPE", required = true, nillable = true)
    protected String wfnodetype;
    @XmlElement(name = "WF_BIZ_INFO_TYPE", required = true, nillable = true)
    protected String wfbizinfotype;

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
     * 获取wfnodetype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFNODETYPE() {
        return wfnodetype;
    }

    /**
     * 设置wfnodetype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFNODETYPE(String value) {
        this.wfnodetype = value;
    }

    /**
     * 获取wfbizinfotype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFBIZINFOTYPE() {
        return wfbizinfotype;
    }

    /**
     * 设置wfbizinfotype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFBIZINFOTYPE(String value) {
        this.wfbizinfotype = value;
    }

}
