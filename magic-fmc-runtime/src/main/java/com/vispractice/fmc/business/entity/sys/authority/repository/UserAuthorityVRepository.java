package com.vispractice.fmc.business.entity.sys.authority.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vispractice.fmc.business.entity.sys.authority.UserAuthorityV;

/**
 * 
 * 编  号：
 * 名  称：UserAuthorityVRepository
 * 描  述：用户权限查询持久化接口
 * 完成日期：2017年10月13日 下午5:41:32
 * 编码作者："LaiJiashen"
 */
public interface UserAuthorityVRepository  extends JpaRepository<UserAuthorityV,String>,JpaSpecificationExecutor<UserAuthorityV> {
	
	/**
	 * 获取员工权限列表（分页）
	 * @param orgId
	 * @param pageable
	 * @return
	 */
	public Page<UserAuthorityV> findByUserOrgId(String orgId, Pageable pageable);

}
