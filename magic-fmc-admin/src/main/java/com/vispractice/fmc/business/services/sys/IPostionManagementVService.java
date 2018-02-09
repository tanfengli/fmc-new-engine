package com.vispractice.fmc.business.services.sys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entities.sys.view.PositionManagemetV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;

public interface IPostionManagementVService {

	public Page<PositionManagemetV> findAll(PositionManagemetV managemetV, Pageable pageable);

	public Page<PositionManagemetV> findBySearch(final PositionManagemetV managemetV, Pageable pageable);

	/**
	 * 查询
	 */
	public PositionManagemetV get(Long id);  
	
	public PositionManagemetV save(PositionManagemetV managemetV);
	
	public void delete(PositionManagemetV managemetV);
	
	/**
	 * 校验岗位名称唯一性
	 */
	public int findByFdName(String fdName,String fdId);

	/**
	 * 获取通用岗位
	 */
	public List<SysOrgElement> getAll();
}
