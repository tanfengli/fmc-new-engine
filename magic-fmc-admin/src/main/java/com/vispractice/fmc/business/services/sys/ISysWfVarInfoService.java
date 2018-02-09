package com.vispractice.fmc.business.services.sys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfVarInfo;;

public interface ISysWfVarInfoService {

	public Page<SysWfVarInfo> findAll(SysWfVarInfo sysWfVarInfo, Pageable pageable);

	public Page<SysWfVarInfo> findBySearch(final SysWfVarInfo sysWfVarInfo, Pageable pageable);

	/**
	 * 保存
	 */
	public void save(SysWfVarInfo sysWfVarInfo);

	/**
	 * 查询
	 */
	public SysWfVarInfo get(Long id);

	/**
	 * 删除
	 */
	public void delete(SysWfVarInfo sysWfVarInfo);

	/**
	 * 变量名唯一性验证
	 */
	public int findByVarName(String varName, String fdId);

	/**
	 * 变量编码唯一性
	 */
	public int findByVarCode(String varCode, String fdId);

	/**
	 * 
	 * 查询所有基础操作
	 * 
	 * @return
	 */
	public List<SysWfVarInfo> findAll();
}
