package com.vispractice.fmc.cluster.adapter;

import org.jgroups.Address;
import org.jgroups.Channel;
import org.jgroups.ChannelListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FmcChannelListener implements ChannelListener {
	
	private static Logger logger = LoggerFactory.getLogger(FmcChannelListener.class);

	@Override
	public void channelConnected(Channel channel) {
		logger.info("channel connected");
		
	}

	@Override
	public void channelDisconnected(Channel channel) {
		logger.info("channel Disconnected");
		
	}

	@Override
	public void channelClosed(Channel channel) {
		logger.info("channel closed");
		
	}

	@Override
	public void channelShunned() {
		logger.info("channel shunned");
		
	}

	@Override
	public void channelReconnected(Address addr) {
		logger.info("channel Reconnected");
		
	}

}
