package com.vispractice.fmc.business.services.sys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; 

import com.vispractice.fmc.business.entities.sys.SysLogError;

public interface ISysLogErrorService {

	public Page<SysLogError> findAll(SysLogError sysLogError, Pageable pageable);

	public Page<SysLogError> findBySearch(final SysLogError sysLogError, Pageable pageable);

	/**
	 * 保存
	 */
	public void save(SysLogError sysLogError);

	/**
	 * 查询
	 */
	public SysLogError get(String id);

	/**
	 * 删除
	 */
	public void delete(SysLogError sysLogError);

}
