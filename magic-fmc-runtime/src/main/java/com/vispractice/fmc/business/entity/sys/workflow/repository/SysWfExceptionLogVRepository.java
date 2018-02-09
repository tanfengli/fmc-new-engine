package com.vispractice.fmc.business.entity.sys.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfExceptionLogV;

/**
 * 
 * 编  号：
 * 名  称：SysWfExceptionLogVRepository
 * 描  述：流程异常日志视图JPA实现类
 * 完成日期：2017年8月22日 下午6:14:16
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysWfExceptionLogVRepository 
		extends JpaRepository<SysWfExceptionLogV, String>, JpaSpecificationExecutor<SysWfExceptionLogV>{

}
