package com.ruiyi.kmss.sys.cluster.dispatcher;

import com.ruiyi.kmss.sys.cluster.interfaces.message.IMessage;

/**
 * 切换调度器运行地址时使用的消息体
 * 
 */
public class DispatcherSwitchMessage implements IMessage {
	private static final long serialVersionUID = 111355488321895081L;

	/**
	 * 停止调度器
	 */
	public static final int STEP_TOSTOP = 1;

	/**
	 * 开始调度器
	 */
	public static final int STEP_TOSTART = 2;
	
	/**
	 * 重启调度器
	 */
	public static final int STEP_RESTART = 3;

	/**
	 * 步骤
	 */
	private int step;

	/**
	 * 是否答复信息
	 */
	private boolean reply;

	/**
	 * 消息源
	 */
	private String srcServer;

	/**
	 * 此次处理的唯一标识
	 */
	private String id;
	
	/**
	 * 扩展参数
	 */
	private Object extendParam;

	public DispatcherSwitchMessage(int step, boolean reply, String srcServer,
			String id) {
		super();
		this.step = step;
		this.reply = reply;
		this.srcServer = srcServer;
		this.id = id;
	}
	
	public DispatcherSwitchMessage(int step, boolean reply, String srcServer,
			String id,Object extendParam) {
		super();
		this.step = step;
		this.reply = reply;
		this.srcServer = srcServer;
		this.id = id;
		this.extendParam = extendParam;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public boolean isReply() {
		return reply;
	}

	public void setReply(boolean reply) {
		this.reply = reply;
	}

	public String getSrcServer() {
		return srcServer;
	}

	public void setSrcServer(String srcServer) {
		this.srcServer = srcServer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(Object extendParam) {
		this.extendParam = extendParam;
	}

}
