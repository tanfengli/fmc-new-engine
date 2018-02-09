package com.vispractice.fmc.business.entity.sys.notify;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 
 * 名 称：已办通知表实体 描 述：SysNotifyTodoDoneInfo.java
 * 完成日期：2017年7月14日 下午5:36:48
 * 编码作者："LaiJiashen"
 */
@Entity
@Data
@Table(name = "SYS_NOTIFY_TODO_DONE_INFO")
public class SysNotifyTodoDoneInfo {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "FD_FINISH_TIME")
	private Date fdFinishTime;

	@Column(name = "FD_TODOID")
	private String fdTodoid;

	@Column(name = "FD_ELEMENTID")
	private String fdElementid;

}
