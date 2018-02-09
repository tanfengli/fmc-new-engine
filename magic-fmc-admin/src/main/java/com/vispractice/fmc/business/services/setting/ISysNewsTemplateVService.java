package com.vispractice.fmc.business.services.setting;

import com.vispractice.fmc.business.entity.sys.news.view.SysNewsTemplateV;

/**
 * 
 * 编  号：
 * 名  称：ISysNewsTemplateVService
 * 描  述：模板试图接口
 * 完成日期：2017年11月27日 下午3:47:54
 * 编码作者："LaiJiashen"
 */
public interface ISysNewsTemplateVService {

	/**
	 * 
	 * @param fdId
	 * @return
	 */
	public SysNewsTemplateV findOne(String fdId);

}
