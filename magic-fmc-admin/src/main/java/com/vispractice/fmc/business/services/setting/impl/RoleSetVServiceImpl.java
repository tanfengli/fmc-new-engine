package com.vispractice.fmc.business.services.setting.impl;

import java.util.Date;
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

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entities.setting.RoleSetV;
import com.vispractice.fmc.business.entities.setting.repositories.RoleSetVRepository;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRoleConf;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleRepository;
import com.vispractice.fmc.business.services.setting.IRoleSetVService;

/**
 * 编 号：<br/>
 * 名 称：RoleSetVServiceImpl<br/>
 * 描 述：<br/>
 * 完成日期：2016年12月22日 上午9:56:46<br/>
 * 编码作者：LaiJiashen <br/>
 */
@Service
@Transactional
public class RoleSetVServiceImpl implements IRoleSetVService {

	@Autowired
	private RoleSetVRepository roleSetVRepository;

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;

	@Autowired
	private SysOrgRoleRepository sysOrgRoleRepository;

	@Override
	public List<RoleSetV> findByFdId(SysOrgRoleConf roleConf) {
		String id = roleConf.getFdId();

		return roleSetVRepository.findByFdId(id);
	}

	@Override
	public Page<RoleSetV> findByExample(final RoleSetV roleSet, Pageable pageable) {
		Specification<RoleSetV> spec = new Specification<RoleSetV>() {
			@Override
			public Predicate toPredicate(Root<RoleSetV> root,CriteriaQuery<?> query,CriteriaBuilder cb) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (null != roleSet) {
					if (StringUtils.isNotBlank(roleSet.getCateId())) {
						list.add(cb.equal(root.get("cateId"),roleSet.getCateId()));
					}
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}
		};
		Page<RoleSetV> page = roleSetVRepository.findAll(spec,pageable);

		return page;
	}

	@Override
	public Boolean save(RoleSetV roleSet) {

		if (this.beforeSave(roleSet)) {
			// 保存到element表
			SysOrgElement sysOrgElement = new SysOrgElement();
			String pinyin = "";

			// 新增
			if (roleSet.getFdId() == null) {
				sysOrgElement.setFdId(IDGenerator.generateID());
				sysOrgElement.setFdCreateTime(new Date(System.currentTimeMillis()));
				sysOrgElement.setFdOrgType((long) SysOrgConstant.ORG_TYPE_ROLE);// 设置类型
				sysOrgElement.setFdIsAbandon((long) 0);
				sysOrgElement.setFdIsBusiness("1");
				sysOrgElement.setFdCateid(roleSet.getCateId());
				sysOrgElement.setFdIsLeaf(1L);
			} else {
				// 修改
				sysOrgElement = sysOrgElementRepository.findOne(roleSet.getFdId());
			}

			// 设置通用信息start
			sysOrgElement.setFdName(roleSet.getFdName());
			sysOrgElement.setFdIsAvailable(Long.valueOf(roleSet.getFdIsAvailable()));
			sysOrgElement.setFdMemo(roleSet.getFdMemo());
			try {
				// 汉字转拼音
				pinyin = PinyinHelper.convertToPinyinString(roleSet.getFdName(),"",PinyinFormat.WITHOUT_TONE);
			} catch (PinyinException e) {
				e.printStackTrace();
			}
			sysOrgElement.setFdNamePinyin(pinyin);
			sysOrgElement.setFdAlterTime(new Date(System.currentTimeMillis()));// 设置修改时间
			// 设置通用信息end

			sysOrgElementRepository.save(sysOrgElement);
			sysOrgElement.setFdNo(sysOrgElement.getFdId());

			// 保存到Role表
			SysOrgRole role = new SysOrgRole();

			// 新增
			if (roleSet.getFdId() == null) {
				role.setFdRoleConfId(roleSet.getCateId());
				role.setFdId(sysOrgElement.getFdId());
			} else {// 修改
				role = sysOrgRoleRepository.findOne(roleSet.getFdId());

			}

			// 设置通用信息start
			role.setFdIsMultiple("0");
			if (null != roleSet.getFdIsMultiple() && roleSet.getFdIsMultiple().equals("true")) {
				role.setFdIsMultiple("1");
			}

			role.setFdParameter(roleSet.getFdParameter());
			role.setFdPlugin(roleSet.getFdPlugin());
			role.setFdRtnValue(roleSet.getFdRtnValue());
			// 设置通用信息end
			sysOrgRoleRepository.save(role);
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 检查是否有重复的角色名称
	 * @param roleSet
	 * @return
	 */
	private Boolean beforeSave(RoleSetV roleSet) {
		
		List<SysOrgElement> roleElementList = sysOrgElementRepository.findByFdNameAndFdOrgTypeAndFdCateid(roleSet.getFdName(), (long)SysOrgConstant.ORG_TYPE_ROLE,roleSet.getCateId());
		if (roleElementList.size()==0) {
			return true;
		}else if(roleElementList.size()==1){
			return null==roleSet.getFdId()?false:roleElementList.get(0).getFdId().equals(roleSet.getFdId())?true:false;
		}else {
			return false;
		}
	}

	@Override
	public void deleteById(String fdId) {
		
		sysOrgElementRepository.delete(fdId);
		sysOrgRoleRepository.delete(fdId);
		
	}
	
}