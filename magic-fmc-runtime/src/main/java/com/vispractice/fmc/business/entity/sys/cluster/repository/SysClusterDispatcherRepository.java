package com.vispractice.fmc.business.entity.sys.cluster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.cluster.SysClusterDispatcher;

@Repository
public interface SysClusterDispatcherRepository extends 
	JpaRepository<SysClusterDispatcher,String>,JpaSpecificationExecutor<SysClusterDispatcher> {
	/**
	 * 根据分发类型删除
	 * @param fdDispatcherKey
	 */
	public void deleteByFdDispatcherKey(String fdDispatcherKey);
	
	/**
	 * 更加节点查询分发任务
	 * @param fdServerKey
	 */
	public List<SysClusterDispatcher> findByFdServerKey(String fdServerKey);
}