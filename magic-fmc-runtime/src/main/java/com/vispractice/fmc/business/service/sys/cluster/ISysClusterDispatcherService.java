package com.vispractice.fmc.business.service.sys.cluster;

import java.util.List;
import java.util.Map;

import com.vispractice.fmc.business.entity.sys.cluster.SysClusterDispatcher;

public interface ISysClusterDispatcherService {
	/**
	 * 保存服务器分发记录
	 * @param fdServerKey
	 * @param fdDispatcherKey
	 */
	public void save(String fdServerKey,String fdDispatcherKey);

	/**
	 * 保存调度器Map到数据库
	 * @param map
	 */
	public void saveDispatcherMap(Map<String, String> map);

	/***
	 * 获取所有调度器对应地址实体
	 * @Title: findAll 
	 * @return
	 */
	public List<SysClusterDispatcher> findAll();
	
	
	/***
	 * 获取所有调度器对应地址实体
	 * @Title: findAll 
	 * @return
	 */
	public List<SysClusterDispatcher> findByFdServerKey(String fdServerKey);
	
}
