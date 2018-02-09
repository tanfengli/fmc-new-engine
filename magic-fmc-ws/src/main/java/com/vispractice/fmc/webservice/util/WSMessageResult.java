package com.vispractice.fmc.webservice.util;

import lombok.Data;

@Data
public class WSMessageResult {
	/**
	 * 处理成功
	 */
	public static final String FLAG_Y = "Y";
	
	/**
	 * 处理失败
	 */
	public static final String FLAG_N = "N";
	
	/**
	 * 消息编码
	 */
	private String code;
	
	/**
	 * 返回值
	 */
	private Object data;
}
