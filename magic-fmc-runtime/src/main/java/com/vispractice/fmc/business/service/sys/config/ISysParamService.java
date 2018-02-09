package com.vispractice.fmc.business.service.sys.config;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.config.SysParam;


/**
 * 
 * 编  号：
 * 名  称：参数服务
 * 描  述：
 * 完成日期：2017年12月5日 下午4:00:10
 * 编码作者："LaiJiashen"
 */
public interface ISysParamService {	


	/**
	 * 根据编码获取参数
	 * @Title: findByCodeIn 
	 * @param fdCodeList
	 */
	public List<SysParam> findByFdCodeIn(List<String> fdCodeList);
	
	/**
	 * 根据code更新value值
	 * @Title: updateFdValueByFdCode 
	 * @param fdCode
	 * @param fdValue
	 */
	public void updateFdValueByFdCode(String fdCode,String fdValue);

}
