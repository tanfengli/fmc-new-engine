package com.vispractice.fmc.business.entity.sys.config.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;

@Repository
public interface SysBusiSysRepository extends JpaRepository<SysBusiSys,String>,JpaSpecificationExecutor<SysBusiSys> {
	/**
	 * 根据流程实例标识获取业务系统记录
	 * @param fdId
	 * @return
	 */
	@Modifying
	@Query("select b from SysNewsMain a,SysBusiSys b where a.fdNewsSource=b.fdCode and a.fdId=?1")
	List<SysBusiSys> findByBillId(String id);
	
	/**
	 * 根据模板标识获取业务系统记录
	 * @param fdId
	 * @return
	 */
	@Modifying
	@Query("select b from SysNewsTemplate a,SysBusiSys b where a.busiSysId=b.fdId and a.fdId=?1")
	List<SysBusiSys> findByTemId(String id);
	
	/**
	 * 根据业务系统标识获取业务系统记录
	 * @param fdCode
	 * @return
	 */
	SysBusiSys getByFdCode(String fdCode);
	
	/**
	 * 根据是否有效获取业务系统
	 * @param fdEnabled
	 * @return
	 */
	public List<SysBusiSys> findByFdEnabled(Long fdEnabled);
	
}