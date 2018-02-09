package com.vispractice.fmc.business.service.sys.org.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.base.constrant.SysOrgConstant;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPostPerson;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgElementRepository;
import com.vispractice.fmc.business.entity.sys.org.repository.SysOrgPostPersonRepository;
import com.vispractice.fmc.business.service.sys.org.ISysOrgPostPersonService;

@Service
@Transactional
public class SysOrgPostPersonServiceImpl implements ISysOrgPostPersonService {

	@Autowired
	private SysOrgPostPersonRepository sysOrgPostPersonRepository;
	
	@Autowired
	private SysOrgElementRepository orgElemntRepo;

	@Override
	public void save(SysOrgPostPerson orgPostPerson) {
		sysOrgPostPersonRepository.save(orgPostPerson);
	}
	
	@Override
	public void save(List<SysOrgPostPerson> orgPostPersonList) {
		sysOrgPostPersonRepository.save(orgPostPersonList);
	}
	
	@Override
	public void add(String personId,String postId){
		sysOrgPostPersonRepository.addOne(personId, postId);
	}

	@Override
	public List<String> findFdPersonId(String fdPostId) {
		return sysOrgPostPersonRepository.findFdPersonId(fdPostId);
	}
	
	@Override
	public List<SysOrgElement> getPersons(String fdPostId){
		List<String> personIds = sysOrgPostPersonRepository.findFdPersonId(fdPostId);
		List<SysOrgElement> persons = orgElemntRepo.findAll(personIds);
		return persons;
	}
	
	@Override
	public List<SysOrgElement> getPosts(String fdPersonId){
		List<String> postIds = sysOrgPostPersonRepository.findFdPostId(fdPersonId);
		List<SysOrgElement> posts = orgElemntRepo.findAll(postIds);
		return posts;
	}
	
	@Override
	public List<SysOrgElement> getPostByPersonId(String fdPersonId){
		return orgElemntRepo.getPostByPersonId(fdPersonId);
	}

	@Override
	public List<String> findFdPostId(String fdPersonId) {
		return sysOrgPostPersonRepository.findFdPostId(fdPersonId);
	}
	
	/**
	 * 通过岗位id删除
	 */
	@Override
	public void deleteByPostId(String fdPostId){
		sysOrgPostPersonRepository.delByPostId(fdPostId);
	}
	
	/**
	 * 通过人员id删除
	 */
	@Override
	public void deleteByPersonId(String fdPersonId){
		sysOrgPostPersonRepository.delByPersonId(fdPersonId);
	}
	
	@Override
	public boolean checkUserId(String userId, String expectedId){
		
		if(null==userId||null==expectedId){
			return false;
		}
		if(userId.equals(expectedId)){
			return true;
		}
		SysOrgElement element = orgElemntRepo.findOne(expectedId);
		//检测当前用户ID是否属于expectedId对应的岗位
		if(null!=element&&null!=element.getFdOrgType()&&element.getFdOrgType()==SysOrgConstant.ORG_TYPE_POST){
			List<String> personIds =  sysOrgPostPersonRepository.findFdPersonId(expectedId);
			if(personIds.contains(userId))
				return true;
		}
		
		return false;
	}

	@Override
	public SysOrgPostPerson isExistPersonPost(String personId, String postId) {
		return sysOrgPostPersonRepository.findByFdPersonidAndFdPostid(personId, postId);

	}

}