package com.vispractice.fmc.business.entities.setting.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entities.setting.view.RoleCateV;

@Repository
public interface RoleCateVRepository  extends JpaRepository<RoleCateV,String>,JpaSpecificationExecutor<RoleCateV> {
	//分页，排序，条件查询
	Page<RoleCateV> findAll(Specification<RoleCateV> spec,Pageable pageable);
}
