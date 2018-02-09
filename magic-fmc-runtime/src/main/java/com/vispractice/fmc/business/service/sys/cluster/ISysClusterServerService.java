package com.vispractice.fmc.business.service.sys.cluster;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.cluster.SysClusterServer;

public interface ISysClusterServerService {
	/**
	 * 分页查询
	 * @param sysClusterServer
	 * @param pageable
	 * @return
	 */
   public Page<SysClusterServer> findAll(SysClusterServer sysClusterServer,Pageable pageable);
   
   /**
    * 条件查询
    * @param sysClusterServer
    * @param pageable
    * @return
    */
   public Page<SysClusterServer> findBySearch(final SysClusterServer sysClusterServer,Pageable pageable);
   
   /**
    * 查询所有集群
    * @return
    */
   public List<SysClusterServer> findAll();
   
	/**
	 * 保存
	 */
	public void save(SysClusterServer sysClusterServer);

	/**
	 * 查询
	 */
	public SysClusterServer get(String id);

	/**
	 * 删除
	 */
	public void deleteByFdId(String fdId);

	/**
	 * 根据调度方式获取集群服务器
	 * @return
	 */
	public List<SysClusterServer> getSeverByDispatcherType(List<String> dispatcherType);

	/**
	 * 获取处于启动状态的集群服务器
	 * @return
	 */
	public List<SysClusterServer> getAliveSever();

	/**
	 * 创建/更新当前服务器节点信息
	 * @param fdKey 服务器key
	 * @return
	 */
	public SysClusterServer registerServer(String fdKey,String localAddress);
}
