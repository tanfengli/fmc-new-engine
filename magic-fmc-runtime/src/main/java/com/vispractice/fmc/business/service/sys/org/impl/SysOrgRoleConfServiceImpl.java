package com.vispractice.fmc.business.service.sys.org.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleLine;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleConfRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleLineRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgRoleConfService;

@Service
@Transactional
public class SysOrgRoleConfServiceImpl implements ISysOrgRoleConfService {
	@Autowired
	private SysOrgRoleConfRepository sysOrgRoleConfRepository;

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;

	@Autowired
	private SysOrgRoleRepository sysOrgRoleRepository;
	
	@Autowired
	private SysOrgRoleLineRepository sysOrgRoleLineRepository;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<SysOrgRoleConf> findAll(SysOrgRoleConf sysOrgRoleConf,Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysOrgRoleConf> example = Example.of(sysOrgRoleConf, matcher);
		
		return sysOrgRoleConfRepository.findAll(example, pageable);
	}

	@Override
	public Page<SysOrgRoleConf> findBySearch(final SysOrgRoleConf sysOrgRoleConf,Pageable pageable) {
		Specification<SysOrgRoleConf> spec = new Specification<SysOrgRoleConf>() {

			@Override
			public Predicate toPredicate(Root<SysOrgRoleConf> root,CriteriaQuery<?> query,CriteriaBuilder cb) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (null != sysOrgRoleConf) {
					if (StringUtils.isNotBlank(sysOrgRoleConf.getFdName()))
						list.add(cb.like(root.<String>get("fdName"),
								"%"+sysOrgRoleConf.getFdName()+"%"));
					if (null!=(sysOrgRoleConf.getFdIsAvailable()))
						list.add(cb.equal(root.get("fdIsAvailable"),
								sysOrgRoleConf.getFdIsAvailable()));
				}
				Predicate[] p = new Predicate[list.size()];

				return cb.and(list.toArray(p));
			}

		};
		
		return sysOrgRoleConfRepository.findAll(spec, pageable);
	}

	/**
	 * 角色线保存
	 * @param sysOrgRoleConf
	 * @return 
	 */
	@Override
	public Boolean save(SysOrgRoleConf sysOrgRoleConf) {
		if (null != sysOrgRoleConf.getFdName()) {
			List<SysOrgElement> editorList = sysOrgRoleConf.getEditorArray();

			sysOrgRoleConf.setSysRoleLineEditors(editorList);

			sysOrgRoleConfRepository.save(sysOrgRoleConf);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<SysOrgRoleConf> findIsAvailable() {

		return sysOrgRoleConfRepository.findIsAvailable();
	}

	@Override
	public SysOrgRoleConf findForm(String fdId) {

		SysOrgRoleConf sysOrgRoleConf = sysOrgRoleConfRepository.findOne(fdId);
		List<SysOrgElement> editorList = sysOrgRoleConf.getSysRoleLineEditors();
		StringBuffer sb = new StringBuffer();
		//获取可维护者名称
		for (SysOrgElement editor : editorList) {
			sb.append(editor.getFdName());
			sb.append(";");
		}
		String editorNames = sb.toString();
		sysOrgRoleConf.setEditorName(editorNames.length()>1?editorNames.substring(0, editorNames.length()-1):"");
		sysOrgRoleConf.setEditorArray(editorList);

		return sysOrgRoleConf;
	}

	/**
	 * 角色线复制
	 */
	@Override
	public void updateCopy (String fdId) throws Exception{
		try {
			// 复制角色线配置基本信息
			SysOrgRoleConf sysOrgRoleConf = sysOrgRoleConfRepository.findOne(fdId);
			SysOrgRoleConf sysOrgRoleConfCopy = new SysOrgRoleConf();
			sysOrgRoleConfCopy.setFdName("copy of " + sysOrgRoleConf.getFdName());
			// 默认为有效
			sysOrgRoleConfCopy.setFdIsAvailable(new Long(1));
			sysOrgRoleConfCopy.setFdOrder(sysOrgRoleConf.getFdOrder());
			//保存可维护者
			List<SysOrgElement> editors = new ArrayList<SysOrgElement>();
			if (sysOrgRoleConf.getSysRoleLineEditors() != null) {
				editors.addAll(sysOrgRoleConf.getSysRoleLineEditors());
				sysOrgRoleConfCopy.setSysRoleLineEditors(editors);
			}
			sysOrgRoleConfRepository.save(sysOrgRoleConfCopy);

			// 找出角色关系，并复制
			List<SysOrgRole> sysOrgRoleList =  sysOrgRoleRepository.findByFdRoleConfId(fdId);
			List<SysOrgRole> sysOrgRoleListCopy = new ArrayList<SysOrgRole>();
			List<SysOrgElement> elementListCopy = new ArrayList<SysOrgElement>();
			
			for (int i = 0; i < sysOrgRoleList.size(); i++) {
				SysOrgRole sysOrgRole = (SysOrgRole) sysOrgRoleList.get(i);
				//角色关系
				SysOrgRole sysOrgRoleCopy = new SysOrgRole();
				sysOrgRoleCopy = (SysOrgRole) sysOrgRole.clone();
				String id = IDGenerator.generateID();
				sysOrgRoleCopy.setFdId(id);
				sysOrgRoleCopy.setFdRoleConfId(sysOrgRoleConfCopy.getFdId());
				sysOrgRoleListCopy.add(sysOrgRoleCopy);
				
				//角色关系相关联的element表
				SysOrgElement element = sysOrgElementRepository.findOne(sysOrgRole.getFdId());
				if(null==element){
					throw new Exception("当前角色线的下角色关系数据有误！");
				}
				SysOrgElement elementCopy = new SysOrgElement();
				elementCopy = (SysOrgElement) element.clone();
				elementCopy.setFdId(id);
				elementCopy.setFdHierarchyId("x"+id+"x");
				elementCopy.setFdCreateTime(new Date(System.currentTimeMillis()));
				elementCopy.setFdAlterTime(null);
				elementListCopy.add(elementCopy);
			}
			sysOrgRoleRepository.save(sysOrgRoleListCopy);
			sysOrgElementRepository.save(elementListCopy);
			
			
			// 找出角色线成员，并复制
			// 按照层级ID进行排序
			List<SysOrgRoleLine> sysOrgRoleLineList = sysOrgRoleLineRepository.findByFdRoleLineConfIdOrderByFdHierarchyId(sysOrgRoleConf.getFdId());
			// 原ID与新对象的对应表，用于复制后更新关系
			Map<String, SysOrgRoleLine> sysOrgRoleLineCopyMap = new HashMap<String, SysOrgRoleLine>();
			List<SysOrgRoleLine> sysOrgRoleLineCopyList = new ArrayList<SysOrgRoleLine>();
			
			// 复制对象
			for (int i = 0; i < sysOrgRoleLineList.size(); i++) {
				SysOrgRoleLine sysOrgRoleLine = sysOrgRoleLineList.get(i);
				SysOrgRoleLine sysOrgRoleLineCopy = new SysOrgRoleLine();
				String id = IDGenerator.generateID();
				sysOrgRoleLineCopy.setFdId(id);
				sysOrgRoleLineCopy.setFdRoleLineConfId(sysOrgRoleConfCopy.getFdId());
				sysOrgRoleLineCopy.setFdName(sysOrgRoleLine.getFdName());
				sysOrgRoleLineCopy.setFdOrder(sysOrgRoleLine.getFdOrder());
				sysOrgRoleLineCopy.setFdMemberId(sysOrgRoleLine.getFdMemberId());
				sysOrgRoleLineCopy.setFdCreateTime(new Date());
				sysOrgRoleLineCopy.setFdParentId(sysOrgRoleLine.getFdParentId());
				sysOrgRoleLineCopy.setFdIsLeaf(sysOrgRoleLine.getFdIsLeaf());
				sysOrgRoleLineCopy.setFdIsAvailable(sysOrgRoleLine.getFdIsAvailable());
				sysOrgRoleLineCopy.setHbmParent(sysOrgRoleLine.getHbmParent());
				sysOrgRoleLineCopyMap.put(sysOrgRoleLine.getFdId(),sysOrgRoleLineCopy);
				sysOrgRoleLineCopyList.add(sysOrgRoleLineCopy);
			}
			// 更新关系
			for (int i = 0; i < sysOrgRoleLineCopyList.size(); i++) {
				SysOrgRoleLine sysOrgRoleLineCopy = sysOrgRoleLineCopyList.get(i);
				if (sysOrgRoleLineCopy.getHbmParent() != null) {
					SysOrgRoleLine parent = sysOrgRoleLineCopyMap.get(sysOrgRoleLineCopy.getFdParentId());
					sysOrgRoleLineCopy.setFdHierarchyId(parent.getFdHierarchyId()+sysOrgRoleLineCopy.getFdId()+"x");
					sysOrgRoleLineCopy.setFdParentId(parent.getFdId());
					sysOrgRoleLineCopy.setHbmParent(parent);
				}else{
					sysOrgRoleLineCopy.setFdHierarchyId("x"+sysOrgRoleLineCopy.getFdId()+"x");
				}
				
				//即时保存到数据库
				em.persist(sysOrgRoleLineCopy);
				em.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		
	}
	
	@Override
	public List<SysOrgRoleConf> findRootElements(){
		SysOrgRoleConf cate = new SysOrgRoleConf();
		cate.setFdId("0");
		cate.setFdName("角色线");
		
		List<SysOrgRoleConf> cateList = new ArrayList<SysOrgRoleConf>();
		cateList.add(cate);
		
		return cateList;
		
	}
	
	@Override
	public List<SysOrgRoleConf> findSubElements(String parentId) {
		List<SysOrgRoleConf> cateList = new ArrayList<SysOrgRoleConf>();

		if (parentId.equals(CommonConstant.NOT_AVAILABLE_FLAG)) {
			cateList = sysOrgRoleConfRepository.findIsAvailable();
		}
		
		return cateList;
	}
	
	@Override
	public List<SysOrgElement> getRoleByConfId(String fdId){
		return sysOrgElementRepository.getRoleByConfId(fdId);
	}
	
	@Override
	public SysOrgRoleConf getOne(String fdId){
		return sysOrgRoleConfRepository.getOne(fdId);
	}
}
