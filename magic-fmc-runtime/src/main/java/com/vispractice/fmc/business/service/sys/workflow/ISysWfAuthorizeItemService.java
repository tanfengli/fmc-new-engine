package com.vispractice.fmc.business.service.sys.workflow;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorizeItem;
 
  
public interface ISysWfAuthorizeItemService {
	
	/**
	 * 获取指定授权记录授权项列表
	 * @param fdAuthoirzeId
	 * @return
	 */
	public List<SysWfAuthorizeItem> findItemByFdAuthoirzeId(String fdAuthoirzeId);
 
	/**
	 * 保存
	 */
	public void save(SysWfAuthorizeItem authorizeItem);
 
	/**
	 * 删除
	 */
	public void deleteByFdAuthorizeId(String fdAuthorizeId);
}
