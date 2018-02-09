package com.vispractice.fmc.business.entity.sys.org;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "SYS_ORG_GROUP_ELEMENT")
public class SysOrgGroupElement {

	@Id
	@Column(name = "FD_ELEMENTID")
	private String fdElementid;

	@Column(name = "FD_GROUPID")
	private String fdGroupid;

}
