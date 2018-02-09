package com.vispractice.fmc.business.services.sys;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfUsage;

public interface ISysWfUsageService {

	public Page<SysWfUsage> findAll(SysWfUsage sysWfUsage, Pageable pageable);

	public Page<SysWfUsage> findBySearch(final SysWfUsage sysWfUsage, Pageable pageable);

	/**
	 * 保存
	 */
	public void save(SysWfUsage sysWfUsage);

	/**
	 * 查询
	 */
	public SysWfUsage get(String id);

	/**
	 * 删除
	 */
	public void delete(SysWfUsage sysWfUsage);

	/**
	 * 验证审批类型默认值唯一性
	 */
	public int findByFdUsageTypeAndFdIsSysSetup(String fdUsageType,String fdIsSysSetup,String fdId);

	Map<String, List<SysWfUsage>> noPageFind();

	void deleteById(String fdId);
}
