package com.vispractice.fmc.web.domain.sys;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SysNotifySettingForm {
	 
	//组织机构同步消息发布设置
	private String orgFdId;
	  
	private String orgFdModelName;
	
	private String orgFdModelId;
	 
	private String orgFdKey;

	private List<String> orgFdNotifyType;
	 
	private String orgFdSubject;
	 
	private String orgFdContent;
	
	private List<SysOrgElement> editorArray;
	
	private String orgPerId;
	
	private String orgPerName;
	
	
	//创建帐号消息发布设置 
	private String creFdId;
	  
	private String creFdModelName;
	
	private String creFdModelId;
	 
	private String creFdKey;

	private List<String> creFdNotifyType;
	 
	private String creFdSubject;
	 
	private String creFdContent;
	
	private List<SysOrgElement> creEditorArray;
	
    private String crePerId;
	
	private String crePerName;
	
	
	//删除帐号消息发布设置 
	private String delFdId;
	  
	private String delFdModelName;
	
	private String delFdModelId;
	 
	private String delFdKey;

	private List<String> delFdNotifyType;
	 
	private String delFdSubject;
	 
	private String delFdContent;
	
	private List<SysOrgElement> delEditorArray;
	
    private String delPerId;
	
	private String delPerName;
	
	
	public String getOrgFdId() {
		return orgFdId;
	}

	public void setOrgFdId(String orgFdId) {
		this.orgFdId = orgFdId;
	}

	public String getOrgFdModelName() {
		return orgFdModelName;
	}

	public void setOrgFdModelName(String orgFdModelName) {
		this.orgFdModelName = orgFdModelName;
	}

	public String getOrgFdModelId() {
		return orgFdModelId;
	}

	public void setOrgFdModelId(String orgFdModelId) {
		this.orgFdModelId = orgFdModelId;
	}

	public String getOrgFdKey() {
		return orgFdKey;
	}

	public void setOrgFdKey(String orgFdKey) {
		this.orgFdKey = orgFdKey;
	}
 
	public List<String> getOrgFdNotifyType() {
		return orgFdNotifyType;
	}

	public void setOrgFdNotifyType(List<String> orgFdNotifyType) {
		this.orgFdNotifyType = orgFdNotifyType;
	}

	public String getOrgFdSubject() {
		return orgFdSubject;
	}

	public void setOrgFdSubject(String orgFdSubject) {
		this.orgFdSubject = orgFdSubject;
	}

	public String getOrgFdContent() {
		return orgFdContent;
	}

	public void setOrgFdContent(String orgFdContent) {
		this.orgFdContent = orgFdContent;
	}

	public List<SysOrgElement> getEditorArray() {
		return editorArray;
	}

	public void setEditorArray(List<SysOrgElement> editorArray) {
		this.editorArray = editorArray;
	}

	public String getOrgPerId() {
		return orgPerId;
	}

	public void setOrgPerId(String orgPerId) {
		this.orgPerId = orgPerId;
	}

	public String getCreFdId() {
		return creFdId;
	}

	public void setCreFdId(String creFdId) {
		this.creFdId = creFdId;
	}

	public String getCreFdModelName() {
		return creFdModelName;
	}

	public void setCreFdModelName(String creFdModelName) {
		this.creFdModelName = creFdModelName;
	}

	public String getCreFdModelId() {
		return creFdModelId;
	}

	public void setCreFdModelId(String creFdModelId) {
		this.creFdModelId = creFdModelId;
	}

	public String getCreFdKey() {
		return creFdKey;
	}

	public void setCreFdKey(String creFdKey) {
		this.creFdKey = creFdKey;
	}

	public List<String> getCreFdNotifyType() {
		return creFdNotifyType;
	}

	public void setCreFdNotifyType(List<String> creFdNotifyType) {
		this.creFdNotifyType = creFdNotifyType;
	}

	public String getCreFdSubject() {
		return creFdSubject;
	}

	public void setCreFdSubject(String creFdSubject) {
		this.creFdSubject = creFdSubject;
	}

	public String getCreFdContent() {
		return creFdContent;
	}

	public void setCreFdContent(String creFdContent) {
		this.creFdContent = creFdContent;
	}

	public List<SysOrgElement> getCreEditorArray() {
		return creEditorArray;
	}

	public void setCreEditorArray(List<SysOrgElement> creEditorArray) {
		this.creEditorArray = creEditorArray;
	}

	public String getDelFdId() {
		return delFdId;
	}

	public void setDelFdId(String delFdId) {
		this.delFdId = delFdId;
	}

	public String getDelFdModelName() {
		return delFdModelName;
	}

	public void setDelFdModelName(String delFdModelName) {
		this.delFdModelName = delFdModelName;
	}

	public String getDelFdModelId() {
		return delFdModelId;
	}

	public void setDelFdModelId(String delFdModelId) {
		this.delFdModelId = delFdModelId;
	}

	public String getDelFdKey() {
		return delFdKey;
	}

	public void setDelFdKey(String delFdKey) {
		this.delFdKey = delFdKey;
	}
 
	public List<String> getDelFdNotifyType() {
		return delFdNotifyType;
	}

	public void setDelFdNotifyType(List<String> delFdNotifyType) {
		this.delFdNotifyType = delFdNotifyType;
	}

	public String getDelFdSubject() {
		return delFdSubject;
	}

	public void setDelFdSubject(String delFdSubject) {
		this.delFdSubject = delFdSubject;
	}

	public String getDelFdContent() {
		return delFdContent;
	}

	public void setDelFdContent(String delFdContent) {
		this.delFdContent = delFdContent;
	}

	public List<SysOrgElement> getDelEditorArray() {
		return delEditorArray;
	}

	public void setDelEditorArray(List<SysOrgElement> delEditorArray) {
		this.delEditorArray = delEditorArray;
	}

	public String getOrgPerName() {
		return orgPerName;
	}

	public void setOrgPerName(String orgPerName) {
		this.orgPerName = orgPerName;
	}

	public String getCrePerId() {
		return crePerId;
	}

	public void setCrePerId(String crePerId) {
		this.crePerId = crePerId;
	}

	public String getCrePerName() {
		return crePerName;
	}

	public void setCrePerName(String crePerName) {
		this.crePerName = crePerName;
	}

	public String getDelPerId() {
		return delPerId;
	}

	public void setDelPerId(String delPerId) {
		this.delPerId = delPerId;
	}

	public String getDelPerName() {
		return delPerName;
	}

	public void setDelPerName(String delPerName) {
		this.delPerName = delPerName;
	}
	
}
