package com.vispractice.fmc.business.service.sys.menu;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vispractice.fmc.business.entity.sys.menu.SysMenu;

/**
 * 
 * 编  号：
 * 名  称：ISysMenuService
 * 描  述：流程系统菜单服务
 * 完成日期：2017年9月28日 下午5:34:22
 * 编码作者："LaiJiashen"
 */
public interface ISysMenuService {

	/**
	 * 新增或修改一个菜单
	 * @param sysMenu
	 * @return 
	 * @throws Exception 
	 */
	public SysMenu save(SysMenu sysMenu) throws Exception;

	/**
	 * 获取流程菜单根节点
	 * @return
	 */
	public List<SysMenu> getRootElements();

	/**
	 * 获取流程菜单子节点
	 * @param 父级ID
	 * @return
	 */
	public List<SysMenu> getSubElements(String parentId);

	/**
	 * 根据id删除菜单及其所有下级
	 * @param fdId
	 * @return 
	 * @throws Exception 
	 */
	public void deleteByFdId(String fdId) throws Exception;

	/**
	 * 通过id获取菜单
	 * @param fdId
	 * @return
	 */
	public SysMenu findByFdId(String fdId);

	/**
	 * 根据父级id获取当前级别下的fdOrder的最大值
	 * @param parentId
	 * @return fdOrder （当前级别下没有节点时返回0，即若父节点order为11，则返回110）
	 * @throws Exception 
	 */
	public Long findMaxFdOrderByFdParentId(String parentId) throws Exception;

	/**
	 * 获取页面编辑用formItem
	 * @Title: getFormItem 
	 * @param sysMenu
	 * @return
	 */
	public SysMenu getFormItem(SysMenu sysMenu);

	/**
	 * 分页查询
	 * @Title: findByPage 
	 * @param sysMenu
	 * @param pageable
	 * @return
	 */
	public Page<SysMenu> findByPage(SysMenu sysMenu, Pageable pageable);

	/**
	 * 移动节点
	 * @param sysMenu 移动节点
	 * @param targetNode 目标节点
	 * @return
	 * @throws Exception
	 */
	public SysMenu moveNode(SysMenu sysMenu,SysMenu targetNode) throws Exception;

	/**
	 * 获取需要授权的子级菜单
	 * @param fdId
	 * @return
	 */
	public List<SysMenu> getNeedAuthSubElements(String fdId);
	
}
