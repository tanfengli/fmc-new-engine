package com.vispractice.fmc.business.entity.sys.news;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name="SYS_NEWS_MAIN")
@NamedQuery(name="SysNewsMain.findAll",query="SELECT s FROM SysNewsMain s")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SysNewsMain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="DOC_SUBJECT")
	private String docSubject;
	
	@Column(name="ENABLED")
	private Long enabled;
	
	@Column(name="FD_DESCRIPTION")
	private String fdDescription;
	
	@Column(name="FD_NEWS_SOURCE")
	private String fdNewsSource;
	
	@Column(name="DOC_CREATE_TIME")
	private Date docCreateTime;
	
	@Column(name="DOC_ALTER_TIME")
	private Date docAlterTime;
	
	@Column(name="DOC_PUBLISH_TIME")
	private Date docPublishTime;
	
	@Column(name="DOC_READ_COUNT")
	private Long docReadCount;
	
	@Column(name="FD_TOP_END_TIME")
	private Date fdTopEndTime;
	
	@Column(name="DOC_CONTENT")
	private String docContent;

	@Column(name="BUSI_SYS_ID")
	private String busiSysId;
	
	@Column(name="EXTEND_FILE_PATH")
	private String extendFilePath;
	
	@Lob
	@Column(name="EXTEND_DATA_XML")
	private String extendDataXml;
	
	@Column(name="FD_USE_FORM")
	private Long fdUseForm;
	
	@Column(name="FD_IMPORTANCE")
	private Long fdImportance;
	
	@Column(name="FD_TOP_DAYS")
	private Long fdTopDays;
	
	@Column(name="FD_MAIN_PICTURE")
	private Long fdMainPicture;
	
	@Column(name="FD_SUMMARY")
	private String fdSummary;
	
	@Column(name="FD_TOP_TIME")
	private Date fdTopTime;
	
	@Column(name="FD_IS_LINK")
	private Long fdIsLink;
	
	@Column(name="FD_IS_PIC_NEWS")
	private Long fdIsPicNews;
	
	@Column(name="FD_KEY")
	private String fdKey;
	
	@Column(name="FD_MODEL_ID")
	private String fdModelId;

	@Column(name="FD_MODEL_NAME")
	private String fdModelName;
	
	@Column(name="FD_LINK_URL")
	private String fdLinkUrl;
	
	@Column(name="AUTH_READER_FLAG")
	private Long authReaderFlag;

	@Column(name="FD_IS_ROLLS")
	private Long fdIsRolls;

	@Column(name="FD_IS_TOP")
	private Long fdIsTop;
	
	@Column(name="FD_IS_TEST")
	private Long fdIsTest;
	
	@Column(name="FD_IS_PASS")
	private Long fdIsPass;
	
	@Column(name="DOC_STATUS")
	private String docStatus;
	
	@Column(name="FD_CONTENT_TYPE")
	private String fdContentType;
	
	@Lob
	@Column(name="FD_HTML_CONTENT")
	private String fdHtmlContent;
	
	@Column(name="FD_WRITER")
	private String fdWriter;
	
	@Column(name="FD_IS_HIDE_SUBJECT")
	private Long fdIsHideSubject;

	@Column(name="FD_LAST_MODIFIED_TIME")
	private Date fdLastModifiedTime;
	
	@Column(name="DOC_CREATOR_CLIENT_IP")
	private String docCreatorClientIp;
	
	@Column(name="AUTH_ATT_NODOWNLOAD")
	private Long authAttNodownload;
	
	@Column(name="AUTH_ATT_NOCOPY")
	private Long authAttNocopy;
	
	@Column(name="AUTH_ATT_NOPRINT")
	private Long authAttNoprint;
	
	@Column(name="FD_CHANGE_ATT")
	private Long fdChangeAtt;
	
	@Column(name="APPLY_CODE")
	private String applyCode;

	@Column(name="APPLY_DATE")
	private Date applyDate;

	@Column(name="APPLY_DESCRPTION")
	private String applyDescrption;
	
	@Lob
	@Column(name="PROCESS_VAR_XML")
	private String processVarXml;
	
	@Column(name="FD_TEMPLATE_ID")
	private String fdTemplateId;
	
	@Column(name="DOC_CREATOR_ID")
	private String docCreatorId;
	
	@Column(name="DOC_ALTEROR_ID")
	private String docAlterorId;

	@Column(name="DOC_AUTHOR_ID")
	private String docAuthorId;
	
	@Column(name="DOC_DEPT_ID")
	private String docDeptId;
	
	@Column(name="AUTH_AREA_ID")
	private String authAreaId;
	
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

	
	/**
	 * 业务系统名称
	 */
	@Transient
	private String busiName;
	
	/**
	 * 模板名称
	 */
	@Transient
	private String templateName;
	
	/**
	 * 创建者名称
	 */
	@Transient
	private String docCreateName;
}