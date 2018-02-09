package com.vispractice.fmc.business.entity.sys.config;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 基础设置 编 号：<br/>
 * 名 称：SysAppConfig<br/>
 * 描 述：<br/>
 * 完成日期：2016年12月21日 下午4:52:38<br/>
 * 编码作者：zhaoxiu<br/>
 */
@Entity
@Data
@Table(name = "sys_app_config")
@NamedQuery(name = "SysAppConfig.findAll", query = "SELECT s FROM SysAppConfig s")
public class SysAppConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_KEY")
	private String fdKey;

	@Column(name = "FD_FIELD")
	private String fdField;

	@Column(name = "FD_VALUE")
	private String fdValue;

}
