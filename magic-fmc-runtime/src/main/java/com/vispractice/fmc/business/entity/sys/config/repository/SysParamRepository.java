package com.vispractice.fmc.business.entity.sys.config.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.config.SysParam;

@Repository
public interface SysParamRepository extends JpaRepository<SysParam,String>,JpaSpecificationExecutor<SysParam> {

	/**
	 * 根据编码获取系统参数
	 * @Title: findByFdCodeIn 
	 * @param fdCodeList
	 * @return
	 */
	public List<SysParam> findByFdCodeIn(List<String> fdCodeList);
	
	/**
	 * 根据code更新value值
	 * @Title: updateFdValueByFdCode 
	 * @param fdCode
	 * @param fdValue
	 */
	@Modifying
	@Query("update SysParam p set p.fdValue=?2 where p.fdCode=?1")
	public void updateFdValueByFdCode(String fdCode,String fdValue);
}
