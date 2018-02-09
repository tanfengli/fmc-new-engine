package com.vispractice.fmc.business.entities.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
 

/**
 * 通知配置管理 通知人中间表
 * 编  号：<br/>
 * 名  称：SysNotifySettingTarget<br/>
 * 描  述：<br/>
 * 完成日期：2017年1月19日 上午9:36:34<br/>
 * 编码作者：Administrator<br/>
 */
@Entity
@Table(name="sys_notify_settingtarget") 
@NamedQuery(name="SysNotifySettingTarget.findAll", query="SELECT s FROM SysNotifySettingTarget s")
public class SysNotifySettingTarget {

    @Column(name="FD_NOTIFYSETTINGID")	
	private String fdNotifySettingId;
	
    @Id
	@Column(name="FD_ORGID")
	private String fdOrgId;

	public String getFdNotifySettingId() {
		return fdNotifySettingId;
	}

	public void setFdNotifySettingId(String fdNotifySettingId) {
		this.fdNotifySettingId = fdNotifySettingId;
	}

	public String getFdOrgId() {
		return fdOrgId;
	}

	public void setFdOrgId(String fdOrgId) {
		this.fdOrgId = fdOrgId;
	} 
}
