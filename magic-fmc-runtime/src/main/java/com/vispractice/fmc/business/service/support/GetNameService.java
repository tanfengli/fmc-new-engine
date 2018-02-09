package com.vispractice.fmc.business.service.support;

public interface GetNameService {
	/**
	 * 获取当前节点名称
	 * @param processId 流程实例
	 * @return 节点名称
	 * @throws Exception
	 */
	public String getCurrentNodesName(String processId) throws Exception;

	/**
	 * 获取当前处理人名称
	 * @param processId 流程实例
	 * @return 当前处理人名称
	 * @throws Exception
	 */
	public String getHandlersName(String processId) throws Exception;
}
