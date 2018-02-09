package com.vispractice.fmc.business.entity.sys.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfCommonTemplateV;

@Repository
public interface SysWfCommonTemplateVRepository
		extends JpaRepository<SysWfCommonTemplateV, String>, JpaSpecificationExecutor<SysWfCommonTemplateV> {

	@Query("select count(*) from SysWfCommonTemplateV a where a.fdIsDefault=?1 and a.fdId not in ?2")
	public int findByFdIsDefault(String fdIsDefault, String fdId);
}