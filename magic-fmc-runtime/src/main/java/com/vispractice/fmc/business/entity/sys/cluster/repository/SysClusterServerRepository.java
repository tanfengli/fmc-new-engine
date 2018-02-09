package com.vispractice.fmc.business.entity.sys.cluster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.cluster.SysClusterServer;

@Repository
public interface SysClusterServerRepository
		extends JpaRepository<SysClusterServer, String>, JpaSpecificationExecutor<SysClusterServer> {
	void deleteByFdId(String fdId);
	
	/**
	 * 
	 * @param fdDispatcherType 调度类型
	 * @return
	 */
	public List<SysClusterServer> findByFdDispatcherType(List<String> fdDispatcherType);
	
	/**
	 * 
	 * @param fdKey 服务器key
	 * @return
	 */
	public List<SysClusterServer> findByFdKey(String fdKey);
	
	/**
	 * 
	 * @param fdShutDown 是否停止
	 * @return
	 */
	@Query("from SysClusterServer c where c.fdShutdown=?1 and c.fdDispatcherType !='0'")
	public List<SysClusterServer> getAliveSever(String fdShutDown);
}