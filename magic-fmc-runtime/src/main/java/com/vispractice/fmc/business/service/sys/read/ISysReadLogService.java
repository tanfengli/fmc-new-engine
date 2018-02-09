package com.vispractice.fmc.business.service.sys.read;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.read.SysReadLogV;

/**
 * 
 * 编  号：
 * 名  称：ISysReadLogService
 * 描  述：阅读日志接口
 * 完成日期：2017年10月18日 下午4:49:22
 * 编码作者："LaiJiashen"
 */
public interface ISysReadLogService {

	/**
	 * 
	 * 获取当前单据已阅人员
	 * @Title: getReaderNames 
	 * @param billId 单据id
	 * @return
	 */
	public String getReaderNames(String billId);

	/**
	 * 
	 * @param billId
	 * @param pageable
	 * @return
	 */
	public Page<SysReadLogV> getReaderList(String billId, Pageable pageable);

	/**
	 * 插入一条阅读信息
	 * @param billId 单据id
	 * @param readerId 阅读者id
	 * @throws Exception
	 */
	public void save(String billId, String readerId) throws Exception;

	/**
	 * 阅读次数(点击率)仅统计发布阶段的阅读记录
	 * @param billId
	 * @return
	 */
	public Long countReadTimes(String billId);

}
