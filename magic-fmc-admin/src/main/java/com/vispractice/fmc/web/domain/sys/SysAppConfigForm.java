package com.vispractice.fmc.web.domain.sys;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
 
public class SysAppConfigForm {
	
	private String fdId;
	private String defaultNotifyType; 
	private List<String> defaultNotifyTypeSel;
	private String notifyCrashTargetIds; 
	private String notifyExpire;
	private String nodeNameSelectItem; 
	private String systemNotifyType; 
	private List<String> systemNotifyTypeSel;
	private String notifyCrashTargetNames; 
	private String notifyTargetSubmit; 
	private String notifyTargetAuthor; 
	private String threadPoolSize;
	private List<SysOrgElement> editorArray;
	
	
	public String getFdId() {
		return fdId;
	}
	public void setFdId(String fdId) {
		this.fdId = fdId;
	}
	public String getDefaultNotifyType() {
		return defaultNotifyType;
	}
	public void setDefaultNotifyType(String defaultNotifyType) {
		this.defaultNotifyType = defaultNotifyType;
	} 
	public List<String> getDefaultNotifyTypeSel() {
		return defaultNotifyTypeSel;
	}
	public void setDefaultNotifyTypeSel(List<String> defaultNotifyTypeSel) {
		this.defaultNotifyTypeSel = defaultNotifyTypeSel;
	}
	public String getNotifyCrashTargetIds() {
		return notifyCrashTargetIds;
	}
	public void setNotifyCrashTargetIds(String notifyCrashTargetIds) {
		this.notifyCrashTargetIds = notifyCrashTargetIds;
	}
	public String getNotifyExpire() {
		return notifyExpire;
	}
	public void setNotifyExpire(String notifyExpire) {
		this.notifyExpire = notifyExpire;
	}
	public String getNodeNameSelectItem() {
		return nodeNameSelectItem;
	}
	public void setNodeNameSelectItem(String nodeNameSelectItem) {
		this.nodeNameSelectItem = nodeNameSelectItem;
	}
	public String getSystemNotifyType() {
		return systemNotifyType;
	}
	public void setSystemNotifyType(String systemNotifyType) {
		this.systemNotifyType = systemNotifyType;
	}
	public List<String> getSystemNotifyTypeSel() {
		return systemNotifyTypeSel;
	}
	public void setSystemNotifyTypeSel(List<String> systemNotifyTypeSel) {
		this.systemNotifyTypeSel = systemNotifyTypeSel;
	}
	public String getNotifyCrashTargetNames() {
		return notifyCrashTargetNames;
	}
	public void setNotifyCrashTargetNames(String notifyCrashTargetNames) {
		this.notifyCrashTargetNames = notifyCrashTargetNames;
	}
	public String getNotifyTargetSubmit() {
		return notifyTargetSubmit;
	}
	public void setNotifyTargetSubmit(String notifyTargetSubmit) {
		this.notifyTargetSubmit = notifyTargetSubmit;
	}
	public String getNotifyTargetAuthor() {
		return notifyTargetAuthor;
	}
	public void setNotifyTargetAuthor(String notifyTargetAuthor) {
		this.notifyTargetAuthor = notifyTargetAuthor;
	}
	public String getThreadPoolSize() {
		return threadPoolSize;
	}
	public void setThreadPoolSize(String threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}
	public List<SysOrgElement> getEditorArray() {
		return editorArray;
	}
	public void setEditorArray(List<SysOrgElement> editorArray) {
		this.editorArray = editorArray;
	}  
}
