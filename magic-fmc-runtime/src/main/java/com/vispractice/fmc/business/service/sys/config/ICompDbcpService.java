package com.vispractice.fmc.business.service.sys.config;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.config.CompDbcp;




public interface ICompDbcpService {

	/**
	 * 获取 所有数据源
	 * @Title: findAll 
	 * @return
	 */
	public List<CompDbcp> findAll();

	/**
	 * 数据源分页查询
	 * @Title: getDbcpPage 
	 * @param pageable
	 * @return
	 */
	public Page<CompDbcp> getDbcpPage(Pageable pageable);

	/**
	 * 根据id删除数据源
	 * @Title: delById 
	 * @param fdId
	 */
	public void delById(String fdId);

	/**
	 * 保存数据源
	 * @Title: save 
	 * @param compDbcp
	 * @throws Exception
	 */
	public void save(CompDbcp compDbcp) throws Exception;

	/**
	 * 
	 * 根据条件分页查询
	 * @Title: findBySearch 
	 * @param compDbcp
	 * @param pageable
	 * @return
	 */
	public Page<CompDbcp> findBySearch(CompDbcp compDbcp, Pageable pageable);

	/**
	 * 测试数据库连接
	 * @Title: testConnection 
	 * @param compDbcp 数据源实体
	 * @return true为测试通过
	 * @throws Exception 
	 */
	public boolean testConnection(CompDbcp compDbcp) throws Exception;
	
	/**
	 * 根据fdName查询数据库
	 * @param fdName 数据库别名
	 * @return
	 */
	public CompDbcp findByFdName(String fdName);



}
