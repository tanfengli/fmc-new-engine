package com.vispractice.fmc.business.service.sys.workflow;

import com.vispractice.fmc.business.entity.sys.workflow.ComponentLockerMain;

public interface IComponentLockerMainService {
	/**
	 * 是否加锁
	 * @param wfInstanceId
	 * @return
	 */
	public ComponentLockerMain isLocked(String wfInstanceId);
	
	/**
	 * 增加锁
	 * @param wfInstanceId
	 * @return
	 */
	public ComponentLockerMain acquireLock(String wfInstanceId,String userId);
	
	/**
	 * 释放锁
	 * @param wfInstanceId
	 */
	public void releaseLock(String wfInstanceId);
}