package com.vispractice.fmc.business.entity.report.template;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 基础设置 编 号：<br/>
 * 名 称：SysReportTemplate <br/>
 * 描 述：<br/>
 * 完成日期：2016年12月21日 下午4:52:38 <br/>
 * 编码作者：ZhouYanyao
 */
@Entity
@Data
@Table(name = "sys_template_statistics_info")
@JsonIgnoreProperties(ignoreUnknown=true)
@NamedStoredProcedureQuery(name="sys_template_statistics_report",procedureName="sys_magic_report.sys_template_statistics_report",
	parameters = {
		@StoredProcedureParameter(name = "fd_start_date",mode = ParameterMode.IN,type = Date.class),
		@StoredProcedureParameter(name = "fd_end_date",mode = ParameterMode.IN,type = Date.class)
})
public class SysReportTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;
	
	@Column(name = "FD_CATEGORY_ID")
	private String fdCategoryId;
	
	@Column(name = "FD_CATEGORY_NAME")
	private String fdCategoryName;
	
	@Column(name = "FD_TEMPLATE_ID")
	private String fdTemplateId;
	
	@Column(name = "FD_TEMPLATE_NAME")
	private String fdTemplateName;
	
	@Column(name = "FD_INSTANCE_NUMBER")
	private String fdInstanceNumber;
	
	@Column(name = "FD_MAX_TIME")
	private String fdMaxTime;
	
	@Column(name = "FD_MIN_TIME")
	private String fdMinTime;
	
	@Column(name = "FD_AVG_TIME")
	private String fdAvgTime;
	
	@Column(name = "FD_ALERT_TIME")
	private String fdAlertTime;
	
	@Column(name = "FD_OVERDUE_NUMBER")
	private String fdOverdueNumber;
	
	@Column(name = "FD_OVERDUE_AVG")
	private String fdOverdueAvg;
	
	@Column(name = "FD_OVERDUE_RATE")
	private String fdOverdueRate;
	
	@Column(name = "FD_START_DATE")
	private Date fdStartDate;
	
	@Column(name = "FD_END_DATE")
	private Date fdEndDate;
}
