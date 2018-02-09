package com.vispractice.fmc.business.entity.sys.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.SysAppConfig;
 
@Repository
public interface SysAppConfigRepository
		extends JpaRepository<SysAppConfig, String>, JpaSpecificationExecutor<SysAppConfig> {
	@Modifying
	@Query("delete from SysAppConfig where fdField = ?1")
	public void delete(String fdField);
	
	public SysAppConfig findByFdField(String filed);
}