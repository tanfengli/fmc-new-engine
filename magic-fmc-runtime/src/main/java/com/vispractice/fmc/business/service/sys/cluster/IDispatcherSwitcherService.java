package com.vispractice.fmc.business.service.sys.cluster;

import java.util.Map;

/**
 * 名  称：IDispatcherSwitcherService
 * 描  述：调度器切换服务
 * 完成日期：2017年11月15日 下午5:44:47
 * 编码作者："LaiJiashen"
 */
public interface IDispatcherSwitcherService {

	/**
	 * 开始切换，可能花费很长时间
	 * @param map 调度器服务器和节点map
	 * @throws Exception 
	 */
	public void startSwitch(Map<String, String> map) throws Exception;

}
