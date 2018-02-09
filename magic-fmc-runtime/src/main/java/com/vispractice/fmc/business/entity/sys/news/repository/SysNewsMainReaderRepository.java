package com.vispractice.fmc.business.entity.sys.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMainReader;


@Repository
public interface SysNewsMainReaderRepository extends JpaRepository<SysNewsMainReader, String>, JpaSpecificationExecutor<SysNewsMainReader>{

}
