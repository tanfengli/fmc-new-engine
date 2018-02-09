package com.ruiyi.kmss.sys.cluster.dispatcher;

import com.ruiyi.kmss.sys.cluster.interfaces.message.IMessage;
import com.ruiyi.kmss.sys.cluster.interfaces.message.IMessageInType;

/**
 * 调度器消息接收者的消息体封装
 * 
 */
public class DispatcherMessage implements IMessageInType {
	private static final long serialVersionUID = 332166643882873515L;

	public DispatcherMessage(String type, IMessage message) {
		super();
		this.type = type;
		this.message = message;
	}

	private String type;

	public String getType() {
		return type;
	}

	private IMessage message = null;

	public IMessage getMessage() {
		return message;
	}
}
