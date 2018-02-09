package com.vispractice.fmc.business.entity.sys.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMainAreader;


@Repository
public interface SysNewsMainAreaderRepository extends JpaRepository<SysNewsMainAreader, String>, JpaSpecificationExecutor<SysNewsMainAreader>{

}
