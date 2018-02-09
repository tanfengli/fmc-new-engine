package com.vispractice.fmc.web.domain.sys;

import java.util.ArrayList;
import java.util.List;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfOperations;

public class SysWfOpeaMainForm {

	private String fdId;
	
	private String fdName;
	
	private String fdNodeType;
	
	private String fdIsDefault;
	
	private List<SysWfOperations> operationsList = new ArrayList<SysWfOperations>();
	
	private List<SysWfOperations> operationsListTwo  = new ArrayList<SysWfOperations>();

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdName() {
		return fdName;
	}

	public void setFdName(String fdName) {
		this.fdName = fdName;
	}

	public String getFdNodeType() {
		return fdNodeType;
	}

	public void setFdNodeType(String fdNodeType) {
		this.fdNodeType = fdNodeType;
	}

	public String getFdIsDefault() {
		return fdIsDefault;
	}

	public void setFdIsDefault(String fdIsDefault) {
		this.fdIsDefault = fdIsDefault;
	}

	public List<SysWfOperations> getOperationsList() {
		return operationsList;
	}

	public void setOperationsList(List<SysWfOperations> operationsList) {
		this.operationsList = operationsList;
	}

	public List<SysWfOperations> getOperationsListTwo() {
		return operationsListTwo;
	}

	public void setOperationsListTwo(List<SysWfOperations> operationsListTwo) {
		this.operationsListTwo = operationsListTwo;
	}
	
	
}
