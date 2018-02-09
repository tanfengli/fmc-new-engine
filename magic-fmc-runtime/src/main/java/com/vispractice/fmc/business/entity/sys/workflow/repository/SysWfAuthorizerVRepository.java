package com.vispractice.fmc.business.entity.sys.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfAuthorizerV;

@Repository
public interface SysWfAuthorizerVRepository
		extends JpaRepository<SysWfAuthorizerV, Long>, JpaSpecificationExecutor<SysWfAuthorizerV> {
}