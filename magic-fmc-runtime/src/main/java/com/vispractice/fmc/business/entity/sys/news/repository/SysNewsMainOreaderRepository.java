package com.vispractice.fmc.business.entity.sys.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMainOreader;

@Repository
public interface SysNewsMainOreaderRepository
		extends JpaRepository<SysNewsMainOreader, String>, JpaSpecificationExecutor<SysNewsMainOreader> {

	public List<SysNewsMainOreader> findByFdMainId(String mainId);

}
