package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

/**
 * 授权项中间表
 * 编  号：<br/>
 * 名  称：SysWfAuthorizeItem<br/>
 * 描  述：<br/>
 * 完成日期：2017年1月17日 上午10:30:49<br/>
 * 编码作者：<br/>
 */
@Entity
@Data
@Table(name="sys_wf_authorize_item")
@IdClass(SysWfAuthorizeItemKey.class)
public class SysWfAuthorizeItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="FD_AUTHORIZE_ORG_ID")
	private String fdAuthorizeOrgId;
	
	@Id
	@Column(name="FD_AUTHORIZE_ID")
	private String fdAuthorizeId;

}
