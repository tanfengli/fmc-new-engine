package com.vispractice.fmc.business.service.sys.workflow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfHistoryTemplateVRepository;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfHistoryTemplateV;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfHistoryTemplateService;

/**
 * 
 * 编  号：
 * 名  称：SysWfHistoryTemplateServiceImpl
 * 描  述：SysWfHistoryTemplateServiceImpl.java
 * 完成日期：2017年12月11日 上午9:16:52
 * 编码作者："LaiJiashen"
 */
@Service
public class SysWfHistoryTemplateServiceImpl implements ISysWfHistoryTemplateService{

	@Autowired
	private SysWfHistoryTemplateVRepository sysWfHistoryTemplateVRepository;
	
	@Override
	public Page<SysWfHistoryTemplateV> findAllByModelId(final String modelId, Pageable pageable) {
		return sysWfHistoryTemplateVRepository.findByFdModelId(modelId,pageable);
	}
	
	

}
