package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import java.util.ArrayList;
import java.util.List;

public class Variables {

	private String name ;
	private List<Variable> variableDefined = new ArrayList<Variable>();
	 
	public List<Variable> getVariableDefined() {
		return variableDefined;
	}
	public void setVariableDefined(List<Variable> variableDefined) {
		this.variableDefined = variableDefined;
	}
	public void addVariableDefined(Variable variable) {
		variableDefined.add(variable);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
