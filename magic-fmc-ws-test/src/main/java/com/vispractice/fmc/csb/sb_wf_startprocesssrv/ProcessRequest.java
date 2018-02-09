
package com.vispractice.fmc.csb.sb_wf_startprocesssrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="request" type="{http://csb.fmc.vispractice.com/SB_WF_StartProcessSrv}SB_WF_StartProcessSrvRequest" minOccurs="0" form="qualified"/&gt;
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

    protected SBWFStartProcessSrvRequest request;

    /**
     * 获取request属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SBWFStartProcessSrvRequest }
     *     
     */
    public SBWFStartProcessSrvRequest getRequest() {
        return request;
    }

    /**
     * 设置request属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SBWFStartProcessSrvRequest }
     *     
     */
    public void setRequest(SBWFStartProcessSrvRequest value) {
        this.request = value;
    }

}
