
package com.vispractice.fmc.csb.msgheader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Extend_Param complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡparamname���Ե�ֵ��
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
     * ����paramname���Ե�ֵ��
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
     * ��ȡparamvalue���Ե�ֵ��
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
     * ����paramvalue���Ե�ֵ��
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
