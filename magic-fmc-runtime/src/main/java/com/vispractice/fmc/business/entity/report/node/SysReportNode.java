package com.vispractice.fmc.business.entity.report.node;

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
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name = "sys_node_statistics_info")
@JsonIgnoreProperties(ignoreUnknown=true)
@NamedStoredProcedureQuery(name="sys_node_statistics_report",procedureName="sys_magic_report.sys_node_statistics_report",
parameters = {
	@StoredProcedureParameter(name = "fd_start_date",mode = ParameterMode.IN,type = Date.class),
	@StoredProcedureParameter(name = "fd_end_date",mode = ParameterMode.IN,type = Date.class)
})
public class SysReportNode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;
	
	@Column(name = "FD_FACT_NODE_ID")
	private String fdFactNodeId;
	
	@Column(name = "FD_FACT_NODE_NAME")
	private String fdFactNodeName;
	
	@Column(name = "FD_TEMPLATE_ID")
	private String fdTemplateId;
	
	@Column(name = "FD_TEMPLATE_NAME")
	private String fdTemplateName;
	
	@Column(name = "FD_AVG_TIME")
	private String fdAvgTime;
	
	@Column(name = "FD_ALERT_TIME")
	private String fdAlertTime;
	
	@Column(name = "FD_OVERDUE_NUMBER")
	private String fdOverdueNumber;
	
	@Column(name = "FD_OVERDUE_AVG")
	private String fdOverdueAvg;
	
	@Transient
	private Date fdStartDate;
	
	@Transient
	private Date fdEndDate;
}
