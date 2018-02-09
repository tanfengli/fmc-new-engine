package com.vispractice.fmc.business.services.setting.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
/**
 * 
 * 编  号：
 * 名  称：SysOrgRolePluginService
 * 描  述：SysOrgRolePluginService.java
 * 完成日期：2017年8月9日 下午6:23:49
 * 编码作者："LaiJiashen"
 */

@Service
public class ParamTranService {
	
	
	@SuppressWarnings("rawtypes")
	public Map tran(String parm){
		
		String[] newStr =  parm.split("&");
		Map map = new HashMap();
		
		for(int i=0;i<newStr.length;i++){
			String[] str =  newStr[i].split("=");
			map=tran(map,str);
		}
		return map;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  Map tran(Map map,String[] str){
		switch(str[0]){
			case "type":
				map.put("type", str[1]);
				break;
			case "level":
				map.put("level", str[1]);
				break;
			case "end":
				map.put("end", str[1]);
				break;
			case "includeme":
				map.put("includeme", str[1]);
				break;
		}
		return map;
	}
	
	
	
	
	
	
	
	
}
