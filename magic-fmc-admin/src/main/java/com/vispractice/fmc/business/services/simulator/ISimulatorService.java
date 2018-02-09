package com.vispractice.fmc.business.services.simulator;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;

/**
 * 
 * 编  号：
 * 名  称：ISimulatorService
 * 描  述：ISimulatorService.java
 * 完成日期：2017年8月9日 下午3:08:27
 * 编码作者："LaiJiashen"
 */
public interface ISimulatorService {

	/**
	 * 
	 * 模拟器计算（计算用户在通用岗位关系或角色线关系中对应所在的组织架构位置）
	 * @Title: startSimulate 
	 * @param personList 人员列表
	 * @param simulateUser 模拟用户
	 */
	public String startSimulate(List<SysOrgElement> personList,SysOrgElement simulateUser);

}
