package com.vispractice.fmc.business.base.domain;

import java.util.List;
import java.util.Map;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;

@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class SbWFApprovalForm {
	/**
	 * 流程实例标识
	 */
	private String wfInstanceId;
	
	/**
	 * 当前处理人
	 */
	private String wfAuditedUserId;
	
	/**
	 * 当前处理人编号
	 */
	private String wfAuditedUserNo;
	
	/**
	 * 当前处理人名称
	 */
	private String wfAuditedUserName;

	/**
	 * 当前人员岗位信息
	 */
	private List<SysOrgElement> currentPosts;
	
	/**
	 * 当前人员岗位编码
	 */
	private String postCode;
	
	/**
	 * 审批结果
	 */
	private String wfResult;
	
	/**
	 * 审批意见内容
	 */
	private String wfOptionCon;
	
	/**
	 * 当前处理节点
	 */
	private String fdHandleNodeId;
	
	/**
	 * 当前工作事项
	 */
	private List<SysWfWorkitem> currWorkItems;
	
	/**
	 * 当前工作项 
	 */
	private String fdHandleWorkItemId;
	
	/**
	 * 与当前工作事项关联工作事项
	 */
	private String fdRelationWorkitemId;
	
	/**
	 * 取消沟通的关联工作事项
	 */
	private String celRelationWorkitemIds;
	
	/**
	 * 下一节点（决策指定节点或者驳回节点）
	 */
	private String futureNodeId;
	
	/**
	 * 下一节点处理人
	 */
	private String futureHandlerIds;
	
	/**
	 * 下一节点处理人编码
	 */
	private String futureHandlerNos;
	
	/**
	 * 下一节点处理人名称
	 */
	private String futureHandlerNames;
	
	/**
	 * 下一节点处理人类型
	 */
	private String futureHandlerSelectType;
	
	/**
	 * 转办人编号
	 */
	private String wfTransferUserNo;
	
	/**
	 * 转交人标识
	 */
	private String toOtherHandlerIds;
	
	/**
	 * 转交人标识
	 */
	private String toOtherHandlerNos;

	/**
	 * 转交人名称
	 */
	private String toOtherHandlerNames;
	
	/**
	 * 转交之前人名称
	 */
	private String toOldHandlerNames;
	
	/**
	 * 加签操作类型
	 */
	private String sourceOperType;
	
	/**
	 * 驳回通过后是否返回此节点
	 */
	private boolean refusePassedToThisNode;
	
	/**
	 * 当前节点操作名称列表 
	 */
	private List<Map<String,Object>> oprNames;

	/**
	 * 通知方式
	 */
	private List<String> notifyType;

	/**
	 * 流程结束时通知我
	 */
	private boolean notifyOnFinish;
	
	/**
	 * 沟通人员
	 */
	private String commicateHandlerIds;
	/**
	 * 沟通人员编码
	 */
	private String commicateHandlerNos;
	
	/**
	 * 沟通人员名称
	 */
	private String commicateHandlerNames;

	/**
	 * 隐藏沟通意见
	 */
	private String isHiddenAuditNote;

	/**
	 * 允许多级沟通
	 */
	private String isMutiCommunicate;
	
	/**
	 * 子级沟通人员范围
	 */
	private String communicateScopeHandlerIds;

	/**
	 * 子级沟通人员范围名称
	 */
	private String communicateScopeHandlerNames;
	
	/**
	 * 取消沟通人员列表
	 */
	private List<Map<String,String>> communicaterList;
	
	/**
	 * 选中取消沟通人员列表
	 */
	private List<Map<String,String>> celCommunicaterList;
	
	/**
	 * 单据基本信息
	 */
	private SysNewsMain sysNewsMain;
	

	/**
	 * 重新定位是否重启子流程
	 */
	private String isRecoverPassedSubprocess;
	
	/**
	 * 回收子流程
	 */
	private String recoverProcessIds;
	
	/**
	 * 回收子流程名称
	 */
	private String recoverProcessNames;
	
	/**
	 * 修改流程图
	 */
	private String flowChartXml;
	
	/**
	 * 需要修改节点列表
	 */
	private List<Map<String,String>> modifiedNodeList;
	
	/**
	 * 可阅读者
	 */
	private List<SysOrgElement> readerArray;
	
	/**
	 * 可编辑者
	 */
	private List<SysOrgElement> editorArray;
}
