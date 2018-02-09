
package com.vispractice.fmc.csb.sb_wf_inquirytasksrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Wf_Op_Item complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the wfopid property.
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
     * Sets the value of the wfopid property.
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
     * Gets the value of the wfopname property.
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
     * Sets the value of the wfopname property.
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
