package com.vispractice.fmc.business.entity.sys.workflow.beans.detail;

public interface ILineDetail {
	public static final int POSITION_BOTTOM = 3;

	public static final int POSITION_LEFT = 4;

	public static final int POSITION_RIGHT = 2;

	public static final int POSITION_TOP = 1;

	public abstract String getCondition();

	public abstract String getDisCondition();

	public abstract String getEndNodeId();

	public abstract int getEndPosition();

	public abstract String getId();

	public abstract String getName();

	public abstract String getPoints();

	public abstract int getPriority();

	public abstract String getStartNodeId();

	public abstract int getStartPosition();

	public abstract INodeDetail loadEndNode();

	public abstract INodeDetail loadStartNode();

	public abstract void reset();

	public abstract void setCondition(String condition);

	public abstract void setDisCondition(String disCondition);

	public abstract void setEndNodeId(String endNodeId);

	public abstract void setEndPosition(int endPosition);

	public abstract void setId(String id);

	public abstract void setName(String name);

	public abstract void setPoints(String points);

	public abstract void setPriority(int priority);

	public abstract void setProcess(IProcessDetail process);

	public abstract void setStartNodeId(String startNodeId);

	public abstract void setStartPosition(int startPosition);
}