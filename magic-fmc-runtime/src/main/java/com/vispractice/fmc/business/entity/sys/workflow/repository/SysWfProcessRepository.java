package com.vispractice.fmc.business.entity.sys.workflow.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfProcess;

@Repository
public interface SysWfProcessRepository extends JpaRepository<SysWfProcess,String>,JpaSpecificationExecutor<SysWfProcess> {
	/**
	 * 根据流程标识查找流程实例
	 * @param modelId
	 * @return
	 */
	public List<SysWfProcess> findByFdModelId(String modelId);
	
	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Query("select p from SysWfProcess p where p.fdId = ?1")
	public  SysWfProcess lockProcess(String fdId);
	
	/**
	 * 根据流程实例找子流程信息
	 * @param wfInstanceId
	 * @return
	 */
	public List<SysWfProcess> findByFdParentid(String wfInstanceId);
	
	/**
	 * 根据单据id删除
	 * @Title: deleteByFdModelId 
	 * @param fdModelId
	 */
	@Modifying
	@Query("delete from SysWfProcess p where p.fdModelId in ?1")
	public void deleteAllByFdModelId(List<String> fdModelId);
}
