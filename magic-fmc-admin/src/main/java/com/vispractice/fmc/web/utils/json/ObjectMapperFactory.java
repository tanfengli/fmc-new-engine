package com.vispractice.fmc.web.utils.json;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactory {

	public static ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(myFmt2);
		Calendar calendar = new GregorianCalendar();
		TimeZone timeZone = calendar.getTimeZone();
//		System.out.println(timeZone.getDisplayName());
//		TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
		mapper.setTimeZone(timeZone);
		return mapper;
	}
}
