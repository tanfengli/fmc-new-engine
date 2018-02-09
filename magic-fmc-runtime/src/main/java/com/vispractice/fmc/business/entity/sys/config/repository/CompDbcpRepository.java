package com.vispractice.fmc.business.entity.sys.config.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.CompDbcp;

@Repository
public interface CompDbcpRepository extends JpaRepository<CompDbcp,String>,JpaSpecificationExecutor<CompDbcp> {
	//分页，排序，条件查询
	Page<CompDbcp> findAll(Specification<CompDbcp> spec,Pageable pageable);
	
	List<CompDbcp> findByfdId(String fdId);
	
	void deleteByfdId(String fdId);
	
	CompDbcp findByfdName(String fdName);
}
