
package com.vispractice.fmc.csb.sb_wf_inquirytasksrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Wf_Op_Collection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Wf_Op_Collection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_OP_ITEM" type="{http://csb.fmc.vispractice.com/ExtendParamCollection}Wf_Op_Item" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Op_Collection", propOrder = {
    "wfopitem"
})
public class WfOpCollection {

    @XmlElement(name = "WF_OP_ITEM")
    protected List<WfOpItem> wfopitem;

    /**
     * Gets the value of the wfopitem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wfopitem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWFOPITEM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WfOpItem }
     * 
     * 
     */
    public List<WfOpItem> getWFOPITEM() {
        if (wfopitem == null) {
            wfopitem = new ArrayList<WfOpItem>();
        }
        return this.wfopitem;
    }

}
