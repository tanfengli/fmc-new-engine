package com.vispractice.fmc.business.entity.sys.webservice;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "sys_webservice_log")
public class SysWebServiceLog implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;
	
	@Column(name = "FD_SERVICE_NAME")
	private String fdServiceName;
	
	@Column(name = "FD_SERVICE_BEAN")
	private String fdServiceBean;
	
	@Column(name = "FD_USER_NAME")
	private String fdUserName;
	
	@Column(name = "FD_CLIENT_IP")
	private String fdClientIp;
	
	@Column(name = "FD_START_TIME")
	private Date fdStartTime;
	
	@Column(name = "FD_END_TIME")
	private Date fdEndTime;
	
	@Column(name = "FD_EXEC_RESULT")
	private String fdExecResult;
	
	@Column(name = "FD_ERROR_MSG")
	private String fdErrorMsg;
	
	@Column(name = "FD_RUN_TIME")
	private Long fdRunTime; 
}
