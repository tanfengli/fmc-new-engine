package com.vispractice.fmc.business.entity.sys.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.SysInterfaceParam;

@Repository
public interface SysInterfaceParamRepository extends JpaRepository<SysInterfaceParam,String>,JpaSpecificationExecutor<SysInterfaceParam> {
	/**
	 * 根据接口编号查询参数
	 * @param interfaceId
	 * @return
	 */
	public List<SysInterfaceParam> findByFdInterfaceId(String interfaceId);
	
	/**
	 * 根据接口编号删除
	 * @param interfaceId
	 */
	public void deleteByFdInterfaceId(String interfaceId);
	
	@Query("from SysInterfaceParam p where p.fdInterfaceId=?1 and p.fdName=?2")
	public SysInterfaceParam findByFdInterfaceIdAndFdName(String interfaceId,String fdName);
}
