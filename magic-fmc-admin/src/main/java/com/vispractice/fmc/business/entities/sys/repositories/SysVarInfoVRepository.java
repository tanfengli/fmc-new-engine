package com.vispractice.fmc.business.entities.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.sys.view.SysVarInfoV;

@Repository
public interface SysVarInfoVRepository extends JpaRepository<SysVarInfoV, Long>, JpaSpecificationExecutor<SysVarInfoV> {
}