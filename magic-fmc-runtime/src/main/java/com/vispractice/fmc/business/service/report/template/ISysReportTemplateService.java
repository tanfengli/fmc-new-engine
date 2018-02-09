package com.vispractice.fmc.business.service.report.template;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.report.template.SysReportTemplate;

public interface ISysReportTemplateService {
	/**
	 * 按模板统计报表
	 * @param SysReportTemplate
	 * @param pageable
	 * @return
	 */
	public Page<SysReportTemplate> searchSysReportTemplate(SysReportTemplate SysReportTemplate,Pageable pageable);
	
	/**
	 * 导入报表数据
	 * @param fdStartDate
	 * @param fdEndDate
	 */
	public void importSysReportTemplate(Date fdStartDate,Date fdEndDate);
	
	/**
	 * 导出报表数据
	 * @param sysReportTemplate
	 */
	public void exportSysReportTemplate(SysReportTemplate sysReportTemplate);
}
