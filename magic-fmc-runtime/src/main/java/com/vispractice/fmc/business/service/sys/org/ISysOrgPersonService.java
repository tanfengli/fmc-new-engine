package com.vispractice.fmc.business.service.sys.org;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;

/**
 * 
 * 编  号：<br/>
 * 名  称：ISysOrgPersonService<br/>
 * 描  述：用户管理<br/>
 * 完成日期：2016年12月14日 下午2:12:25<br/>
 * 编码作者：sky<br/>
 */
public interface ISysOrgPersonService {
	/**
	 * 模糊查找人员信息
	 * @param sysOrgPerson
	 * @param pageable
	 * @return
	 */
	public Page<SysOrgPerson> findByExample(SysOrgPerson sysOrgPerson, Pageable pageable);

	/**
	 * 查找人员信息
	 * @param sysOrgPerson
	 * @param pageable
	 * @return
	 */
	public Page<SysOrgPerson> findBySearch(final SysOrgPerson sysOrgPerson, Pageable pageable);

	/**
	 * 根据登录名查找人员信息
	 * @param loginName
	 * @return
	 */
	public SysOrgPerson getAvailablePersonByLoginName(String loginName);
	
	/**
	 * 保存
	 */
	public void save(SysOrgPerson sysOrgPerson);

	/**
	 * 查询
	 */
	public SysOrgPerson get(String fdId);

	/**
	 * 删除
	 */
	public void delete(String fdId);

}
