package com.vispractice.fmc.business.entity.sys.news.view;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Data
@Table(name="SYS_NEWS_TEMPLATE_V")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysNewsTemplateV {
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="DOC_CREATE_TIME")
	private Date docCreateTime;
	
	@Column(name="FD_IMPORTANCE")
	private String fdImportance;
	
	@Column(name="FD_NUMBER_PREFIX")
	private String fdNumberPrefix;
	
	@Column(name="DOC_CONTENT")
	private String docContent;
	
	@Column(name="FD_USE_FORM")
	private String fdUseForm;
	
	@Column(name="FD_APP_LINK")
	private String fdAppLink;
	
	@Column(name="AUTH_READER_FLAG")
	private String authReaderFlag;
	
	@Column(name="AUTH_TMP_ATT_NODOWNLOAD")
	private String authTmpAttNodownload;
	
	@Column(name="AUTH_TMP_ATT_NOCOPY")
	private String authTmpAttNocopy;
	
	@Column(name="FD_STATUS")
	private String fdStatus;
	
	@Column(name="AUTH_TMP_ATT_NOPRINT")
	private String authTmpAttNoprint;
	
	@Column(name="FD_ORDER")
	private String fdOrder;
	
	@Column(name="FD_STYLE")
	private String fdStyle;
	
	@Column(name="FD_CONTENT_TYPE")
	private String fdContentType;
	
	@Column(name="FD_CATEGORY_ID")
	private String fdCategoryId;
	
	@Column(name="DOC_CREATOR_ID")
	private String docCreatorId;
	
	@Column(name="AUTH_AREA_ID")
	private String authAreaId;
	
	@Column(name="BUSI_SYS_ID")
	private String busiSysId;
	
	@Column(name="AUTH_NOT_READER_FLAG")
	private String authNotReaderFlag;
	
	@Column(name="FD_HIERARCHY_ID")
	private String fdHierarchyId;
	
	@Column(name="DOC_ALTEROR_ID")
	private String docALTERORId;
	
	@Column(name="DOC_ALTER_TIME")
	private Date docAlterTime;
	
	@Column(name="FD_ISINHERIT_MAINTAINER")
	private String fdIsinheritMaintainer;
	
	@Column(name="FD_CHANGE_ATT")
	private String fdChageAtt;
	
	@Column(name="FD_ISINHERIT_USER")
	private String fdIsinheritUser;
	
	@Column(name="FD_PARENT_ID")
	private String fdParentId;
	
	@Column(name="CREATOR_ID")
	private String creatorId;
	
	//创建人名字
	@Column(name="CREATOR_NAME")
	private String creatorName;
	
	//业务系统ID
	@Column(name="BUSI_ID")
	private String busyId;
	
	//业务系统名字
	@Column(name="BUSI_NAME")
	private String busyName;
	
	//业务系统编码
	@Column(name="BUSI_CODE")
	private String busyCode;
	
	//分类名字
	@Column(name="CATEGORY_NAME")
	private String categoryName;
	
	//模板XML
	@Column(name="FD_FLOW_CONTENT")
	private String fdFlowContent;
	
	@Transient
	private String clickType;
	
	@Transient
	private String extendParam1;
	
	@Transient
	private String extendParam2;
}
