package com.vispractice.fmc.business.service.sys.config;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.config.SysWfInterface;

public interface ISysWfInterfaceService {
	/**
	 * 获取有效接口信息
	 * @return
	 */
	public List<SysWfInterface> findInterface();
	
	/**
	 * 分页查询接口信息
	 * @param sysWfInterface
	 * @param pageable
	 * @return
	 */
	public Page<SysWfInterface> searchInterface(SysWfInterface sysWfInterface,Pageable pageable);
	
	
	/**
	 * 获取接口树形节点
	 * @return
	 */
	List<SysWfInterface> findAllElements();
	
	/**
	 * 获取接口树形根节点
	 * @return
	 */
	List<SysWfInterface> getRootElements();
	
	/**
	 * 获取接口树形子节点
	 * @param parentId
	 * @return
	 */
	List<SysWfInterface> getSubElements(String parentId);
	
	/**
	 * 根据业务系统和类型获取接口
	 * @param fdBusiId
	 * @param fdType
	 * @return
	 */
	List<SysWfInterface> findByBusiAndType(String fdBusiId,String fdType);
	
	/**
	 * 保存接口信息
	 * @param sysWfInterface
	 */
	void saveInterface(SysWfInterface sysWfInterface);
	
	/**
	 * 保存接口信息
	 * @param fdId
	 */
	void deleteInterface(String fdId);
	
	/**
	 * 查看接口授权信息
	 * @param sysBusiCode
	 * @param sysWfInterfaceCode
	 * @return
	 */
	public SysWfInterface findByBusiCodeAndInterfaceCode(String sysBusiCode,String sysWfInterfaceCode);
}
