package com.vispractice.fmc.business.services.setting;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entities.setting.RoleSetV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;

public interface IRoleSetVService {

	public List<RoleSetV> findByFdId(SysOrgRoleConf roleConf);

	Page<RoleSetV> findByExample(RoleSetV roleSet, Pageable pageable);

	/**
	 * 
	 * 实现流程:保存角色关系信息<br/>
	 * @Title: save
	 * @param roleSet
	 * @return
	 */
	public Boolean save(RoleSetV roleSet);

	/**
	 * 删除角色关系
	 * @Title: deleteById 
	 * @param fdId
	 */
	public void deleteById(String fdId);
}
