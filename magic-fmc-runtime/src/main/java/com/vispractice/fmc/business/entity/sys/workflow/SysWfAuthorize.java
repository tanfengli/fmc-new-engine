package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 流程授权
 * 编  号：<br/>
 * 名  称：SysWfAuthOrize<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月28日 下午5:48:51<br/>
 * 编码作者：Administrator<br/>
 */
@Entity
@Data
@Table(name="SYS_WF_AUTHORIZE")
@NamedQuery(name="SysWfAuthorize.findAll", query="SELECT s FROM SysWfAuthorize s")
public class SysWfAuthorize implements Serializable{
	private static final long serialVersionUID = 1L;
	// 授权处理
	public static String AUTHORIZE_PROCESS = "0";
	// 授权阅读
	public static String AUTHORIZE_READING = "1";
	
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_AUTHORIZE_TYPE")
	private String fdAuthorizeType;
	
	@Column(name="FD_START_TIME")
	private Date fdStartTime;
	
	@Column(name="FD_END_TIME")
	private Date fdEndTime;
		 
	@Column(name="FD_EXPIRE_DELETED")
	private String fdExpireDeleted;
	 
	@Column(name="FD_CREATE_TIME")
	private Date fdCreateTime;

	@Column(name="FD_AUTHORIZER")
	private String fdAuthorizer;	
	
	@Column(name="FD_CREATOR")
	private String fdCreator;
	
	@Column(name="FD_AUTHORIZED_PERSON")
	private String fdAuthorizedPerson;
	
	@Column(name="IS_ALL_SCOPE")
	private String isAllScope;
	
}
