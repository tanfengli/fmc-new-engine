package com.vispractice.fmc.business.entity.sys.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;

@Repository
public interface SysQuartzJobRepository
		extends JpaRepository<SysQuartzJob, String>, JpaSpecificationExecutor<SysQuartzJob> {

}