package com.vispractice.fmc.webservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSonUtils {

	public static String jsonToString(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		
		String result = "{}";
		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return result;
	}
}
