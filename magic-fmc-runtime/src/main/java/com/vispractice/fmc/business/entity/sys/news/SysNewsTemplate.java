package com.vispractice.fmc.business.entity.sys.news;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vispractice.fmc.base.utils.AutoArrayList;
import com.vispractice.fmc.business.entity.sys.category.SysCategoryMain;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfTemplate;

@Entity
@Data
@Table(name = "sys_news_template")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysNewsTemplate implements Cloneable {
	@Id
	@Column(name = "FD_ID")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String fdId;

	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "DOC_CREATE_TIME")
	private Date docCreateTime;

	@Column(name = "FD_IMPORTANCE")
	private Long fdImportance;

	@Column(name = "FD_NUMBER_PREFIX")
	private String fdNumberPrefix;

	@Column(name = "DOC_CONTENT")
	private String docContent;

	@Column(name = "FD_USE_FORM")
	private String fdUseForm;

	@Column(name = "FD_APP_LINK")
	private String fdAppLink;

	@Column(name = "AUTH_READER_FLAG")
	private Long authReaderFlag;

	@Column(name = "AUTH_TMP_ATT_NODOWNLOAD")
	private Long authTmpAttNodownload;

	@Column(name = "AUTH_TMP_ATT_NOCOPY")
	private String authTmpAttNocopy;

	@Column(name = "FD_STATUS")
	private String fdStatus;

	@Column(name = "AUTH_TMP_ATT_NOPRINT")
	private Long authTmpAttNoprint;

	@Column(name = "FD_ORDER")
	private Long fdOrder;

	@Column(name = "FD_STYLE")
	private String fdStyle;

	@Column(name = "FD_CONTENT_TYPE")
	private String fdContentType;

	@Column(name = "FD_CATEGORY_ID")
	private String fdCategoryId;
	
	// 分类名字
	@Transient
	private String categoryName;
	
	// 流程模板类别
	@Transient
	private SysCategoryMain docCategory;

	@Column(name = "DOC_CREATOR_ID")
	private String docCreatorId;

	@Transient
	private String docCreatorName;

	@Column(name = "AUTH_AREA_ID")
	private String authAreaId;

	@Column(name = "BUSI_SYS_ID")
	private String busiSysId;
	
	// 业务系统名字
	@Transient
	private String busyName;

	@Column(name = "AUTH_NOT_READER_FLAG")
	private Long authNotReaderFlag;

	@Column(name = "FD_HIERARCHY_ID")
	private String fdHierarchyId;

	@Column(name = "DOC_ALTEROR_ID")
	private String docAlterId;

	@Transient
	private String docAlterName;

	@Column(name = "DOC_ALTER_TIME")
	private Date docAlterTime;

	@Column(name = "FD_ISINHERIT_MAINTAINER")
	private Long fdIsinheritMaintainer;

	@Column(name = "FD_CHANGE_ATT")
	private Long fdChageAtt;

	@Column(name = "FD_ISINHERIT_USER")
	private Long fdIsinheritUser;

	@Column(name = "FD_PARENT_ID")
	private String fdParentId;
	
	/**
	 * 模板版本
	 */
	@Transient
	private String fdVersion;
	
	/**
	 * 启动时间
	 */
	@Transient
	private Date enableTimeDate;
	
	// 流程模板模型
	@Transient
	private List<SysWfTemplate> sysWfTemplates;

	// 可修改者
	@SuppressWarnings("unchecked")
	@Transient
	private List<SysOrgElement> templateEditor = new AutoArrayList(SysOrgElement.class);

	// 可使用者
	@SuppressWarnings("unchecked")
	@Transient
	private List<SysOrgElement> templateReader = new AutoArrayList(SysOrgElement.class);

	// 流程图XML
	@Transient
	private String flowXml;

	// 默认模板名称
	@Transient
	private String defaultTemplate;

	// 模板定义方式
	@Transient
	private String pattern;
	
	@Transient
	private String otherFlowXml;
	
	@Transient
	private String otherTemplateId;

	@Override
	public Object clone() {
		SysNewsTemplate o = null;
		try {
			o = (SysNewsTemplate) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return o;
	}
}
