package com.vispractice.fmc.business.entity.sys.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vispractice.fmc.business.entity.sys.news.view.SysNewsTemplateV;

public interface SysNewsTemplateVRepository extends JpaRepository<SysNewsTemplateV,String>,JpaSpecificationExecutor<SysNewsTemplateV> {
}
