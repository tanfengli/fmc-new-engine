package com.vispractice.fmc.business.services.sys.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entities.setting.repositories.RepeatPostOnRoleconfVRepository;
import com.vispractice.fmc.business.entities.setting.view.RepeatPostOnRoleconfV;
import com.vispractice.fmc.business.entities.sys.repositories.RoleLineMemberVRepository;
import com.vispractice.fmc.business.entities.sys.view.RoleLineMemberV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLine;
import com.vispractice.fmc.business.entity.sys.org.repository.RoleMemberVRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleConfRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleLineRepository;
import com.vispractice.fmc.business.entity.sys.org.view.RoleMemberV;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRoleLineDefaultRoleService;
import com.vispractice.fmc.business.services.sys.IRoleLineMemberVService;

/**
 * 编  号：
 * 名  称：RoleLineMemberVServiceImpl
 * 描  述：RoleLineMemberVServiceImpl.java
 * 完成日期：2017年7月27日 上午11:38:58
 * 编码作者：LaiJiashen
 */

@Service
@Transactional
public class RoleLineMemberVServiceImpl implements IRoleLineMemberVService {

	@Autowired
	private RoleLineMemberVRepository roleLineMemberVRepository;

	@Autowired
	private SysOrgRoleConfRepository sysOrgRoleConfRepository;

	@Autowired
	private SysOrgRoleLineRepository sysOrgRoleLineRepository;
	
	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;
	
	@Autowired
	private RepeatPostOnRoleconfVRepository repeatPostOnRoleconfVRepository;
	
	@Autowired
	private ISysOrgRoleLineDefaultRoleService sysOrgRoleLineDefaultRoleService;
	
	@Autowired
	private RoleMemberVRepository roleMemberVRepository;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public RoleLineMemberV findById(String fdId) {

		RoleLineMemberV newNode = new RoleLineMemberV();
		RoleLineMemberV node = roleLineMemberVRepository.findOne(fdId);

		if (null != node) {
			newNode = (RoleLineMemberV) node.clone();
		}
		if (null != node && null == node.getParentName()) {
			SysOrgRoleConf roleConf = sysOrgRoleConfRepository.findOne(node.getFdRoelLineConfId());
			newNode.setParentName(roleConf.getFdName());
			return newNode;
		}
		return node;
	}

	@Override
	public List<SysOrgRoleLine> addQuickly(RoleMemberV roleMemberParent,List<String> eleIdList) {

		List<SysOrgRoleLine> roleMemberList = new ArrayList<SysOrgRoleLine>();

		for (String elementId : eleIdList) {

			SysOrgRoleLine role = new SysOrgRoleLine();
			role.setFdRoleLineConfId(roleMemberParent.getConfId());
			role.setFdParentId(roleMemberParent.getNewParentId());
			role.setFdMemberId(elementId);
			role.setFdCreateTime(new Timestamp(System.currentTimeMillis()));
			role.setFdIsLeaf(Long.valueOf(CommonConstant.AVAILABLE_FLAG));
			role.setFdIsAvailable(Long.valueOf(CommonConstant.AVAILABLE_FLAG));
			String uuid =  IDGenerator.generateID();
			role.setFdId(uuid);
			if(null!=roleMemberParent.getHierarchyId()){
				role.setFdHierarchyId(roleMemberParent.getHierarchyId()+uuid+"x");
			}else{
				role.setFdHierarchyId("x"+uuid+"x");
			}
			roleMemberList.add(role);
		}
		//更新新父级叶子节点
		sysOrgRoleLineRepository.updateFdIsLeafByFdId(roleMemberParent.getFdId(), new Long(CommonConstant.NOT_AVAILABLE_FLAG));
		sysOrgRoleLineRepository.save(roleMemberList);
		
		return roleMemberList;
	}
	
	
	/**
	 * 重复检查
	 */
	@Override
	public List<RepeatPostOnRoleconfV> checkRepeat(String confId){
		List<RepeatPostOnRoleconfV> repeatPostOnRoleconfVList = repeatPostOnRoleconfVRepository.findByConfId(confId);
		em.clear();
		
		//将岗位id和name转化为岗位列表
		for (RepeatPostOnRoleconfV repeatPostOnRoleconfV : repeatPostOnRoleconfVList) {
			List<Map<String, String>> postList = new ArrayList<Map<String,String>>();
			String[] postIds = repeatPostOnRoleconfV.getPostIds().split(",");
			String[] postNames = repeatPostOnRoleconfV.getPostNames().split(",");
			//避免岗位名称中有","的情况
			if(postNames.length>postIds.length){
				//do something
				List<String> postIdList = new ArrayList<String>();
				for (String string : postIds) {
					postIdList.add(string);
				}
				
				List<SysOrgElement> postElementList = sysOrgElementRepository.findAll(postIdList);
				for (int i = 0; i < postElementList.size(); i++) {
					postNames[i] = postElementList.get(i).getFdName();
				}
			}
			//
			for (int i = 0; i < postIds.length; i++) {
				Map<String, String> postMap = new HashMap<String, String>();
				postMap.put("postId", postIds[i]);
				postMap.put("postName", postNames[i]);
				postList.add(postMap);
			}
			repeatPostOnRoleconfV.setPostList(postList);
		}		
		return repeatPostOnRoleconfVList;
	}
	
	@Override
	public RoleMemberV findByFdId(String fdId){
		RoleMemberV roleMemberV = roleMemberVRepository.findOne(fdId);
		return roleMemberV;
	}
	
	@Override
	public List<RoleMemberV> findByFdId(List<String> idList){
		List<RoleMemberV> roleMemberVList = roleMemberVRepository.findAll(idList);
		return roleMemberVList;
	}
	
}
