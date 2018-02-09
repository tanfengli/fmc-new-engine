package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.vispractice.fmc.business.base.utils.XMLReaderOrCreate;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfCommonTemplate;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfCommonTemplateRepository;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfCommonTemplateVRepository;
import com.vispractice.fmc.business.entity.sys.workflow.view.SysWfCommonTemplateV;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfCommonTemplateService;
 

@Service
@Transactional
public class SysWfCommonTemplateServiceImpl implements ISysWfCommonTemplateService {

	@Autowired
	private SysWfCommonTemplateRepository commonTemplateRepository;
	
	@Autowired
	private SysWfCommonTemplateVRepository commonTemplateVRepository;

	@Override
	public Page<SysWfCommonTemplateV> findBySearch(final SysWfCommonTemplateV sysWfCommonTemplateV, Pageable pageable) {
		
		Specification<SysWfCommonTemplateV> spec = new Specification<SysWfCommonTemplateV>() {

			@Override
			public Predicate toPredicate(Root<SysWfCommonTemplateV> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> list = new ArrayList<Predicate>();
				
				if(null != sysWfCommonTemplateV){ 
					if (StringUtils.isNotBlank(sysWfCommonTemplateV.getFdName())) 
					    list.add(cb.like(root.<String>get("fdName"), "%"+sysWfCommonTemplateV.getFdName()+"%"));
					if (StringUtils.isNotBlank(sysWfCommonTemplateV.getFdIsDefault()))
						list.add(cb.equal(root.get("fdIsDefault"), sysWfCommonTemplateV.getFdIsDefault()));
				}
				Predicate[] p = new Predicate[list.size()];
				
				return cb.and(list.toArray(p));
			}

		};
		
		Page<SysWfCommonTemplateV> page = commonTemplateVRepository.findAll(spec, pageable);
		List<SysWfCommonTemplateV> list = page.getContent();
		for (SysWfCommonTemplateV swc : list) {
			//读取xml
			String content = swc.getFdFlowContent();
			XMLReaderOrCreate reader = new XMLReaderOrCreate();
			Map<String, String> map = reader.getRootElementAttr(content);  
			
			String description = map.get("description");//流程说明 
			String rejectReturn = map.get("rejectReturn");
			String notifyType = map.get("notifyType");
			String notifyOnFinish = map.get("notifyOnFinish");
			String notifyDraftOnFinish = map.get("notifyDraftOnFinish");
			String dayOfNotifyPrivileger = map.get("dayOfNotifyPrivileger");//流程启动多少天通知
			String privilegerIds = map.get("privilegerIds");//特权人
			String privilegerNames = map.get("privilegerNames"); 
			if(null!=description){
				swc.setDescription(description);
			}
			if(null!=rejectReturn){
				swc.setRejectReturn(rejectReturn);
				List<String> strList = new ArrayList<String>(); 
				if(rejectReturn.equals("true")){
					strList.add("1");
					swc.setRejectReturnChe(strList);
				}  
			}
			if(null!=notifyType){
				swc.setNotifyType(notifyType);
				List<String> strList = new ArrayList<String>(); 
				strList.add(notifyType);
				swc.setNotifyTypeChe(strList); 
			}
			if(null!=notifyOnFinish){
				swc.setNotifyOnFinish(notifyOnFinish);
				List<String> strList = new ArrayList<String>(); 
				if(notifyOnFinish.equals("true")){
					strList.add("1");
					swc.setNotifyOnFinishChe(strList);
				}
			}
			if(null!=notifyDraftOnFinish){
				swc.setNotifyDraftOnFinish(notifyDraftOnFinish);
				List<String> strList = new ArrayList<String>(); 
				if(notifyDraftOnFinish.equals("true")){
					strList.add("1");
					swc.setNotifyDraftOnFinishChe(strList);
				}
			}
			if(null!=dayOfNotifyPrivileger){
				swc.setDayOfNotifyPrivileger(dayOfNotifyPrivileger);
			}
			if(null!=privilegerIds){
				swc.setPrivilegerIds(privilegerIds);
			}
			if(null!=privilegerNames){
				swc.setPrivilegerNames(privilegerNames);
			}
			
		}
		return page;

	}

	@Override
	public Page<SysWfCommonTemplateV> findAll(SysWfCommonTemplateV sysWfCommonTemplateV, Pageable pageable) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysWfCommonTemplateV> example = Example.of(sysWfCommonTemplateV, matcher); 
		return commonTemplateVRepository.findAll(example,pageable);
	}

	@Override
	public void save(SysWfCommonTemplate sysWfCommonTemplate) {
		commonTemplateRepository.save(sysWfCommonTemplate);		 
	}

	@Override
	public SysWfCommonTemplate get(String id) {
		return commonTemplateRepository.getOne(id);
	}

	@Override
	public void deleteByFdId(String fdId) {
		commonTemplateRepository.delete(fdId);		
	}
	
	@Override
	public List<SysWfCommonTemplate> findRoot(){
		return commonTemplateRepository.findAll();
	}

	@Override
	public int findByFdIsDefault(String fdIsDefault,String fdId) { 
		return commonTemplateVRepository.findByFdIsDefault(fdIsDefault,fdId);
	}
	
	@Override
	public SysWfCommonTemplate getDefaultTemplate(){
		SysWfCommonTemplate sysWfCommonTemplate = commonTemplateRepository.findByFdIsDefault();
		return sysWfCommonTemplate;
	}

	@Override
	public String getContent(String fdId) throws Exception{ 
		String xml = "";
		try {
			SysWfCommonTemplate sysWfTemplate =  commonTemplateRepository.findOne(fdId);
			//获取到xml
			if(null != sysWfTemplate){
				xml = sysWfTemplate.getFdFlowContent();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return xml;
	}
}