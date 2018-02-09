package com.vispractice.fmc.business.entity.sys.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMainEditor;


@Repository
public interface SysNewsMainEditorRepository extends JpaRepository<SysNewsMainEditor, String>, JpaSpecificationExecutor<SysNewsMainEditor>{

}
