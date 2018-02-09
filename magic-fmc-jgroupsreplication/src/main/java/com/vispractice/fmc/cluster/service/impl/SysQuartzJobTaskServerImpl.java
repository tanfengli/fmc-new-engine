package com.vispractice.fmc.cluster.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.jgroups.JChannel;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.ruiyi.kmss.sys.cluster.dispatcher.DispatcherMessage;
import com.ruiyi.kmss.sys.cluster.interfaces.message.SimpleMessage;
import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;
import com.vispractice.fmc.business.service.sys.workflow.ISysQuartzJobTaskService;
import com.vispractice.fmc.cluster.adapter.JGroupChannel;
import com.vispractice.fmc.cluster.adapter.JGroupProperties;

@Service
@Profile("jgroupEngine")
@Slf4j
public class SysQuartzJobTaskServerImpl implements ISysQuartzJobTaskService {
	@Autowired
	private JGroupProperties jGroupProperties;
	
	private static final String MESSAGETYPE_PREFIX = "com.vispractice.fmc.service.impl.";

	
	@Override
	public void submit(SysQuartzJob job,String executeType) throws Exception {
		
		log.info("begin call sysQuartzJob " + job.getFdId());

		JChannel channel = JGroupChannel.getInstance(jGroupProperties.getConfXml(), jGroupProperties.getGroup());

		if (null == channel || !channel.isConnected()) {
			log.error("JGroup消息通道没有连接，无法发送消息！");
			return;
		}

		// View view = channel.getView();
		// System.out.println(view.toString());

		// channel.getState(null, 10000);
		try {
			SimpleMessage msg =new SimpleMessage();
			msg.setType("sysQuartzJob");
			JSONObject json  = new JSONObject();
			json.put("fdId", job.getFdId());
			json.put("executeType", executeType);
			msg.setBody(json.toJSONString());
			channel.send(null, channel.getLocalAddress(),
					new DispatcherMessage(MESSAGETYPE_PREFIX + "asyncSysQuartzThread",msg));
			log.info("流程任务消息发送成功");
		} catch (Exception e) {
			log.error("JGroup通道异常，发送消息失败！" + e.getMessage());
			e.printStackTrace();
		}
	}
}
