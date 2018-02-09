package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;

@Repository
public interface SysWfTemplateRepository extends JpaRepository<SysWfTemplate,String>,JpaSpecificationExecutor<SysWfTemplate> {
	/**
	 * 根据模型标识获取流程模板
	 * @param fdModelId
	 * @return
	 */
	List<SysWfTemplate> findByFdModelId(String fdModelId);
}
