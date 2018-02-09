package com.vispractice.fmc.business.entity.sys.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class SysNewsMainAeditor {

	@Id
	@Column(name = "AUTH_ALL_EDITOR_ID")
	private String authAllEditorId;

	@Column(name = "FD_MAIN_ID")
	private String fdMainId;
}
