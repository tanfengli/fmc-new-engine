package com.vispractice.fmc.business.entity.sys.news.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.news.view.DocumentSubmmitedV;

@Repository
public interface DocumentSubmmitedVRepository extends JpaRepository<DocumentSubmmitedV,String>,JpaSpecificationExecutor<DocumentSubmmitedV> {
	/**
	 * 查询我的申请接口
	 * @param creator
	 * @param pageable
	 * @return
	 */
	// Page<DocumentSubmmitedV> findByCreator(String creator,Pageable pageable);
	
	/**
	 * 
	 */
	Page<DocumentSubmmitedV> findAll(Specification<DocumentSubmmitedV> specification,Pageable pageable);
	
	/**
	 * 根据流程实例
	 * @param fdId
	 * @return
	 */
	List<DocumentSubmmitedV> findByFdId(String fdId);
}
