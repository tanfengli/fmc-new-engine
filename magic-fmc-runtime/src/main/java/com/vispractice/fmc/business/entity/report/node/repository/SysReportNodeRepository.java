package com.vispractice.fmc.business.entity.report.node.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.report.node.SysReportNode;
/**
 * 编  号：
 * 名  称：SysReportNodeRepository
 * 描  述：SysReportNodeRepository.java
 * 完成日期：2017年12月20日 上午10:36:38
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysReportNodeRepository extends JpaRepository<SysReportNode,String>,JpaSpecificationExecutor<SysReportNode> {
	@Procedure(name = "sys_node_statistics_report")
	void importSysReportNode(@Param("fd_start_date") Date fdStartDate,@Param("fd_end_date") Date fdEndDate);
}
