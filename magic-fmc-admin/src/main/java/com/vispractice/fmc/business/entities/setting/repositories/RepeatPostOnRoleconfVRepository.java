package com.vispractice.fmc.business.entities.setting.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.setting.view.RepeatPostOnRoleconfV;

@Repository
public interface RepeatPostOnRoleconfVRepository extends JpaRepository<RepeatPostOnRoleconfV, String>, JpaSpecificationExecutor<RepeatPostOnRoleconfV>{

	public List<RepeatPostOnRoleconfV> findByConfId(String confId);

	
	
}
