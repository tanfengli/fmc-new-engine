package com.vispractice.fmc.business.entity.sys.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;

@Repository
public interface SysNewsMainRepository extends JpaRepository<SysNewsMain,String>,JpaSpecificationExecutor<SysNewsMain> {
	/**
	 * 依据流程ID查单据列表
	 * @param temId
	 * @return list 流程单据列表
	 */
	public List<SysNewsMain> findByFdTemplateId(String temId);
	
	/**
	 * 依据业务系统类型及业务编码查找流程实例
	 * @param BusiSysId	业务系统ID
	 * @param applyCode	业务编号
	 * @return SysNewsMain 流程单据对象
	 */
	public SysNewsMain findByBusiSysIdAndApplyCode(String BusiSysId,String applyCode);
	
	/**
	 * 根据人员和流程实例获取单据信息
	 * @param userNo
	 * @param wfInstanceId
	 * @return
	 */
	@Query("select a from SysNewsMain a,SysOrgElement b where a.docCreatorId=b.fdId and a.fdId=?2 and b.fdNo=?1")
	public SysNewsMain findByUserNoAndWfInstanceId(String userNo,String wfInstanceId);
	
	/**
	 * 根据id删除
	 * @Title: deleteByfdId 
	 * @param fdIdList
	 */
	@Modifying
	@Query("delete from SysNewsMain a where a.fdId in ?1")
	public void deleteAllByfdId(List<String> fdIdList);
}
