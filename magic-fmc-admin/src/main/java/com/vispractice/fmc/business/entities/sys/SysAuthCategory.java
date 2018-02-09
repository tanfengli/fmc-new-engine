package com.vispractice.fmc.business.entities.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "SYS_AUTH_CATEGORY")
@NamedQuery(name = "SysAuthCategory.findAll", query = "SELECT s FROM SysAuthCategory s")
public class SysAuthCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_ORDER")
	private Long fdOrder;

	@Column(name = "DOC_CREATE_TIME")
	private Date docCreateTime;

	@Column(name = "DOC_CREATOR_ID")
	private String docCreateId;

}
