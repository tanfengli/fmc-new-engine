package com.vispractice.fmc.business.entity.sys.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SysNewsMainOreader {
	
	@Id
	@Column(name="AUTH_OTHER_READER_ID")
	private String authOtherReaderId;
	
	@Column(name="FD_MAIN_ID")
	private String fdMainId;

	public String getAuthOtherReaderId() {
		return authOtherReaderId;
	}

	public void setAuthOtherReaderId(String authOtherReaderId) {
		this.authOtherReaderId = authOtherReaderId;
	}

	public String getFdMainId() {
		return fdMainId;
	}

	public void setFdMainId(String fdMainId) {
		this.fdMainId = fdMainId;
	}
	
	
	

}
