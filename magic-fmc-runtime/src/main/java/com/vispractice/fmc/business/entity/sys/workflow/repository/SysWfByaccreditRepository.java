package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfByaccredit;

@Repository
public interface SysWfByaccreditRepository extends JpaRepository<SysWfByaccredit, String>, JpaSpecificationExecutor<SysWfByaccredit>{

	/**
	 * 
	 * 根据工作项ID获取授权信息<br/>
	 * @param fdWorkitemId
	 * @return
	 */
	public List<SysWfByaccredit> findByFdWorkitemId(String fdWorkitemId);
	
}
