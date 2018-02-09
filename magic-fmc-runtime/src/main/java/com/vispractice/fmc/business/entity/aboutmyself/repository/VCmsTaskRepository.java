package com.vispractice.fmc.business.entity.aboutmyself.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.aboutmyself.VCmsTask;

@Repository
public interface VCmsTaskRepository extends JpaRepository<VCmsTask,String>,JpaSpecificationExecutor<VCmsTask> {
}