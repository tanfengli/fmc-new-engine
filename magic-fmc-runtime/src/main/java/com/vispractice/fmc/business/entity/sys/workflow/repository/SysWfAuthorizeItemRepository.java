package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorizeItem;

@Repository
public interface SysWfAuthorizeItemRepository extends JpaRepository<SysWfAuthorizeItem,String>,JpaSpecificationExecutor<SysWfAuthorizeItem> {
	/**
	 * 根据权限标识删除权限项
	 * @param fdAuthorizeId
	 */
	@Modifying
	@Query("delete from SysWfAuthorizeItem where fdAuthorizeId = ?1")
	public void deleteByFdAuthorizeId(String fdAuthorizeId);
	
	@Modifying
	@Query(value="insert into Sys_Wf_Authorize_Item (fd_Authorize_Id,fd_Authorize_Org_Id)  values  (?1,?2)",nativeQuery=true)
	public void insertOne(String fdAuthorizeId,String fdAuthorizeOrgId);
	
	/**
	 * 获取指定授权记录的授权项列表
	 * @return
	 */
	List<SysWfAuthorizeItem> findByFdAuthorizeId(String fdAuthorzeId);
}