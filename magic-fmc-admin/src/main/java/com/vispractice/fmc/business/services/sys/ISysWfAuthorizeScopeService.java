package com.vispractice.fmc.business.services.sys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorizeScope;

public interface ISysWfAuthorizeScopeService {

	public Page<SysWfAuthorizeScope> findAll(SysWfAuthorizeScope authorizeScope, Pageable pageable);
  
	/**
	 * 保存
	 */
	public void save(SysWfAuthorizeScope authorizeScope);

	/**
	 * 查询
	 */
	public SysWfAuthorizeScope get(String id);

	/**
	 * 删除
	 */
	public void deleteByfdAuthorizeId(String fdId);

}
