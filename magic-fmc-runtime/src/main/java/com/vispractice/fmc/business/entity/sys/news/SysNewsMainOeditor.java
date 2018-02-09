package com.vispractice.fmc.business.entity.sys.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class SysNewsMainOeditor {
	
	@Id
	@Column(name="AUTH_OTHER_EDITOR_ID")
	private String authOtherEditorId;
	
	@Column(name="FD_MAIN_ID")
	private String fdMainId;

	public String getAuthOtherEditorId() {
		return authOtherEditorId;
	}

	public void setAuthOtherEditorId(String authOtherEditorId) {
		this.authOtherEditorId = authOtherEditorId;
	}

	public String getFdMainId() {
		return fdMainId;
	}

	public void setFdMainId(String fdMainId) {
		this.fdMainId = fdMainId;
	}
	
	

}
