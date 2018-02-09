package com.vispractice.fmc.business.entities.setting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.setting.view.RoleAssignV;


@Repository
public interface RoleAssignVRepository  extends JpaRepository<RoleAssignV, String>, JpaSpecificationExecutor<RoleAssignV>  {

}
