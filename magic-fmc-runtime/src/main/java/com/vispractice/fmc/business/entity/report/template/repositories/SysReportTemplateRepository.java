package com.vispractice.fmc.business.entity.report.template.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.report.template.SysReportTemplate;

@Repository
public interface SysReportTemplateRepository extends JpaRepository<SysReportTemplate,String>,JpaSpecificationExecutor<SysReportTemplate> {
	@Procedure(name = "sys_template_statistics_report")
	void importSysReportTemplate(@Param("fd_start_date") Date fdStartDate,@Param("fd_end_date") Date fdEndDate);
}
