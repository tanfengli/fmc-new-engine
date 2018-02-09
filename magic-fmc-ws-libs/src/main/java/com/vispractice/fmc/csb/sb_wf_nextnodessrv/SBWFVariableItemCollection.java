//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2017.12.19 时间 03:11:48 PM CST 
//


package com.vispractice.fmc.csb.sb_wf_nextnodessrv;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SB_WF_Variable_Item_Collection complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="SB_WF_Variable_Item_Collection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WF_OP_ITEM" type="{http://csb.fmc.vispractice.com/SB_WF_NextNodesSrv}SB_WF_Variable_Item" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SB_WF_Variable_Item_Collection", propOrder = {
    "wfopitem"
})
public class SBWFVariableItemCollection {

    @XmlElement(name = "WF_OP_ITEM")
    protected List<SBWFVariableItem> wfopitem;

    /**
     * Gets the value of the wfopitem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wfopitem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWFOPITEM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SBWFVariableItem }
     * 
     * 
     */
    public List<SBWFVariableItem> getWFOPITEM() {
        if (wfopitem == null) {
            wfopitem = new ArrayList<SBWFVariableItem>();
        }
        return this.wfopitem;
    }

}
