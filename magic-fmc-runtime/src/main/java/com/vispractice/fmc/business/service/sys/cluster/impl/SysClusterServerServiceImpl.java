package com.vispractice.fmc.business.service.sys.cluster.impl;
 
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.entity.sys.cluster.SysClusterServer;
import com.vispractice.fmc.business.entity.sys.cluster.repository.SysClusterServerRepository;
import com.vispractice.fmc.business.service.sys.cluster.ISysClusterServerService;

@Slf4j
@Service
@Transactional
public class SysClusterServerServiceImpl implements ISysClusterServerService {
	/**
	 * 集群服务
	 */
	@Autowired
	private SysClusterServerRepository clusterServerRepository; 
	
	
	@Override
	public Page<SysClusterServer> findAll(SysClusterServer sysClusterServer,Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysClusterServer> example = Example.of(sysClusterServer, matcher); 
		
		return clusterServerRepository.findAll(example,pageable);
	}
	
	@Override
	public Page<SysClusterServer> findBySearch(final SysClusterServer sysClusterServer,Pageable pageable) {
		Specification<SysClusterServer> spec = new Specification<SysClusterServer>() {
			@Override
			public Predicate toPredicate(Root<SysClusterServer> root,CriteriaQuery<?> query,CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				
				if (null != sysClusterServer) { 
					if (StringUtils.isNotBlank(sysClusterServer.getFdName()))
						list.add(cb.equal(root.get("fdName"), sysClusterServer.getFdName())); 
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}
		};
		
		return clusterServerRepository.findAll(spec,pageable);
	}
	
	@Override
	public void save(SysClusterServer sysClusterServer) {
		clusterServerRepository.save(sysClusterServer);		 
	}

	@Override
	public void deleteByFdId(String fdId) {
		clusterServerRepository.deleteByFdId(fdId);		
	}

	@Override
	public SysClusterServer get(String id) { 
		return clusterServerRepository.getOne(id);
	}

	@Override
	public List<SysClusterServer> findAll() {
		List<SysClusterServer> result = clusterServerRepository.findAll();
		
		return result;
	} 
	
	@Override
	public List<SysClusterServer> getSeverByDispatcherType(List<String> dispatcherType){
		
		List<SysClusterServer> list = clusterServerRepository.findByFdDispatcherType(dispatcherType);
		return list;
	}
	
	@Override
	public List<SysClusterServer> getAliveSever(){
		return clusterServerRepository.getAliveSever(CommonConstant.NOT_AVAILABLE_FLAG);
	}
	
	@Override
	public SysClusterServer registerServer(String fdKey,String localAddress){
		log.info("开始注册本服务器。。。。");
		List<SysClusterServer> existSameKeyServers = clusterServerRepository.findByFdKey(fdKey);
		SysClusterServer localServer = null;
		// 若存在相同key的服务器，现时处理方式为直接替代
		if (existSameKeyServers.size()>0) {
			log.warn("已存在相同key:["+fdKey+"]的服务器，现处理方式为用当前服务器替代原相同key服务器。");
			localServer = existSameKeyServers.get(0);
		}else {
			localServer = new SysClusterServer();
		}
		
		if (StringUtils.isNotEmpty(fdKey)) {
			localServer.setFdKey(fdKey);
			localServer.setFdName(fdKey);
			localServer.setFdAnonymous(CommonConstant.NOT_AVAILABLE_FLAG);
		}else {
			localServer.setFdKey("Anonymous");
			localServer.setFdName("Anonymous");
			localServer.setFdAnonymous(CommonConstant.AVAILABLE_FLAG);
		}
		//设置调度方式
		localServer.setFdDispatcherType(SysClusterServer.DISPATCHERTYPE_NONE);
		log.info("本服务器不参与调度...");
		//设置进程号
		localServer.setFdPid(ManagementFactory.getRuntimeMXBean().getName());
		// 设置其它参数
		localServer.setFdAddress(localAddress);
		Date now = new Date();
		localServer.setFdRefreshTime(now);
		localServer.setFdStartTime(now);
		localServer.setFdShutdown(CommonConstant.NOT_AVAILABLE_FLAG);
		
		clusterServerRepository.save(localServer);
		SysClusterServer.localSever = localServer;
//		refreshSever();
		return localServer;
	}
	
	@Scheduled(fixedDelay=SysClusterServer.DEAD_TIME/2)
	public void refreshSever(){
//		if (null==SysClusterServer.localSever) {
//			log.warn("本地服务器为空，请检查本集群服务器是否正常启动。。。");
//			return ;
//		}
//		// 更新刷新时间
//		// 暂不作检查集群服务器是否正常连接问题
//		Date now = new Date();
//		SysClusterServer.localSever.setFdRefreshTime(now);
//		SysClusterServer server = clusterServerRepository.save(SysClusterServer.localSever);
//		SysClusterServer.localSever = server;
	}
	
}