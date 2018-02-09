package com.vispractice.fmc.business.services.setting.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entities.setting.repositories.RoleAssignVRepository;
import com.vispractice.fmc.business.entities.setting.view.RoleAssignV;
import com.vispractice.fmc.business.entities.sys.SysAuthRole;
import com.vispractice.fmc.business.entities.sys.repositories.SysAuthEdtRepository;
import com.vispractice.fmc.business.entities.sys.repositories.SysAuthMraRepository;
import com.vispractice.fmc.business.entities.sys.repositories.SysAuthRoleRepository;
import com.vispractice.fmc.business.entities.sys.repositories.SysAuthRraRepository;
import com.vispractice.fmc.business.entities.sys.repositories.SysAuthUraRepository;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.services.setting.IRoleAssignVService;
import com.vispractice.fmc.web.domain.setting.SysRoleAssignForm;

@Service
@Transactional
public class RoleAssignVServiceImpl  implements IRoleAssignVService {

	@Autowired
	private RoleAssignVRepository roleAssignVRepository;

	@Autowired
	private SysAuthRoleRepository sysAuthRoleRepository;

	@Autowired
	private SysAuthRraRepository sysAuthRraRepository;
	
	@Autowired
	private SysAuthUraRepository sysAuthUraRepository;
	
	@Autowired
	private SysAuthMraRepository sysAuthMraRepository;
	
	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;
	
	@Autowired
	private SysAuthEdtRepository sysAuthEdtRepository;
	
	/**
	 * where条件拼接
	 */
	@Override
	public Page<RoleAssignV> findBySearch(final RoleAssignV RoleAssignV, Pageable pageable) {
		
		Specification<RoleAssignV> spec = new Specification<RoleAssignV>() {
			@Override
			public Predicate toPredicate(Root<RoleAssignV> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();
				if(null != RoleAssignV){
					if (StringUtils.isNotBlank(RoleAssignV.getFdId()))
						list.add(cb.equal(root.get("fdId"), RoleAssignV.getFdId()));
					if(StringUtils.isNotBlank(RoleAssignV.getFdName())){
						list.add(cb.like(root.<String>get("fdName"),"%"+RoleAssignV.getFdName()+"%"));
					}
					if(null != RoleAssignV.getCategoryId()&&RoleAssignV.getCategoryId().equals("未分类")){
						list.add(cb.isNull(root.get("categoryId")));
					}
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		return roleAssignVRepository.findAll(spec, pageable);

	}
	
	/**
	 * 新增/编辑
	 */
	@Override
	public void save(SysRoleAssignForm sysRoleAssignForm,String currentUserId) {

		SysAuthRole sysAuthRole = new SysAuthRole();

		if (sysRoleAssignForm.getFdId() != null) {
			sysAuthRole.setFdId(sysRoleAssignForm.getFdId());
		}
		/*********** 保存到SysAuthRole表 ****************/
		sysAuthRole.setFdName(sysRoleAssignForm.getFdName());
		sysAuthRole.setFdType("0");
		sysAuthRole.setFdCreatorid(currentUserId);
		sysAuthRole.setFdCategoryId(sysRoleAssignForm.getCategoryId());
		sysAuthRole.setFdDescription(sysRoleAssignForm.getFdDescription());

		sysAuthRoleRepository.save(sysAuthRole);

		/*********** 保存到角色继承关系到SysRoleRra表 ****************/
		List<SysAuthRole> inhRoleList = sysRoleAssignForm.getInhRoleList();
		sysAuthRraRepository.delByFdRoleid(sysAuthRole.getFdId());
		for (SysAuthRole inhRole : inhRoleList) {
			sysAuthRraRepository.addOne(sysAuthRole.getFdId(), inhRole.getFdId());
		}
		
		/*******************保存可维护者到SysAuthEdt*************************/
		List<SysOrgElement> edtList = sysRoleAssignForm.getEdtList();
		sysAuthRole.setEditorList(edtList);
		
		
		sysAuthRoleRepository.save(sysAuthRole);
	}

	/**
	 * 删除
	 */
	@Override
	public void deleteById(String fdId) {
		//删除分配用户
		sysAuthUraRepository.delByFdRoleid(fdId);
		//删除已授权菜单
		sysAuthMraRepository.delByFdRoleid(fdId);
		sysAuthRoleRepository.delete(fdId);
	}

	/**
	 * 获取编辑form
	 */
	@Override
	public SysRoleAssignForm findForm(RoleAssignV roleAssignV) {
		
		SysRoleAssignForm form = new SysRoleAssignForm();
		SysAuthRole sysAuthRole = sysAuthRoleRepository.findOne(roleAssignV.getFdId());

		form.setFdId(roleAssignV.getFdId());
		form.setFdName(roleAssignV.getFdName());
		form.setCategoryId(roleAssignV.getCategoryId());
		form.setCategoryName(roleAssignV.getCategoryName());
		form.setCreatorName(roleAssignV.getCreatorName());
		form.setFdDescription(roleAssignV.getFdDescription());

		//获取继承角色
		List<SysAuthRole> inhRoleList = sysAuthRraRepository.findInheritRoleByRoleId(roleAssignV.getFdId());
		if (null!=inhRoleList&&inhRoleList.size()>0) {
			form.setInhRoleList(inhRoleList);
			StringBuffer inhRoleName = new StringBuffer();
			for(SysAuthRole inhRole : inhRoleList){
				inhRoleName.append(inhRole.getFdName());
				inhRoleName.append(";");
			}
			form.setInhRoleName(inhRoleName.substring(0, inhRoleName.length()-1));
		}
		
		//获取可维护者
		List<SysOrgElement> edtElementList = sysAuthRole.getEditorList();
		StringBuffer edtName = new StringBuffer();
		for(SysOrgElement edt : edtElementList){
			edtName.append(edt.getFdName()+";");
		}
		form.setEdtList(edtElementList);
		if(edtName.length()>0){
			form.setEdtName(edtName.substring(0, edtName.length()-1));
		}
		
		return form;
	}
	
	@Override
	public List<RoleAssignV> findAll(){
		List<RoleAssignV> list = roleAssignVRepository.findAll();
		return list;
	}
	
	
}
