package com.vispractice.fmc.business.entity.sys.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitemData;

@Repository
public interface SysWfWorkitemDataRepository extends JpaRepository<SysWfWorkitemData, String>,JpaSpecificationExecutor<SysWfWorkitem> {
	
	/**
	 * 查找指定工作项和用户的工作数据
	 * @param fdWorkItemId
	 * @param fdUserId
	 * @return
	 */
	SysWfWorkitemData getWorkItemDataByFdWorkitemIdAndFdUserId(String fdWorkItemId, String fdUserId);
	
}
