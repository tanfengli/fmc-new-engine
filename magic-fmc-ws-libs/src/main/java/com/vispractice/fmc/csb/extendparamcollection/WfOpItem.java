//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.09.15 时间 04:01:22 PM CST 
//


package com.vispractice.fmc.csb.extendparamcollection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_Op_Item complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Op_Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_OP_ID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_OP_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Op_Item", propOrder = {
    "wfopid",
    "wfopname"
})
public class WfOpItem {

    @XmlElement(name = "WF_OP_ID", required = true, nillable = true)
    protected String wfopid;
    @XmlElement(name = "WF_OP_NAME", required = true, nillable = true)
    protected String wfopname;

    /**
     * 获取wfopid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFOPID() {
        return wfopid;
    }

    /**
     * 设置wfopid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFOPID(String value) {
        this.wfopid = value;
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

}
