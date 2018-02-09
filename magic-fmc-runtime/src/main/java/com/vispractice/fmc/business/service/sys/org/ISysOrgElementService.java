package com.vispractice.fmc.business.service.sys.org;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.base.domain.SysOrgElementForm;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;

/**
 * 编  号：<br/>
 * 名  称：ISysOrgElementService<br/>
 * 描  述：组织管理服务接口<br/>
 * 完成日期：2016年12月14日 下午2:12:56<br/>
 * 编码作者：sky<br/>
 */
public interface ISysOrgElementService {
	
	/**
	 * 
	 * 实现流程:读取根路径<br/>
	 * @Title: getRoot 
	 * @return List
	 */
	public List<SysOrgElement> findRootElements();
	/**
	 * 
	 * 实现流程:读取单层下级节点<br/>
	 * @return
	 */
	public List<SysOrgElement> findSubElements(String parentId);
	
	/**
	 * 
	 * 实现流程:根据父节点和 fd_org_type   查询  1：机构 2：部门 4：岗位 8：人员 <br/>
	 * @return
	 */
	List<SysOrgElement> findElements(String fdParentId, List<Long> list);
	
	/**
	 * 
	 * 实现流程:读取所有递归下级<br/>
	 * @return
	 */
	public Page<SysOrgElement> findHierarchyElements(SysOrgElementForm sysOrgElementForm,Pageable pageable);
	
	public SysOrgElement findByFdNo(String fdNo);
	
	public SysOrgElement save(SysOrgElement sysOrgElement);

	/**
	 * 实现流程:TODO(根据id找对象)<br/>
	 * 1.XXX<br/>
	 * @Title: findByFdId 
	 * @param fdId
	 * @return
	 */
	public SysOrgElement findByFdId(String fdId);
	
	/**
	 * 根据id列表获取组织架构成员
	 * @param fdIdList
	 * @return
	 */
	public List<SysOrgElement> findByFdIdIn(List<String> fdIdList);
	
	/**
	 * 依据传入id的顺序列表获取组织架构成员
	 * @param fdIdList
	 * @return
	 */
	public List<SysOrgElement> findOrderElementByFdIdIn(List<String> fdIdList);
	
	/** 
	 * 实现流程:TODO(查询人员信息)<br/>
	 * 1.XXX<br/>
	 * @Title: findByName 
	 * @param fdName
	 * @param fdOrgType
	 * @return
	 */
	public List<SysOrgElement> findByName(String fdName,Long fdOrgType);
	
	List<SysOrgElement> findFdNameElements(SysOrgElementForm sysOrgElementForm);
	
	/**
	 * 
	 * 删除节点
	 * @param fdId	节点ID
	 */
	public boolean deleteByFdId(String fdId);
	
	List<SysOrgElement> findFdNameType(SysOrgElementForm sysOrgElementForm);
	
	/**
	 * 
	 * 获取人员的岗位<br/>
	 * @Title: findPosts 
	 * @param fdId 人员ID
	 * @return	岗位列表
	 */
	List<SysOrgElement> findPosts(String fdId);
	
	/**
	 * 获取岗位下人员
	 * @param postId
	 * @return
	 */
	List<SysOrgElement> findPostPerson(String postId);
	
	/**
	 * 
	 * 根据父节点和组织架构类型查找子节点
	 * @Title: findSubElements 
	 * @param parentId 父节点fdid
	 * @param fdOrgTypeList 组织架构类型
	 * @return
	 */
	public List<SysOrgElement> findSubElements(String parentId,List<Long> fdOrgTypeList);
	
	
	/** 
	 * 实现流程:TODO(查询人员信息)<br/>
	 * 1.XXX<br/>
	 * @Title: findByName 
	 * @param fdName
	 * @param fdOrgType
	 * @return
	 */
	public List<SysOrgElement> findByName(String fdName);
	/**
	 * 根据fdNo查询组织、部门等对象
	 * @param fdNo
	 * @return
	 */
	public SysOrgElement findOrgByFdNo(String fdNo);
	
}
