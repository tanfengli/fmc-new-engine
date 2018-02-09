
package com.vispractice.fmc.csb.msgheader;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Extend_Param_Collection complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Extend_Param_Collection"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="EXTEND_PARAM" type="{http://csb.fmc.vispractice.com/MsgHeader}Extend_Param" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Extend_Param_Collection", propOrder = {
    "extendparam"
})
public class ExtendParamCollection {

    @XmlElement(name = "EXTEND_PARAM")
    protected List<ExtendParam> extendparam;

    /**
     * Gets the value of the extendparam property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extendparam property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEXTENDPARAM().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtendParam }
     * 
     * 
     */
    public List<ExtendParam> getEXTENDPARAM() {
        if (extendparam == null) {
            extendparam = new ArrayList<ExtendParam>();
        }
        return this.extendparam;
    }

}
