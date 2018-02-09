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
 * 变量设置
 * 编  号：<br/>
 * 名  称：SysWfVarInfo<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月17日 下午1:45:48<br/>
 * 编码作者：
 */ 
@Data
@Entity
@Table(name="sys_wf_var_info")
@NamedQuery(name="SysWfVarInfo.findAll", query="SELECT s FROM SysWfVarInfo s")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysWfVarInfo implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="VAR_NAME")
	private String varName;
	
	@Column(name="VAR_TYPE")
	private String varType;
	 
	@Column(name="VAR_USE_TYPE")
	private String varUseType;
	 
	@Column(name="VAR_IS_JDBC")
	private String varIsJdbc;
	
	@Column(name="VAR_SQL")
	private String varSql;
	
	@Column(name="VAR_CODE")
	private String varCode;
	
	@Column(name="VAR_STATUS")
	private String varStatus;
	 
	@Column(name="VAR_CREATE_TIME")
	private Date varCreateTime;
	
	@Column(name="VAR_CREATOR_ID")
	private String varCreatorId;
	
	@Column(name="VAR_CONN_FD_ID")
	private String varConnFdId;
	
	public String getIsParent(){
		return this.varCode==""?"true":"false";
	};
}
