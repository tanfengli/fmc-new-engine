package com.vispractice.fmc.business.entity.sys.news.view;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "sys_news_basic_v")
public class SysNewsBasicV implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "FD_ID")
	private String fdId;

	@Column(name = "title")
	private String title;

	@Column(name = "sys_news_template_fd_id")
	private String sysNewsTemplateFdId;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "apply_code")
	private String applyCode;

	@Column(name = "busy_name")
	private String busyName;

	@Column(name = "fd_importance")
	private Long fdImportance;

	@Column(name = "fd_login_name")
	private String fdLoginName;
	
	@Column(name = "creator_name")
	private String creatorName;

	@Column(name = "doc_create_time")
	private Date docCreatTime;

	@Column(name = "doc_read_count")
	private String docReadCount;
	
	//当前处理人名字
	//@Transient
	//private String currentHandlerNames;
	
	//当前处理人（不包括岗位）
	//@Transient
	//private String currHandlerIds;
	
	//当前处理人名称（不包括岗位）
	//@Transient
	//private String currHandlerNames;
	
	//当前处理人组织架构列表
	//@Transient
	//private List<SysOrgElement> currHandlerArray;

	//已经处理人名字
	//@Transient
	//private String historyHandlerNames;

	//流程说明
	//@Transient
	//private String wfDescription;

	//即将流向名称
	//@Transient
	//private String nextNode;

	//即将节点
	//@Transient
	//private String nextNodeId;

	//即将节点
	//@Transient
	//private String nextNodeName;

	//即将节点
	//@Transient
	//private String fdFactNodeId;

	//即将节点
	//@Transient
	//private String fdFactNodeName;

	//@Transient
	//private List<SysWfLogV> rejectNode;
}
