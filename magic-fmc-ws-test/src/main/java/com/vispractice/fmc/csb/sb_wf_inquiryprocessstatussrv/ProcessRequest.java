
package com.vispractice.fmc.csb.sb_wf_inquiryprocessstatussrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="request" type="{http://csb.fmc.vispractice.com/SB_WF_InquiryProcessStatusSrv}SB_WF_InquiryProcessStatusSrvRequest" minOccurs="0" form="qualified"/&gt;
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
    "request"
})
@XmlRootElement(name = "processRequest")
public class ProcessRequest {

    protected SBWFInquiryProcessStatusSrvRequest request;

    /**
     * ��ȡrequest���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link SBWFInquiryProcessStatusSrvRequest }
     *     
     */
    public SBWFInquiryProcessStatusSrvRequest getRequest() {
        return request;
    }

    /**
     * ����request���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link SBWFInquiryProcessStatusSrvRequest }
     *     
     */
    public void setRequest(SBWFInquiryProcessStatusSrvRequest value) {
        this.request = value;
    }

}
