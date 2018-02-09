package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfOperations;

public interface ISysWfOperationsService {

	public Page<SysWfOperations> findAll(SysWfOperations operMain, Pageable pageable);

	public Page<SysWfOperations> findBySearch(final SysWfOperations operMain, Pageable pageable);

	public List<SysWfOperations> findByFdOperatorId(String fdOperatorId);

	/**
	 * 保存
	 */
	public void save(SysWfOperations operMain);

	/**
	 * 查询
	 */
	public SysWfOperations get(Long id);

	/**
	 * 删除
	 */
	public void delete(SysWfOperations operMain);

	/**
	 * 实现流程:TODO(根据主表id删除从表信息)<br/>
	 * 1.XXX<br/>
	 * 
	 * @Title: deleteByFdOperatorId
	 * @param fdOperatorId
	 */
	public void deleteByFdOperatorId(String fdOperatorId);

}
