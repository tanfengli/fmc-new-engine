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

import com.vispractice.fmc.business.base.domain.CommonForm;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorize;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorizeItem;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorizeScope;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuthorizerVRepository;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfAuthorizerV;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfAuthorizeItemService;
import com.vispractice.fmc.business.services.sys.ISysWfAuthorizeScopeService;
import com.vispractice.fmc.business.services.sys.ISysWfAuthorizerService;
import com.vispractice.fmc.business.services.sys.ISysWfAuthorizerVService;

@Service
@Transactional
public class SysWfAuthorizerVServiceImpl implements ISysWfAuthorizerVService {
	@Autowired
	private ISysOrgElementService elementService;
	
	@Autowired
	private ISysWfAuthorizerService wfAuthorizerService; 
	
	@Autowired
	private ISysWfAuthorizeItemService authorizeItemService;
	
	@Autowired
	private ISysWfAuthorizeScopeService authorizeScopeService;
	
	@Autowired
	private SysWfAuthorizerVRepository wfAuthorizerVRepository;

	@Override
	public Page<SysWfAuthorizerV> findAll(SysWfAuthorizerV authorizerV, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysWfAuthorizerV> example = Example.of(authorizerV, matcher);
		return wfAuthorizerVRepository.findAll(example, pageable);
	}

	@Override
	public Page<SysWfAuthorizerV> findBySearch(final SysWfAuthorizerV authorizerV, Pageable pageable) {

		Specification<SysWfAuthorizerV> spec = new Specification<SysWfAuthorizerV>() {

			@Override
			public Predicate toPredicate(Root<SysWfAuthorizerV> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> list = new ArrayList<Predicate>();

				if (null != authorizerV) {
					if (StringUtils.isNotBlank(authorizerV.getFdAuthorizerPersonName()))
						list.add(cb.like(root.<String>get("fdAuthorizerPersonName"),
								"%" + authorizerV.getFdAuthorizerPersonName() + "%"));
				}
				Predicate[] p = new Predicate[list.size()];

				return cb.and(list.toArray(p));
			}
		};

		Page<SysWfAuthorizerV> page = wfAuthorizerVRepository.findAll(spec, pageable);
		for (SysWfAuthorizerV sysWfAuthorizerV : page) {
			List<String> list = new ArrayList<String>();
			if (null != sysWfAuthorizerV.getFdExpireDeleted() && !"".equals(sysWfAuthorizerV.getFdExpireDeleted())) {
				if (sysWfAuthorizerV.getFdExpireDeleted().equals("1")) {
					list.add(sysWfAuthorizerV.getFdExpireDeleted());
					sysWfAuthorizerV.setFdExpireDeletedChe(list);
				}
			}
			// 获取授权项
			if (null != sysWfAuthorizerV.getFdAuthorizeOrgId() && !"".equals(sysWfAuthorizerV.getFdAuthorizeOrgId())) {
				List<CommonForm> commonForms = new ArrayList<CommonForm>();
				List<String> listFdId = StringToList(sysWfAuthorizerV.getFdAuthorizeOrgId());
				List<String> listFdName = StringToList(sysWfAuthorizerV.getFdAuthorizeOrgName());
				List<String> authorizedChe = new ArrayList<String>();
				for (int i = 0; i < listFdId.size(); i++) {
					CommonForm cf = new CommonForm();
					cf.setFdId(listFdId.get(i));
					if(listFdName.size()>i)
						cf.setFdName(listFdName.get(i));
					else
						cf.setFdName("null");
					commonForms.add(cf);

					authorizedChe.add(listFdId.get(i));
					sysWfAuthorizerV.setFdAuthorizedChe(authorizedChe);
				}
				sysWfAuthorizerV.setFdAuthorizeContext(commonForms);
			}

			// 授权范围
			if (null != sysWfAuthorizerV.getFdAuthorizedCateTextId()
					&& !"".equals(sysWfAuthorizerV.getFdAuthorizedCateTextId())) {

				List<CommonForm> commonForms = new ArrayList<CommonForm>();
				List<String> listFdId = StringToList(sysWfAuthorizerV.getFdAuthorizedCateTextId());
				List<String> listFdName = StringToList(sysWfAuthorizerV.getFdAuthorizedCateText());
				for (int i = 0; i < listFdId.size(); i++) {
					CommonForm cf = new CommonForm();
					cf.setFdId(listFdId.get(i));
					if(listFdName.size()>i)
						cf.setFdName(listFdName.get(i));
					else
						cf.setFdName("null");
					commonForms.add(cf);
				}
				sysWfAuthorizerV.setAuthorizeScopeArray(commonForms);
			}

		}

		return page;

	}

	/**
	 * 字符串转数组
	 */
	public static List<String> StringToList(String obj) {
		String[] array = obj.split(",");
		List<String> list = new ArrayList<String>();
		for (String str : array) {
			list.add(str);
		}
		return list;
	}

	@Override
	public SysWfAuthorizerV get(Long id) {
		return wfAuthorizerVRepository.getOne(id);
	}

	@Override
	public SysWfAuthorizerV save(SysWfAuthorizerV authorizerV,SysOrgPerson sysOrgPerson) {
		List<CommonForm> authorizeScopes = authorizerV.getAuthorizeScopeArray();
		List<String> listChe = authorizerV.getFdAuthorizedChe();
		
		if(authorizerV.getFdId() == null){
			SysOrgElement element = elementService.findByFdNo(sysOrgPerson.getFdLoginName());
			authorizerV.setFdCreator(element.getFdId());	 
			Date utilDate=new Date();
			authorizerV.setFdCreateTime(new java.util.Date(utilDate.getTime()));
		}   
		
		SysWfAuthorize swf = new SysWfAuthorize(); 
		swf.setFdId(authorizerV.getFdId());
		swf.setFdAuthorizedPerson(authorizerV.getFdAuthorizedPerson());
		swf.setFdAuthorizer(authorizerV.getFdAuthorizer());
		swf.setFdAuthorizeType(authorizerV.getFdAuthorizeType());
		swf.setFdCreateTime(authorizerV.getFdCreateTime());
		swf.setFdCreator(authorizerV.getFdCreator());
		swf.setFdEndTime(authorizerV.getFdEndTime());  
		if(authorizerV.getFdExpireDeletedChe().size()>0){
			swf.setFdExpireDeleted(authorizerV.getFdExpireDeletedChe().get(0));
		}else{
			swf.setFdExpireDeleted("0");
		}
		
		if (authorizeScopes != null && authorizeScopes.size() > 0) {
			swf.setIsAllScope("0");
		} else {
			swf.setIsAllScope("1");
		}

		swf.setFdStartTime(authorizerV.getFdStartTime());
		SysWfAuthorize authorize = wfAuthorizerService.save(swf);
		
		//从表-授权项
		authorizeItemService.deleteByFdAuthorizeId(authorize.getFdId());
		for (String str : listChe) {
			SysWfAuthorizeItem item = new SysWfAuthorizeItem();
			item.setFdAuthorizeId(authorize.getFdId());
			item.setFdAuthorizeOrgId(str);
			authorizeItemService.save(item);
		}
		
		//删除从表 sys_wf_authorize_scope,重新添加--授权范围
		authorizeScopeService.deleteByfdAuthorizeId(authorize.getFdId()); 
		for (CommonForm sas : authorizeScopes) {
			SysWfAuthorizeScope authorizeScope = new SysWfAuthorizeScope();
			authorizeScope.setFdAuthorizeCateId(sas.getFdId());
			authorizeScope.setFdAuthorizeCateName(sas.getFdName());
			authorizeScope.setFdAuthorizeCateShowText(sas.getFdName());
			authorizeScope.setFdTemplateId(sas.getFdId());
			authorizeScope.setFdModelName("com.ruiyi.kmss.sys.news.model.SysNewsMain");
			authorizeScope.setFdAuthorizeId(authorize.getFdId());
			authorizeScopeService.save(authorizeScope);
		}
		
		return authorizerV;
	}

	@Override
	public void delete(SysWfAuthorizerV authorizerV) {
	}
}