package com.vispractice.fmc.business.entity.sys.org.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.view.RoleMemberV;

/**
 * 
 * 名  称：RoleMemberVRepository
 * 描  述：RoleMemberVRepository.java
 * 完成日期：2017年7月26日 上午9:06:03
 * 编码作者："LaiJiashen"
 */

@Repository
public interface RoleMemberVRepository extends JpaRepository<RoleMemberV,String>,JpaSpecificationExecutor<RoleMemberV> {
	
	public List<RoleMemberV> findByParentId(String parentId);
	
	public Page<RoleMemberV> findByConfId(String confId,Pageable pageable);
	
	public Page<RoleMemberV> findByHierarchyIdLike(String hierarchyId,Pageable pageable);

	@Query("from RoleMemberV a where a.confId=?1 and a.parentId is null")
	public List<RoleMemberV> findRoleMemberRoot(String confId);

	public Page<RoleMemberV> findByFdNameLikeAndHierarchyIdLike(String memberName,String hierarchyId,Pageable pageable);

	public Page<RoleMemberV> findByConfIdAndFdNameLike(String confId,String memberName,Pageable pageable);

}
