package com.vispractice.fmc.business.service.sys.authority;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.authority.UserAuthorityV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;

/**
 * 
 * 编  号：
 * 名  称：IUserAuthSearch
 * 描  述：用户权限查询接口
 * 完成日期：2017年10月13日 上午11:04:17
 * 编码作者："LaiJiashen"
 */
public interface IUserAuthSearchService {

	/**
	 * 获取有效用户
	 * @param element
	 * @param pageable
	 * @return
	 */
	public Page<SysOrgElement> findUser(SysOrgElement element, Pageable pageable);

	/**
	 * 
	 * 获取用户权限列表
	 * @param orgId 用户组织架构id
	 * @param pageable
	 * @return
	 */
	public Page<UserAuthorityV> findUserAuthority(String orgId, Pageable pageable);

}
