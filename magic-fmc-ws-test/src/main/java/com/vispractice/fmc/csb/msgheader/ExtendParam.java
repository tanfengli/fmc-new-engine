
package com.vispractice.fmc.csb.msgheader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Extend_Param complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Extend_Param"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PARAM_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PARAM_VALUE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Extend_Param", propOrder = {
    "paramname",
    "paramvalue"
})
public class ExtendParam {

    @XmlElement(name = "PARAM_NAME", required = true, nillable = true)
    protected String paramname;
    @XmlElement(name = "PARAM_VALUE", required = true, nillable = true)
    protected String paramvalue;

    /**
     * 获取paramname属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPARAMNAME() {
        return paramname;
    }

    /**
     * 设置paramname属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPARAMNAME(String value) {
        this.paramname = value;
    }

    /**
     * 获取paramvalue属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPARAMVALUE() {
        return paramvalue;
    }

    /**
     * 设置paramvalue属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPARAMVALUE(String value) {
        this.paramvalue = value;
    }

}
