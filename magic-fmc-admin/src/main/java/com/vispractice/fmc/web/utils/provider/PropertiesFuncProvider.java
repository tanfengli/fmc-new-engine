package com.vispractice.fmc.web.utils.provider;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfVarInfo;

public class PropertiesFuncProvider {

	protected String className;

	protected ResourceBundle bundle;
	
	
	
	private static final String QUARTZJOB_SOURCE= "quartzjob.quartzJob";
	private static final String QUARTZJOB_CRON_SOURCE= "quartzjob.quartzJobCron";
	

	public PropertiesFuncProvider() {

	}

	// com.vispractice.fmc.sys.workflow.engine.OtherFunction
//	public PropertiesFuncProvider(String baseName) {
//		className = baseName;
//		String[] arr = baseName.split("\\.");
//		String destName = FUNCTION_SOURCE+"."+arr[arr.length-1];
//		try {
//			bundle = ResourceBundle.getBundle(destName);
//		} catch (MissingResourceException e) {
//		}
//	}

//	
//	public static void main(String[] args) {
//		String str = "com.vispractice.fmc.sys.workflow.engine.util.ModelUtil";
//
//		try {
//			// ResourceBundle bundle = ResourceBundle.getBundle(baseName);
//			initSysQuartzJob();
//		} catch (MissingResourceException e) {
//		}
//	}
	
	
	public static List<Map<String,String>> initSysQuartzJob(){
		List<Map<String,String>> jobList = new ArrayList<Map<String,String>>();
		
		ResourceBundle cronBundle = ResourceBundle.getBundle(QUARTZJOB_CRON_SOURCE);
		Enumeration<String> cronNames = cronBundle.getKeys();
		Map<String,String> cronMap = new HashMap<String, String>();
		while (cronNames.hasMoreElements()) {
			 String serviceName = (String) cronNames.nextElement();
			 String value = cronBundle.getString(serviceName);
			 cronMap.put(serviceName, value);
			  
		}
		
		ResourceBundle bundle = ResourceBundle.getBundle(QUARTZJOB_SOURCE);
		Enumeration<String> allName = bundle.getKeys();
		
		
		while (allName.hasMoreElements()) {
	            // 获取每一个名称
	            String serviceName = (String) allName.nextElement();
	            String value = bundle.getString(serviceName);
	            Map<String,String> map = new HashMap<String, String>();
	            String[] arr = serviceName.split("\\.");
	            map.put("serviceName", arr[0]);
	            map.put("methodName", arr[1]);
	            map.put("jobName", value);
	            map.put("cron", cronMap.get(serviceName+".cronexpression"));
	            jobList.add(map);
	    }
		
		return jobList;
	}
}
