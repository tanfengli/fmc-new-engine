package com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor;

import java.util.HashMap;
import java.util.Map;

public class ActionDescriptor {
	/*
	 * 操作服务类型，spring或class
	 */
	private String type;
	
	/*
	 * 操作服名称(spring的服务名，或class的全路径
	 */
	private String value;
	
	/*
	 * 服务或类引用的参数集合
	 */
	private Map<String, String> args = new HashMap<String, String>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// public NodeService getNodeService();

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Map<String, String> getArgs() {
		return args;
	}
	
	public void setArgs(Map<String,String> args) {
		this.args = args ;
	}
}
