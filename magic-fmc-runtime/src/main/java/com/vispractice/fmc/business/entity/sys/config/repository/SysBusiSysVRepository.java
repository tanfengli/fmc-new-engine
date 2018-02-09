package com.vispractice.fmc.business.entity.sys.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.view.SysBusiSysV;

@Repository
public interface SysBusiSysVRepository
		extends JpaRepository<SysBusiSysV, String>, JpaSpecificationExecutor<SysBusiSysV> {
}