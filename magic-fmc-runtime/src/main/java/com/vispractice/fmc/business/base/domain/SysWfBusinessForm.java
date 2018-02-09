package com.vispractice.fmc.business.base.domain;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfHistoryNode;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfNode;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfLogV;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class SysWfBusinessForm {
	/**
	 * 单据基本信息
	 */
	private SysNewsMain basic;
	
	/**
	 * 单据审批日志信息
	 */
	private List<SysWfLogV> wfLog;
	
	/**
	 * 单据节点信息
	 */
	private List<SysWfNode> nodeList;
	
	/**
	 * 单据历史记录信息
	 */
	private List<SysWfHistoryNode> historyList;
	
	/**
	 * 流程图内容
	 */
	private String fdFlowContent;

	/**
	 * 当前处理人
	 */
	private String currentHandlerIds;
	
	/**
	 * 当前处理人名称
	 */
	private String currentHandlerNames;
	
	/**
	 * 已经处理人名称
	 */
	private String historyHandlerNames;
	
	/**
	 * 已阅人员
	 */
	private String readerNames;
	
	/**
	 * 阅读次数
	 */
	private Long readTimes;
}
