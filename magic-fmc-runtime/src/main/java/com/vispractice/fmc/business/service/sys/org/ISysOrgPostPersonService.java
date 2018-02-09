package com.vispractice.fmc.business.service.sys.org;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPostPerson;
  
public interface ISysOrgPostPersonService {
 
	/**
	 * 保存
	 */
	public void save(SysOrgPostPerson orgPostPerson);

	/**
	 * 根据岗位查询人员信息
	 */
	public List<String> findFdPersonId(String fdPostId); 
	
	/**
	 * 根据人员查询岗位信息
	 */
	public List<String> findFdPostId(String fdPersonId); 
	
	/**
	 * 
	 * userId和expectedId相同或userId属于expectedId所对应的岗位时，返回true<br/>
	 * @param userId 用户ID
	 * @param expectedId 
	 * @return
	 */
	public boolean checkUserId(String userId, String expectedId);

	/**
	 * 
	 * 用过岗位ID获取岗位下人员
	 * @param fdPostId 岗位ID
	 * @return 人员列表
	 */
	public List<SysOrgElement> getPersons(String fdPostId);

	/**
	 * 保存岗位人员对应关系
	 * @Title: save 
	 * @param orgPostPersonList
	 */
	public void save(List<SysOrgPostPerson> orgPostPersonList);

	/**
	 * 插入一条数据
	 * @Title: add 
	 * @param personId
	 * @param postId
	 */
	public void add(String personId, String postId);

	/**
	 * 通过人员id删除
	 * @param fdPersonId
	 */
	public void deleteByPersonId(String fdPersonId);

	/**
	 * 通过岗位id删除
	 * @param fdPostId
	 */
	public void deleteByPostId(String fdPostId);

	/**
	 * 用过人员ID获取所属岗位
	 * @Title: getPosts 
	 * @param fdPersonId
	 * @return
	 */
	public List<SysOrgElement> getPosts(String fdPersonId);

	/**
	 * 通过人员ID获取其有效的岗位
	 * @param fdPersonId
	 * @return
	 */
	public List<SysOrgElement> getPostByPersonId(String fdPersonId);
	
	/**
	 * 查找人员是否有岗位
	 * @param postId
	 * @param personId
	 * @return
	 */
	public SysOrgPostPerson isExistPersonPost(String personId, String postId);
}
