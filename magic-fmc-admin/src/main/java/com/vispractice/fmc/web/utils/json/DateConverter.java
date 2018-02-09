package com.vispractice.fmc.web.utils.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 
 * 名  称：DateConverter
 * 描  述：全局日期转换器（前台页面日期类型到后台Date的映射）
 * 完成日期：2017年11月2日 下午4:17:36
 * 编码作者："LaiJiashen"
 */
@Component
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar();
		TimeZone timeZone = calendar.getTimeZone();
        sdf.setTimeZone(timeZone);
        Date date = null;
        try {
            date = sdf.parse((String) source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
	}
	
	

}
