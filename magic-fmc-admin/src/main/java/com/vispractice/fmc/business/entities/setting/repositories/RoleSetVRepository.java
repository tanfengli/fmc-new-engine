package com.vispractice.fmc.business.entities.setting.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.setting.RoleSetV;


@Repository
public interface RoleSetVRepository   extends JpaRepository<RoleSetV, String>, JpaSpecificationExecutor<RoleSetV> {

	
	List<RoleSetV> findByFdId(String fdId);

	List<RoleSetV> findByCateId(String cateId);
	
}
