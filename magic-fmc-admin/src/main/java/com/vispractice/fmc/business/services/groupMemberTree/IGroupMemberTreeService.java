package com.vispractice.fmc.business.services.groupMemberTree;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgGroupCate;

public interface IGroupMemberTreeService {

	/**
	 * 
	 * 实现流程:获取地址本树子节点<br/>
	 * @Title: findSubElements 
	 * @param parentId
	 * @return
	 */
	List<SysOrgGroupCate> findSubElements(String parentId);

	/**
	 * 
	 * 实现流程: 获取地址本树跟节点<br/>
	 * @Title: findRootElements 
	 * @return
	 */
	List<SysOrgGroupCate> findRootElements();

	/**
	 * 
	 * 实现流程:获取群组成员<br/>
	 * @Title: getGroupMember 
	 * @param groupId 群组parentid
	 * @param fdOrgType 组织架构类型
	 * @return
	 */
	List<SysOrgElement> getGroupMember(String groupId,List<Long> fdOrgType);
	
	/**
	 * 
	 * 实现流程:获取地址本树子节点（仅群组）<br/>
	 * @Title: findSubElementsOnlyGroup 
	 * @param parentId
	 * @return
	 */
	List<SysOrgGroupCate> findSubElementsOnlyGroup(String parentId);
	
	/**
	 * 
	 * 实现流程:通过群组类型id获取群组<br/>
	 * @Title: getGroup 
	 * @param cateId
	 * @return
	 */
	List<SysOrgElement> getGroup(String cateId);

	/**
	 * 
	 * 获取地址本成员描述
	 * @param id
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	public String getDescription(String id, String name) throws Exception;
	
	/**
	 * 获取地址本组织架构根节点
	 * @Title: findRootOrgElements 
	 * @return
	 */
	public List<SysOrgElement> findRootOrgElements();

	/**
	 * 获取地址本组织架构子节点 
	 * @Title: findSubOrgElements 
	 * @param parentId
	 * @return
	 */
	public List<SysOrgElement> findSubOrgElements(String parentId);

	/**
	 * 
	 * 获取地址本组织架构人员
	 * @Title: getOrgElements 
	 * @param fdId
	 * @param fdOrgTypes
	 * @return
	 */
	public List<SysOrgElement> getOrgElements(String fdId, List<Long> fdOrgTypes);

}
                                                  