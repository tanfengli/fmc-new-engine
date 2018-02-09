package com.vispractice.fmc.business.entity.sys.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.category.SysCategoryMainReader;

@Repository
public interface SysCategoryMainReaderRepository extends JpaRepository<SysCategoryMainReader, String>, JpaSpecificationExecutor<SysCategoryMainReader>{

	@Modifying
	@Query("delete from SysCategoryMainReader a where a.fdCategoryId=?1")
	public void deleteByFdCategoryId(String fdCategoryId);

	public List<SysCategoryMainReader> findByFdCategoryId(String fdCategoryId);
}
