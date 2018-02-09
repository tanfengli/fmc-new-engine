package com.ruiyi.kmss.sys.cluster.interfaces.message;

/**
 * 简单实用的消息体，可指定消息类型和消息体<br>
 * 目的：当需要发送一个简单的消息时可直接使用，不需要单独定义一个消息体
 * 
 * @author 叶中奇
 * 
 */
public class SimpleMessage implements IMessageInType {
	private static final long serialVersionUID = -7071804520708693718L;

	/**
	 * 消息类型
	 */
	private String type = null;

	/**
	 * 消息体
	 */
	private String body = null;

	public SimpleMessage() {
		super();
	}

	public SimpleMessage(String type, String body) {
		super();
		this.type = type;
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
