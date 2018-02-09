
package com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://csb.fmc.vispractice.com/SB_WF_InquiryProcessStatusSrv}SB_WF_InquiryProcessStatusSrvResponse" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "_return"
})
@XmlRootElement(name = "processResponse")
public class ProcessResponse {

    @XmlElement(name = "return")
    protected SBWFInquiryProcessStatusSrvResponse _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SBWFInquiryProcessStatusSrvResponse }
     *     
     */
    public SBWFInquiryProcessStatusSrvResponse getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SBWFInquiryProcessStatusSrvResponse }
     *     
     */
    public void setReturn(SBWFInquiryProcessStatusSrvResponse value) {
        this._return = value;
    }

}
