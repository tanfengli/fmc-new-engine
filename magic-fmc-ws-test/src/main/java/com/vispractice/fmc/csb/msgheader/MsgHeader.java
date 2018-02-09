
package com.vispractice.fmc.csb.msgheader;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>MsgHeader complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="MsgHeader"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SOURCE_SYSTEM_ID" type="{http://csb.fmc.vispractice.com/MsgHeader}less_than_30"/&gt;
 *         &lt;element name="SOURCE_SYSTEM_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="USER_ID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="USER_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="SUBMIT_DATE" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="PAGE_SIZE" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="CURRENT_PAGE" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="TOTAL_RECORD" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="PROVINCE_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ENVIRONMENT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="EXTEND_PARAM_COLLECTION" type="{http://csb.fmc.vispractice.com/MsgHeader}Extend_Param_Collection"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MsgHeader", propOrder = {
    "sourcesystemid",
    "sourcesystemname",
    "userid",
    "username",
    "submitdate",
    "pagesize",
    "currentpage",
    "totalrecord",
    "provincecode",
    "environmentname",
    "extendparamcollection"
})
public class MsgHeader {

    @XmlElement(name = "SOURCE_SYSTEM_ID", required = true)
    protected String sourcesystemid;
    @XmlElement(name = "SOURCE_SYSTEM_NAME", required = true)
    protected String sourcesystemname;
    @XmlElement(name = "USER_ID", required = true)
    protected String userid;
    @XmlElement(name = "USER_NAME", required = true)
    protected String username;
    @XmlElement(name = "SUBMIT_DATE", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submitdate;
    @XmlElement(name = "PAGE_SIZE", required = true, defaultValue = "10", nillable = true)
    protected BigDecimal pagesize;
    @XmlElement(name = "CURRENT_PAGE", required = true, defaultValue = "1", nillable = true)
    protected BigDecimal currentpage;
    @XmlElement(name = "TOTAL_RECORD", required = true, nillable = true)
    protected BigDecimal totalrecord;
    @XmlElement(name = "PROVINCE_CODE", required = true, nillable = true)
    protected String provincecode;
    @XmlElement(name = "ENVIRONMENT_NAME", required = true, nillable = true)
    protected String environmentname;
    @XmlElement(name = "EXTEND_PARAM_COLLECTION", required = true)
    protected ExtendParamCollection extendparamcollection;

    /**
     * ��ȡsourcesystemid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOURCESYSTEMID() {
        return sourcesystemid;
    }

    /**
     * ����sourcesystemid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOURCESYSTEMID(String value) {
        this.sourcesystemid = value;
    }

    /**
     * ��ȡsourcesystemname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOURCESYSTEMNAME() {
        return sourcesystemname;
    }

    /**
     * ����sourcesystemname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOURCESYSTEMNAME(String value) {
        this.sourcesystemname = value;
    }

    /**
     * ��ȡuserid���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERID() {
        return userid;
    }

    /**
     * ����userid���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERID(String value) {
        this.userid = value;
    }

    /**
     * ��ȡusername���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUSERNAME() {
        return username;
    }

    /**
     * ����username���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUSERNAME(String value) {
        this.username = value;
    }

    /**
     * ��ȡsubmitdate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSUBMITDATE() {
        return submitdate;
    }

    /**
     * ����submitdate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSUBMITDATE(XMLGregorianCalendar value) {
        this.submitdate = value;
    }

    /**
     * ��ȡpagesize���Ե�ֵ��
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
     * ����pagesize���Ե�ֵ��
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
     * ��ȡcurrentpage���Ե�ֵ��
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
     * ����currentpage���Ե�ֵ��
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
     * ��ȡtotalrecord���Ե�ֵ��
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
     * ����totalrecord���Ե�ֵ��
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
     * ��ȡprovincecode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPROVINCECODE() {
        return provincecode;
    }

    /**
     * ����provincecode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPROVINCECODE(String value) {
        this.provincecode = value;
    }

    /**
     * ��ȡenvironmentname���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getENVIRONMENTNAME() {
        return environmentname;
    }

    /**
     * ����environmentname���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setENVIRONMENTNAME(String value) {
        this.environmentname = value;
    }

    /**
     * ��ȡextendparamcollection���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ExtendParamCollection }
     *     
     */
    public ExtendParamCollection getEXTENDPARAMCOLLECTION() {
        return extendparamcollection;
    }

    /**
     * ����extendparamcollection���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendParamCollection }
     *     
     */
    public void setEXTENDPARAMCOLLECTION(ExtendParamCollection value) {
        this.extendparamcollection = value;
    }

}
