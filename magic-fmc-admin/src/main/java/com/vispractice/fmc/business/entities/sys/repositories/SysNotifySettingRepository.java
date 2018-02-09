package com.vispractice.fmc.business.entities.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.sys.SysNotifySetting;
 

@Repository
public interface SysNotifySettingRepository
		extends JpaRepository<SysNotifySetting, String>, JpaSpecificationExecutor<SysNotifySetting> {
}