package com.vispractice.fmc.business.entity.sys.org;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 
 * 编 号：<br/>
 * 名 称：SysOrgGroupCate<br/>
 * 描 述：<br/>
 * 完成日期：2016年12月14日 下午2:48:22<br/>
 * 编码作者："LaiJiashen"<br/>
 */

@Entity
@Data
@Table(name = "SYS_ORG_GROUP_CATE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SysOrgGroupCate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_KEYWORD")
	private String fdKeyword;

	@Column(name = "FD_CREATE_TIME")
	private Date fdCreateTime;

	@Column(name = "FD_ALTER_TIME")
	private Date fdAlterTime;

	@Column(name = "FD_PARENTID")
	private String fdParentId;
	
	@Column(name = "FD_IS_LEAF")
	private Long fdIsLeaf;

//	// 尾节点
//	@Transient
//	private Long isLeaf=0L;

	// 当前节点类型: 做选择成员组件用
	@Transient
	private String nodeType;
	
	@Transient
	public String getIsParent(){
		return this.fdIsLeaf == null || this.fdIsLeaf == 0?"true":"false";
	}

}
