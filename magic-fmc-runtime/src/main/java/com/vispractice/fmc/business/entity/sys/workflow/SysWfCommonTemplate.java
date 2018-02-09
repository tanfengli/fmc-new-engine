package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 通用模板管理
 * 编  号：<br/>
 * 名  称：SysWfCommonTemplate<br/>
 * 描  述：<br/>
 * 完成日期：2017年1月9日 下午3:28:50<br/>
 * 编码作者：Administrator<br/>
 */
@Data
@Entity
@Table(name="sys_wf_common_template")
@NamedQuery(name="SysWfCommonTemplate.findAll", query="SELECT s FROM SysWfCommonTemplate s")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysWfCommonTemplate implements Serializable{
 
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_NAME")
	private String fdName;

	@Column(name="FD_IS_DEFAULT")
	private Long fdIsDefault;
	
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
	
	@Column(name="FD_CREATOR_ID")
	private String fdCreatorId;

}
