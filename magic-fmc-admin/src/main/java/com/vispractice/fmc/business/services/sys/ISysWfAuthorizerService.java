package com.vispractice.fmc.business.services.sys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorize;

public interface ISysWfAuthorizerService {

	public Page<SysWfAuthorize> findAll(SysWfAuthorize wfAuthorize, Pageable pageable);
  
	/**
	 * 保存
	 */
	public SysWfAuthorize save(SysWfAuthorize wfAuthorize);

	/**
	 * 查询
	 */
	public SysWfAuthorize get(String id);

	/**
	 * 删除
	 */
	public void deleteByFdId(String fdId);

}
