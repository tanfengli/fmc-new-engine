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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.base.constrant.BaseTreeConstant;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entities.setting.repositories.GroupAdminRepository;
import com.vispractice.fmc.business.entities.setting.view.GroupAdmin;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgGroupElementRepository;
import com.vispractice.fmc.business.services.setting.IGroupAdminService;
import com.vispractice.fmc.web.domain.setting.GroupMemberForm;

@Transactional
@Service
public class GroupAdminServiceImpl implements IGroupAdminService {

	@Autowired
	private GroupAdminRepository groupAdminRepository;

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;

	@Autowired
	private SysOrgGroupElementRepository sysOrgGroupElementRepository;

	/**
	 * 检查是否有重复的群组成员名称
	 * @param groupMemberForm
	 * @return
	 */
	private Boolean beforeSave(GroupMemberForm groupMemberForm) {
		//检查是否有重复的群组成员名称
		List<SysOrgElement> groupElementList = sysOrgElementRepository.findByFdNameAndFdOrgType(groupMemberForm.getFdName(), (long)SysOrgConstant.ORG_TYPE_GROUP);
		if (groupElementList.size()==0) {
			return true;
		}else if(groupElementList.size()==1){
			return null==groupMemberForm.getFdId()?false:groupElementList.get(0).getFdId().equals(groupMemberForm.getFdId())?true:false;
		}else {
			return false;
		}
	}

	/**
	 * 增加
	 */
	@Override
	public Boolean save(GroupMemberForm groupMemberForm) {

		if (beforeSave(groupMemberForm)) {

			SysOrgElement sysOrgElement = new SysOrgElement();
			String pinyin = "";
			String newId = IDGenerator.generateID();
			
			if (groupMemberForm.getFdId() != null) {
				sysOrgElement.setFdId(groupMemberForm.getFdId());
			}else{
				sysOrgElement.setFdId(newId);
			}
			sysOrgElement.setFdName(groupMemberForm.getFdName());
			sysOrgElement.setFdIsAvailable(Long.valueOf(groupMemberForm.getFdIsAvailable()));
			try {
				pinyin = PinyinHelper.convertToPinyinString(groupMemberForm.getFdName(), "",PinyinFormat.WITHOUT_TONE);// 汉字转拼音
			} catch (PinyinException e) {
				e.printStackTrace();
			}
			sysOrgElement.setFdNamePinyin(pinyin);
			sysOrgElement.setFdCreateTime(new Date(System.currentTimeMillis()));
			sysOrgElement.setFdAlterTime(new Date(System.currentTimeMillis()));
			sysOrgElement.setFdMemo(groupMemberForm.getFdMemo());

			sysOrgElement.setFdOrgType((long) SysOrgConstant.ORG_TYPE_GROUP);
			sysOrgElement.setFdNo(newId);
			sysOrgElement.setFdIsBusiness("1");
			sysOrgElement.setFdCalendarId("1");
			sysOrgElement.setFdHierarchyId(BaseTreeConstant.HIERARCHY_ID_SPLIT+newId+BaseTreeConstant.HIERARCHY_ID_SPLIT);
			sysOrgElement.setFdIsAbandon(1L);
			sysOrgElement.setFdCateid(groupMemberForm.getFdCateid());
			sysOrgElement.setFdIsLeaf(1L);

			sysOrgElementRepository.save(sysOrgElement);

			/*** 保存群组成员 ***/
			// 清理
			sysOrgGroupElementRepository.deleteByFdGroupid(groupMemberForm.getFdId());

			// 保存
			List<SysOrgElement> sysOrgElementList = groupMemberForm
					.getGroupElementArray();
			for (SysOrgElement element : sysOrgElementList) {
				String groupId = sysOrgElement.getFdId();
				String elementId = element.getFdId();

				sysOrgGroupElementRepository.insertOne(groupId,elementId);

			}
			
			return true;

		}
		
		return false;

	}

	/**
	 * 修改
	 */
	@Override
	public void update(GroupAdmin groupAdmin) {

		sysOrgElementRepository.update(groupAdmin.getFdName(),
				Long.valueOf(groupAdmin.getFdIsAvailable()), groupAdmin.getFdId());

	}

	/**
	 * 删除
	 */
	@Override
	public void delById(GroupAdmin groupAdmin) {

		sysOrgElementRepository.delete(groupAdmin.getFdId());

	}

	public Page<GroupAdmin> findByExample(GroupAdmin groupAdmin,
			Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreNullValues();
		Example<GroupAdmin> example = Example.of(groupAdmin, matcher);

		return groupAdminRepository.findAll(example, pageable);
	}

	@Override
	public Page<GroupAdmin> findBySearch(final GroupAdmin groupAdmin,
			Pageable pageable) {

		Specification<GroupAdmin> spec = new Specification<GroupAdmin>() {

			@Override
			public Predicate toPredicate(Root<GroupAdmin> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (null != groupAdmin) {
					if (StringUtils.isNotBlank(groupAdmin.getFdName()))
						list.add(cb.like(root.<String> get("fdName"), "%"
								+ groupAdmin.getFdName() + "%"));
					if (StringUtils.isNotBlank(groupAdmin.getFdCateid()))
						list.add(cb.equal(root.get("fdCateid"),
								groupAdmin.getFdCateid()));
					if (StringUtils.isNotBlank(groupAdmin.getFdIsAvailable()))
						list.add(cb.equal(root.get("fdIsAvailable"),
								groupAdmin.getFdIsAvailable()));

				}
				Predicate[] p = new Predicate[list.size()];

				return cb.and(list.toArray(p));
			}

		};
		return groupAdminRepository.findAll(spec, pageable);

	}

	@Override
	public List<SysOrgElement> findMember(String fdId,List<Long> fdOrgType) {

		List<SysOrgElement> elementList = new ArrayList<SysOrgElement>();
		elementList = sysOrgElementRepository.findByFdParentidAndFdOrgTypeInAndFdIsAvailable(fdId,fdOrgType,Long.valueOf(CommonConstant.AVAILABLE_FLAG));
		
		return elementList;
	}


	@Override
	public List<SysOrgElement> findGroupMember(String groupId) {

		List<SysOrgElement> elementList = sysOrgElementRepository
				.findGroupMember(groupId);

		return elementList;
	}

	
	@Override
	public List<SysOrgElement> findPerson(String fdId, Long fdOrgType) {
		return sysOrgElementRepository.findByFdParentidAndFdOrgTypeAndFdIsAvailable(fdId,fdOrgType,Long.valueOf(CommonConstant.AVAILABLE_FLAG));
	} 
	
}
