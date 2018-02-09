package com.vispractice.fmc.business.entity.sys.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfCommonTemplate;
 

@Repository
public interface SysWfCommonTemplateRepository extends JpaRepository<SysWfCommonTemplate,String>,JpaSpecificationExecutor<SysWfCommonTemplate> {
	/**
	 * 获取默认通用模板
	 * @return
	 */
	@Query("from SysWfCommonTemplate a where a.fdIsDefault=1")
	public SysWfCommonTemplate findByFdIsDefault();
}