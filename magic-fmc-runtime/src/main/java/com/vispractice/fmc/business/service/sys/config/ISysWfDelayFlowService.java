package com.vispractice.fmc.business.service.sys.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.config.SysWfDelayFlow;
import com.vispractice.fmc.business.entity.sys.config.view.SysWfDelayV;

/**
 * 
 * 编  号：
 * 名  称：
 * 描  述：
 * 完成日期：2017年12月5日 下午4:00:10
 * 编码作者："LaiJiashen"
 */
public interface ISysWfDelayFlowService {	
	/**
	 * 根据条件查询所有预警设置
	 * @param sysWfDelayV
	 * @param pageable
	 * @return
	 */
	public Page<SysWfDelayV> findAll(SysWfDelayV sysWfDelayV,Pageable pageable);

	/**
	 * 保存
	 * @Title: save 
	 * @param sysWfDelayFlow
	 */
	public void save(SysWfDelayFlow sysWfDelayFlow);

	/**
	 * 删除一条 记录
	 * @Title: deleteOne 
	 * @param fdId
	 */
	public void deleteOne(String fdId);

}
