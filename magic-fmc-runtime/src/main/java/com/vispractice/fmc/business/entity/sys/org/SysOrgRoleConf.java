package com.vispractice.fmc.business.entity.sys.org;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "SYS_ORG_ROLE_CONF")
@JsonIgnoreProperties(ignoreUnknown = true)
@NamedQuery(name = "SysOrgRoleConf.findAll", query = "SELECT s FROM SysOrgRoleConf s")
public class SysOrgRoleConf {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_ORDER")
	private Long fdOrder;

	@Column(name = "FD_IS_AVAILABLE")
	private Long fdIsAvailable;

	@Transient
	private List<SysOrgElement> editorArray;

	@Transient
	private String editorName;

	@ManyToMany(targetEntity = SysOrgElement.class)
	@JoinTable(name = "sys_org_role_line_editor", inverseJoinColumns = @JoinColumn(name = "auth_editor_id", referencedColumnName = "fd_id"), joinColumns = @JoinColumn(name = "fd_role_line_conf_id", referencedColumnName = "fd_id"))
	private List<SysOrgElement> sysRoleLineEditors;


}
