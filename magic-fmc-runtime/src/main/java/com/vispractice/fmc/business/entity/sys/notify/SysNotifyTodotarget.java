package com.vispractice.fmc.business.entity.sys.notify;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "SYS_NOTIFY_TODOTARGET")
@IdClass(SysNotifyTodotargetKey.class)  
public class SysNotifyTodotarget {
	@Id
	@Column(name = "FD_TODOID")
	private String fdTodoid;

	@Id  
	@Column(name = "FD_ORGID")
	private String fdOrgid;
}
