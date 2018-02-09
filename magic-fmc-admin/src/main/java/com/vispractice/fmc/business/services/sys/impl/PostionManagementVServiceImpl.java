package com.vispractice.fmc.business.services.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.base.utils.IDGenerator;
import com.vispractice.fmc.business.entities.sys.repositories.PositionManagemetVRepository;
import com.vispractice.fmc.business.entities.sys.view.PositionManagemetV;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgRole;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgRoleRepository;
import com.vispractice.fmc.business.services.sys.IPostionManagementVService;

@Service
@Transactional
public class PostionManagementVServiceImpl implements IPostionManagementVService {

	@Autowired
	private PositionManagemetVRepository managemetVRepository;
	
	@Autowired
	private SysOrgElementRepository elementRepository;
	
	@Autowired SysOrgRoleRepository orgRoleRepository;

	@Override
	public Page<PositionManagemetV> findBySearch(final PositionManagemetV managemetV, Pageable pageable) {
		
		Specification<PositionManagemetV> spec = new Specification<PositionManagemetV>() {

			@Override
			public Predicate toPredicate(Root<PositionManagemetV> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(null != managemetV){ 
					if (StringUtils.isNotBlank(managemetV.getFdName())) 
					 list.add(cb.like(root.<String>get("fdName"), "%"+managemetV.getFdName()+"%"));
					if (null!=managemetV.getFdIsAvailable())
						list.add(cb.equal(root.get("fdIsAvailable"), managemetV.getFdIsAvailable()));
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}

		};
		return managemetVRepository.findAll(spec, pageable);

	}

	@Override
	public Page<PositionManagemetV> findAll(PositionManagemetV managemetV, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<PositionManagemetV> example = Example.of(managemetV, matcher); 
		return managemetVRepository.findAll(example,pageable);
	}
 
	@Override
	public PositionManagemetV get(Long id) {
		return managemetVRepository.getOne(id);
	}

	@Override
	public PositionManagemetV save(PositionManagemetV managemetV) { 
		SysOrgElement soe = new SysOrgElement();
		SysOrgRole sor = new SysOrgRole();
		if(managemetV.getFdId() == null){ 
			String id = IDGenerator.generateID();
			soe.setFdId(id);
			sor.setFdId(id);
			soe.setFdCreateTime(new Date(System.currentTimeMillis()));
			soe.setFdAlterTime(new Date(System.currentTimeMillis()));
		}else{
			soe.setFdId(managemetV.getFdId());
			sor.setFdId(managemetV.getFdId());
		} 
		
		String pinyin="";
		try {
			pinyin = PinyinHelper.convertToPinyinString(managemetV.getFdName(),
					"", PinyinFormat.WITHOUT_TONE);// 汉字转拼音
		} catch (PinyinException e) {
			e.printStackTrace();
		}
		soe.setFdNamePinyin(pinyin);
		soe.setFdOrgType((long) SysOrgConstant.ORG_TYPE_ROLE);
		soe.setFdName(managemetV.getFdName()); 
		soe.setFdIsAvailable(Long.valueOf(managemetV.getFdIsAvailable().toString()));
		soe.setFdIsAbandon(Long.parseLong("0"));
		soe.setFdIsBusiness("1");
		soe.setFdMemo(managemetV.getFdMemo());
		soe.setFdCalendarId("1");
		soe = elementRepository.save(soe);

		soe.setFdNo(soe.getFdId());
		soe.setFdHierarchyId("x"+soe.getFdId()+"x");
	    elementRepository.save(soe);
		//从表
		sor.setFdPlugin(managemetV.getFdPlugin());
		sor.setFdParameter(managemetV.getFdParameter()); 
		sor.setFdIsMultiple(managemetV.getFdIsMultiple());
		sor.setFdRtnValue(managemetV.getFdRtnValue()); 
		if(null==sor.getFdId() || "".equals(sor.getFdId())){
	 		sor.setFdId(soe.getFdId());
	 	} 
		 
		orgRoleRepository.save(sor);
	 	
		return managemetV;
	}

	@Override
	public void delete(PositionManagemetV managemetV) {
		SysOrgRole sor = orgRoleRepository.findByFdId(managemetV.getFdId());
		if(null!=sor){
			orgRoleRepository.delete(managemetV.getFdId());
		}
		elementRepository.delete(managemetV.getFdId());
	}

	@Override
	public int findByFdName(String fdName,String fdId) { 
		return managemetVRepository.findByFdName(fdName,fdId);
	}
	
	@Override
	public List<SysOrgElement>  getAll(){
		return elementRepository.getGeneralPost(Long.valueOf((CommonConstant.AVAILABLE_FLAG)));
	}
 
}