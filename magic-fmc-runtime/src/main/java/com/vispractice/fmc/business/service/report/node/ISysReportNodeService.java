package com.vispractice.fmc.business.service.report.node;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.report.node.SysReportNode;

/**
 * 编  号：
 * 名  称：ISysReportNodeService
 * 描  述：ISysReportNodeService.java
 * 完成日期：2017年12月20日 上午10:37:34
 * 编码作者："LaiJiashen"
 */
public interface ISysReportNodeService {
	
	/**
	 * 按节点统计报表
	 * @param sysReportNode
	 * @param pageable
	 * @return
	 */
	public Page<SysReportNode> searchSysReportNode (SysReportNode sysReportNode,Pageable pageable);
	
	/**
	 * 导入报表数据
	 * @param fdStartDate
	 * @param fdEndDate
	 */
	public void importSysReportNode(Date fdStartDate,Date fdEndDate);
	
	/**
	 * 导出报表数据
	 * @param sysReportTemplate
	 */
	public void exportSysReportNode(SysReportNode sysReportNode);
}
