package com.vispractice.fmc.business.entity.sys.workflow;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.vispractice.fmc.base.utils.AutoArrayList;
 
/**
 * 操作方式
 * 编  号：<br/>
 * 名  称：SysWfOperMain<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月17日 下午3:55:35<br/>
 * 编码作者：zhaoxiu<br/>
 */
@Entity
@Table(name="sys_wf_oper_main")
@NamedQuery(name="SysWfOperMain.findAll", query="SELECT s FROM SysWfOperMain s")
public class SysWfOperMain implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	 
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	  
	@Column(name="FD_NAME")
	private String fdName;
	
	@Column(name="FD_NODE_TYPE")
	private String fdNodeType;
	
	@Column(name="FD_IS_DEFAULT")
	private String fdIsDefault;
	
	@SuppressWarnings("unchecked")
	@Transient
	private List<SysWfOperations> operationsList = new AutoArrayList(SysWfOperations.class);
 
	@SuppressWarnings("unchecked")
	@Transient
	private List<SysWfOperations> operationsListTwo = new AutoArrayList(SysWfOperations.class);
	
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

	public String getFdNodeType() {
		return fdNodeType;
	}

	public void setFdNodeType(String fdNodeType) {
		this.fdNodeType = fdNodeType;
	}

	public String getFdIsDefault() {
		return fdIsDefault;
	}

	public void setFdIsDefault(String fdIsDefault) {
		this.fdIsDefault = fdIsDefault;
	}

	public List<SysWfOperations> getOperationsList() {
		return operationsList;
	}

	public void setOperationsList(List<SysWfOperations> operationsList) {
		this.operationsList = operationsList;
	}

	public List<SysWfOperations> getOperationsListTwo() {
		return operationsListTwo;
	}

	public void setOperationsListTwo(List<SysWfOperations> operationsListTwo) {
		this.operationsListTwo = operationsListTwo;
	}  
	
}
