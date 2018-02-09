package com.vispractice.fmc.business.entity.sys.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "sys_news_template_editor")
public class SysNewsTemplateEditor {

	@Id
	@Column(name = "AUTH_EDITOR_ID")
	private String authEditorId;

	@Column(name = "FD_TEMPLATE_ID")
	private String fdTemplateId;

}
