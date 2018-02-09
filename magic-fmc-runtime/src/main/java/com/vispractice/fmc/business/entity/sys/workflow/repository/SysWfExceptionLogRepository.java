package com.vispractice.fmc.business.entity.sys.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfExceptionLog;

/**
 * 
 * 编 号： 名 称：SysWfExceptionLogRepository 描 述：流程异常日志JPA实现类 完成日期：2017年8月22日
 * 下午5:57:58 编码作者："LaiJiashen"
 */
@Repository
public interface SysWfExceptionLogRepository
		extends JpaRepository<SysWfExceptionLog, String>, JpaSpecificationExecutor<SysWfExceptionLog> {

	SysWfExceptionLog findByFdProcessId(String processId);
}
