package com.vispractice.fmc.business.services.groupMemberTree.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.entities.sys.repositories.PositionManagemetVRepository;
import com.vispractice.fmc.business.entities.sys.view.PositionManagemetV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgGroupCate;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgGroupCateRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgPostPersonRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleConfRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementAllPathVService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.services.groupMemberTree.IGroupMemberTreeService;

@Service
@Transactional
public class GroupMemberTreeServiceImpl implements IGroupMemberTreeService {

	@Autowired
	private SysOrgGroupCateRepository sysOrgGroupCateRepository;

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;

	@Autowired
	private SysOrgRoleConfRepository sysOrgRoleConfRepository;
	
	@Autowired
	private SysOrgRoleRepository sysOrgRoleRepository;
	
	@Autowired
	private SysOrgPostPersonRepository sysOrgPostPersonRepository;
	
	@Autowired
	private PositionManagemetVRepository positionManagemetVRepository;
	
	@Autowired
	private ISysOrgElementAllPathVService sysOrgElementAllPathVService;
	
	@Autowired
	private ISysOrgElementService sysOrgElementService;
	
	@PersistenceContext
	private EntityManager em;
	

	private static String group = "group";
	private static String role = "role";
	private static String post = "post";

	@Override
	public List<SysOrgGroupCate> findRootElements() {

		SysOrgGroupCate cate = new SysOrgGroupCate();
		cate.setFdId("0");
		cate.setFdName("常用地址本");
		cate.setFdIsLeaf(0L);

		List<SysOrgGroupCate> cateList = new ArrayList<SysOrgGroupCate>();
		cateList.add(cate);

		return cateList;
	}


	@Override
	public List<SysOrgGroupCate> findSubElements(String parentId) {

		List<SysOrgGroupCate> cateList = new ArrayList<SysOrgGroupCate>();

		// 根节点
		if (parentId.equals(CommonConstant.NOT_AVAILABLE_FLAG)) {
			SysOrgGroupCate cate1 = new SysOrgGroupCate();
			cate1.setFdId(group);
			cate1.setFdName("常用群组");
			cate1.setFdIsLeaf(0L);
			cateList.add(cate1);

			SysOrgGroupCate cate2 = new SysOrgGroupCate();
			cate2.setFdId(role);
			cate2.setFdName("角色线");
			cate2.setFdIsLeaf(0L);
			cateList.add(cate2);

			SysOrgGroupCate cate3 = new SysOrgGroupCate();
			cate3.setFdId(post);
			cate3.setFdName("通用岗位");
			cate3.setFdIsLeaf(1L);
			cate3.setNodeType(post);
			cateList.add(cate3);

		} else {

			switch (parentId) {
			// 查找群组根节点
			case "group":
				cateList = sysOrgGroupCateRepository.findRootElements();
				em.clear();
				for (SysOrgGroupCate sysOrgGroupCate : cateList) {
					sysOrgGroupCate.setFdIsLeaf(0L);
				}
				break;
			case "role":
				List<SysOrgRoleConf> roleList = sysOrgRoleConfRepository.findIsAvailable();

				for (SysOrgRoleConf roleConf : roleList) {
					SysOrgGroupCate newCate = new SysOrgGroupCate();
					newCate.setFdId(roleConf.getFdId());
					newCate.setFdName(roleConf.getFdName());
					newCate.setFdIsLeaf(1L);
					newCate.setNodeType(role);

					cateList.add(newCate);
				}
				break;

			// 查找下级群组
			default:
				cateList = sysOrgGroupCateRepository.findByFdParentId(parentId);
				em.clear();
				for (SysOrgGroupCate cate : cateList) {
					cate.setFdIsLeaf(0L);
				}
				// 获取群组成员
				List<SysOrgElement> groupMembers = sysOrgElementRepository.getGroupByCateId(parentId);
				for (SysOrgElement groupMember : groupMembers) {
					SysOrgGroupCate cate = new SysOrgGroupCate();
					cate.setFdId(groupMember.getFdId());
					cate.setFdName(groupMember.getFdName());
					cate.setFdIsLeaf(1L);
					cate.setNodeType(group);

					cateList.add(cate);
				}
				break;
			}

		}

		return cateList;
	}
	
	@Override
	public List<SysOrgElement> findRootOrgElements(){
		SysOrgElement element = new SysOrgElement();
		element.setFdId("0");
		element.setFdName("组织架构与账号管理");
		element.setFdIsLeaf(0L);
		
		List<SysOrgElement> elementList = new ArrayList<SysOrgElement>();
		elementList.add(element);
		return elementList;
	}
	
	@Override
	public List<SysOrgElement> findSubOrgElements(String parentId){
		List<SysOrgElement> elementList = new ArrayList<SysOrgElement>();
		
		if("0".equals(parentId)){//获取机构
			elementList = sysOrgElementService.findRootElements();
		}else{//只获取机构，部门
			List<Long> fdOrgTypeList = new ArrayList<Long>();
			fdOrgTypeList.add(new Long(SysOrgConstant.ORG_TYPE_ORG));
			fdOrgTypeList.add(new Long(SysOrgConstant.ORG_TYPE_DEPT));
			elementList = sysOrgElementService.findSubElements(parentId, fdOrgTypeList);
		}
		
		return elementList;
	}

	@Override
	public List<SysOrgGroupCate> findSubElementsOnlyGroup(String parentId) {

		List<SysOrgGroupCate> cateList = new ArrayList<SysOrgGroupCate>();

		// 根节点
		if (parentId.equals(CommonConstant.NOT_AVAILABLE_FLAG)) {
			SysOrgGroupCate cate1 = new SysOrgGroupCate();
			cate1.setFdId(group);
			cate1.setFdName("常用群组");
			cate1.setFdIsLeaf(0L);
			cateList.add(cate1);
			

		} else {

			switch (parentId) {
			// 查找群组根节点
			case "group":
				cateList = sysOrgGroupCateRepository.findRootElements();
				em.clear();
				for (SysOrgGroupCate cate : cateList) {
					cate.setFdIsLeaf(0L);
				}
				break;

			// 查找下级群组
			default:
				cateList = sysOrgGroupCateRepository.findByFdParentId(parentId);
				em.clear();
				for (SysOrgGroupCate cate : cateList) {
					cate.setFdIsLeaf(0L);
				}
				// 获取群组
				List<SysOrgElement> groupMembers = sysOrgElementRepository.getGroupByCateId(parentId);
				for (SysOrgElement groupMember : groupMembers) {
					SysOrgGroupCate cate = new SysOrgGroupCate();
					cate.setFdId(groupMember.getFdId());
					cate.setFdName(groupMember.getFdName());
					cate.setFdIsLeaf(1L);
					cate.setNodeType(group);

					cateList.add(cate);
				}
				break;
			}

		}

		return cateList;
	}

	@Override
	public List<SysOrgElement> getGroupMember(String groupId,List<Long> fdOrgType) {

		return sysOrgElementRepository.getGroupMemberByGroupId(groupId,fdOrgType);
	}

	@Override
	public List<SysOrgElement> getGroup(String cateId) {

		return sysOrgElementRepository.getGroupByCateId(cateId);
	}
	
	/**
	 * 
	 * 获取详情
	 * 1.通用岗位
	 * 2.角色线
	 * 3.组织架构
	 * @param id
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String getDescription(String id,String name) throws Exception{
		
		SysOrgElement element = null;
		SysOrgRole role = null;
		PositionManagemetV generalPostV = null;
		StringBuffer description = new StringBuffer();
		
		//通用岗位
		generalPostV = positionManagemetVRepository.findByFdIdAndFdName(id, name);
		if(null!=generalPostV){
			description.append(generalPostV.getFdName()).append("(通用岗位) - ").append(generalPostV.getFdMemo());
			return description.toString();
		}
		
		//角色线
		role = sysOrgRoleRepository.findOne(id);
		if (null != role) {
			String roleLineName = sysOrgRoleConfRepository.getOne(role.getFdRoleConfId()).getFdName();
			description.append(name).append("(角色线: ").append(roleLineName).append(")");
			return description.toString();
		}
		
		//组织架构
		element = sysOrgElementRepository.findOne(id);
		if(null!=element){
			description.append(sysOrgElementAllPathVService.getDescription(id));
			return description.toString();
		}
		
		return description.toString();
	}


	@Override
	public List<SysOrgElement> getOrgElements(String fdId,List<Long> fdOrgTypes) {
		
		List<SysOrgElement> elementList = new ArrayList<SysOrgElement>();
		if("0".equals(fdId)){
			fdId = null;
			fdOrgTypes.clear();
			fdOrgTypes.add(new Long(SysOrgConstant.ORG_TYPE_ORG));
		}
		if (fdOrgTypes.size()==0) {
			fdOrgTypes = null;
		}
		elementList = sysOrgElementRepository.findByFdParentidAndFdOrgTypeInAndFdIsAvailable(
					"0".equals(fdId)?null:fdId,fdOrgTypes,Long.valueOf(CommonConstant.AVAILABLE_FLAG));
		
		return elementList;
	}
	
	
	

}
