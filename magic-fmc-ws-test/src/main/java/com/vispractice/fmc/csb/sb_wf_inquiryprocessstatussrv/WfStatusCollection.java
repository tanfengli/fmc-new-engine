
package com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Wf_Status_Collection complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Wf_Status_Collection"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="WF_STATUS_ITEM" type="{http://csb.fmc.vispractice.com/SB_WF_InquiryProcessStatusSrv}Wf_Status_Item" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wf_Status_Collection", propOrder = {
    "wfstatusitem"
})
public class WfStatusCollection {

    @XmlElement(name = "WF_STATUS_ITEM")
    protected List<WfStatusItem> wfstatusitem;

    /**
     * Gets the value of the wfstatusitem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wfstatusitem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWFSTATUSITEM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WfStatusItem }
     * 
     * 
     */
    public List<WfStatusItem> getWFSTATUSITEM() {
        if (wfstatusitem == null) {
            wfstatusitem = new ArrayList<WfStatusItem>();
        }
        return this.wfstatusitem;
    }

}
