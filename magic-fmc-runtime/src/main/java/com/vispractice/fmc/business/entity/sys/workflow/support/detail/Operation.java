package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import com.vispractice.fmc.business.base.constrant.OAConstant;

public class Operation implements OAConstant {
	private String name;

	private int type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
