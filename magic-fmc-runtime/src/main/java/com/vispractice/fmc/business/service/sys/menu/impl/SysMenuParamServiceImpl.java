package com.vispractice.fmc.business.service.sys.menu.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.menu.SysMenuParam;
import com.vispractice.fmc.business.entity.sys.menu.repository.SysMenuParamRepository;
import com.vispractice.fmc.business.service.sys.menu.ISysMenuParamService;
/**
 * 
 * 编  号：
 * 名  称：SysMenuParamServiceImpl
 * 描  述：SysMenuParamServiceImpl.java
 * 完成日期：2017年10月16日 下午6:26:19
 * 编码作者："LaiJiashen"
 */
@Service
@Transactional
public class SysMenuParamServiceImpl implements ISysMenuParamService {
	@Autowired
	private SysMenuParamRepository sysMenuParamRepository;

	@Override
	public void saveSysMenuParams(List<SysMenuParam> sysMenuParams,String fdMenuId) {
		sysMenuParamRepository.deleteByFdMenuId(fdMenuId);
	
		for (SysMenuParam sysMenuParam : sysMenuParams) {
			sysMenuParam.setFdMenuId(fdMenuId);
			sysMenuParamRepository.save(sysMenuParam);
		}
	
	}

	@Override
	public List<SysMenuParam> findSysMenuParam(String fdMenuId) {
		return sysMenuParamRepository.findByFdMenuId(fdMenuId);
	}
}
