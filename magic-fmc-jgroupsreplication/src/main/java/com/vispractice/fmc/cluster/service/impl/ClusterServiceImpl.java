package com.vispractice.fmc.cluster.service.impl;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ruiyi.kmss.sys.cluster.dispatcher.DispatcherMessage;
import com.ruiyi.kmss.sys.cluster.dispatcher.DispatcherSwitchMessage;
import com.ruiyi.kmss.sys.cluster.interfaces.message.IMessage;
import com.ruiyi.kmss.sys.cluster.interfaces.message.SimpleMessage;
import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfEvent;
import com.vispractice.fmc.business.service.sys.workflow.IClusterService;
import com.vispractice.fmc.cluster.adapter.JGroupChannel;
import com.vispractice.fmc.cluster.adapter.JGroupProperties;

@Service
@Profile("jgroupEngine")
@Slf4j
public class ClusterServiceImpl implements IClusterService, InitializingBean {

	/**
	 * 消息类型前缀
	 */
	private static final String MESSAGETYPE_PREFIX = "com.ruiyi.kmss.sys.cluster.dispatcher.receiver.";

	@Autowired
	private JGroupProperties jGroupProperties;

	// @Async("jGroupAsync")
	public void submit(SysWfEvent event) {
		log.info("begin call remote fmc server" + event.getFdId());

		JChannel channel = JGroupChannel.getInstance(jGroupProperties.getConfXml(), jGroupProperties.getGroup());

		if (null == channel || !channel.isConnected()) {
			log.error("JGroup消息通道没有连接，无法发送消息！");
			return;
		}

		// View view = channel.getView();
		// System.out.println(view.toString());

		// channel.getState(null, 10000);
		try {
			channel.send(null, channel.getLocalAddress(),
					new DispatcherMessage(MESSAGETYPE_PREFIX + "sysWfEventService", new SimpleMessage()));
			log.info("流程任务消息发送成功");
		} catch (Exception e) {
			log.error("JGroup通道异常，发送消息失败！" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void sendToServer(Map<String, String> map, List<String> addressStringList) {
		log.info("begin call remote fmc server");
		
		/**
		 * 本次操作唯一标识
		 */
		String id = IDGenerator.generateID();

		// 获取信道
		IMessage message = new DispatcherSwitchMessage(DispatcherSwitchMessage.STEP_RESTART, false,null, id, map);
		
		JChannel channel = JGroupChannel.getInstance(jGroupProperties.getConfXml(), jGroupProperties.getGroup());

		if (null == channel || !channel.isConnected()) {
			log.error("JGroup消息通道没有连接，无法发送消息！");
			return;
		}
		try {
			String addressString = addressStringList.get(0);
			List<Address> addresslList = channel.getView().getMembers();
			for (Address address2 : addresslList) {
				if (address2.toString().equals(addressString)) {
					break;
				}
			}
			// 暂时按广播处理
			channel.send(null, channel.getLocalAddress(), message);
			log.info("消息发送成功...");
		} catch (Exception e) {
			log.error("JGroup通道异常，发送消息失败！" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 启动信道
		JGroupChannel.getInstance(jGroupProperties.getConfXml(), jGroupProperties.getGroup());

	}

}
