package com.vispractice.fmc.business.entity.sys.read.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.read.SysReadLog;
/**
 * 
 * 编  号：
 * 名  称：SysReadLogNoteRepository
 * 描  述：阅读日志持久化接口
 * 完成日期：2017年10月18日 下午4:42:47
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysReadLogRepository extends JpaRepository<SysReadLog, String>, JpaSpecificationExecutor<SysReadLog> {

	/**
	 * 通过单据id获取已阅读
	 * @Title: getReaderNames 
	 * @param billId
	 * @return
	 */
	@Query("select e from SysOrgElement e where e.fdId in (select distinct l.fdReaderId from SysReadLog l where l.fdModelId=?1 and l.fdReadType=?2)")
	public List<SysOrgElement> getReaderElements(String billId,Long fdReaderType);
	
	/**
	 * 计算阅读次数
	 * @param billId 单据id
	 * @param fdReadType 阅读单据类型
	 * @return
	 */
	@Query("select count(1) from SysReadLog l where l.fdModelId=?1 and l.fdReadType=?2")
	public Long countReadTimes(String billId,Long fdReadType);
	
}
