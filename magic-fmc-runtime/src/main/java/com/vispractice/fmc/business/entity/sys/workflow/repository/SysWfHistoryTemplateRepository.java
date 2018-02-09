package com.vispractice.fmc.business.entity.sys.workflow.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryTemplate;
/**
 * 
 * 编  号：
 * 名  称：SysWfHistoryTemplateVRepository
 * 描  述：SysWfHistoryTemplateVRepository.java
 * 完成日期：2017年12月8日 上午10:56:30
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysWfHistoryTemplateRepository
		extends JpaRepository<SysWfHistoryTemplate, String>, JpaSpecificationExecutor<SysWfHistoryTemplate> {

	

}
