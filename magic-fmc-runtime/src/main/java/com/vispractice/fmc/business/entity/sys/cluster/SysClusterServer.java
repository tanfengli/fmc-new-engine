package com.vispractice.fmc.business.entity.sys.cluster;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 群集信息
 * 编  号：<br/>
 * 名  称：SysClusterServer <br/>
 * 描  述：<br/>
 * 完成日期：2017年1月3日 下午6:29:44 <br/>
 * 编码作者：ZhouYanyao <br/>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name="sys_cluster_server")
@NamedQuery(name="SysClusterServer.findAll",query="SELECT s FROM SysClusterServer s")
public class SysClusterServer implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	public static SysClusterServer localSever = null;
	
	/**
	 * 判定为死亡的时间间隔
	 */
	public static final int DEAD_TIME =  60 * 1000;
	
	/**
	 * 调度模式：启用所有调度器
	 */
	public static final String DISPATCHERTYPE_ALL = "2";

	/**
	 * 调度模式：启用本地调度器
	 */
	public static final String DISPATCHERTYPE_LOCAL = "1";

	/**
	 * 调度模式：禁用所有调度器
	 */
	public static final String DISPATCHERTYPE_NONE = "0";
	 
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId; 
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="FD_KEY")
	private String fdKey;
	
	@Column(name="FD_ANONYMOUS")
	private String fdAnonymous;
	
	@Column(name="FD_DISPATCHER_TYPE")
	private String fdDispatcherType;
	
	@Column(name="FD_URL")
	private String fdUrl;
	
	@Column(name="FD_PID")
	private String fdPid;
	
	@Column(name="FD_ADDRESS")
	private String fdAddress;
	
	@Column(name="FD_START_TIME")
	private Date fdStartTime;
	
	@Column(name="FD_REFRESH_TIME")
	private Date fdRefreshTime;
	
	@Column(name="FD_SHUTDOWN")
	private String fdShutdown;
	
	@Transient
	private String sysWfEventService;
	
	@Transient
	private String sysQuartzDispatcher;
	
	@Transient
	private String sysOperatorType;
	
}
