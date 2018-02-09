
package com.vispractice.fmc.csb.sb_wf_inquirytasksrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Wf_Process_Task_Collection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Wf_Process_Task_Collection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_PROCESS_TASK_ITEM" type="{http://csb.fmc.vispractice.com/ExtendParamCollection}Wf_Process_Task_Item" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Process_Task_Collection", propOrder = {
    "wfprocesstaskitem"
})
public class WfProcessTaskCollection {

    @XmlElement(name = "WF_PROCESS_TASK_ITEM")
    protected List<WfProcessTaskItem> wfprocesstaskitem;

    /**
     * Gets the value of the wfprocesstaskitem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wfprocesstaskitem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWFPROCESSTASKITEM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WfProcessTaskItem }
     * 
     * 
     */
    public List<WfProcessTaskItem> getWFPROCESSTASKITEM() {
        if (wfprocesstaskitem == null) {
            wfprocesstaskitem = new ArrayList<WfProcessTaskItem>();
        }
        return this.wfprocesstaskitem;
    }

}
