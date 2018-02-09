package com.vispractice.fmc.web.domain.setting;

import java.util.Date;
import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class GroupMemberForm {
	private String fdId;
	private Date fdAlterTime;
	private String fdCalendarId;
	private String fdCateid;
	private Date fdCreateTime;
	private Long fdFlagDeleted;
	private String fdHierarchyId;
	private String fdImportInfo;
	private Long fdIsAbandon;
	private String fdIsAvailable;
	private String fdIsBusiness;
	private String fdKeyword;
	private String fdLdapDn;
	private String fdMemo;
	private String fdName;
	private String fdNamePinyin;
	private String fdNo;
	private Long fdOrder;
	private Long fdOrgType;
	private String fdParentid;
	private String fdParentorgid;
	private String fdSuperLeaderid;
	private String fdThisLeaderid;
	private List<SysOrgElement> groupElementArray;
}
