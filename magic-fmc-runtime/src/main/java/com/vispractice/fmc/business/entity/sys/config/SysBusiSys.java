package com.vispractice.fmc.business.entity.sys.config;

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

/**
 * 业务系统配置 编 号：<br/>
 * 名 称：SysWfVarInfo<br/>
 * 描 述：<br/>
 * 完成日期：2016年12月17日 下午1:45:48<br/>
 * 编码作者：ZhouYanyao <br/>
 */
@Entity
@Data
@Table(name = "sys_busi_sys")
@NamedQuery(name = "SysBusiSys.findAll", query = "SELECT s FROM SysBusiSys s")
public class SysBusiSys implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_CODE")
	private String fdCode;

	@Column(name = "FD_ORDER")
	private String fdOrder;

	@Column(name = "FD_CREATED_DATE")
	private Date fdCreatedDate;

	@Column(name = "FD_CREATED_BY")
	private String fdCreatedBy;

	@Column(name = "FD_ENABLED")
	private Long fdEnabled;
	
	@Transient
	private String fdParameter;
}
