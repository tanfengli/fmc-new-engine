package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorize;

@Repository
public interface SysWfAuthorizeRepository
		extends JpaRepository<SysWfAuthorize,String>,JpaSpecificationExecutor<SysWfAuthorize> {
	
	/**
	 * 根据标识回收权限
	 * @param fdId
	 */
	void deleteByFdId(String fdId);
	
	/**
	 * 获取指定模板范围的授权处理记录
	 * @param fdAuthorizeType
	 * @param date
	 * @param fdTemplateId
	 * @return
	 */
	@Query("select sysWfAuthorize from SysWfAuthorize sysWfAuthorize, SysWfAuthorizeScope authScope, SysWfAuthorizeItem sysWfAuthorizeItem"
			+ " where sysWfAuthorize.fdAuthorizeType = ?1 and sysWfAuthorize.fdId = authScope.fdAuthorizeId and authScope.fdTemplateId = ?3"
			+ " and (sysWfAuthorize.fdStartTime is null or sysWfAuthorize.fdStartTime <= ?2)"
			+ " and (sysWfAuthorize.fdEndTime is null or sysWfAuthorize.fdEndTime >= ?2)"
			+ " and sysWfAuthorize.fdId = sysWfAuthorizeItem.fdAuthorizeId and sysWfAuthorizeItem.fdAuthorizeOrgId = ?4")
	List<SysWfAuthorize> findAuthorizeHandlerByTemplateId(String fdAuthorizeType, Date date, String fdTemplateId,String elementId);
	
	/**
	 * 获取指定授权类型且没设置授权范围的授权记录
	 * @param fdAuthorizeType
	 * @return
	 */
	@Query("select sysWfAuthorize from SysWfAuthorize sysWfAuthorize, SysWfAuthorizeItem sysWfAuthorizeItem"
			+ " where sysWfAuthorize.fdAuthorizeType = ?1 and sysWfAuthorize.isAllScope='1'"
			+ " and (sysWfAuthorize.fdStartTime is null or sysWfAuthorize.fdStartTime <= ?2)"
			+ " and (sysWfAuthorize.fdEndTime is null or sysWfAuthorize.fdEndTime >= ?2)"
			+ "and sysWfAuthorize.fdId = sysWfAuthorizeItem.fdAuthorizeId and sysWfAuthorizeItem.fdAuthorizeOrgId = ?3")
	List<SysWfAuthorize> findAuthorizeHandlerWithAllScope(String fdAuthorizeType, Date date,String elementId);
	
	/**
	 * 获取指定模板范围的授权阅读记录
	 * @param fdAuthorizeType
	 * @param fdTemplateId
	 * @return
	 */
	@Query("select sysWfAuthorize from SysWfAuthorize sysWfAuthorize, SysWfAuthorizeScope authScope, SysWfAuthorizeItem sysWfAuthorizeItem "
			+ "where sysWfAuthorize.isAllScope = '0' and sysWfAuthorize.fdAuthorizeType = ?1 and authScope.fdTemplateId = ?2 "
			+ "and sysWfAuthorize.fdId = authScope.fdAuthorizeId and sysWfAuthorize.fdId = sysWfAuthorizeItem.fdAuthorizeId "
			+ "and sysWfAuthorizeItem.fdAuthorizeOrgId = ?3 ")
	List<SysWfAuthorize> findAuthorizeReaderByTemplateId(String fdAuthorizeType, String fdTemplateId, String elementId);
	
	/**
	 * 获取指定授权类型且没设置授权范围的授权记录
	 * @param fdAuthorizeType
	 * @return
	 */
	@Query("select sysWfAuthorize from SysWfAuthorize sysWfAuthorize, SysWfAuthorizeItem sysWfAuthorizeItem"
			+ " where sysWfAuthorize.fdAuthorizeType = '1' and sysWfAuthorize.isAllScope='1' and sysWfAuthorize.fdId = sysWfAuthorizeItem.fdAuthorizeId"
			+ " and sysWfAuthorizeItem.fdAuthorizeOrgId = ?1")
	List<SysWfAuthorize> findAuthorizeReaderWithAllScope(String elementId);
	
}

