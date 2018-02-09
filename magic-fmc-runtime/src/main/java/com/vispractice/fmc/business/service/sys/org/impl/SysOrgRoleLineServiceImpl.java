package com.vispractice.fmc.business.service.sys.org.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.base.constrant.BaseTreeConstant;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLine;
import com.vispractice.fmc.business.entity.sys.org.beans.context.SysOrgRolePluginContext;
import com.vispractice.fmc.business.entity.sys.org.repository.RoleMemberVRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleConfRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleLineRepository;
import com.vispractice.fmc.business.entity.sys.org.view.RoleMemberV;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRoleLineService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class SysOrgRoleLineServiceImpl implements ISysOrgRoleLineService {

	@Autowired
	private SysOrgRoleLineRepository sysOrgRoleLineRepository;

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;

	@Autowired
	private RoleMemberVRepository roleMemberVRepository;
	
	@Autowired
	private SysOrgRoleConfRepository sysOrgRoleConfRepository;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<RoleMemberV> findRootElements(String fdRoleLineConfId) {

		List<RoleMemberV> roleList = new ArrayList<RoleMemberV>();

		if (roleList.isEmpty()) {
			RoleMemberV member = new RoleMemberV();
			member.setFdId("0");
			member.setFdName("角色线成员");
			member.setIsRoleMember(CommonConstant.NOT_AVAILABLE_FLAG);
			member.setFdIsLeaf(0L);
			roleList.add(member);
		}

		return roleList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findSubElements(String parentId) {
		
		List<SysOrgRoleConf> roleConfList = new ArrayList<SysOrgRoleConf>();
		List<RoleMemberV> roleList = new ArrayList<RoleMemberV>();
		
		//第二级为角色线
		if("0".equals(parentId)){
			roleConfList = sysOrgRoleConfRepository.findIsAvailable();
			for(SysOrgRoleConf conf : roleConfList){
				RoleMemberV roleMember = new RoleMemberV();
				roleMember.setConfId(conf.getFdId());
				roleMember.setFdId(conf.getFdId());
				roleMember.setFdName(conf.getFdName());
				roleMember.setIsRoleMember(CommonConstant.NOT_AVAILABLE_FLAG);
				roleMember.setFdIsLeaf(0L);
				roleList.add(roleMember);
			}
		}else {//第三级及以下为角色线成员
			roleList = roleMemberVRepository.findByParentId(parentId);
			if(roleList.size()==0){//获取角色线成员的根
				roleList = roleMemberVRepository.findRoleMemberRoot(parentId);
			}
		}
		return roleList;
	}
	
	@Override
	public Page<RoleMemberV> findAllSubMembers(RoleMemberV roleMemberV,Pageable pageable) throws Exception{
		
		if(!"0".equals(roleMemberV.getIsRoleMember())&&null!=roleMemberV.getFdId()){
			roleMemberV = roleMemberVRepository.findOne(roleMemberV.getFdId());
			if(null==roleMemberV){
				throw new Exception("找不到当前选中节点，请确保当前角色线成员数据是否有误。");
			}
		}
		
		String hierarchyId = roleMemberV.getHierarchyId();
		String confId = roleMemberV.getConfId();
		String memberName = roleMemberV.getSearchMemberName();
		
		Page<RoleMemberV> roleMemberList = null;
		try {
			if(null==hierarchyId){
				if(StringUtils.isEmpty(memberName))
					roleMemberList = roleMemberVRepository.findByConfId(confId,pageable);
				else{
					memberName="%"+memberName+"%";
					roleMemberList = roleMemberVRepository.findByConfIdAndFdNameLike(confId,memberName,pageable);
					}
			}else{
				hierarchyId = hierarchyId+"%";
				if(StringUtils.isEmpty(memberName)){
					roleMemberList = roleMemberVRepository.findByHierarchyIdLike(hierarchyId,pageable);}
				else{
					memberName="%"+memberName+"%";
					roleMemberList = roleMemberVRepository.findByFdNameLikeAndHierarchyIdLike(memberName,hierarchyId,pageable);
				}
			}
		} catch (Exception e) {
			throw new Exception("查询列表时出错，请检查数据库链接。");
		}
		
		
		
		return roleMemberList;
	}

	@Override
	public boolean deleteByFdId(String fdId) throws RuntimeException{
		
		SysOrgRoleLine roleMember = sysOrgRoleLineRepository.findOne(fdId);
		if(null==roleMember){
			throw new RuntimeException("角色线成员不存在，id="+fdId);
		}
		
		//删除角色
		List<SysOrgRoleLine> subList =  sysOrgRoleLineRepository.findByFdParentIdAndFdIsAvailable(roleMember.getFdId(),Long.valueOf(CommonConstant.AVAILABLE_FLAG));
		if (subList.size()>0) {
			return false;
		}
		String parentId = roleMember.getFdParentId();
		//更新父级叶子节点
		if (null!=parentId) {
			List<SysOrgRoleLine> roleList = sysOrgRoleLineRepository.findByFdParentId(parentId);
			if (null!=roleList&&roleList.size()==1&&roleList.get(0).getFdId().equals(fdId)) {
				sysOrgRoleLineRepository.updateFdIsLeafByFdId(parentId, new Long(CommonConstant.AVAILABLE_FLAG));
			}
		}
		sysOrgRoleLineRepository.deleteByFdId(fdId);
		return true;
	}

	@Override
	public SysOrgRoleLine save(SysOrgRoleLine sysOrgRoleLine) {

		String hierarchyId = "x";

		if (null == sysOrgRoleLine.getFdId()) {
			String id = IDGenerator.generateID();
			sysOrgRoleLine.setFdId(id);
			if (sysOrgRoleLine.getFdParentId() != null) {
				hierarchyId = sysOrgRoleLineRepository.findOne(sysOrgRoleLine.getFdParentId()).getFdHierarchyId();
				this.updateParentIsLeaf(sysOrgRoleLine.getFdParentId());
			}
			sysOrgRoleLine.setFdHierarchyId(hierarchyId + sysOrgRoleLine.getFdId() + "x");// 设置层次Id
			sysOrgRoleLine.setFdCreateTime(new Date());
			sysOrgRoleLine.setFdIsLeaf(Long.valueOf(CommonConstant.AVAILABLE_FLAG));
			sysOrgRoleLine.setFdIsAvailable(Long.valueOf(CommonConstant.AVAILABLE_FLAG));
		}
		this.beforeSave(sysOrgRoleLine);
		sysOrgRoleLineRepository.save(sysOrgRoleLine);
		return sysOrgRoleLine;

	}
	
	//保存前的检测
	private void beforeSave(SysOrgRoleLine sysOrgRoleLine) throws RuntimeException{
		//1.成员组织架构类型检测
		if (null!=sysOrgRoleLine.getFdMemberId()) {
			SysOrgElement member = sysOrgElementRepository.findOne(sysOrgRoleLine.getFdMemberId());
			if (null!=member&&member.getFdOrgType()==Long.valueOf(SysOrgConstant.ORG_TYPE_DEPT)||member.getFdOrgType()==Long.valueOf(SysOrgConstant.ORG_TYPE_ORG)) {
				List<SysOrgRoleLine> subList = sysOrgRoleLineRepository.findByFdParentId(sysOrgRoleLine.getFdId());
				if (subList.size()>0) {
					throw new RuntimeException("机构部门下不能创建角色，请重新选择成员");
				}
			}
		}
		
	}
	
	private void updateParentIsLeaf(String parentId) {
		SysOrgRoleLine parent = sysOrgRoleLineRepository.findOne(parentId);
		if (parent.getFdIsLeaf()!=Long.valueOf(CommonConstant.NOT_AVAILABLE_FLAG)) {
			parent.setFdIsLeaf(Long.valueOf(CommonConstant.NOT_AVAILABLE_FLAG));
		}
		sysOrgRoleLineRepository.save(parent);
	}
	
	@Override
	public SysOrgRoleLine getOne(String fdId){
		try {
			SysOrgRoleLine roleMember = sysOrgRoleLineRepository.findOne(fdId);
			return roleMember;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public RoleMemberV getOneMember(String fdId){
		try {
			RoleMemberV roleMember = roleMemberVRepository.findOne(fdId);
			return roleMember;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 转化
	 */
	public List<SysOrgRoleLine> tran(List<SysOrgRoleLine> roleList) {

		List<SysOrgRoleLine> newList = new ArrayList<SysOrgRoleLine>();

		for (SysOrgRoleLine role : roleList) {
			String memberName = sysOrgElementRepository.findOne(role.getFdMemberId()).getFdName();
			SysOrgRoleLine newRole = new SysOrgRoleLine();
			newRole = (SysOrgRoleLine) role.clone();
			if (null == role.getFdName()) {
				newRole.setFdName(memberName);

			} else {
				newRole.setFdName(role.getFdName() + "(" + memberName + ")");
			}

			newList.add(newRole);
		}

		return newList;
	}

	/**
	 * 
	 */
	@Override
	public RoleMemberV getParentNode(RoleMemberV roleMemberV) {
		
		if("0".equals(roleMemberV.getIsRoleMember())){
			return null;
		}
		if (null!=roleMemberV.getFdId()) {
			roleMemberV = roleMemberVRepository.findOne(roleMemberV.getFdId());
		}
		
		String parentId = roleMemberV.getParentId();
		RoleMemberV roleMemberV2 = null;
		
		//当前为第一级时找出其角色线
		if(StringUtils.isBlank(parentId)){
			SysOrgRoleConf lineConf =  sysOrgRoleConfRepository.findOne(roleMemberV.getConfId());
			if(null==lineConf){
				return null;
			}else{
				roleMemberV2 = new RoleMemberV();
				roleMemberV2.setConfId(lineConf.getFdId());
				roleMemberV2.setFdId(lineConf.getFdId());
				roleMemberV2.setFdName(lineConf.getFdName());
				roleMemberV2.setIsRoleMember(CommonConstant.NOT_AVAILABLE_FLAG);
			}
		}else {
			roleMemberV2 = roleMemberVRepository.findOne(parentId);
		}
		
		return roleMemberV2;
	}

	/**
	 * 
	 * 更新 角色线成员的 角色线分类id，上一级id，层级id
	 * @param roleMemberV
	 * @throws Exception 
	 */
	@Override
	public void moveNode(RoleMemberV roleMemberV) throws Exception{
		
		String fdId = roleMemberV.getFdId(); 
		
		SysOrgRoleLine roleMember = sysOrgRoleLineRepository.findOne(roleMemberV.getFdId());
		if (null==roleMember) {
			throw new Exception("找不到当前角色线成员 .");
		}
		List<SysOrgRoleLine> subOrgRoleMemberLines = sysOrgRoleLineRepository.findByFdHierarchyIdLikeExcludeSelf(roleMember.getFdHierarchyId());
		
		//更新原父级的叶子节点
		if (null!=roleMember.getFdParentId()) {
			String oldParentId = roleMember.getFdParentId();
			List<SysOrgRoleLine> parentSubsList = sysOrgRoleLineRepository.findByFdParentId(oldParentId);
			if (null!=parentSubsList&&parentSubsList.size()==1&&parentSubsList.get(0).getFdId().equals(fdId)) {
				sysOrgRoleLineRepository.updateFdIsLeafByFdId(oldParentId, new Long(CommonConstant.AVAILABLE_FLAG));
			}
		}
		
		//移动到角色线下
		if ("0".equals(roleMemberV.getIsParentRoleMember())) {
			
			roleMember.setFdRoleLineConfId(roleMemberV.getNewConfId());
			roleMember.setFdParentId(null);
			String newHierechyId = "x"+roleMember.getFdId()+"x";
			roleMember.setFdHierarchyId(newHierechyId);
			
			for (SysOrgRoleLine sysOrgRoleLine : subOrgRoleMemberLines) {
				sysOrgRoleLine.setFdRoleLineConfId(roleMemberV.getNewConfId());
				
				String hierechyId = new String();
				String newSubHierechyId = new String();
				hierechyId = sysOrgRoleLine.getFdHierarchyId();
				newSubHierechyId = hierechyId.substring(hierechyId.indexOf(newHierechyId));
				sysOrgRoleLine.setFdHierarchyId(newSubHierechyId);
			}
		}else{
			//移动到某一个角色线成员下
			roleMember.setFdRoleLineConfId(roleMemberV.getNewConfId());
			roleMember.setFdParentId(roleMemberV.getNewParentId());
			
			//更新新父级叶子节点
			sysOrgRoleLineRepository.updateFdIsLeafByFdId(roleMemberV.getNewParentId(), new Long(CommonConstant.NOT_AVAILABLE_FLAG));
			
			RoleMemberV newParentRoleMember = roleMemberVRepository.findOne(roleMemberV.getNewParentId());
			if(null==newParentRoleMember){
				throw new Exception("发生错误，找不到当前角色成员的上一级.");
			}
			String oldHierechyId = roleMember.getFdHierarchyId();
			String newHierechyId =  newParentRoleMember.getHierarchyId()+roleMember.getFdId()+'x';
			
			for (SysOrgRoleLine sysOrgRoleLine : subOrgRoleMemberLines) {
				
				sysOrgRoleLine.setFdRoleLineConfId(roleMemberV.getNewConfId());
				String newSubHierechyId = "";
				newSubHierechyId = sysOrgRoleLine.getFdHierarchyId().replaceAll(oldHierechyId, newHierechyId);
				sysOrgRoleLine.setFdHierarchyId(newSubHierechyId);
			}
			roleMember.setFdHierarchyId(newHierechyId);
		}
		
		sysOrgRoleLineRepository.save(subOrgRoleMemberLines);
		sysOrgRoleLineRepository.save(roleMember);
		
	}
	
}
