package com.vispractice.fmc.business.entity.sys.read.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.read.SysReadLogV;

/**
 * 
 * 编  号：
 * 名  称：SysReadLogVRepository
 * 描  述：阅读日志视图持久化接口
 * 完成日期：2017年10月18日 下午6:27:13
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysReadLogVRepository extends JpaRepository<SysReadLogV, String>, JpaSpecificationExecutor<SysReadLogV>{

	/**
	 * 获取当前单据的被阅读的记录
	 * @param billId 单据id
	 * @param fdReadType 阅读的流程类型
	 * @param pageable
	 * @return
	 */
	public Page<SysReadLogV> findByFdModelId(String billId,Pageable pageable);

}
