package com.vispractice.fmc.business.entity.sys.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.view.SysWfDelayV;
/**
 * 
 * 编  号：
 * 名  称：WfMonitorTimeWorkVRepository
 * 描  述：WfMonitorTimeWorkVRepository.java
 * 完成日期：2017年12月7日 下午4:16:30
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysWfDelayVRepository extends JpaRepository<SysWfDelayV,String>,JpaSpecificationExecutor<SysWfDelayV>{

}
