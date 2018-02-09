
package com.vispractice.fmc.csb.sb_wf_inquirytasksrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Wf_Process_Var complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Wf_Process_Var">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_VAR_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="WF_VAR_VALUE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the wfvarname property.
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
     * Sets the value of the wfvarname property.
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
     * Gets the value of the wfvarvalue property.
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
     * Sets the value of the wfvarvalue property.
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
