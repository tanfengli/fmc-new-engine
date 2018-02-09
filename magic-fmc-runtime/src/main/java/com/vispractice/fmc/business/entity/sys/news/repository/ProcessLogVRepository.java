package com.vispractice.fmc.business.entity.sys.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.view.ProcessLogV;

@Repository
public interface ProcessLogVRepository extends JpaRepository<ProcessLogV,String> {
	/**
	 * 根据流程实例标识获取审批日志
	 * @param wfInstanceId
	 * @param pageable
	 * @return
	 */
	List<ProcessLogV> findByWfInstanceId(String wfInstanceId);
}
