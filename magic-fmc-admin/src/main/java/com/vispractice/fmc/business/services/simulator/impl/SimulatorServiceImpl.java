package com.vispractice.fmc.business.services.simulator.impl;


import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;
import com.vispractice.fmc.business.entity.sys.org.beans.context.SysOrgRolePluginContext;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRolePlugin;
import com.vispractice.fmc.business.services.simulator.ISimulatorService;

/**
 * 
 * 编  号：
 * 名  称：SimulatorServiceImpl
 * 描  述：SimulatorServiceImpl.java
 * 完成日期：2017年8月9日 下午3:08:01
 * 编码作者："LaiJiashen"
 */
@Slf4j
@Service
public class SimulatorServiceImpl implements ISimulatorService,ApplicationContextAware{
	
	@Autowired
	private SysOrgRoleRepository sysOrgRoleRepository;
	
	private ApplicationContext applicationContext;
	
	/**
	 * 模拟器计算
	 * @param personList
	 * @param simulateUser
	 * @return 计算结果
	 */
	@Override
	public String startSimulate(List<SysOrgElement> personList,SysOrgElement simulateUser){
		
		StringBuffer sb = new StringBuffer();
		
		for (SysOrgElement sysOrgElement : personList) {
			//
			if ((new Long(SysOrgConstant.ORG_TYPE_ROLE)).equals(sysOrgElement.getFdOrgType())) {
				List<SysOrgElement> list = new ArrayList<SysOrgElement>();
				try {
					list = this.parseSysOrgRole(sysOrgElement, simulateUser);
				} catch (Exception e) {
					log.error("计算发生错误！");
					e.printStackTrace();
				}
				sb.append(sysOrgElement.getFdName()).append(" = ").append(this.getElementName(list)).append("<br>");
			}else {
				sb.append(sysOrgElement.getFdName()).append("<br>");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 解释组织架构中的角色信息<br>
	 * 例子：originElement输入“直线领导”的角色，baseElement输入“张三”对应的个人，返回张三的直线领导
	 * 
	 * @param originElement
	 *            需要解释的原始信息，可以为任何类型的组织架构元素
	 * @param baseElement
	 *            基于解释的用户信息，可为个人、岗位、部门、机构
	 * @return 解释的结果
	 * @throws Exception
	 */
	public List<SysOrgElement> parseSysOrgRole (SysOrgElement originElement , SysOrgElement baseElement ) throws Exception{
		SysOrgRolePluginContext pluginContext = this.setRolePluginContext(originElement, baseElement);
		ISysOrgRolePlugin sysOrgRolePlugin = (ISysOrgRolePlugin) applicationContext.getBean(pluginContext.getRole().getFdPlugin());
		List<SysOrgElement> list = sysOrgRolePlugin.parse(pluginContext);
		return list;
	}
	
	/**
	 * 设置RolePluginContext
	 * @param roleElement
	 * @param simulateElement
	 * @return RolePluginContext
	 */
	private SysOrgRolePluginContext setRolePluginContext(SysOrgElement roleElement,SysOrgElement simulateElement) {
		SysOrgRole role = sysOrgRoleRepository.findOne(roleElement.getFdId());
		SysOrgRolePluginContext context = new SysOrgRolePluginContext(simulateElement, role);
		return context;
	}
	
	/**
	 * 
	 * 获取组织架构名称
	 * @Title: getElementName 
	 * @param elements
	 * @return 
	 */
	private String getElementName(List<SysOrgElement> elements){
		StringBuffer sb = new StringBuffer();
		if (elements.size()==0) {
			return "";
		}
		for (SysOrgElement sysOrgElement : elements) {
			sb.append(sysOrgElement.getFdName()).append(";");
		}
		if (sb.toString().endsWith(";")) {
			return sb.toString().substring(0, sb.toString().length()-1);
		}else {
			return sb.toString();
		}
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

}
