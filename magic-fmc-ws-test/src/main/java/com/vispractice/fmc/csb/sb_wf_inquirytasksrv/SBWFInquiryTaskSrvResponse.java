
package com.vispractice.fmc.csb.sb_wf_inquirytasksrv;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SB_WF_InquiryTaskSrvResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SB_WF_InquiryTaskSrvResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SERVICE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SERVICE_MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TOTAL_RECORD" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="TOTAL_PAGE" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PAGE_SIZE" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CURRENT_PAGE" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="WF_PROCESS_TASK_COLLECTION" type="{http://csb.fmc.vispractice.com/ExtendParamCollection}Wf_Process_Task_Collection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_InquiryTaskSrvResponse", namespace = "http://csb.fmc.vispractice.com/SB_WF_InquiryTaskSrv", propOrder = {
    "serviceflag",
    "servicemessage",
    "totalrecord",
    "totalpage",
    "pagesize",
    "currentpage",
    "wfprocesstaskcollection"
})
public class SBWFInquiryTaskSrvResponse {

    @XmlElement(name = "SERVICE_FLAG", required = true, nillable = true)
    protected String serviceflag;
    @XmlElement(name = "SERVICE_MESSAGE", required = true, nillable = true)
    protected String servicemessage;
    @XmlElement(name = "TOTAL_RECORD", required = true, nillable = true)
    protected BigDecimal totalrecord;
    @XmlElement(name = "TOTAL_PAGE", required = true, nillable = true)
    protected BigDecimal totalpage;
    @XmlElement(name = "PAGE_SIZE", required = true, nillable = true)
    protected BigDecimal pagesize;
    @XmlElement(name = "CURRENT_PAGE", required = true, nillable = true)
    protected BigDecimal currentpage;
    @XmlElement(name = "WF_PROCESS_TASK_COLLECTION", required = true)
    protected WfProcessTaskCollection wfprocesstaskcollection;

    /**
     * Gets the value of the serviceflag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICEFLAG() {
        return serviceflag;
    }

    /**
     * Sets the value of the serviceflag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICEFLAG(String value) {
        this.serviceflag = value;
    }

    /**
     * Gets the value of the servicemessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSERVICEMESSAGE() {
        return servicemessage;
    }

    /**
     * Sets the value of the servicemessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSERVICEMESSAGE(String value) {
        this.servicemessage = value;
    }

    /**
     * Gets the value of the totalrecord property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTOTALRECORD() {
        return totalrecord;
    }

    /**
     * Sets the value of the totalrecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTOTALRECORD(BigDecimal value) {
        this.totalrecord = value;
    }

    /**
     * Gets the value of the totalpage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTOTALPAGE() {
        return totalpage;
    }

    /**
     * Sets the value of the totalpage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTOTALPAGE(BigDecimal value) {
        this.totalpage = value;
    }

    /**
     * Gets the value of the pagesize property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPAGESIZE() {
        return pagesize;
    }

    /**
     * Sets the value of the pagesize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPAGESIZE(BigDecimal value) {
        this.pagesize = value;
    }

    /**
     * Gets the value of the currentpage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCURRENTPAGE() {
        return currentpage;
    }

    /**
     * Sets the value of the currentpage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCURRENTPAGE(BigDecimal value) {
        this.currentpage = value;
    }

    /**
     * Gets the value of the wfprocesstaskcollection property.
     * 
     * @return
     *     possible object is
     *     {@link WfProcessTaskCollection }
     *     
     */
    public WfProcessTaskCollection getWFPROCESSTASKCOLLECTION() {
        return wfprocesstaskcollection;
    }

    /**
     * Sets the value of the wfprocesstaskcollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link WfProcessTaskCollection }
     *     
     */
    public void setWFPROCESSTASKCOLLECTION(WfProcessTaskCollection value) {
        this.wfprocesstaskcollection = value;
    }

}
