
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
 * &lt;complexType name="Wf_Op_Item"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WF_OP_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_OP_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
