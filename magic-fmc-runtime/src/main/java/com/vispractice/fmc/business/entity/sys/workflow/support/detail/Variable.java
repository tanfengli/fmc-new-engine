package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OAConstant;

public class Variable implements OAConstant{

	private String name;

	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
