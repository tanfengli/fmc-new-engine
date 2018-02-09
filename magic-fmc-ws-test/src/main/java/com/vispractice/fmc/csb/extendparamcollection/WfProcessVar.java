
package com.vispractice.fmc.csb.extendparamcollection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_Process_Var complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Process_Var"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WF_VAR_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="WF_VAR_VALUE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Process_Var", propOrder = {
    "wfvarname",
    "wfvarvalue"
})
public class WfProcessVar {

    @XmlElement(name = "WF_VAR_NAME", required = true, nillable = true)
    protected String wfvarname;
    @XmlElement(name = "WF_VAR_VALUE", required = true, nillable = true)
    protected String wfvarvalue;

    /**
     * 获取wfvarname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFVARNAME() {
        return wfvarname;
    }

    /**
     * 设置wfvarname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFVARNAME(String value) {
        this.wfvarname = value;
    }

    /**
     * 获取wfvarvalue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFVARVALUE() {
        return wfvarvalue;
    }

    /**
     * 设置wfvarvalue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFVARVALUE(String value) {
        this.wfvarvalue = value;
    }

}
