package com.vispractice.fmc.business.entity.sys.workflow.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * 编  号：
 * 名  称：SysWfHistoryTemplateV
 * 描  述：SysWfHistoryTemplateV.java
 * 完成日期：2017年12月8日 上午10:55:24
 * 编码作者："LaiJiashen"
 */
@Data
@Entity
@Table(name="sys_wf_history_template_v")
public class SysWfHistoryTemplateV implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name="FD_ID")
	private String fdId;

	@Column(name="FD_CATEGORY_NAME")
	private String fdCategoryName;
	
	@Column(name="FD_VERSION_NUM")
	private String fdVersionNum;
	
	@Column(name="FD_MODIFY_NAME")
	private String fdModifyName;
	
	@Column(name="FD_MODEL_ID")
	private String fdModelId;
	
	@Column(name="FD_MODIFY_TIME")
	private Date fdModifyTime;
	
	@Column(name="FD_STATUS")
	private String fdStatus;
	
}