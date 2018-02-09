package com.vispractice.fmc.business.entity.sys.workflow.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vispractice.fmc.business.base.domain.CommonForm;

@Data
@Entity
@Table(name = "Sys_Wf_Authorizer_V")
@NamedQuery(name = "SysWfAuthorizerV.findAll", query = "SELECT p FROM SysWfAuthorizerV p")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysWfAuthorizerV implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_AUTHORIZE_TYPE")
	private String fdAuthorizeType;

	@Column(name = "FD_START_TIME")
	private Date fdStartTime;

	@Column(name = "FD_END_TIME")
	private Date fdEndTime;

	@Column(name = "FD_EXPIRE_DELETED")
	private String fdExpireDeleted;

	@Column(name = "FD_CREATE_TIME")
	private Date fdCreateTime;

	@Column(name = "FD_AUTHORIZER")
	private String fdAuthorizer;

	@Column(name = "FD_CREATOR")
	private String fdCreator;

	@Column(name = "FD_AUTHORIZED_PERSON")
	private String fdAuthorizedPerson;

	@Column(name = "fd_authorizer_Name")
	private String fdAuthorizerName;

	@Column(name = "fd_authorizer_person_Name")
	private String fdAuthorizerPersonName;

	@Column(name = "fd_authorize_cate_showtext_Id")
	private String fdAuthorizedCateTextId;

	@Column(name = "fd_authorize_cate_showtext")
	private String fdAuthorizedCateText;

	@Column(name = "fd_Authorize_Org_Id")
	private String fdAuthorizeOrgId;

	@Column(name = "fd_Authorize_Org_Name")
	private String fdAuthorizeOrgName;

	@Transient
	private List<CommonForm> authorizeScopeArray;

	@Transient
	private List<String> fdExpireDeletedChe;

	@Transient
	private List<String> fdAuthorizedChe;

	@Transient
	private List<CommonForm> fdAuthorizeContext;
}
