package com.vispractice.fmc.business.service.sys.menu.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.business.base.constrant.BaseTreeConstant;
import com.vispractice.fmc.business.entity.sys.config.SysBusiSys;
import com.vispractice.fmc.business.entity.sys.config.repository.SysBusiSysRepository;
import com.vispractice.fmc.business.entity.sys.menu.SysMenu;
import com.vispractice.fmc.business.entity.sys.menu.SysMenuParam;
import com.vispractice.fmc.business.entity.sys.menu.repository.SysMenuParamRepository;
import com.vispractice.fmc.business.entity.sys.menu.repository.SysMenuRepository;
import com.vispractice.fmc.business.service.sys.menu.ISysMenuService;
/**
 * 
 * 编  号：
 * 名  称：SysMenuServiceImpl
 * 描  述：SysMenuServiceImpl.java
 * 完成日期：2017年9月28日 下午5:35:49
 * 编码作者："LaiJiashen"
 */
@Service
@Transactional
public class SysMenuServiceImpl implements ISysMenuService{
	
	@Autowired
	private SysMenuRepository sysMenuRepository;
	
	@Autowired
	private SysBusiSysRepository sysBusiSysRepository;
	
	@Autowired
	private SysMenuParamRepository sysMenuParamRepository;
	
	@Autowired
	private ResourceUtil resourceUtil;
	
	@Override
	public SysMenu save(SysMenu sysMenu) throws Exception{
		SysMenu sysMenu2 = null;
		try {
			List<SysMenu> menuList = sysMenuRepository.findByFdNo(sysMenu.getFdNo());
			if (menuList.size()>0&&!menuList.get(0).getFdId().equals(sysMenu.getFdId())) {
				throw new Exception("菜单编码已存在，请重新输入!");
			}
			
			// 若当前节点无效或无需授权时更新其所有子级
			if (sysMenu.getFdIsAvailable()==Long.valueOf(CommonConstant.NOT_AVAILABLE_FLAG)||sysMenu.getFdAuthorizeType()==SysMenu.notNeedAuthorize) {
				List<SysMenu> subMenuList = sysMenuRepository.findByFdHierarchyIdLike(sysMenu.getFdHierarchyId()+"%");
				for (SysMenu subMenu : subMenuList) {
					// 所有下级也设为无效
					if (sysMenu.getFdIsAvailable()==Long.valueOf(CommonConstant.NOT_AVAILABLE_FLAG))
						subMenu.setFdIsAvailable(Long.valueOf(CommonConstant.NOT_AVAILABLE_FLAG));
					// 所有下级也设为无需授权
					if (sysMenu.getFdAuthorizeType()==SysMenu.notNeedAuthorize)
						subMenu.setFdAuthorizeType(SysMenu.notNeedAuthorize);
				}
				sysMenuRepository.save(subMenuList);
			}
			
			sysMenu2 = sysMenuRepository.save(sysMenu);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return sysMenu2;
	}
	
	/**
	 * 获取根节点
	 */
	@Override
	public List<SysMenu> getRootElements() {
		List<SysMenu> list = new ArrayList<SysMenu>();
		List<SysBusiSys> enabledBusiSysList = sysBusiSysRepository.findByFdEnabled(Long.valueOf(CommonConstant.AVAILABLE_FLAG));
		
		for (SysBusiSys sysBusiSys : enabledBusiSysList) {
			SysMenu rootMenu = new SysMenu();
			rootMenu.setFdId(sysBusiSys.getFdId());
			rootMenu.setFdName(sysBusiSys.getFdName());
			rootMenu.setFdIsLeaf(0L);
			list.add(rootMenu);
		}
		
		return list;
	}
	
	/**
	 * 获取子节点
	 */
	@Override
	public List<SysMenu> getSubElements(String parentId) {
		SysBusiSys sysBusiSys = sysBusiSysRepository.findOne(parentId);
		if (null!=sysBusiSys) {
			return sysMenuRepository.findRoot(parentId);
		}else {
			return sysMenuRepository.findByFdParentIdOrderByFdOrder(parentId);
		}
	}
	
	/**
	 * 获取需要授权的子节点
	 */
	@Override
	public List<SysMenu> getNeedAuthSubElements(String parentId){
		SysBusiSys sysBusiSys = sysBusiSysRepository.findOne(parentId);
		if (null!=sysBusiSys) {
			return sysMenuRepository.findRoot(parentId,SysMenu.menuAuhorize);
		}else {
			return sysMenuRepository.findByFdParentIdAndFdAuthorizeTypeOrderByFdOrder(parentId,SysMenu.menuAuhorize);
		}
	}
	
	
	@Override
	public SysMenu findByFdId(String fdId){
		SysMenu sysMenu = sysMenuRepository.findOne(fdId);
		return sysMenu;
	}
	
	@Override
	public Page<SysMenu> findByPage(SysMenu sysMenu,Pageable pageable){
		Page<SysMenu> page = null;
		Specification<SysMenu> spec = this.getWhereClause(sysMenu);
		page = sysMenuRepository.findAll(spec,pageable);
		return page;
	}
	
	/**
	 * 动态生成where语句
	 * @Title: getWhereClause 
	 * @param sysMenu
	 * @return
	 */
	private Specification<SysMenu> getWhereClause(final SysMenu sysMenu) {
		
		final String fdHierarchyId = sysMenu.getFdHierarchyId();
		final String filterName = sysMenu.getFilterName();
		
		Specification<SysMenu> spec = new Specification<SysMenu>() {
			
			@Override
			public Predicate toPredicate(Root<SysMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				//菜单名称/编号
				if (StringUtils.isNotBlank(filterName)) {
					Predicate f1 = cb.like(root.<String>get("fdName"), "%"+filterName+"%");
					Predicate f2 = cb.like(root.<String>get("fdNo"), "%"+filterName+"%");
					Predicate f3 = cb.or(f1, f2);
					expressions.add(f3);
				}
				//层级id
				if (StringUtils.isNotBlank(fdHierarchyId)) {
					Predicate h1 = cb.like(root.<String>get("fdHierarchyId"), fdHierarchyId+"%");
					expressions.add(h1);
				}else {
					Predicate b1 = cb.equal(root.<String>get("busiSysId"), sysMenu.getFdId());
					expressions.add(b1);
				}
				//根据fdOrder排序
				Order order1 = new OrderImpl(root.get("fdHierarchyId"));
				Order order2 = new OrderImpl(root.get("fdOrder"));
				query.orderBy(order1,order2);
				
				return predicate;
			}
		};
		return spec;
	}
	
	/**
	 * 获取formItem
	 * @param sysMenu
	 * @return 
	 */
	@Override
	public SysMenu getFormItem(SysMenu sysMenu){
		
		String menuId = sysMenu.getFdId();
		
		//获取菜单路径
		if (null!=menuId) {
			StringBuffer path = new StringBuffer();
			List<String> parentIds = this.splitStrToList(sysMenu.getFdHierarchyId(), BaseTreeConstant.HIERARCHY_ID_SPLIT);
			List<SysMenu> allParentMenus = sysMenuRepository.findAll(parentIds);
			for (SysMenu sysMenu2 : allParentMenus) {
				path.append(sysMenu2.getFdName());
				path.append("/");
			}
			sysMenu.setPath(path.substring(0, path.length()-1));
		}
		//获取参数配置
		List<SysMenuParam> paramList = null;
		paramList = sysMenuParamRepository.findByFdMenuId(menuId);
		sysMenu.setParamList(paramList);
		
		return sysMenu;
	}
	
	/**
	 * 通过分割符分割字符串并转化为列表
	 * @Title: splitStrToList 
	 * @param str 字符串
	 * @param regex 分隔符
	 * @return
	 */
	private List<String> splitStrToList(String str,String regex){
		String [] strArray = str.split(regex);
		List<String> strList = new ArrayList<String>();
		for (String string : strArray) {
			if (StringUtils.isNotEmpty(string)) {
				strList.add(string);
			}
		}
		return strList;
	}
	
	/**
	 * 获取菜单当前级别最大号
	 * @param parentId
	 * @return
	 * @throws Exception 
	 */
	@Override
	public Long findMaxFdOrderByFdParentId(String parentId) throws Exception{
		Long maxOrder = null;
		try {
			// 获取根节点下的序号最大值
			if (null==parentId) {
				maxOrder = sysMenuRepository.findMaxFdOrderByFdRoot();
				maxOrder = maxOrder==null?SysMenu.increaseNumber:maxOrder;
			}else {
				// 获取父级下节点序号的最大值
				maxOrder = sysMenuRepository.findMaxFdOrderByFdParentId(parentId);
				maxOrder = maxOrder==null?SysMenu.increaseNumber:maxOrder;
			}
		} catch (Exception e) {
			throw new Exception("根据父级id获取子级中序列的最大值发生错误："+e.getMessage());
		}
		
		return maxOrder;
	}
	
	/**
	 * 移动节点
	 * 1.更新移动节点的排序号，父级id和层级id
	 * 2.更新移动节点的所有子节点的层级ID
	 * 3.更新移动节点的父节点
	 * @throws Exception 
	 */
	@Override
	public SysMenu moveNode(SysMenu sysMenu,SysMenu targetNode) throws Exception{
		//移动位置
		String moveType = sysMenu.getMoveType();
		//移动前的父节点id
		String oldParentId = sysMenu.getFdParentId();
		//移动前的层级id
		String oldHierarchyId = sysMenu.getFdHierarchyId();
		
		//1.更新移动节点的排序号，父级id和层级id
		if (SysMenu.inner.equals(moveType)) {
			//移动到目标节点内
			this.moveInTargetNode(sysMenu, targetNode);
		}else {
			//移动到目标节点前/后
			this.moveBehindTargetNode(sysMenu, targetNode);
		}
		
		//2.更新移动节点的所有子节点的层级ID和业务系统ID
		List<SysMenu> subMenuList = sysMenuRepository.findByFdHierarchyIdLike(oldHierarchyId+"%");
		SysMenu parentMenu = new SysMenu();
		if (sysMenu.getFdParentId()!=null) {
			parentMenu = sysMenuRepository.findOne(sysMenu.getFdParentId());
		}
		if (parentMenu.getFdIsAvailable()==Long.valueOf(CommonConstant.NOT_AVAILABLE_FLAG)) {
			sysMenu.setFdIsAvailable(Long.valueOf(CommonConstant.NOT_AVAILABLE_FLAG));
		}
		if (parentMenu.getFdAuthorizeType()==SysMenu.notNeedAuthorize) {
			sysMenu.setFdAuthorizeType(SysMenu.notNeedAuthorize);
		}
		//开始更新所有子级
		for (SysMenu subMenu : subMenuList) {
			subMenu.setBusiSysId(sysMenu.getBusiSysId());
			subMenu.setFdHierarchyId(subMenu.getFdHierarchyId().replaceAll(oldHierarchyId, sysMenu.getFdHierarchyId()));
			if (parentMenu.getFdIsAvailable()==Long.valueOf(CommonConstant.NOT_AVAILABLE_FLAG)) {
				subMenu.setFdIsAvailable(Long.valueOf(CommonConstant.NOT_AVAILABLE_FLAG));
			}
			// 所有下级也设为无需授权
			if (parentMenu.getFdAuthorizeType()==SysMenu.notNeedAuthorize){
				subMenu.setFdAuthorizeType(SysMenu.notNeedAuthorize);
			}
		}
		sysMenuRepository.save(subMenuList);
		
		//3.更新移动节点的父节点
		if (null!=oldParentId) {
			List<SysMenu> menuList = sysMenuRepository.findByFdParentId(oldParentId);
			if (menuList.size()==1&&menuList.get(0).getFdId().equals(sysMenu.getFdId())) {
				sysMenuRepository.updateFdIsLeaf(oldParentId, 1L);
			}
		}
		
		sysMenuRepository.save(sysMenu);
		return sysMenu;
	}
	
	/**
	 * 移动到目标节点内
	 * @param sysMenu
	 * @param targetNode
	 * @throws Exception
	 */
	private void moveInTargetNode(SysMenu sysMenu,SysMenu targetNode) throws Exception {
		//移动后的排序号
		Long newOrder = null;
		
		//1.更新移动节点
		Long maxOrder = this.findMaxFdOrderByFdParentId(targetNode.getFdId());
		newOrder = maxOrder + SysMenu.increaseNumber;
		sysMenu.setFdOrder(newOrder);
		sysMenu.setBusiSysId(targetNode.getBusiSysId());
		sysMenu.setFdParentId(targetNode.getFdId());
		sysMenu.setFdHierarchyId(targetNode.getFdHierarchyId()+ sysMenu.getFdId() + BaseTreeConstant.HIERARCHY_ID_SPLIT);
		
		//2.更新目标节点
		if(new Long(1).compareTo(targetNode.getFdIsLeaf())==0){
			sysMenuRepository.updateFdIsLeaf(targetNode.getFdId(), 0L);
		}
	}
	
	/**
	 * 移动到目标节点前/后
	 * @param sysMenu
	 * @param targetNode
	 * @throws Exception
	 */
	private void moveBehindTargetNode(SysMenu sysMenu,SysMenu targetNode) throws Exception {
		//移动位置
		String moveType = sysMenu.getMoveType();
		//移动后的排序号
		Long newOrder = null;
		
		//1.更新移动节点
		if (SysMenu.next.equals(moveType)) {
			newOrder = this.getNextNewOrder(targetNode.getFdParentId(), targetNode.getFdOrder());
		}else {
			newOrder = this.getPrevNewOrder(targetNode.getFdParentId(), targetNode.getFdOrder());
		}
		sysMenu.setFdOrder(newOrder);
		sysMenu.setBusiSysId(targetNode.getBusiSysId());
		if (null == sysMenuRepository.findOne(targetNode.getFdParentId())) {
			sysMenu.setFdParentId(null);
		}else {
			sysMenu.setFdParentId(targetNode.getFdParentId());
		}
		String newFdHierarchyId = targetNode.getFdHierarchyId().replaceAll(targetNode.getFdId()+BaseTreeConstant.HIERARCHY_ID_SPLIT, "")+sysMenu.getFdId()+BaseTreeConstant.HIERARCHY_ID_SPLIT; 
		sysMenu.setFdHierarchyId(newFdHierarchyId);
	}
	
	/**
	 * 获取添加到上一节点的新序号
	 */
	private Long getPrevNewOrder(String parentId,Long order) throws Exception{
		if (null==order) {
			throw new NullPointerException("序号为空");
		}
		Long newOrder = null;
		Long prevOrder = null;
		
		if (null!=parentId) {
			prevOrder = sysMenuRepository.findPrevOrder(parentId, order);
		}else {
			prevOrder = sysMenuRepository.findPrevOrderByRoot(order);
		}
		prevOrder = prevOrder==null?0:prevOrder;
		
		if (order-prevOrder<=2) {
			newOrder = (order + SysMenu.increaseNumber + prevOrder)/2;
			//更新后述所有节点排序
			this.updateAllNextOrder(parentId, order, true);
		}else {
			newOrder = (order+prevOrder)/2;
		}
		
		return newOrder;
	}
	
	/**
	 * 获取添加到下一节点的新序号
	 */
	private Long getNextNewOrder(String parentId,Long order) {
		if (null==order) {
			throw new NullPointerException("序号为空");
		}
		Long newOrder = null;
		Long nextOrder = null;
		
		if (null!=parentId) {
			nextOrder = sysMenuRepository.findNextOrder(parentId, order);
		}else {
			nextOrder = sysMenuRepository.findNextOrderByRoot(order);
		}
		nextOrder = nextOrder==null?order+SysMenu.increaseNumber*2:nextOrder;
		
		if (nextOrder-order<=2) {
			newOrder = order + SysMenu.increaseNumber/2;
			//更新后述所有节点排序
			this.updateAllNextOrder(parentId, order, false);
		}else {
			newOrder = (order+nextOrder)/2;
		}
		
		return newOrder;
	}
	
	/**
	 * 更新所有后述节点的排序号
	 * @param parentId
	 * @param order
	 * @param isNextOrder
	 */
	private void updateAllNextOrder(String parentId,Long order,boolean isNextOrder) {
		if (!isNextOrder) {
			order = sysMenuRepository.findNextOrder(parentId, order);
		}
		List<SysMenu> allNextMenuList = new ArrayList<SysMenu>();
		if (null!=parentId) {
			allNextMenuList = sysMenuRepository.findAllNextMenu(parentId, order);
		}else {
			allNextMenuList = sysMenuRepository.findAllNextMenuByRoot(order);
		}
		for (SysMenu sysMenu : allNextMenuList) {
			sysMenu.setFdOrder(sysMenu.getFdOrder()+SysMenu.increaseNumber);
		}
		sysMenuRepository.save(allNextMenuList);
	}
	
	/**
	 * 删除菜单
	 * @param fdId
	 * @return
	 * @throws Exception 
	 */
	@Override
	public void deleteByFdId(String fdId) throws Exception{
		
		List<SysMenu> subMenuList = sysMenuRepository.findByFdParentId(fdId);
		
		if (subMenuList.size()>0) {//删除当前菜单及其所有下级
			SysMenu sysMenu = sysMenuRepository.findOne(fdId);
			String hierarchyId = sysMenu.getFdHierarchyId();
			sysMenuRepository.deleteSubs(hierarchyId+"%");
		}else {
			this.updateParentIsleaf(fdId);
			sysMenuRepository.delete(fdId);
		}
		
	}
	
	/**
	 * 更新父节点的isLeaf字段
	 * 若父节点下已没有节点，则设置isLeaf为0
	 * @param fdId
	 */
	private void updateParentIsleaf(String fdId) throws Exception{
		try {
			SysMenu sysMenu = sysMenuRepository.findOne(fdId);
			if (null!=sysMenu.getFdParentId()) {
				List<SysMenu> menuList = sysMenuRepository.findByFdParentId(sysMenu.getFdParentId());
				if (menuList.size()==1) {
					SysMenu parentmMenu = sysMenuRepository.findOne(sysMenu.getFdParentId());
					parentmMenu.setFdIsLeaf(1L);
					sysMenuRepository.save(parentmMenu);
				}
			}
		} catch (Exception e) {
			throw new Exception("更新父节点的isLeaf字段发生错误.");
		}
		
	}

}
