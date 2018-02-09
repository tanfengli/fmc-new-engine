package com.vispractice.fmc.cluster.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyi.kmss.sys.cluster.dispatcher.DispatcherSwitchMessage;
import com.ruiyi.kmss.sys.cluster.interfaces.message.IMessage;
import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entity.sys.cluster.SysClusterServer;
import com.vispractice.fmc.business.service.sys.cluster.IDispatcherSwitcherService;
import com.vispractice.fmc.business.service.sys.cluster.ISysClusterServerService;
import com.vispractice.fmc.business.service.sys.workflow.IClusterService;

/**
 * 
 * 名  称：DispatcherSwitcherServiceImpl
 * 描  述：调度器切换实现类
 * 完成日期：2017年11月13日 下午4:51:38
 * 编码作者："LaiJiashen"
 */
@Service
@Slf4j
public class DispatcherSwitcherServiceImpl implements IDispatcherSwitcherService{

	// =============使用到的服务============
	@Autowired
	private IClusterService clusterService;
	
	@Autowired
	private ISysClusterServerService sysClusterServerService;
	
	
	// =============执行逻辑=============
	@Override
	public synchronized void startSwitch(Map<String, String> map){
		List<SysClusterServer> servers =  sysClusterServerService.getAliveSever();
		if (servers.size()==0) {
			log.error("没有存在启动状态的可调度服务器，停止调度器切换。。。");
			return ;
		}
		List<String> addressStringList = new ArrayList<String>();
		addressStringList.add(servers.get(0).getFdAddress());
		// 发送消息
//		IMessage message = new DispatcherSwitchMessage(DispatcherSwitchMessage.STEP_RESTART, false,null, id,map);
		clusterService.sendToServer(map, addressStringList);
	}

}
