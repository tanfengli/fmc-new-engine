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
 * <p>SB_WF_Node_Item complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_Node_Item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Node_Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Node_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Node_Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Biz_Info_Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SB_WF_CreateOper_Item_Collection" type="{http://csb.fmc.vispractice.com/SB_WF_NextNodesSrv}SB_WF_CreateOper_Item_Collection"/>
 *         &lt;element name="SB_WF_HandlerOper_Item_Collection" type="{http://csb.fmc.vispractice.com/SB_WF_NextNodesSrv}SB_WF_HandlerOper_Item_Collection"/>
 *         &lt;element name="SB_WF_Variable_Item_Collection" type="{http://csb.fmc.vispractice.com/SB_WF_NextNodesSrv}SB_WF_Variable_Item_Collection"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_Node_Item", propOrder = {
    "nodeId",
    "nodeName",
    "nodeType",
    "bizInfoType",
    "sbwfCreateOperItemCollection",
    "sbwfHandlerOperItemCollection",
    "sbwfVariableItemCollection"
})
public class SBWFNodeItem {

    @XmlElement(name = "Node_Id", required = true, nillable = true)
    protected String nodeId;
    @XmlElement(name = "Node_Name", required = true, nillable = true)
    protected String nodeName;
    @XmlElement(name = "Node_Type", required = true, nillable = true)
    protected String nodeType;
    @XmlElement(name = "Biz_Info_Type", required = true, nillable = true)
    protected String bizInfoType;
    @XmlElement(name = "SB_WF_CreateOper_Item_Collection", required = true)
    protected SBWFCreateOperItemCollection sbwfCreateOperItemCollection;
    @XmlElement(name = "SB_WF_HandlerOper_Item_Collection", required = true)
    protected SBWFHandlerOperItemCollection sbwfHandlerOperItemCollection;
    @XmlElement(name = "SB_WF_Variable_Item_Collection", required = true)
    protected SBWFVariableItemCollection sbwfVariableItemCollection;

    /**
     * 获取nodeId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * 设置nodeId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeId(String value) {
        this.nodeId = value;
    }

    /**
     * 获取nodeName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * 设置nodeName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeName(String value) {
        this.nodeName = value;
    }

    /**
     * 获取nodeType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodeType() {
        return nodeType;
    }

    /**
     * 设置nodeType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodeType(String value) {
        this.nodeType = value;
    }

    /**
     * 获取bizInfoType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBizInfoType() {
        return bizInfoType;
    }

    /**
     * 设置bizInfoType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBizInfoType(String value) {
        this.bizInfoType = value;
    }

    /**
     * 获取sbwfCreateOperItemCollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SBWFCreateOperItemCollection }
     *     
     */
    public SBWFCreateOperItemCollection getSBWFCreateOperItemCollection() {
        return sbwfCreateOperItemCollection;
    }

    /**
     * 设置sbwfCreateOperItemCollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SBWFCreateOperItemCollection }
     *     
     */
    public void setSBWFCreateOperItemCollection(SBWFCreateOperItemCollection value) {
        this.sbwfCreateOperItemCollection = value;
    }

    /**
     * 获取sbwfHandlerOperItemCollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SBWFHandlerOperItemCollection }
     *     
     */
    public SBWFHandlerOperItemCollection getSBWFHandlerOperItemCollection() {
        return sbwfHandlerOperItemCollection;
    }

    /**
     * 设置sbwfHandlerOperItemCollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SBWFHandlerOperItemCollection }
     *     
     */
    public void setSBWFHandlerOperItemCollection(SBWFHandlerOperItemCollection value) {
        this.sbwfHandlerOperItemCollection = value;
    }

    /**
     * 获取sbwfVariableItemCollection属性的值。
     * 
     * @return
     *     possible object is
     *     {@link SBWFVariableItemCollection }
     *     
     */
    public SBWFVariableItemCollection getSBWFVariableItemCollection() {
        return sbwfVariableItemCollection;
    }

    /**
     * 设置sbwfVariableItemCollection属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link SBWFVariableItemCollection }
     *     
     */
    public void setSBWFVariableItemCollection(SBWFVariableItemCollection value) {
        this.sbwfVariableItemCollection = value;
    }

}
