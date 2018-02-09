
package com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.vispractice.fmc.csb.msgheader.MsgHeader;


/**
 * <p>SB_WF_InquiryProcessStatusSrvRequest complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_InquiryProcessStatusSrvRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MsgHeader" type="{http://csb.fmc.vispractice.com/MsgHeader}MsgHeader"/&gt;
 *         &lt;element name="WF_INSTANCE_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_InquiryProcessStatusSrvRequest", propOrder = {
    "msgHeader",
    "wfinstanceid"
})
public class SBWFInquiryProcessStatusSrvRequest {

    @XmlElement(name = "MsgHeader", required = true)
    protected MsgHeader msgHeader;
    @XmlElement(name = "WF_INSTANCE_ID", required = true, nillable = true)
    protected String wfinstanceid;

    /**
     * 获取msgHeader属性的值。
     * 
     * @return
     *     possible object is
     *     {@link MsgHeader }
     *     
     */
    public MsgHeader getMsgHeader() {
        return msgHeader;
    }

    /**
     * 设置msgHeader属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link MsgHeader }
     *     
     */
    public void setMsgHeader(MsgHeader value) {
        this.msgHeader = value;
    }

    /**
     * 获取wfinstanceid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWFINSTANCEID() {
        return wfinstanceid;
    }

    /**
     * 设置wfinstanceid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWFINSTANCEID(String value) {
        this.wfinstanceid = value;
    }

}
