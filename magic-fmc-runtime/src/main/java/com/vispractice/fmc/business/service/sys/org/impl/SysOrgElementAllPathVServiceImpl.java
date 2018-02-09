package com.vispractice.fmc.business.service.sys.org.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.base.constant.CommonConstant;
import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementAllPathVRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgPostPersonRepository;
import com.vispractice.fmc.business.entity.sys.org.view.SysOrgElementAllPathV;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementAllPathVService;

@Service
@Transactional
public class SysOrgElementAllPathVServiceImpl implements
		ISysOrgElementAllPathVService {

	@Autowired
	private SysOrgElementAllPathVRepository elementAllPathVRepository;

	@Autowired
	private SysOrgPostPersonRepository sysOrgPostPersonRepository;

	@Autowired
	private SysOrgElementRepository sysOrgElementRepository;
	
	
	@Override
	public String getDescription(String fdId) throws Exception{
		
		SysOrgElementAllPathV allPathV = this.getElementAllPath(fdId);
		if (CommonConstant.NOT_AVAILABLE_FLAG.equals(String.valueOf(allPathV.getFdIsAvailable()))) {
			allPathV.setDescription(allPathV.getDescription()+"<span style=\"color: red;\">(已失效)</span>");
		}
		return allPathV.getDescription();
	}
	
	@Override
	public SysOrgElementAllPathV getElementAllPath(String fdId) throws Exception{
		
		SysOrgElement element = sysOrgElementRepository.findOne(fdId);
		SysOrgElementAllPathV allPathV = new SysOrgElementAllPathV();
		allPathV =elementAllPathVRepository.findOne(fdId);
		StringBuffer description = new StringBuffer();
		
		if(null!=element){
			String orgType = "";
			int type = element.getFdOrgType().intValue();
			switch (type) {
			case SysOrgConstant.ORG_TYPE_ORG:
				orgType="机构";
				break;
			case SysOrgConstant.ORG_TYPE_DEPT:
				orgType="部门";
				break;
			case SysOrgConstant.ORG_TYPE_POST:
				orgType="岗位";
				break;
			case SysOrgConstant.ORG_TYPE_PERSON:
				orgType="人员";
				break;
			}
			String nameZh = allPathV.getNameZh();
			description.append(element.getFdName()).append("(").append(orgType).append(") - ").append(nameZh);
			
			// 获取岗位下人员名称
			if (type==SysOrgConstant.ORG_TYPE_POST) {
				List<String> personIdList = sysOrgPostPersonRepository.findFdPersonId(element.getFdId());
				if (personIdList.size()>0) {
					List<SysOrgElement> personElementlList = sysOrgElementRepository.findAll(personIdList);
					description.append("(");
					for (SysOrgElement sysOrgElement : personElementlList) {
						description.append(sysOrgElement.getFdName()+",");
					}
					description.replace(description.length()-1, description.length(), "");
					description.append(")");
				}else{
					description.append("(该岗位下无人员)");
				}
			}
			allPathV.setDescription(description.toString());
		}
		
		
		return allPathV;
	
		
	}
	
	@Override
	public SysOrgElementAllPathV findByFdId(String fdId) {
		return elementAllPathVRepository.findByFdId(fdId);
	}


}