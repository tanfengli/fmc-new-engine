
package com.vispractice.fmc.csb.extendparamcollection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_Process_Var complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡwfvarname���Ե�ֵ��
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
     * ����wfvarname���Ե�ֵ��
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
     * ��ȡwfvarvalue���Ե�ֵ��
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
     * ����wfvarvalue���Ե�ֵ��
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
