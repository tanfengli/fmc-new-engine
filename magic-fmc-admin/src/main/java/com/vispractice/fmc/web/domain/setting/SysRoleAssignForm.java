package com.vispractice.fmc.web.domain.setting;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vispractice.fmc.business.entities.sys.SysAuthRole;
import com.vispractice.fmc.business.entity.sys.menu.SysMenu;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
/**
 * 
 * 编  号：
 * 名  称：SysRoleAssignForm
 * 描  述：角色分配页面form
 * 完成日期：2017年9月20日 上午11:17:00
 * 编码作者："LaiJiashen"
 */
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class SysRoleAssignForm {

	private String fdId;
	private String fdName;
	private String categoryId;
	private String categoryName;
	private String creatorName;
	private String fdDescription;
	private List<SysOrgElement> uraList;
	private String uraName;
	private List<SysOrgElement> edtList;
	private String edtName;
	/**
	 * 继承角色列表
	 */
	private List<SysAuthRole> inhRoleList;
	/**
	 * 继承角色名臣个
	 */
	private String inhRoleName;
	/**
	 * 已授权菜单列表
	 */
	private List<SysMenu> authorizedMenus;
	

}
