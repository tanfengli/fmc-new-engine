package com.vispractice.fmc.business.entity.aboutmyself;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "audit_bill_v")
public class AuditBillV {
	@Id
	@Column(name = "FD_ID")
	private String fdId;
	
	@Column(name = "APPLY_CODE")
	private String applyCode;
	
	@Column(name = "FD_NEWS_SOURCE")
	private String fdNewsSource;
	
	@Column(name = "DOC_STATUS")
	private String docStatus;
	
	@Column(name = "TASK_USER_NO")
	private String taskUserNo;

	@Column(name = "TASK_USER_NAME")
	private String taskUserName;
	
	@Column(name = "APP_USER_NO")
	private String appUserNo;

	@Column(name = "APP_USER_NAME")
	private String appUserName;
	
	@Column(name = "DOC_CREATOR_ID")
	private String docCreatorId;
	
	@Column(name = "DOC_CREATOR_NAME")
	private String docCreatorName;
	
	@Column(name = "NODE_NAME")
	private String nodeName;

	@Column(name = "DEAL_NAME")
	private String dealName;
	
	@Column(name = "DOC_CREATE_TIME")
	private Date docCreateTime;

	@Column(name = "DOC_PUBLISH_TIME")
	private Date docPublishTime;
	
	@Column(name = "FD_MODEL_ID")
	private String fdModelId;

	@Column(name = "FD_TEMPLATE_ID")
	private String fdTemplateId;

	@Column(name = "DOC_SUBJECT")
	private String docSubject;
	
	@Column(name = "FD_IMPORTANCE")
	private Long fdImportance;

	@Column(name = "FD_DESCRIPTION")
	private String fdDescription;

	@Column(name = "DOC_CONTENT")
	private String docContent;
	
	@Column(name="ATTR1")
	private String attr1;

	@Column(name="ATTR10")
	private String attr10;

	@Column(name="ATTR11")
	private String attr11;

	@Column(name="ATTR12")
	private String attr12;

	@Column(name="ATTR13")
	private String attr13;

	@Column(name="ATTR14")
	private String attr14;

	@Column(name="ATTR15")
	private String attr15;

	@Column(name="ATTR16")
	private String attr16;

	@Column(name="ATTR17")
	private String attr17;

	@Column(name="ATTR18")
	private String attr18;

	@Column(name="ATTR19")
	private String attr19;

	@Column(name="ATTR2")
	private String attr2;

	@Column(name="ATTR20")
	private String attr20;

	@Column(name="ATTR3")
	private String attr3;

	@Column(name="ATTR4")
	private String attr4;

	@Column(name="ATTR5")
	private String attr5;

	@Column(name="ATTR6")
	private String attr6;

	@Column(name="ATTR7")
	private String attr7;

	@Column(name="ATTR8")
	private String attr8;

	@Column(name="ATTR9")
	private String attr9;

	public String getDocContent() {
		return docContent;
	}

	public void setDocContent(String docContent) {
		this.docContent = docContent;
	}

	public String getTaskUserNo() {
		return taskUserNo;
	}

	public void setTaskUserNo(String taskUserNo) {
		this.taskUserNo = taskUserNo;
	}

	public String getTaskUserName() {
		return taskUserName;
	}

	public void setTaskUserName(String taskUserName) {
		this.taskUserName = taskUserName;
	}

	public String getAppUserNo() {
		return appUserNo;
	}

	public void setAppUserNo(String appUserNo) {
		this.appUserNo = appUserNo;
	}

	public String getAppUserName() {
		return appUserName;
	}

	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getDocCreatorName() {
		return docCreatorName;
	}

	public void setDocCreatorName(String docCreatorName) {
		this.docCreatorName = docCreatorName;
	}

	public String getFdId() {
		return fdId;
	}

	public void setFdId(String fdId) {
		this.fdId = fdId;
	}

	public String getDocSubject() {
		return docSubject;
	}

	public void setDocSubject(String docSubject) {
		this.docSubject = docSubject;
	}

	public String getFdDescription() {
		return fdDescription;
	}

	public void setFdDescription(String fdDescription) {
		this.fdDescription = fdDescription;
	}

	public String getFdNewsSource() {
		return fdNewsSource;
	}

	public void setFdNewsSource(String fdNewsSource) {
		this.fdNewsSource = fdNewsSource;
	}

	public Date getDocCreateTime() {
		return docCreateTime;
	}

	public void setDocCreateTime(Date docCreateTime) {
		this.docCreateTime = docCreateTime;
	}

	public Date getDocPublishTime() {
		return docPublishTime;
	}

	public void setDocPublishTime(Date docPublishTime) {
		this.docPublishTime = docPublishTime;
	}

	public String getFdModelId() {
		return fdModelId;
	}

	public void setFdModelId(String fdModelId) {
		this.fdModelId = fdModelId;
	}

	public String getDocStatus() {
		return docStatus;
	}

	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}

	public String getFdTemplateId() {
		return fdTemplateId;
	}

	public void setFdTemplateId(String fdTemplateId) {
		this.fdTemplateId = fdTemplateId;
	}

	public String getDocCreatorId() {
		return docCreatorId;
	}

	public void setDocCreatorId(String docCreatorId) {
		this.docCreatorId = docCreatorId;
	}

	public Long getFdImportance() {
		return fdImportance;
	}

	public void setFdImportance(Long fdImportance) {
		this.fdImportance = fdImportance;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr10() {
		return attr10;
	}

	public void setAttr10(String attr10) {
		this.attr10 = attr10;
	}

	public String getAttr11() {
		return attr11;
	}

	public void setAttr11(String attr11) {
		this.attr11 = attr11;
	}

	public String getAttr12() {
		return attr12;
	}

	public void setAttr12(String attr12) {
		this.attr12 = attr12;
	}

	public String getAttr13() {
		return attr13;
	}

	public void setAttr13(String attr13) {
		this.attr13 = attr13;
	}

	public String getAttr14() {
		return attr14;
	}

	public void setAttr14(String attr14) {
		this.attr14 = attr14;
	}

	public String getAttr15() {
		return attr15;
	}

	public void setAttr15(String attr15) {
		this.attr15 = attr15;
	}

	public String getAttr16() {
		return attr16;
	}

	public void setAttr16(String attr16) {
		this.attr16 = attr16;
	}

	public String getAttr17() {
		return attr17;
	}

	public void setAttr17(String attr17) {
		this.attr17 = attr17;
	}

	public String getAttr18() {
		return attr18;
	}

	public void setAttr18(String attr18) {
		this.attr18 = attr18;
	}

	public String getAttr19() {
		return attr19;
	}

	public void setAttr19(String attr19) {
		this.attr19 = attr19;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getAttr20() {
		return attr20;
	}

	public void setAttr20(String attr20) {
		this.attr20 = attr20;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	public String getAttr4() {
		return attr4;
	}

	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}

	public String getAttr5() {
		return attr5;
	}

	public void setAttr5(String attr5) {
		this.attr5 = attr5;
	}

	public String getAttr6() {
		return attr6;
	}

	public void setAttr6(String attr6) {
		this.attr6 = attr6;
	}

	public String getAttr7() {
		return attr7;
	}

	public void setAttr7(String attr7) {
		this.attr7 = attr7;
	}

	public String getAttr8() {
		return attr8;
	}

	public void setAttr8(String attr8) {
		this.attr8 = attr8;
	}

	public String getAttr9() {
		return attr9;
	}

	public void setAttr9(String attr9) {
		this.attr9 = attr9;
	}
}
