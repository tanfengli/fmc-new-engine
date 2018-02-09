package com.vispractice.fmc.business.entity.sys.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.vispractice.fmc.business.entity.sys.webservice.SysWebServiceLog;

/**
 * 编  号：
 * 名  称：SysWebServiceLogRepository
 * 描  述：接口调用日志信息
 * 完成日期：2017年10月18日 下午4:42:47
 * 编码作者：ZhouYanyao
 */
@Repository
public interface SysWebServiceLogRepository extends JpaRepository<SysWebServiceLog,String>,JpaSpecificationExecutor<SysWebServiceLog> {
}
