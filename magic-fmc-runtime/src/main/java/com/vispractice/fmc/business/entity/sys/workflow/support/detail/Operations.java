package com.vispractice.fmc.business.entity.sys.workflow.support.detail;

import java.util.ArrayList;
import java.util.List;

public class Operations {
	private String refId;

	private List<Operation> creatorOperations = new ArrayList<Operation>();

	private List<Operation> handlerOperations = new ArrayList<Operation>();

	public List<Operation> getCreatorOperations() {
		return creatorOperations;
	}

	public void addCreatorOperation(Operation operation) {
		creatorOperations.add(operation);
	}

	public void setCreatorOperations(List<Operation> creatorOperations) {
		this.creatorOperations = creatorOperations;
	}

	public List<Operation> getHandlerOperations() {
		return handlerOperations;
	}

	public void addHandlerOperation(Operation operation) {
		handlerOperations.add(operation);
	}

	public void setHandlerOperations(List<Operation> handlerOperations) {
		this.handlerOperations = handlerOperations;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}
}
