package com.vispractice.fmc.business.services.sys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entities.sys.view.SysVarInfoV;

public interface ISysVarInfoVService {

	public Page<SysVarInfoV> findAll(SysVarInfoV infoV, Pageable pageable);

	public Page<SysVarInfoV> findBySearch(final SysVarInfoV infoV, Pageable pageable);

	/**
	 * 查询
	 */
	public SysVarInfoV get(Long id);
}
