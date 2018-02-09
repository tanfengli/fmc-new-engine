package com.vispractice.fmc.business.base.domain;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * 编  号：<br/>
 * 名  称：SysOrgElementForm<br/>
 * 描  述：组织架构查询条件<br/>
 * 完成日期：2016年12月22日 上午10:43:21<br/>
 * 编码作者：sky<br/>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysOrgElementForm {
	
	private String nodeId;	//树节点
	
	private List<Long> checkedValue;	//
	
	private String filterName;
	
	private List<Long> availableFlag;
	
	//查询方式：true为精确查询，false为模糊查询
	private Boolean searchMode; 
	
	//是否根据树节点id查询
	private Boolean ifSearch;
	
	
	
	

	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId
	 *            要设置的 nodeId
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the checkedValue
	 */
	public List<Long> getCheckedValue() {
		return checkedValue;
	}

	/**
	 * @param checkedValue 要设置的  checkedValue
	 */
	public void setCheckedValue(List<Long> checkedValue) {
		this.checkedValue = checkedValue;
	}

	/**
	 * @return the filterName
	 */
	public String getFilterName() {
		return filterName;
	}

	/**
	 * @param filterName 要设置的  filterName
	 */
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public Boolean getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(Boolean searchMode) {
		this.searchMode = searchMode;
	}

	public Boolean getIfSearch() {
		return ifSearch;
	}

	public void setIfSearch(Boolean ifSearch) {
		this.ifSearch = ifSearch;
	}

	
}
