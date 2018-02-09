package com.vispractice.fmc.business.base.utils.function;

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
	
	private static final String FUNCTION_SOURCE= "plugin.function";
	
	private static String function_code = "function";
	private static String name = "name";
	private static String title ="title";
	private static String param = "param";
	
	private static final String QUARTZJOB_SOURCE= "quartzjob.quartzJob";
	private static final String QUARTZJOB_CRON_SOURCE= "quartzjob.quartzJobCron";
	
	

	public PropertiesFuncProvider() {

	}

	// com.vispractice.fmc.sys.workflow.engine.OtherFunction
	public PropertiesFuncProvider(String baseName) {
		className = baseName;
		String[] arr = baseName.split("\\.");
		String destName = FUNCTION_SOURCE+"."+arr[arr.length-1];
		try {
			bundle = ResourceBundle.getBundle(destName);
		} catch (MissingResourceException e) {
		}
	}

	public FunctionScript getFunctionScript(String funcName) throws Exception {
		if (bundle == null) {
			return null;
		}
		String functionScript = null;
		try {
			functionScript = className + "." + bundle.getString(funcName).trim();
		} catch (MissingResourceException e) {
			return null;
		}
		return new FunctionScript(functionScript);
	}

	
	
private static Map<String,List<FunctionModel>> propertiesMap = new HashMap<String, List<FunctionModel>>();
	
	
	public static List<SysWfVarInfo> getFunctionList(String funcName) {
		List<FunctionModel> functionList = propertiesMap.get(funcName);
		if(functionList==null||functionList.size()==0){
			functionList = readProperties(funcName);
		}
		 return change(functionList);
	}
	
	
	private static List<FunctionModel> readProperties(String funcName){
		ResourceBundle bundle = ResourceBundle.getBundle("plugin.function."+funcName);
		Enumeration<String> allName = bundle.getKeys();
		Map<String,FunctionModel> map = new HashMap<String, FunctionModel>();
		List<FunctionModel> list = new ArrayList<FunctionModel>();
		while (allName.hasMoreElements()) {
	            // 获取每一个名称
	            String functionName = (String) allName.nextElement();
	            String functionKey = functionName.substring(0,functionName.lastIndexOf("."));
	            String key = functionName.substring(functionName.lastIndexOf(".")+1);
	            String value = bundle.getString(functionName);
	            
	            FunctionModel model = map.get(functionKey);
	            if(model==null){
	            	model = new FunctionModel();
	            }
	            
	            if(name.equals(key)){
            		model.setName(value);
            	}else if(title.equals(key)){
            		model.setTitle(value);
            	}else if(param.equals(key)){
            		model.setParam(value);
            	}
	            model.setFdId(functionKey);
	            map.put(functionKey, model);
	    }
		
		for(String key :map.keySet()){
			list.add(map.get(key));
		}
//		List<FunctionModel> functionMap  = propertiesMap.get(funcName);
		propertiesMap.put(funcName, list);
		
		return list;
	}
	
	public static List<SysWfVarInfo> change(List<FunctionModel> functionList){
		List<SysWfVarInfo> list = new ArrayList<SysWfVarInfo>();
		for(FunctionModel function:functionList){
			SysWfVarInfo var = new SysWfVarInfo();
			var.setFdId(function.getFdId());
			var.setVarName(function.getName().split("\\.")[1]);
			var.setVarCode(function.getName());
			
			var.setVarType(function_code);
			var.setVarSql(function.getParam());
			list.add(var);
		}
		
		return list;
		
	}
	
	public static void main(String[] args) {
		String str = "com.vispractice.fmc.sys.workflow.engine.util.ModelUtil";

		try {
			// ResourceBundle bundle = ResourceBundle.getBundle(baseName);
			initSysQuartzJob();
		} catch (MissingResourceException e) {
		}
	}
	
	
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
