package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfLogV;

public interface SysWfLogVRepository extends PagingAndSortingRepository<SysWfLogV,Long> {
	
	List<SysWfLogV> findByFdId(String fdId);

	@Query("select distinct a.fdName from SysWfLogV a where a.fdId=?1")
	List<String> getDistinctName(String fdId);

	@Query("select distinct new SysWfLogV(a.fdName,a.fdFactNodeId,a.fdFactNodeName) from SysWfLogV a where a.fdId=?1 order by a.fdFactNodeId")
	List<SysWfLogV> getDistinctNode(String fdId);
	
}
