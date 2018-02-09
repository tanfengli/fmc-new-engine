package com.vispractice.fmc.business.entities.setting.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 编  号：<br/>
 * 名  称：GroupAdmin<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月15日 下午4:34:32<br/>
 * 编码作者：LaiJiashen<br/>
 */

@Data
@Entity
@Table(name="GROUP_ADMIN")
public class GroupAdmin implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private String id;
	
	
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="FD_MEMBER_NAME")
	private String fdMemberName;
	
	@Column(name="FD_IS_AVAILABLE")
	private String fdIsAvailable;
	
	@Column(name="FD_CATEID")
	private String fdCateid;
	
	@Column(name="GROUP_NAME")
	private String groupName;
	
	@Column(name="FD_MEMO")
	private String fdMemo;
}
