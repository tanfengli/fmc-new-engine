package com.vispractice.fmc.business.entities.sys.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Sys_Var_Info_V")
@NamedQuery(name="SysVarInfoV.findAll", query="SELECT p FROM SysVarInfoV p")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysVarInfoV implements Serializable { 

	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="VAR_NAME")
	private String varName;
	
	@Column(name="VAR_TYPE")
	private String varType;
	 
	@Column(name="VAR_USE_TYPE")
	private String varUseType;
	
	@Column(name="var_use_type_name")
	private String varUseTypeName;
	
	@Column(name="VAR_IS_JDBC")
	private String varIsJdbc;
	
	@Column(name="VAR_SQL")
	private String varSql;
	
	@Column(name="VAR_CODE")
	private String varCode;
	
	@Column(name="VAR_STATUS")
	private String varStatus;
	 
	@Column(name="VAR_CREATE_TIME")
	private Date varCreateTime;
	
	@Column(name="VAR_CREATOR_ID")
	private String varCreatorId;
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="VAR_CONN_FD_ID")
	private String varConnFdId;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getVarType() {
		return varType;
	}

	public void setVarType(String varType) {
		this.varType = varType;
	}

	public String getVarUseType() {
		return varUseType;
	}

	public void setVarUseType(String varUseType) {
		this.varUseType = varUseType;
	}
 
	public String getVarUseTypeName() {
		return varUseTypeName;
	}

	public void setVarUseTypeName(String varUseTypeName) {
		this.varUseTypeName = varUseTypeName;
	}

	public String getVarIsJdbc() {
		return varIsJdbc;
	}

	public void setVarIsJdbc(String varIsJdbc) {
		this.varIsJdbc = varIsJdbc;
	}

	public String getVarSql() {
		return varSql;
	}

	public void setVarSql(String varSql) {
		this.varSql = varSql;
	}

	public String getVarCode() {
		return varCode;
	}

	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}

	public String getVarStatus() {
		return varStatus;
	}

	public void setVarStatus(String varStatus) {
		this.varStatus = varStatus;
	}

	public Date getVarCreateTime() {
		return varCreateTime;
	}

	public void setVarCreateTime(Date varCreateTime) {
		this.varCreateTime = varCreateTime;
	}

	public String getVarCreatorId() {
		return varCreatorId;
	}

	public void setVarCreatorId(String varCreatorId) {
		this.varCreatorId = varCreatorId;
	}

	public String getFdName() {
		return fdName;
	}

	public void setFdName(String fdName) {
		this.fdName = fdName;
	}

	public String getVarConnFdId() {
		return varConnFdId;
	}

	public void setVarConnFdId(String varConnFdId) {
		this.varConnFdId = varConnFdId;
	}
}
