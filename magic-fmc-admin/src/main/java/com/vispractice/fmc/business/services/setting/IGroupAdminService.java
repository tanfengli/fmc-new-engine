package com.vispractice.fmc.business.services.setting;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entities.setting.view.GroupAdmin;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.web.domain.setting.GroupMemberForm;

public interface IGroupAdminService {

	/**
	 * 查询
	 */
	Page<GroupAdmin> findBySearch(GroupAdmin groupAdmin, Pageable pageable);

	/**
	 * 删除
	 */
	void delById(GroupAdmin groupAdmin);

	/**
	 * 修改
	 */
	void update(GroupAdmin groupAdmin);

	/**
	 * 增加
	 * 
	 * @return
	 */
	Boolean save(GroupMemberForm groupMemberForm);

	public List<SysOrgElement> findPerson(String fdId, Long fdOrgType);

	// /**
	// * 成员描述
	// */
	// String memberDescription(String fdId);
	List<SysOrgElement> findGroupMember(String groupId);

	List<SysOrgElement> findMember(String fdId, List<Long> fdOrgType);

}
