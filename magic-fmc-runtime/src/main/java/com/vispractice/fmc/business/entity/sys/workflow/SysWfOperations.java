package com.vispractice.fmc.business.entity.sys.workflow;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
 
/**
 * 操作方式
 * 编  号：<br/>
 * 名  称：SysWfOperations<br/>
 * 描  述：<br/>
 * 完成日期：2016年12月17日 下午3:55:35<br/>
 * 编码作者：<br/>
 */
@Data
@SuppressWarnings("rawtypes")
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name="SYS_WF_OPERATIONS")
@NamedQuery(name="SysWfOperations.findAll", query="SELECT s FROM SysWfOperations s")
public class SysWfOperations implements Comparable {
	@Id
	@GeneratedValue(generator="system-uuid")  
	@GenericGenerator(name="system-uuid", strategy = "uuid")  
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name="FD_OPER_NAME")
	private String fdOperName;
	
	@Column(name="FD_OPER_TYPE")
	private Long fdOperType;
	
	@Column(name="FD_ORDER")
	private Long fdOrder;
	
	@Column(name="FD_OPERATOR_ID")
	private String fdOperatorId;

	@Override
	public int compareTo(Object o) {
		SysWfOperations sysFlowOperationsForm = (SysWfOperations)o;
		Long currentVal = getFdOrder() == null ? 0 : getFdOrder();
		Long compareVal = sysFlowOperationsForm.getFdOrder() == null?0:sysFlowOperationsForm.getFdOrder();
		
		return currentVal.intValue() - compareVal.intValue(); 
	} 
}
