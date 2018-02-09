package com.vispractice.fmc.business.entity.sys.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.SysNewsTemplateEditor;


@Repository
public interface SysNewsTemplateEditorRepository extends JpaRepository<SysNewsTemplateEditor,String>,JpaSpecificationExecutor<SysNewsTemplateEditor> {
	public List<SysNewsTemplateEditor> findByFdTemplateId(String fdTemplateId);

	/**
	 * 删除
	 * @param fdId
	 */
	@Modifying
	@Query(value="delete from sys_news_template_editor where fd_template_id = ?1",nativeQuery = true)
	public void delByFdTemplateId(String fdId);
	
	/**
	 * 添加
	 * @param fdTemplateId
	 * @param authEditorId
	 */
	@Modifying
	@Query(value = "insert into sys_news_template_editor (fd_Template_Id,AUTH_EDITOR_ID) values (?1, ?2)",nativeQuery = true)
	public void insertOne(String fdTemplateId,String authEditorId);
}
