package com.vispractice.fmc.business.entity.sys.news.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.news.view.ProcessHistoryV;

@Repository
public interface ProcessHistoryVRepository extends JpaRepository<ProcessHistoryV,String> {
	/**
	 * 根据流程实例标识获取历史记录
	 * @param wfInstanceId
	 * @return
	 */
	List<ProcessHistoryV> findByWfInstanceId(String wfInstanceId);
}
