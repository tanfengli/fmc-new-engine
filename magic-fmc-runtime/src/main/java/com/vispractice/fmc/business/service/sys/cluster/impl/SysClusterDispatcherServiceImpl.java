package com.vispractice.fmc.business.service.sys.cluster.impl;
 
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.cluster.SysClusterDispatcher;
import com.vispractice.fmc.business.entity.sys.cluster.repository.SysClusterDispatcherRepository;
import com.vispractice.fmc.business.service.sys.cluster.ISysClusterDispatcherService;

@Service
@Transactional
public class SysClusterDispatcherServiceImpl implements ISysClusterDispatcherService {
	/**
	 * 集群分配服务
	 */
	@Autowired
	private SysClusterDispatcherRepository sysClusterDispatcherRepository;

	@Override
	public void save(String fdServerKey,String fdDispatcherKey) {
		SysClusterDispatcher sysClusterDispatcher = new SysClusterDispatcher();
		sysClusterDispatcher.setFdServerKey(fdServerKey);
		sysClusterDispatcher.setFdDispatcherKey(fdDispatcherKey);
		
		sysClusterDispatcherRepository.deleteByFdDispatcherKey(fdDispatcherKey);
		sysClusterDispatcherRepository.save(sysClusterDispatcher);
	} 
	
	@SuppressWarnings("rawtypes")
	@Override
	public void saveDispatcherMap(Map<String, String> map){
		Iterator iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			this.save((String)entry.getValue(), (String)entry.getKey());
		}
	}
	
	@Override
	public List<SysClusterDispatcher> findAll(){
		return sysClusterDispatcherRepository.findAll();
	}

	@Override
	public List<SysClusterDispatcher> findByFdServerKey(String fdServerKey) {
		return sysClusterDispatcherRepository.findByFdServerKey(fdServerKey);
	}
	
	
}