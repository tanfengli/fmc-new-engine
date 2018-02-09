package com.vispractice.fmc.business.entities.sys.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.sys.SysAuthEdt;


@Repository
public interface SysAuthEdtRepository  extends JpaRepository<SysAuthEdt, String>, JpaSpecificationExecutor<SysAuthEdt> {

	List<SysAuthEdt> findByFdRoleid(String fdId);

	
	
}
