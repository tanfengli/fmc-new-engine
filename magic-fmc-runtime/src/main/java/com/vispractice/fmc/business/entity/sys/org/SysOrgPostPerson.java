package com.vispractice.fmc.business.entity.sys.org;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "SYS_ORG_POST_PERSON")
@IdClass(SysOrgPostPersonKey.class) 
public class SysOrgPostPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 岗位标识
	 */
	@Id
	@Column(name = "FD_POSTID")
	private String fdPostid;

	/**
	 * 人员标识
	 */
	@Id
	@Column(name = "FD_PERSONID")
	private String fdPersonid;
	
}