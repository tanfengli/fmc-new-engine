package com.vispractice.fmc.business.entity.sys.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vispractice.fmc.business.entity.sys.menu.SysMenu;
/**
 * 
 * 编  号：
 * 名  称：SysMenuRepository
 * 描  述：流程系统菜单持久层
 * 完成日期：2017年9月28日 下午5:32:43
 * 编码作者："LaiJiashen"
 */
@Repository
public interface SysMenuRepository extends JpaRepository<SysMenu, String>, JpaSpecificationExecutor<SysMenu>{

	/**
	 * 获取流程菜单根节点
	 * @param busiSysId 业务系统id
	 * @return 菜单根节点列表
	 */
	@Query("from SysMenu a where a.fdParentId is null and a.busiSysId=?1 order by a.fdOrder")
	public List<SysMenu> findRoot(String busiSysId);
	
	/**
	 * 获取流程菜单根节点
	 * @param busiSysId 业务系统id
	 * @param fdAuthorizeType 授权方式
	 * @return 菜单根节点列表
	 */
	@Query("from SysMenu a where a.fdParentId is null and a.busiSysId=?1 and a.fdAuthorizeType=?2 order by a.fdOrder")
	public List<SysMenu> findRoot(String busiSysId,Long fdAuthorizeType);
	
	@Query("from SysMenu a where a.fdParentId is null order by a.fdOrder")
	public List<SysMenu> findRoot();
	
	/**
	 * 根据编号查询
	 * @Title: findByFdName 
	 * @return
	 */
	public List<SysMenu> findByFdNo(String fdNo);
	
	/**
	 * 根据层级id模糊查询
	 * @param fdHierarchyId
	 * @return
	 */
	public List<SysMenu> findByFdHierarchyIdLike(String fdHierarchyId);
	
	/**
	 * 根据父节点获取菜单
	 * @param parentId
	 * @return 菜单下级列表
	 */
	public List<SysMenu> findByFdParentId(String parentId);
	
	/**
	 * 根据父节点获取菜单并排序
	 * @param parentId
	 * @return 菜单下级列表
	 */
	public List<SysMenu> findByFdParentIdOrderByFdOrder(String parentId);
	
	/**
	 * 根据父节点获取菜单并排序
	 * @param parentId
	 * @return 菜单下级列表
	 */
	public List<SysMenu> findByFdParentIdAndFdAuthorizeTypeOrderByFdOrder(String parentId,Long fdAuthorizeType);
	
	/**
	 * 获取所有流程菜单，由fdOrder递增排序
	 * @return
	 */
	@Query("select a from SysMenu a where a.fdIsAvailable=1 order by a.fdParentId,a.fdOrder")
	public List<SysMenu> getAllByOrderByFdParentIdAndFdOrder();
	
	/**
	 * 根据父级id获取当前级别下的fdOrder的最大值
	 * @param parentId
	 * @return
	 */
	@Query("select max(a.fdOrder) from SysMenu a where a.fdParentId = ?1")
	public Long findMaxFdOrderByFdParentId(String parentId);
	
	/**
	 * 获取流程菜单中根节点的fdOrder的最大值
	 * @param parentId
	 * @return
	 */
	@Query("select max(a.fdOrder) from SysMenu a where a.fdParentId is null")
	public Long findMaxFdOrderByFdRoot();
	
	/**
	 * 找出同级内下一个排序号
	 * @param parentId 父级id
	 * @param order 当前排序号
	 * @return 后一个排序号
	 */
	@Query("select max(a.fdOrder) from SysMenu a where a.fdParentId = ?1 and a.fdOrder<?2")
	public Long findPrevOrder(String parentId,Long order);
	
	/**
	 * 找出同级内上一个排序号
	 * @param parentId 父级id
	 * @param order 当前排序号
	 * @return 前一个排序号
	 */
	@Query("select min(a.fdOrder) from SysMenu a where a.fdParentId = ?1 and a.fdOrder>?2")
	public Long findNextOrder(String parentId,Long order);
	
	/**
	 * 找出根节点内下一个排序号
	 * @param order 当前排序号
	 * @return 后一个排序号
	 */
	@Query("select max(a.fdOrder) from SysMenu a where a.fdParentId is null and a.fdOrder<?1")
	public Long findPrevOrderByRoot(Long order);
	
	/**
	 * 找出根节点内上一个排序号
	 * @param order 当前排序号
	 * @return 前一个排序号
	 */
	@Query("select min(a.fdOrder) from SysMenu a where a.fdParentId is null and a.fdOrder>?1")
	public Long findNextOrderByRoot(Long order);
	
	/**
	 * 获取同级内当前排序后所有节点（包括本节点）
	 * @param parentId 父级id
	 * @param order 排序号
	 * @return
	 */
	@Query("from SysMenu a where a.fdParentId=?1 and a.fdOrder>=?2")
	public List<SysMenu> findAllNextMenu(String parentId,Long order);
	
	/**
	 * 获取根节点内当前排序后所有节点（包括本节点）
	 * @param order 排序号
	 * @return
	 */
	@Query("from SysMenu a where a.fdParentId is null and a.fdOrder>=?1")
	public List<SysMenu> findAllNextMenuByRoot(Long order);
	
	/**
	 * 通过层级id删除菜单及其所有子级
	 * @Title: deleteByFdHierarchyIdLike 
	 * @param hierarchyId
	 */
	@Modifying
	@Query("delete from SysMenu a where a.fdHierarchyId like ?1")
	public void deleteSubs(String hierarchyId);
	
	/**
	 * 更新叶子字段
	 * @param fdId 
	 * @param fdIsLeaf
	 */
	@Modifying
	@Query("update SysMenu a set a.fdIsLeaf = ?2 where a.fdId=?1")
	public void updateFdIsLeaf(String fdId,Long fdIsLeaf);
	
}
