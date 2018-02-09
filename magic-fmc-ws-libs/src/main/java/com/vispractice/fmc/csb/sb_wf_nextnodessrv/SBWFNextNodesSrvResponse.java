//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_nextnodessrv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SB_WF_NextNodesSrvResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_NextNodesSrvResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SERVICE_FLAG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SERVICE_MESSAGE" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SB_WF_LAST_Node_Collection" type="{http://csb.fmc.vispractice.com/SB_WF_NextNodesSrv}SB_WF_LAST_Node_Collection"/>
 *         &lt;element name="SB_WF_NEXT_Node_Collection" type="{http://csb.fmc.vispractice.com/SB_WF_NextNodesSrv}SB_WF_NEXT_Node_Collection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_NextNodesSrvResponse", propOrder = {
    "serviceflag",
    "servicemessage",
    "sbwflastNodeCollection",
    "sbwfnextNodeCollection"
})
public class SBWFNextNodesSrvResponse {

    @XmlElement(name = "SERVICE_FLAG", required = true, nillable = true)
    protected String serviceflag;
    @XmlElement(name = "SERVICE_MESSAGE", required = true, nillable = true)
    protected String servicemessage;
    @XmlElement(name = "SB_WF_LAST_Node_Collection", required = true)
    protected SBWFLASTNodeCollection sbwflastNodeCollection;
    @XmlElement(name = "SB_WF_NEXT_Node_Collection", required = true)
    protected SBWFNEXTNodeCollection sbwfnextNodeCollection;

    /**
     * 获取serviceflag属性的值。
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
     * 设置serviceflag属性的值。
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
     * 获取servicemessage属性的值。
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
     * 设置servicemessage属性的值。
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
     * 获取sbwflastNodeCollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SBWFLASTNodeCollection }
     *     
     */
    public SBWFLASTNodeCollection getSBWFLASTNodeCollection() {
        return sbwflastNodeCollection;
    }

    /**
     * 设置sbwflastNodeCollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SBWFLASTNodeCollection }
     *     
     */
    public void setSBWFLASTNodeCollection(SBWFLASTNodeCollection value) {
        this.sbwflastNodeCollection = value;
    }

    /**
     * 获取sbwfnextNodeCollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SBWFNEXTNodeCollection }
     *     
     */
    public SBWFNEXTNodeCollection getSBWFNEXTNodeCollection() {
        return sbwfnextNodeCollection;
    }

    /**
     * 设置sbwfnextNodeCollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SBWFNEXTNodeCollection }
     *     
     */
    public void setSBWFNEXTNodeCollection(SBWFNEXTNodeCollection value) {
        this.sbwfnextNodeCollection = value;
    }

}
