package com.vispractice.fmc.business.entity.sys.workflow.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name = "sys_wf_common_template_v")
@NamedQuery(name="SysWfCommonTemplateV.findAll", query="SELECT s FROM SysWfCommonTemplateV s")
public class SysWfCommonTemplateV  implements Serializable{
	 
		private static final long serialVersionUID = 1L;
	 
		@Id
		@GeneratedValue(generator="system-uuid")  
		@GenericGenerator(name="system-uuid", strategy = "uuid")  
		@Column(name="FD_ID")
		private String fdId;
		
		@Column(name="FD_NAME")
		private String fdName;

		@Column(name="FD_IS_DEFAULT")
		private String fdIsDefault;
		
		@Column(name="FD_FLOW_CONTENT")
		private String fdFlowContent;
		
		@Column(name="FD_MODEL_NAME")
		private String fdModelName;
		
		@Column(name="FD_KEY")
		private String fdKey;
		
		@Column(name="FD_CREATE_TIME")
		private Date fdCreateTime; 
		
		@Column(name="FD_VERSION")
		private String fdVersion;
		
		@Column(name="USER_NAME")
		private String userName;
		
		@Column(name="FD_CREATOR_ID")
		private String fdCreatorId;

		//xml读取字段
		@Transient
		private String description;
		
		@Transient
		private String rejectReturn;
		@Transient
		private List<String> rejectReturnChe;
		
		@Transient
		private String notifyType; 
		@Transient
		private List<String> notifyTypeChe;
		
		@Transient
		private String notifyOnFinish;
		@Transient
		private List<String> notifyOnFinishChe;
		
		@Transient
		private String notifyDraftOnFinish;
		@Transient
		private List<String> notifyDraftOnFinishChe;
		
		@Transient
		private String dayOfNotifyPrivileger;
		
		@Transient
		private String privilegerIds;
		
		@Transient
		private String privilegerNames;
		
		public String getFdId() {
			return fdId;
		}

		public void setFdId(String fdId) {
			this.fdId = fdId;
		}

		public String getFdName() {
			return fdName;
		}

		public void setFdName(String fdName) {
			this.fdName = fdName;
		}

		public String getFdIsDefault() {
			return fdIsDefault;
		}

		public void setFdIsDefault(String fdIsDefault) {
			this.fdIsDefault = fdIsDefault;
		}

		public String getFdFlowContent() {
			return fdFlowContent;
		}

		public void setFdFlowContent(String fdFlowContent) {
			this.fdFlowContent = fdFlowContent;
		}

		public String getFdModelName() {
			return fdModelName;
		}

		public void setFdModelName(String fdModelName) {
			this.fdModelName = fdModelName;
		}

		public String getFdKey() {
			return fdKey;
		}

		public void setFdKey(String fdKey) {
			this.fdKey = fdKey;
		}

		public Date getFdCreateTime() {
			return fdCreateTime;
		}

		public void setFdCreateTime(Date fdCreateTime) {
			this.fdCreateTime = fdCreateTime;
		}

		public String getFdVersion() {
			return fdVersion;
		}

		public void setFdVersion(String fdVersion) {
			this.fdVersion = fdVersion;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getFdCreatorId() {
			return fdCreatorId;
		}

		public void setFdCreatorId(String fdCreatorId) {
			this.fdCreatorId = fdCreatorId;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getRejectReturn() {
			return rejectReturn;
		}

		public void setRejectReturn(String rejectReturn) {
			this.rejectReturn = rejectReturn;
		}

		public String getNotifyType() {
			return notifyType;
		}

		public void setNotifyType(String notifyType) {
			this.notifyType = notifyType;
		}

		public String getNotifyOnFinish() {
			return notifyOnFinish;
		}

		public void setNotifyOnFinish(String notifyOnFinish) {
			this.notifyOnFinish = notifyOnFinish;
		}

		public String getNotifyDraftOnFinish() {
			return notifyDraftOnFinish;
		}

		public void setNotifyDraftOnFinish(String notifyDraftOnFinish) {
			this.notifyDraftOnFinish = notifyDraftOnFinish;
		}

		public String getDayOfNotifyPrivileger() {
			return dayOfNotifyPrivileger;
		}

		public void setDayOfNotifyPrivileger(String dayOfNotifyPrivileger) {
			this.dayOfNotifyPrivileger = dayOfNotifyPrivileger;
		}

		public String getPrivilegerIds() {
			return privilegerIds;
		}

		public void setPrivilegerIds(String privilegerIds) {
			this.privilegerIds = privilegerIds;
		}

		public String getPrivilegerNames() {
			return privilegerNames;
		}

		public void setPrivilegerNames(String privilegerNames) {
			this.privilegerNames = privilegerNames;
		}

		public List<String> getRejectReturnChe() {
			return rejectReturnChe;
		}

		public void setRejectReturnChe(List<String> rejectReturnChe) {
			this.rejectReturnChe = rejectReturnChe;
		}

		public List<String> getNotifyTypeChe() {
			return notifyTypeChe;
		}

		public void setNotifyTypeChe(List<String> notifyTypeChe) {
			this.notifyTypeChe = notifyTypeChe;
		}

		public List<String> getNotifyOnFinishChe() {
			return notifyOnFinishChe;
		}

		public void setNotifyOnFinishChe(List<String> notifyOnFinishChe) {
			this.notifyOnFinishChe = notifyOnFinishChe;
		}

		public List<String> getNotifyDraftOnFinishChe() {
			return notifyDraftOnFinishChe;
		}

		public void setNotifyDraftOnFinishChe(List<String> notifyDraftOnFinishChe) {
			this.notifyDraftOnFinishChe = notifyDraftOnFinishChe;
		} 
}
