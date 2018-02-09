package com.vispractice.fmc.business.entity.sys.workflow.beans.parameter;

import com.vispractice.fmc.business.base.utils.BeanReaderWriter;

public class NodeParameter {
	/**
	 * 节点标识
	 */
	public static String NODE_PARAMETER_NODE = "<node-param />";
	
	/**
	 * 过期跳过
	 */
	public static String SYSTEM_EXPIRED_JUMP_TYPE = "1";
	
	/**
	 * 过期废弃
	 */
	public static String SYSTEM_EXPIRED_ABANDON_TYPE = "2";

	/**
	 * 当前节点
	 */
	private String nodeId;
	
	/**
	 * 操作名字集合
	 */
	private String oprNames;
	
	/**
	 * 当前节点系统自动跳转方试
	 */
	private String systemJumpType;
	
	/**
	 * 驳回时返回本节点
	 */
	private String toRefuseThisNodeId;
	
	/**
	 * 驳回时返回本节点的处理人
	 */
	private String toRefuseThisHandlerId;

	/**
	 * 驳回时返回本节点的处理人
	 */
	private String toRefuseThisHandlerName;
	
	/**
	 * 返回节点原先处理人配置,用于比对是否被修改
	 */
	private String toRefuseThisHandlerConfig;
	
	/**
	 * 驳回时返回本节点的处理人的位移
	 */
	private int toRefuseThisHandlerOffset = 0;
	
	/**
	 * 当前处理人的位移
	 */
	private int currHandlerOffset = 0;
	
	/**
	 * 驳回时返回本节点的节点类型
	 */
	private int toRefuseThisProcessType;


	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getToRefuseThisNodeId() {
		return toRefuseThisNodeId;
	}

	public void setToRefuseThisNodeId(String toRefuseThisNodeId) {
		this.toRefuseThisNodeId = toRefuseThisNodeId;
	}

	public String getToRefuseThisHandlerId() {
		return toRefuseThisHandlerId;
	}

	public String getToRefuseThisHandlerName() {
		return toRefuseThisHandlerName;
	}

	public void setToRefuseThisHandlerId(String toRefuseThisHandlerId) {
		this.toRefuseThisHandlerId = toRefuseThisHandlerId;
	}

	public void setToRefuseThisHandlerName(String toRefuseThisHandlerName) {
		this.toRefuseThisHandlerName = toRefuseThisHandlerName;
	}

	/**
	 * 返回节点原先处理人配置,用于比对是否被修改
	 * @return toRefuseThisHandlerConfig
	 * @author 
	 * @since 2009-12-25
	 */
	public String getToRefuseThisHandlerConfig() {
		return toRefuseThisHandlerConfig;
	}

	/**
	 * 返回节点原先处理人配置,用于比对是否被修改
	 * @param toRefuseThisHandlerConfig  要设置的 toRefuseThisHandlerConfig
	 * @author 
	 * @since 2009-12-25
	 */
	public void setToRefuseThisHandlerConfig(String toRefuseThisHandlerConfig) {
		this.toRefuseThisHandlerConfig = toRefuseThisHandlerConfig;
	}

	public void setOprNames(String oprNames) {
		this.oprNames = oprNames;
	}

	public String getOprNames() {
		return oprNames;
	}

	public String getSystemJumpType() {
		return systemJumpType;
	}

	public void setSystemJumpType(String systemJumpType) {
		this.systemJumpType = systemJumpType;
	}

	
	/**
	 * 驳回时返回本节点的处理人的位移
	 * @return toRefuseThisHandlerOffset
	 * @author 
	 * @since 2009-12-25
	 */
	public int getToRefuseThisHandlerOffset() {
		return toRefuseThisHandlerOffset;
	}

	/**
	 * 驳回时返回本节点的处理人的位移
	 * @param toRefuseThisHandlerOffset 要设置的 toRefuseThisHandlerOffset
	 * @author
	 * @since 2009-12-25
	 */
	public void setToRefuseThisHandlerOffset(int toRefuseThisHandlerOffset) {
		this.toRefuseThisHandlerOffset = toRefuseThisHandlerOffset;
	}

	public void setCurrHandlerOffset(int currHandlerOffset) {
		this.currHandlerOffset = currHandlerOffset;
	}

	public int getCurrHandlerOffset() {
		return currHandlerOffset;
	}

	/**
	 * 驳回时返回本节点的节点类型
	 * @return toRefuseThisProcessType
	 * @author
	 * @since 2009-12-25
	 */
	public int getToRefuseThisProcessType() {
		return toRefuseThisProcessType;
	}

	/**
	 * 驳回时返回本节点的节点类型
	 * @param toRefuseThisProcessType 要设置的 toRefuseThisProcessType
	 * @author
	 * @since 2009-12-25
	 */
	public void setToRefuseThisProcessType(int toRefuseThisProcessType) {
		this.toRefuseThisProcessType = toRefuseThisProcessType;
	}
	
	private static BeanReaderWriter beanReaderWriter = new BeanReaderWriter(
			NodeParameter.class);

	public String toString() {
		return beanReaderWriter.write(this);
	}

	public synchronized static NodeParameter parse(String xml) {
		return beanReaderWriter.parse(xml, NODE_PARAMETER_NODE);
	}

}
