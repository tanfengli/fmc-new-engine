package com.vispractice.fmc.business.entities.sys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
/**
 * 
 * 名  称：SysAuthRra
 * 描  述：继承角色关系表
 * 完成日期：2017年10月12日 下午2:42:11
 * 编码作者："LaiJiashen"
 */
@Data
@Entity
@Table(name="SYS_AUTH_RRA")
@NamedQuery(name="SysAuthRra.findAll", query="SELECT s FROM SysAuthRra s")
public class SysAuthRra implements Serializable  {
	
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;


	@Column(name="FD_ROLEID")
	private String fdRoleid;
	
	@Id
	@Column(name="FD_INHROLEID")
	private String fdInhroleid;

}
