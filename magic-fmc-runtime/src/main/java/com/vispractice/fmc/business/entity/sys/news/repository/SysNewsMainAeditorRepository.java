package com.vispractice.fmc.business.entity.sys.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMainAeditor;

@Repository
public interface SysNewsMainAeditorRepository extends JpaRepository<SysNewsMainAeditor, String>, JpaSpecificationExecutor<SysNewsMainAeditor>{

}
