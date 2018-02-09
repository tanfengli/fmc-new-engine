//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_approvalhandlersrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_Handler_List complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Handler_List">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_HANDLER_NOS" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_HANDLER_NAMES" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_NODE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Handler_List", propOrder = {
    "wfhandlernos",
    "wfhandlernames",
    "wfnodeid"
})
public class WfHandlerList {

    @XmlElement(name = "WF_HANDLER_NOS", required = true, nillable = true)
    protected String wfhandlernos;
    @XmlElement(name = "WF_HANDLER_NAMES", required = true, nillable = true)
    protected String wfhandlernames;
    @XmlElement(name = "WF_NODE_ID", required = true, nillable = true)
    protected String wfnodeid;

    /**
     * 获取wfhandlernos属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFHANDLERNOS() {
        return wfhandlernos;
    }

    /**
     * 设置wfhandlernos属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFHANDLERNOS(String value) {
        this.wfhandlernos = value;
    }

    /**
     * 获取wfhandlernames属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFHANDLERNAMES() {
        return wfhandlernames;
    }

    /**
     * 设置wfhandlernames属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFHANDLERNAMES(String value) {
        this.wfhandlernames = value;
    }

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

}
