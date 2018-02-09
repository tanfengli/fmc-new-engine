
package com.vispractice.fmc.csb.sb_wf_inquirytasksrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Wf_Process_Var_Collection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Wf_Process_Var_Collection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_PROCESS_VAR" type="{http://csb.fmc.vispractice.com/ExtendParamCollection}Wf_Process_Var" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Process_Var_Collection", propOrder = {
    "wfprocessvar"
})
public class WfProcessVarCollection {

    @XmlElement(name = "WF_PROCESS_VAR")
    protected List<WfProcessVar> wfprocessvar;

    /**
     * Gets the value of the wfprocessvar property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wfprocessvar property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWFPROCESSVAR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WfProcessVar }
     * 
     * 
     */
    public List<WfProcessVar> getWFPROCESSVAR() {
        if (wfprocessvar == null) {
            wfprocessvar = new ArrayList<WfProcessVar>();
        }
        return this.wfprocessvar;
    }

}
