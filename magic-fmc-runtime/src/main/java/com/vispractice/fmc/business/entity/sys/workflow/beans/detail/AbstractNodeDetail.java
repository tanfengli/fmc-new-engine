package com.vispractice.fmc.business.entity.sys.workflow.beans.detail;

import java.util.ArrayList;
import java.util.List;

import com.vispractice.fmc.business.entity.sys.workflow.beans.descriptor.NodeDescriptor;
import com.vispractice.fmc.business.entity.sys.workflow.support.detail.Advice;

public abstract class AbstractNodeDetail implements INodeDetail {
	protected IProcessDetail process;

	protected String id;

	protected String name;

	protected int x;

	protected int y;

	protected String description;

	protected List<ILineDetail> outLines;

	protected List<ILineDetail> inLines;

	protected String handlerIds;

	protected String handlerNames;
	
	protected String handlerDescs;

	protected String optHandlerIds;

	protected String optHandlerNames;

	// 新增流程意见属性 add by limh 2010年9月19日
	protected boolean canModifyNotionPopedom;

	protected String nodeCanViewCurNodeIds;

	protected String nodeCanViewCurNodeNames;

	protected String otherCanViewCurNodeIds;

	protected String otherCanViewCurNodeNames;

	// 新增是否重新计算已流经节点处理人选项 limh 2011年4月18日

	protected boolean recalculateHandler;
	
	protected String nodeDelay;
	
	protected String bizInfoType;
	
	protected String handlerSelectType;
	
	public String getHandlerSelectType() {
		return handlerSelectType;
	}

	public void setHandlerSelectType(String handlerSelectType) {
		this.handlerSelectType = handlerSelectType;
	}

	public String getBizInfoType() {
		return bizInfoType;
	}

	public void setBizInfoType(String bizInfoType) {
		this.bizInfoType = bizInfoType;
	}

	public String getNodeDelay() {
		return nodeDelay;
	}

	public void setNodeDelay(String nodeDelay) {
		this.nodeDelay = nodeDelay;
	}

	/**
	 * 前后置逻辑
	 */
	protected Advice advice = new Advice();	//默认有一个空的Advice

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public List<ILineDetail> loadOutLines() {
		if (outLines == null) {
			outLines = new ArrayList<ILineDetail>();
			List<ILineDetail> allLines = process.getLines();
			for (int i = 0; i < allLines.size(); i++) {
				ILineDetail lineDetail = allLines.get(i);
				if (id.equals(lineDetail.getStartNodeId()))
					outLines.add(lineDetail);
			}
		}
		return outLines;
	}

	public List<ILineDetail> loadInLines() {
		if (inLines == null) {
			inLines = new ArrayList<ILineDetail>();
			List<ILineDetail> allLines = process.getLines();
			for (int i = 0; i < allLines.size(); i++) {
				ILineDetail lineDetail = allLines.get(i);
				if (id.equals(lineDetail.getEndNodeId()))
					inLines.add(lineDetail);
			}
		}
		return inLines;
	}

	public String getHandlerIds() {
		return handlerIds;
	}

	public void setHandlerIds(String handlerIds) {
		this.handlerIds = handlerIds;
	}

	public String getHandlerNames() {
		return handlerNames;
	}

	public void setHandlerNames(String handlerNames) {
		this.handlerNames = handlerNames;
	}

	public String getHandlerDescs() {
		return handlerDescs;
	}

	public void setHandlerDescs(String handlerDescs) {
		this.handlerDescs = handlerDescs;
	}

	public void setProcess(IProcessDetail process) {
		this.process = process;
	}

	public IProcessDetail loadDetail() {
		return process;
	}

	public void reset() {
		outLines = null;
	}

	public NodeDescriptor parseNodeDescriptor() {
		NodeDescriptor nodeDescriptor = new NodeDescriptor();
		nodeDescriptor.setId(getId());
		nodeDescriptor.setName(getName());
		return nodeDescriptor;
	}

	/**
	 * @return nodeCanViewCurNodeIds
	 */
	public String getNodeCanViewCurNodeIds() {
		return nodeCanViewCurNodeIds;
	}

	/**
	 * @return nodeCanViewCurNodeNames
	 */
	public String getNodeCanViewCurNodeNames() {
		return nodeCanViewCurNodeNames;
	}

	/**
	 * @return otherCanViewCurNodeIds
	 */
	public String getOtherCanViewCurNodeIds() {
		return otherCanViewCurNodeIds;
	}

	/**
	 * @return otherCanViewCurNodeNames
	 */
	public String getOtherCanViewCurNodeNames() {
		return otherCanViewCurNodeNames;
	}

	/**
	 * @param nodeCanViewCurNodeIds
	 *            要设置的 nodeCanViewCurNodeIds
	 */
	public void setNodeCanViewCurNodeIds(String nodeCanViewCurNodeIds) {
		this.nodeCanViewCurNodeIds = nodeCanViewCurNodeIds;
	}

	/**
	 * @param nodeCanViewCurNodeNames
	 *            要设置的 nodeCanViewCurNodeNames
	 */
	public void setNodeCanViewCurNodeNames(String nodeCanViewCurNodeNames) {
		this.nodeCanViewCurNodeNames = nodeCanViewCurNodeNames;
	}

	/**
	 * @param otherCanViewCurNodeIds
	 *            要设置的 otherCanViewCurNodeIds
	 */
	public void setOtherCanViewCurNodeIds(String otherCanViewCurNodeIds) {
		this.otherCanViewCurNodeIds = otherCanViewCurNodeIds;
	}

	/**
	 * @param otherCanViewCurNodeNames
	 *            要设置的 otherCanViewCurNodeNames
	 */
	public void setOtherCanViewCurNodeNames(String otherCanViewCurNodeNames) {
		this.otherCanViewCurNodeNames = otherCanViewCurNodeNames;
	}

	/**
	 * @return canModifyNotionPopedom
	 */
	public boolean isCanModifyNotionPopedom() {
		return canModifyNotionPopedom;
	}

	/**
	 * @param canModifyNotionPopedom
	 *            要设置的 canModifyNotionPopedom
	 */
	public void setCanModifyNotionPopedom(boolean canModifyNotionPopedom) {
		this.canModifyNotionPopedom = canModifyNotionPopedom;
	}

	/**
	 * @return recalculateHandler
	 */
	public boolean isRecalculateHandler() {
		return recalculateHandler;
	}

	/**
	 * @param recalculateHandler
	 *            要设置的 recalculateHandler
	 */
	public void setRecalculateHandler(boolean recalculateHandler) {
		this.recalculateHandler = recalculateHandler;
	}

	public Advice getAdvice() {
		return advice;
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}
}
