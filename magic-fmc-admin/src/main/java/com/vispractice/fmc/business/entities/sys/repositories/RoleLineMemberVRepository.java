package com.vispractice.fmc.business.entities.sys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entities.sys.view.RoleLineMemberV;

@Repository
public interface RoleLineMemberVRepository extends JpaRepository<RoleLineMemberV, String>, JpaSpecificationExecutor<RoleLineMemberV> {

}
