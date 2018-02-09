package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfOperMain;

public interface ISysWfOperMainService {

	public Page<SysWfOperMain> findAll(SysWfOperMain operMain, Pageable pageable);

	public Page<SysWfOperMain> findBySearch(final SysWfOperMain operMain, Pageable pageable);

	/**
	 * 保存
	 */
	public SysWfOperMain save(SysWfOperMain operMain);

	/**
	 * 查询
	 */
	public SysWfOperMain getById(String id);

	/**
	 * 删除
	 */
	public void deleteByFdId(String fdId);
	
	/**
	 * 节点类型是否默认唯一性验证
	 */
	public int findByFdNodeTypeAndFdIsDefault(String fdNodeType,String fdIsDefault,String fdId);

	public List<SysWfOperMain> findList();
}
