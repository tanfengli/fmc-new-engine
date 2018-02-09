package com.vispractice.fmc.business.entity.sys.menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * 编  号：
 * 名  称：SysMenu
 * 描  述：流程系统菜单
 * 完成日期：2017年9月28日 下午5:03:00
 * 编码作者："LaiJiashen"
 */

@Data
@Entity
@Table(name="sys_menu")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SysMenu {
	
	/**
	 * 成为子节点
	 */
	public static final String inner = "inner";
	/**
	 * 成为同级前一个节点
	 */
	public static final String prev = "prev";
	/**
	 * 成为同级后一个节点
	 */
	public static final String next = "next";
	/**
	 * 序号递增数
	 */
	public static Long increaseNumber = 100L;
	/**
	 * 无需授权
	 */
	public static final Long notNeedAuthorize = 1L;
	/**
	 * 菜单授权
	 */
	public static final Long menuAuhorize = 0L;
	
	@Id
	@Column(name="FD_ID")
	private String fdId;
	
	@Column(name = "FD_NAME")
	private String fdName;

	@Column(name = "FD_HIERARCHY_ID")
	private String fdHierarchyId;
	
	@Column(name = "DOC_CREATE_TIME")
	private Date docCreateTime;

	@Column(name = "DOC_ALTER_TIME")
	private Date docAlterTime;
	
	@Column(name = "DOC_CREATOR_ID")
	private String docCreateId;

	@Column(name = "DOC_ALTEROR_ID")
	private String docAlterorId;

	@Column(name = "FD_PARENT_ID")
	private String fdParentId;
	
	@Column(name = "LINK_URL")
	private String linkUrl;
	
	@Column(name="FD_IS_LEAF")
	private Long fdIsLeaf;
	
	@Column(name="FD_IS_AVAILABLE")
	private Long fdIsAvailable;
	
	@Column(name = "ICON")
	private String icon;
	
	@Column(name = "FD_ORDER")
	private Long fdOrder;
	
	@Column(name = "FD_DESCRIPTION")
	private String fdDescription;
	
	@Column(name = "FD_NO")
	private String fdNo;
	
	@Column(name = "FD_TYPE")
	private String fdType;
	
	@Column(name = "BUSI_SYS_ID")
	private String busiSysId;
	
	@Column(name = "FD_IS_OPEN")
	private Long fdIsOpen;
	
	@Column(name = "FD_AUTHORIZE_TYPE")
	private Long fdAuthorizeType;
	
	/**
	 * 菜单路径
	 */
	@Transient
	private String path;
	
	/**
	 * 自定义参数
	 */
	@Transient
	private String fdParameter;
	
	/**
	 * 自定义参数列表
	 */
	@Transient
	private List<SysMenuParam> paramList;
	
	/**
	 * 菜单名称/编号 
	 */
	@Transient
	private String filterName;
	
	/****节点移动所需属性****/
	/**
	 * 移动位置类型
	 */
	@Transient
	private String moveType;
	
	public boolean getOpen(){
		return null!=this.fdIsOpen&&this.fdIsOpen==1?true:false;
	}
	
	public String getIsParent(){
		return null==this.fdIsLeaf||this.fdIsLeaf==1?"false":"true";
	};
	
	public void setDocCreateTime(String currentTimeMills) throws ParseException {
		if (StringUtils.isNotEmpty(currentTimeMills)) {
			SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.docCreateTime = a.parse(currentTimeMills);
		}
	}
	
	public void setDocAlterTime(String currentTimeMills) throws ParseException {
		if (StringUtils.isNotEmpty(currentTimeMills)) {
			SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.docAlterTime =a.parse(currentTimeMills);
		}
	}
	
}
