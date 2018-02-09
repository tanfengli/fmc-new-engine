package com.vispractice.fmc.business.entity.sys.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplateReader;


@Repository
public interface SysNewsTemplateReaderRepository extends JpaRepository<SysNewsTemplateReader, String>, JpaSpecificationExecutor<SysNewsTemplateReader>{

	List<SysNewsTemplateReader> findByFdTemplateId(String fdId);

	@Modifying
	@Query(value="delete from sys_news_template_reader where fd_template_id = ?1",nativeQuery = true)
	public void delByFdTemplateId(String fdId);
	
	@Modifying
	@Query(value = "insert into sys_news_template_reader (fd_Template_Id,auth_Reader_Id) values (?1, ?2)",nativeQuery = true)
	public void insertOne(String fdTemplateId,String authReaderId);

}
