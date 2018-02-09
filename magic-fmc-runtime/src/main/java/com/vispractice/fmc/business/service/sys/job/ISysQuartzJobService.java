package com.vispractice.fmc.business.service.sys.job;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.job.SysQuartzJob;


public interface ISysQuartzJobService {

	/**
	 * 分页查询系统任务
	 * @param quartzJob
	 * @param pageable
	 * @return
	 */
	public Page<SysQuartzJob> findBySearch(final SysQuartzJob quartzJob, Pageable pageable);
	/**
	 * 报错系统任务
	 * @param quartzJob
	 */
	public void save(SysQuartzJob quartzJob);
	/**
	 * 删除系统任务
	 * @param quartzJob
	 */
	public void delete(SysQuartzJob quartzJob);
	/**
	 * 查询所有定时任务
	 * @return
	 */
	public List<SysQuartzJob> findAll();
	/**
	 * 根据ID查询定时任务
	 * @param fdId
	 * @return
	 */
	public SysQuartzJob findByFdId(String fdId);
	/**
	 * 初始化系统任务
	 */
	public void initSysQuartzJob(List<Map<String,String>> list);
	/**
	 * 发布定时任务
	 * @param quartzJob
	 * @param executeType
	 */
	public void executeJob(SysQuartzJob quartzJob,String executeType);
	
}
