package com.vispractice.fmc.business.entities.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.sys.SysAuthCategory;


@Repository
public interface SysAuthCategoryRepository extends JpaRepository<SysAuthCategory, String>, JpaSpecificationExecutor<SysAuthCategory>  {

	void deleteByFdId(String fdId);
	
}
