package com.vispractice.fmc.business.base.simulator;

import java.util.List;

public class SimulateForm {

	private String http;
	
	private Integer threadNum;
	
	private Boolean flag;
	
	private Integer maxNum;
	
	private Boolean isApprove;
	
	private String templateId;
	
	private String handlerNames;
	
	private String handlerIds;
	
	private List  handlerArray;

	public String getHttp() {
		return http;
	}

	public void setHttp(String http) {
		this.http = http;
	}

	public Integer getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(Integer threadNum) {
		this.threadNum = threadNum;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}


	public Boolean getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(Boolean isApprove) {
		this.isApprove = isApprove;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getHandlerNames() {
		return handlerNames;
	}

	public void setHandlerNames(String handlerNames) {
		this.handlerNames = handlerNames;
	}

	public String getHandlerIds() {
		return handlerIds;
	}

	public void setHandlerIds(String handlerIds) {
		this.handlerIds = handlerIds;
	}

	public List getHandlerArray() {
		return handlerArray;
	}

	public void setHandlerArray(List handlerArray) {
		this.handlerArray = handlerArray;
	}
	
}
