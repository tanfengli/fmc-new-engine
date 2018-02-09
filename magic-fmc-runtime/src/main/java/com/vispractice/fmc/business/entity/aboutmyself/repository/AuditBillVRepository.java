package com.vispractice.fmc.business.entity.aboutmyself.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.aboutmyself.AuditBillV;

@Repository
public interface AuditBillVRepository extends JpaRepository<AuditBillV,String>,JpaSpecificationExecutor<AuditBillV> {
}
