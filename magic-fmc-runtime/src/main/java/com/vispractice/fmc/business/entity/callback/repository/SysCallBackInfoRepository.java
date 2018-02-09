package com.vispractice.fmc.business.entity.callback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.callback.SysCallBackInfo;

@Repository
public interface SysCallBackInfoRepository extends JpaRepository<SysCallBackInfo,String>,JpaSpecificationExecutor<SysCallBackInfo>{
	
	public void deleteSysCallBackInfoByInstanceId(String instanceId);
	
	@Query("from SysCallBackInfo c where c.isSuccess=0")
	public List<SysCallBackInfo> querySysCallBackInfoList();
}
