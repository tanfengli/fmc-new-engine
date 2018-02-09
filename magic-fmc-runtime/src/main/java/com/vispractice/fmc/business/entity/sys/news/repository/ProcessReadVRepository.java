package com.vispractice.fmc.business.entity.sys.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.view.ProcessReadV;

@Repository
public interface ProcessReadVRepository extends JpaRepository<ProcessReadV,String>,JpaSpecificationExecutor<ProcessReadV> {
	
	/**
	 * 计算已读/未读数量
	 * @Title: countNum 
	 * @param userNo 用户编号
	 * @param isRead 是否已读
	 * @return
	 */
	@Query("select count(a) from ProcessReadV a where a.readUserNo=?1 and a.isRead=?2")
	public Long countNum(String userNo,Long isRead);
	
	/**
	 * 计算总数
	 * @Title: countNum 
	 * @param userNo 用户编号
	 * @return
	 */
	@Query("select count(a) from ProcessReadV a where a.readUserNo=?1")
	public Long countAll(String userNo);
	
}
