package com.vispractice.fmc.cluster.adapter;

import org.jgroups.Channel;
import org.jgroups.ChannelException;
import org.jgroups.ChannelListener;
import org.jgroups.JChannel;
import org.jgroups.Receiver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JGroupChannel {

	private static JChannel channel = null;

	private JGroupChannel() {}

	public static JChannel getInstance(String xmlFile, String group) {
		if (null == channel) {
			try {
				channel = JGroupChannel.initJChannel(xmlFile, group);
			} catch (Exception e) {
				log.error("消息通道初始化错误！" + e.getMessage());
				e.printStackTrace();
				if (null != channel) {
					channel.close();
				}
			}
		}
		if (null != channel) {
			if (!channel.isOpen()) {
				try {
					channel.open();
				} catch (ChannelException e) {
					log.error("消息通道初始化错误！" + e.getMessage());
					e.printStackTrace();
				}
			}

			if (!channel.isConnected()) {
				try {
					channel.connect(group);
				} catch (ChannelException e) {
					log.error("消息通道初始化错误！" + e.getMessage());
					e.printStackTrace();
				}
			}
		}

		return channel;
	}

	public static synchronized JChannel initJChannel(String xmlFile, String group) throws Exception {
		if (null == channel) {
			log.info("初始化JChannel! ");
			channel = new JChannel(xmlFile);

			Receiver r = new FmcReceiverAdapter();
			channel.setReceiver(r);

			ChannelListener c = new FmcChannelListener();
			channel.addChannelListener(c);

			channel.setOpt(Channel.LOCAL, Boolean.FALSE);
			channel.setOpt(Channel.AUTO_RECONNECT, Boolean.TRUE);

			channel.connect(group);
		}

		return channel;
	}
}
