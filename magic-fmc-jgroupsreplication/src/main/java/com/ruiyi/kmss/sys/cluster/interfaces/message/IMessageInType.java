package com.ruiyi.kmss.sys.cluster.interfaces.message;

/**
 * 群集消息体，消息类型可自己给定
 * 
 */
public interface IMessageInType extends IMessage {
	/**
	 * 消息类型
	 * 
	 * @return
	 */
	public String getType();
}
