package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 授权范围
 * 编  号：<br/>
 * 名  称：SysWfAuthorizeScope<br/>
 * 描  述：<br/>
 * 完成日期：2017年1月16日 下午4:55:38<br/>
 * 编码作者：Administrator<br/>
 */
@Entity
@Table(name="SYS_WF_AUTHORIZE_SCOPE")
@NamedQuery(name="SysWfAuthorizeScope.findAll", query="SELECT s FROM SysWfAuthorizeScope s")
public class SysWfAuthorizeScope implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_AUTHORIZE_CATE_ID")
	private String fdAuthorizeCateId;
	
	@Column(name="FD_AUTHORIZE_CATE_NAME")
	private String fdAuthorizeCateName;
	
	@Column(name="FD_TEMPLATE_ID")
	private String fdTemplateId;
	
	@Column(name="FD_TEMPLATE_NAME")
	private String fdTemplateName;
	
	@Column(name="FD_AUTHORIZE_CATE_SHOWTEXT")
	private String fdAuthorizeCateShowText;
	
	@Column(name="FD_MODEL_NAME")
	private String fdModelName;
	
	@Column(name="FD_MODULE_NAME")
	private String fdModuleName;
	
	@Column(name="FD_AUTHORIZE_ID")
	private String fdAuthorizeId;

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getFdAuthorizeCateId() {
		return fdAuthorizeCateId;
	}

	public void setFdAuthorizeCateId(String fdAuthorizeCateId) {
		this.fdAuthorizeCateId = fdAuthorizeCateId;
	}

	public String getFdAuthorizeCateName() {
		return fdAuthorizeCateName;
	}

	public void setFdAuthorizeCateName(String fdAuthorizeCateName) {
		this.fdAuthorizeCateName = fdAuthorizeCateName;
	}

	public String getFdTemplateId() {
		return fdTemplateId;
	}

	public void setFdTemplateId(String fdTemplateId) {
		this.fdTemplateId = fdTemplateId;
	}

	public String getFdTemplateName() {
		return fdTemplateName;
	}

	public void setFdTemplateName(String fdTemplateName) {
		this.fdTemplateName = fdTemplateName;
	}

	public String getFdAuthorizeCateShowText() {
		return fdAuthorizeCateShowText;
	}

	public void setFdAuthorizeCateShowText(String fdAuthorizeCateShowText) {
		this.fdAuthorizeCateShowText = fdAuthorizeCateShowText;
	}

	public String getFdModelName() {
		return fdModelName;
	}

	public void setFdModelName(String fdModelName) {
		this.fdModelName = fdModelName;
	}

	public String getFdModuleName() {
		return fdModuleName;
	}

	public void setFdModuleName(String fdModuleName) {
		this.fdModuleName = fdModuleName;
	}

	public String getFdAuthorizeId() {
		return fdAuthorizeId;
	}

	public void setFdAuthorizeId(String fdAuthorizeId) {
		this.fdAuthorizeId = fdAuthorizeId;
	}
}
