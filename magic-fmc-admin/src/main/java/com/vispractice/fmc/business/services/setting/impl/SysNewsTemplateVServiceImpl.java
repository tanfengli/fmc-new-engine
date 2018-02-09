package com.vispractice.fmc.business.services.setting.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsTemplateVRepository;
import com.vispractice.fmc.business.entity.sys.news.view.SysNewsTemplateV;
import com.vispractice.fmc.business.services.setting.ISysNewsTemplateVService;

/**
 * 
 * 编  号：
 * 名  称：SysNewsTemplateVServiceImpl
 * 描  述：模板试图接口实现类
 * 完成日期：2017年11月27日 下午3:48:26
 * 编码作者："LaiJiashen"
 */
@Service
public class SysNewsTemplateVServiceImpl implements ISysNewsTemplateVService{
	
	@Autowired
	private SysNewsTemplateVRepository sysNewsTemplateVRepository;
	 
	@Override
	public SysNewsTemplateV findOne(String fdId) {
		return sysNewsTemplateVRepository.findOne(fdId);
	}

}
