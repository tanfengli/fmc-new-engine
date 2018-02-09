package com.vispractice.fmc.business.entity.sys.org.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLine;

@Repository
public interface SysOrgRoleLineRepository
		extends JpaRepository<SysOrgRoleLine, String>, JpaSpecificationExecutor<SysOrgRoleLine> {

	@Query("from SysOrgRoleLine a where a.fdRoleLineConfId=?1 and a.fdParentId is null order by fdName")
	public List<SysOrgRoleLine> findRoot(String fdRoleLineConfId);

	public List<SysOrgRoleLine> findByFdParentIdOrderByFdName(String parentId);

	/**
	 * 逻辑删除角色线成员
	 * @Title: deleteByFdId 
	 * @param fdId
	 */
	@Modifying
	@Query("update from SysOrgRoleLine l set l.fdIsAvailable=0 where l.fdId=?1")
	public void deleteByFdId(String fdId);

	public List<SysOrgRoleLine> findByFdParentId(String parentId);
	
	public List<SysOrgRoleLine> findByFdParentIdAndFdIsAvailable(String parentId,Long fdIsAvailable);

	public List<SysOrgRoleLine> findByFdId(String fdId);

	@Query("from SysOrgRoleLine a where a.fdHierarchyId like ?1%")
	public List<SysOrgRoleLine> findByFdHierarchyIdLike(String hierarchyId);
	
	@Query("from SysOrgRoleLine a where a.fdHierarchyId like ?1% and a.fdHierarchyId!=?1")
	public List<SysOrgRoleLine> findByFdHierarchyIdLikeExcludeSelf(String hierarchyId);

	@Query("from SysOrgRoleLine a where a.fdRoleLineConfId=?1 ")
	public List<SysOrgRoleLine> findByFdRoleLineConfId(String confId);
	
	/**
	 * 
	 * 根据角色线ID获取角色线成员并根据层级ID排序
	 * @param roleLineConfId 角色线ID
	 * @return
	 */
	public List<SysOrgRoleLine> findByFdRoleLineConfIdOrderByFdHierarchyId(String roleLineConfId);
	
	
	/**
	 * 根据成员id和角色线分类id查找角色线成员
	 * @Title: findFdIdAndMemberId 
	 * @param memberIds 成员id列表
	 * @param confId 角色线分类id
	 * @return
	 */
	@Query("from SysOrgRoleLine a where a.fdMemberId in ?1 and a.fdRoleLineConfId=?2")
	public List<SysOrgRoleLine> findFdIdAndMemberId(List<String> memberIds,String confId);

	/**
	 * 
	 * 获取直接下属
	 * @param 角色线成员fdId
	 * @return 组织架构实体列表
	 */
	@Query("select distinct e from SysOrgRoleLine l , SysOrgElement e where l.fdMemberId is not null and l.fdMemberId=e.fdId and l.fdParentId=?1")
	public List<SysOrgElement> getDirectSubordinate(String fdId);
	
	//获取所有下属
	@Query("select distinct e from SysOrgRoleLine l , SysOrgElement e where l.fdMemberId is not null and l.fdMemberId=e.fdId and l.fdHierarchyId like ?1")
	public List<SysOrgElement> getAllSubordinate(String fdHierarchyId);
	
	/**
	 * 更新角色线成员的是否叶子标识
	 * @param fdId 
	 * @param isLeaf 
	 */
	@Modifying
	@Query("update SysOrgRoleLine set fdIsLeaf = ?2 where fdId = ?1")
	public void updateFdIsLeafByFdId(String fdId,Long isLeaf);
	
}
