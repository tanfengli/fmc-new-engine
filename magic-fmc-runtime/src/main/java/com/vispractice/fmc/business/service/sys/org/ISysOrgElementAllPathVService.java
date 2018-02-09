package com.vispractice.fmc.business.service.sys.org;

import com.vispractice.fmc.business.entity.sys.org.view.SysOrgElementAllPathV;
  

public interface ISysOrgElementAllPathVService { 

	
	public SysOrgElementAllPathV findByFdId(String fdId);

	/**
	 * 获取组织架构成员描述
	 * @param fdId 组织架构id
	 * @return 描述字符串
	 * @throws Exception
	 */
	public String getDescription(String fdId) throws Exception;

	/**
	 * 
	 * 获取组织架构成员全路径及描述
	 * @param fdId
	 * @return SysOrgElementAllPathV
	 * @throws Exception
	 */
	public SysOrgElementAllPathV getElementAllPath(String fdId) throws Exception; 
  
}
