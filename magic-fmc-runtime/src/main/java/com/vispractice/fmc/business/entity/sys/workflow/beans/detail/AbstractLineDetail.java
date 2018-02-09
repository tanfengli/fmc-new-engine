package com.vispractice.fmc.business.entity.sys.workflow.beans.detail;

public abstract class AbstractLineDetail implements ILineDetail {
	protected String condition;

	protected String disCondition;

	protected INodeDetail endNode;

	protected String endNodeId;

	protected int endPosition;

	protected String id;

	protected String name;

	protected String points;

	protected int priority;

	protected IProcessDetail process;

	protected INodeDetail startNode;

	protected String startNodeId;

	protected int startPosition;

	public String getCondition() {
		return condition;
	}

	public String getDisCondition() {
		return disCondition;
	}

	public String getEndNodeId() {
		return endNodeId;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPoints() {
		return points;
	}

	public int getPriority() {
		return priority;
	}

	public String getStartNodeId() {
		return startNodeId;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public INodeDetail loadEndNode() {
		if (endNode == null)
			endNode = process.getNodeById(endNodeId);
		return endNode;
	}

	public INodeDetail loadStartNode() {
		if (startNode == null)
			startNode = process.getNodeById(startNodeId);
		return startNode;
	}

	public void reset() {
		startNode = null;
		endNode = null;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setDisCondition(String disCondition) {
		this.disCondition = disCondition;
	}

	public void setEndNodeId(String endNodeId) {
		this.endNodeId = endNodeId;
	}

	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setProcess(IProcessDetail process) {
		this.process = process;
	}

	public void setStartNodeId(String startNodeId) {
		this.startNodeId = startNodeId;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}
}
