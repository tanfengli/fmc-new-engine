
package com.vispractice.fmc.csb.extendparamcollection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_Op_Item complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡwfopid���Ե�ֵ��
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
     * ����wfopid���Ե�ֵ��
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
     * ��ȡwfopname���Ե�ֵ��
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
     * ����wfopname���Ե�ֵ��
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
