package com.vispractice.fmc.webservice.util;

import java.util.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 编  号：
 * 名  称：TypeTranUtil
 * 描  述：数据格式转化工具类
 * 完成日期：2017年5月18日 上午10:30:46
 * 编码作者："LaiJiashen"
 */
public class TypeTranUtil {
	/**
	 * 实现流程:从XMLGregorianCalendar转换成Timestamp<br/>
	 * @Title: xMLGregorianCalendarToTimestamp 
	 * @param obj
	 * @return
	 */
	public static Timestamp xMLGregorianCalendarToTimestamp(XMLGregorianCalendar obj){
		
		Timestamp timestamp = new Timestamp(obj.toGregorianCalendar().getTimeInMillis());
		
		return timestamp;
	}
	
	/**
	 * 实现流程:从Timestamp转换成XMLGregorianCalendar<br/>
	 * @Title: timestampToXMLGregorianCalendar 
	 * @param obj
	 * @return 
	 */
	public static XMLGregorianCalendar timestampToXMLGregorianCalendar(Timestamp obj){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(obj.getTime());
		
		try {
			XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			return cal;
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * 实现流程:XMLGregorianCalendar转化为  java.util.Date<br/>
	 * @Title: xmlToDate 
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar dateToXML(Date date){  
	    GregorianCalendar cal = new GregorianCalendar();  
        cal.setTime(date);  
        XMLGregorianCalendar gc = null;  
        try {  
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);  
        } catch (Exception e) {  
             e.printStackTrace();  
        }  
        
	    return gc;  
	}  
	  
	/**
	 * 
	 * 实现流程: java.sql.Date转化为  XMLGregorianCalendar<br/>
	 * @Title: DateToXML 
	 * @param gc
	 * @return
	 */
	public static Date xmlToDate(XMLGregorianCalendar gc){  
	    GregorianCalendar ca = gc.toGregorianCalendar();  
	    
	    return new Date(ca.getTime().getTime());  
	}  
}
