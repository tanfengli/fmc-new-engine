package com.vispractice.fmc.business.service.sys.notify;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodo;

public interface ISysNotifyTodoDoneInfoService {

	/**
	 * 
	 * 设置为已办
	 * @param todo todo待办实体
	 * @param personIds 人员ID列表
	 */
	public void setPersonsDone(SysNotifyTodo todo, List<String> personIds);

	/**
	 * 
	 * 插入一条已办数据
	 * @param todoId 待办ID
	 * @param elementId 人员ID
	 */
	public void addOne(String todoId, String elementId);

}
