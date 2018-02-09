package com.vispractice.fmc.business.entities.sys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
@Entity
@Table(name = "SYS_AUTH_ROLE")
public class SysAuthRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_ALIAS")
	private String fdAlias;

	@Column(name = "FD_DESCRIPTION")
	private String fdDescription;

	@Column(name = "FD_MODULE_PATH")
	private String fdModulePath;

	@Column(name = "FD_TYPE")
	private String fdType;

	@Column(name = "FD_CREATORID")
	private String fdCreatorid;

	@Column(name = "FD_CATEGORY_ID")
	private String fdCategoryId;

	/**
	 * 可维护者
	 */
	@ManyToMany(targetEntity = SysOrgElement.class)
	@JoinTable(name = "sys_auth_edt", inverseJoinColumns = @JoinColumn(name = "fd_orgelementid", referencedColumnName = "fd_id"), joinColumns = @JoinColumn(name = "fd_roleid", referencedColumnName = "fd_id"))
	private List<SysOrgElement> editorList;


}
