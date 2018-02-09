package com.vispractice.fmc.business.services.sys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfAuthorizerV;
 

public interface ISysWfAuthorizerVService {

	public Page<SysWfAuthorizerV> findAll(SysWfAuthorizerV authorizerV, Pageable pageable);
 
	public Page<SysWfAuthorizerV> findBySearch(final SysWfAuthorizerV authorizerV, Pageable pageable);
	
	public SysWfAuthorizerV get(Long id);  
	
	public SysWfAuthorizerV save(SysWfAuthorizerV authorizerV,SysOrgPerson sysOrgPerson);
	
	public void delete(SysWfAuthorizerV authorizerV);
}
