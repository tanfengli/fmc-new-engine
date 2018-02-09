package com.vispractice.fmc.business.entity.sys.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.category.SysCategoryMainEditor;

@Repository
public interface SysCategoryMainEditorRepository extends JpaRepository<SysCategoryMainEditor,String>,JpaSpecificationExecutor<SysCategoryMainEditor>{
	
	@Modifying
	@Query(value="delete from Sys_Category_Main_Editor a where a.fd_Category_Id=?1",nativeQuery=true)
	public void deleteByCategoryId(String fdCategoryId);
	
	@Modifying
	@Query(value = "insert into Sys_Category_Main_Editor (fd_Category_Id,AUTH_EDITOR_ID) values (?1, ?2)",nativeQuery = true)
	public void insertOne(String fdCategoryId,String authEditorId);

	public List<SysCategoryMainEditor> findByFdCategoryId(String fdCategoryId);
}
