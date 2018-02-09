package com.vispractice.fmc.business.service.sys.workflow;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfHistoryTemplateV;
/**
 * 
 * 编  号：
 * 名  称：ISysWfHistoryTemplateService
 * 描  述：ISysWfHistoryTemplateService.java
 * 完成日期：2017年12月11日 上午9:08:34
 * 编码作者："LaiJiashen"
 */
public interface ISysWfHistoryTemplateService {
	
	
	/**
	 * 
	 * @param modelId 模板主表ID
	 * @param pageable
	 * @return
	 */
	public Page<SysWfHistoryTemplateV> findAllByModelId(String modelId, Pageable pageable);
	
}
