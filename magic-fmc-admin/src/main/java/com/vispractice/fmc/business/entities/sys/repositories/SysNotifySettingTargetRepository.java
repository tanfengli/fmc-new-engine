package com.vispractice.fmc.business.entities.sys.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vispractice.fmc.business.entities.sys.SysNotifySettingTarget;
 

public interface SysNotifySettingTargetRepository extends JpaRepository<SysNotifySettingTarget, String>, JpaSpecificationExecutor<SysNotifySettingTarget> {
	
	@Modifying
	@Query("delete from SysNotifySettingTarget where fdNotifySettingId = ?1")
	public void deleteByFdNotifySettingId(String fdNotifySettingId);
	
	@Query("select s.fdOrgId from SysNotifySettingTarget s where s.fdNotifySettingId = ?1")
	public List<String> findFdOrgId(String fdNotifySettingId); 
}
