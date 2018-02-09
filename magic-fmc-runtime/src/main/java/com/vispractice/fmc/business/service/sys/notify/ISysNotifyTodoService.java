package com.vispractice.fmc.business.service.sys.notify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodo;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;

/**
 * 名 称：待办通知表服务 
 * 描 述：ISysNotifyTodoService.java 
 * 完成日期：2017年7月15日 上午11:06:56
 * 编码作者：ZhouYanyao
 */
public interface ISysNotifyTodoService {
	/**
	 * 删除待办target,并设为已办
	 * @param key 流程实例
	 * @param parameter1 节点实体
	 * @param workItem 工作项实体
	 * @param userId  用户
	 */
	public void deleteTodoNotify(String key,String parameter1,SysWfWorkitem workItem,String userId);

	/**
	 * 移除代办：移除代办未处理人. 
	 * 审批过程应该是先调用deleteTodoNotify方法把当前人代办设置为已办
	 * @param key 流程实例
	 * @param parameter1 节点实体
	 * @param parameter2  工作项
	 */
	public void clearTodoPersons(String key,String parameter1,String parameter2);

	/**
	 * 根据人员和实例获取待办
	 * @param userNo
	 * @param wfInstanceId
	 * @return
	 */
	public List<SysNotifyTodo> findByWfInstanceAndUserNo(String userNo,String wfInstanceId);

	/**
	 * 同时生成待办及待阅
	 * @param sysNewsMain
	 * @param wfItem
	 * @param notifyType
	 * @param fdType
	 * @param replaceMap
	 * @param msgKey
	 * @param accHandlers
	 * @param handlers
	 * @param readers
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void sendNotify(SysNewsMain sysNewsMain, SysWfWorkitem wfItem, String notifyType, HashMap replaceMap,
			String msgKey, Map<String, List<SysOrgElement>> accHandlers, Map<String, List<SysOrgElement>> readers,
			List<SysOrgElement> handlers) throws Exception;

	/**
	 * 生成待阅信息
	 * @param sysNewsMain
	 * @param notifyType
	 * @param fdType
	 * @param replaceMap
	 * @param msgKey
	 * @param readers
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void sendAuthReaderNotify(SysNewsMain sysNewsMain, String notifyType, int fdType, HashMap replaceMap,
			String msgKey, List<SysOrgElement> readers) throws Exception;

	/**
	 * 生成待办
	 * @param sysNewsMain
	 * @param node
	 * @param wfItem
	 * @param accHandlers
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void sendAuthHandleNotify(SysNewsMain sysNewsMain, SysWfWorkitem wfItem, String notifyType, int fdType,
			HashMap replaceMap, String msgKey, List<SysOrgElement> handlers)
			throws Exception;

}
