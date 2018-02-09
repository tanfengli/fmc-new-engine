package com.vispractice.fmc.business.entity.km.review.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.km.review.KmReviewSn;

@Repository
public interface KmReviewSnRepository extends JpaRepository<KmReviewSn,String>,JpaSpecificationExecutor<KmReviewSn>{
	
	/**
	 * 
	 * 实现流程: 根据模块名，模板ID，单号前缀查询（加了悲观锁）<br/>
	 * @param modelName 模块名
	 * @param templateId 模板ID
	 * @param prefix 前缀
	 * @return
	 */
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public List<KmReviewSn> findByFdModelNameAndFdTemplateIdAndFdPrefix(String modelName,String templateId,String prefix);

}
