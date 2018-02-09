package com.vispractice.fmc.business.entity.sys.org.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;

@Repository
@CacheConfig(cacheNames = "sysOrgElement")
public interface SysOrgElementRepository extends JpaRepository<SysOrgElement,String>,JpaSpecificationExecutor<SysOrgElement> {
	public List<SysOrgElement> findByFdOrgType(Long fdOrgType);

	@Query("select a from SysOrgElement a where a.fdNo=?1 and a.fdIsAvailable=1")
	public SysOrgElement findByFdNo(String fdNo);

	@Query("select e from SysOrgElement e where e.fdParentid is null and e.fdOrgType in ?1 and e.fdIsAvailable=1 order by e.fdOrgType, e.fdOrder asc")
	public List<SysOrgElement> findRootElements(List<Long> fdOrgType);
	
	@Query("select e from SysOrgElement e where e.fdParentid = ?1 and e.fdIsAvailable=?2 order by e.fdOrgType, e.fdOrder asc")
	public List<SysOrgElement> findByFdParentidAndFdIsAvailable(String fdParentId,Long isAvaliable);

	@Query("select e from SysOrgElement e where e.fdParentid = ?1 and e.fdOrgType in ?2 and e.fdIsAvailable=?3 order by e.fdOrgType, e.fdOrder asc")
	public List<SysOrgElement> findByFdParentidAndFdOrgTypeInAndFdIsAvailable(String fdParentId,List<Long> fdOrgType,Long isAvaliable);

	@Query("select e from SysOrgElement e where e.fdParentid = ?1 and e.fdOrgType = ?2 and e.fdIsAvailable=?3 order by e.fdOrgType, e.fdOrder asc")
	public List<SysOrgElement> findByFdParentidAndFdOrgTypeAndFdIsAvailable(String fdParentId,Long fdOrgType,Long isAvaliable);

	public List<SysOrgElement> findByFdNameLikeAndFdOrgTypeInAndFdIsAvailable(String fdName,List<Long> orgTypeList,String isAvailable);

	@Modifying
	@Query("update SysOrgElement a set a.fdName=?1, a.fdIsAvailable=?2 where a.fdId=?3")
	int update(String fdName, Long fdIsAvailable, String fdId);

	@Modifying
	@Query("update SysOrgElement e set e.fdIsAvailable = 0,e.fdIsAbandon = 1 where e.fdId =?1")
	public void deleteByFdId(String fdId);
	
	@Modifying
	@Query("update SysOrgElement e set e.fdIsLeaf = ?2 where e.fdId = ?1")
	public void updateFdIsLeafByFdId(String fdId,Long fdIsLeaf);
	
	public List<SysOrgElement> findByFdIdIn(List<String> fdId);
	
	@Query("from SysOrgElement e where e.fdParentid=?1 and e.fdIsAbandon = 0")
	public List<SysOrgElement> findByFdParentid(String fdParentid);

	@Query("from SysOrgElement e where e.fdParentid=?1 and e.fdOrgType in ?2 and e.fdIsAbandon = 0 and e.fdIsAvailable = 1")
	public List<SysOrgElement> findByFdParentidAndFdOrgTypeIn(String fdParentId, List<Long> fdOrgType);

	public List<SysOrgElement> findByFdNameAndFdOrgType(String fdName, Long fdOrgType);
	
	public List<SysOrgElement> findByFdNameAndFdOrgTypeAndFdCateid(String fdName, Long fdOrgType,String fdCateid);

	@Query("from SysOrgElement a where a.fdId in (select b.fdElementid from SysOrgGroupElement b where b.fdGroupid=?1)")
	public List<SysOrgElement> findGroupMember(String fdGroupId);

	@Query("from SysOrgElement a where a.fdId in (select b.authEditorId from SysCategoryMainEditor b where b.fdCategoryId=?1)")
	public List<SysOrgElement> findEditorMember(String fdCategoryId);

	@Query("from SysOrgElement a where a.fdId in (select b.authReaderId from SysCategoryMainReader b where b.fdCategoryId=?1)")
	public List<SysOrgElement> findReaderMember(String fdCategoryId);

	public List<SysOrgElement> findByFdNameAndFdOrgTypeInAndFdIsAvailable(String filterName,List<Long> checkedValue,String availableFlag);

	public List<SysOrgElement> findByFdParentidAndFdNameAndFdOrgTypeInAndFdIsAvailable(String nodeId,String filterName,List<Long> checkedValue,String availableFlag);

	public List<SysOrgElement> findByFdParentidAndFdNameLikeAndFdOrgTypeInAndFdIsAvailable(String nodeId,String string,List<Long> checkedValue,String availableFlag);

	public List<SysOrgElement> findByFdCateidAndFdOrgType(String fdId,Long orgTypeGroup);

	/**
	 * 通过群组类型id获取群组
	 */
	@Query("select a from SysOrgElement a, SysOrgGroupCate b where  a.fdCateid=b.fdId and a.fdOrgType=16 and a.fdCateid=?1")
	public List<SysOrgElement> getGroupByCateId(String cateId);

	/**
	 * 通过群组id获取群组成员
	 */
	@Query("from SysOrgElement a where a.fdId in (select b.fdElementid from SysOrgGroupElement b where b.fdGroupid=?1) and a.fdOrgType in ?2")
	public List<SysOrgElement> getGroupMemberByGroupId(String groupId,List<Long> fdOrgType);
	
	/**
	 * 通过群组id获取群组成员
	 */
	@Query("from SysOrgElement a where a.fdId in (select b.fdElementid from SysOrgGroupElement b where b.fdGroupid=?1) and a.fdOrgType in ?2")
	public List<SysOrgElement> getGroupMemberByGroupIdAndFdOrgType(String groupId,List<Long> fdOrgTypes);

	/**
	 * 通过角色线Id获取角色成员
	 */
	@Query("select e from SysOrgElement e where e.fdId in (select a.fdId from SysOrgRole a,SysOrgRoleConf b where a.fdRoleConfId=b.fdId and b.fdId=?1)")
	public List<SysOrgElement> getRoleByConfId(String confId);

	/**
	 * 通过人员Id获取岗位
	 */
	@Query("select e from SysOrgElement e where e.fdId in (select a.fdPostid from SysOrgPostPerson a where a.fdPersonid=?1) and e.fdIsAvailable=1")
	public List<SysOrgElement> getPostByPersonId(String fdPersonid);

	/**
	 * 实现流程: 根据SysWfNode的fdId获取处理人的element对象<br/>
	 * @Title: gethandlerBySysWfNodeId 
	 * @param sysWfNodeId
	 * @return
	 */
	@Query("select a from SysOrgElement a,SysWfWorkitem b where a.fdId=b.fdExpectedId and b.fdNodeId=?1")
	public SysOrgElement gethandlerBySysWfNodeId(String sysWfNodeId);
	
	/**
	 * 获取通用岗位
	 * @param isAvailable 是否可用
	 * @return List<SysOrgElement>
	 */
	@Query("select e from SysOrgElement e,SysOrgRole r where e.fdId=r.fdId and r.fdRoleConfId is null and e.fdIsAvailable=?1")
	public List<SysOrgElement> getGeneralPost(Long isAvailable);
	
	/**
	 * 获取直接领导
	 * @Title: getThisLeader 
	 * @param fdId 
	 * @return 直接领导
	 */
	@Query("select b from SysOrgElement a,SysOrgElement b where a.fdThisLeaderid=b.fdId and a.fdId=?1")
	public SysOrgElement getThisLeader(String fdId);
	
	/**
	 * 获取上级领导
	 * @Title: getSuperLeader 
	 * @param fdId 
	 * @return 上级领导
	 */
	@Query("select b from SysOrgElement a,SysOrgElement b where a.fdSuperLeaderid=b.fdId and a.fdId=?1")
	public SysOrgElement getSuperLeader(String fdId);
	
	/**
	 * 获取上级
	 * @Title: getParent
	 * @param fdId 
	 * @return 上级
	 */
	@Query("select b from SysOrgElement a,SysOrgElement b where a.fdParentid=b.fdId and a.fdId=?1")
	public SysOrgElement getParent(String fdId);
	
	/**
	 * 根据用户获取岗位信息
	 * @param fdId
	 * @return
	 */
	@Query("select a from SysOrgElement a,SysOrgPostPerson b where a.fdId=b.fdPostid and b.fdPersonid=?1")
	public List<SysOrgElement> getPostElement(String fdId);
	
	public List<SysOrgElement> findByFdCalendarId(String fdCalendarId);

	@Query("select a from SysOrgElement a,SysOrgPostPerson b where a.fdId=b.fdPersonid and b.fdPostid=?1")
	public List<SysOrgElement> findPostPerson(String postId);
	
	public List<SysOrgElement> findByFdName(String fdName);

	@Query("select a from SysOrgElement a where a.fdNo=?1  and a.fdIsAvailable=1")
	public SysOrgElement findOrgByFdNo(String fdNo);
}
