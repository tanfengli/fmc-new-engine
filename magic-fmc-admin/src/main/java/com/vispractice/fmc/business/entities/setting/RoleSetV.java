package com.vispractice.fmc.business.entities.setting;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * 
 * 编  号：<br/>
 * 名  称：RoleSetV<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月22日 上午9:46:53<br/>
 * 编码作者："LaiJishen"<br/>
 */

@Entity
@Table(name="ROLE_SET_V")
@JsonIgnoreProperties(ignoreUnknown=true)
@NamedQuery(name="RoleSetV.findAll", query="SELECT s FROM RoleSetV s")
public class RoleSetV  implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	
	
	@Id   
	private String fdId;
	
	@Column(name="FD_PLUGIN")
	private String fdPlugin;
	
	@Column(name="FD_PARAMETER")
	private String fdParameter;
	
	@Column(name="FD_IS_MULTIPLE")
	private String fdIsMultiple;
	
	@Column(name="FD_RTN_VALUE")
	private String fdRtnValue;
	
	@Column(name="FD_ROLE_CONF_ID")
	private String fdRoleConfId;
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="CATE_ID")
	private String cateId;
	
	@Column(name="CATE_NAME")
	private String cateName;
	
	@Column(name="FD_IS_AVAILABLE")
	private String fdIsAvailable;
	
	@Column(name="FD_MEMO")
	private String fdMemo;
	

	
	

	public String getFdRtnValue() {
		return fdRtnValue;
	}

	public void setFdRtnValue(String fdRtnValue) {
		this.fdRtnValue = fdRtnValue;
	}

	public String getFdRoleConfId() {
		return fdRoleConfId;
	}

	public void setFdRoleConfId(String fdRoleConfId) {
		this.fdRoleConfId = fdRoleConfId;
	}

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdName() {
		return fdName;
	}

	public void setFdName(String fdName) {
		this.fdName = fdName;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	

	public String getFdIsAvailable() {
		return fdIsAvailable;
	}

	public void setFdIsAvailable(String fdIsAvailable) {
		this.fdIsAvailable = fdIsAvailable;
	}

	public String getFdPlugin() {
		return fdPlugin;
	}

	public void setFdPlugin(String fdPlugin) {
		this.fdPlugin = fdPlugin;
	}

	public String getFdParameter() {
		return fdParameter;
	}

	public void setFdParameter(String fdParameter) {
		this.fdParameter = fdParameter;
	}



	public String getFdIsMultiple() {
		return fdIsMultiple;
	}

	public void setFdIsMultiple(String fdIsMultiple) {
		this.fdIsMultiple = fdIsMultiple;
	}

	public String getFdMemo() {
		return fdMemo;
	}

	public void setFdMemo(String fdMemo) {
		this.fdMemo = fdMemo;
	}
	
	
	
	
	
	
	
}
