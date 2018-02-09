package com.vispractice.fmc.business.entity.sys.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.menu.SysMenuParam;

@Repository
public interface SysMenuParamRepository extends JpaRepository<SysMenuParam,String>,JpaSpecificationExecutor<SysMenuParam> {
	/**
	 * 根据菜单ID查询参数
	 * @param interfaceId
	 * @return
	 */
	public List<SysMenuParam> findByFdMenuId(String interfaceId);
	
	/**
	 * 根据菜单id删除
	 * @param interfaceId
	 */
	public void deleteByFdMenuId(String interfaceId);
}
