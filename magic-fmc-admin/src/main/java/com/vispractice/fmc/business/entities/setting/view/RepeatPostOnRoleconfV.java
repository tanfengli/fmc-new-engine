package com.vispractice.fmc.business.entities.setting.view;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

/**
 * 编  号：
 * 名  称：RepeatPostOnRoleconfV
 * 描  述：RepeatPostOnRoleconfV.java
 * 完成日期：2017年8月7日 上午10:28:28
 * 编码作者：LaiJiashen
 */
@Data
@Entity(name="repeat_post_on_roleconf_v")
public class RepeatPostOnRoleconfV {
	@Id
	@Column(name="PERSON_ID")
	private String personId;
	
	@Column(name="PERSON_NAME")
	private String personName;
	
	@Column(name="CONF_ID")
	private String confId;
	
	@Column(name="POST_IDS")
	private String postIds;
	
	@Column(name="POST_NAMES")
	private String postNames;

	@Transient
	private List<Map<String,String>> postList;
}
