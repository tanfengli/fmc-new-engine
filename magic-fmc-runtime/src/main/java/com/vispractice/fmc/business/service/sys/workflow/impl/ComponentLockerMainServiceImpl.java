package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entity.sys.workflow.ComponentLockerMain;
import com.vispractice.fmc.business.entity.sys.workflow.repository.ComponentLockerMainRepository;
import com.vispractice.fmc.business.service.sys.workflow.IComponentLockerMainService;

@Service
@Transactional
public class ComponentLockerMainServiceImpl implements IComponentLockerMainService {
	@Autowired
	private ComponentLockerMainRepository componentLockerMainRepository;
	
	public ComponentLockerMain acquireLock(String wfInstanceId,String userId) {
		ComponentLockerMain componentLockerMain = new ComponentLockerMain();
		componentLockerMain.setFdId("sysWfProcess#" + wfInstanceId);
		componentLockerMain.setFdModelId(wfInstanceId);
		componentLockerMain.setFdModelName("com.ruiyi.kmss.sys.workflow.engine.spi.model.SysWfProcess");
		componentLockerMain.setFdLockerOwner(userId + Thread.currentThread().getId());
		componentLockerMain.setFdLockedTime(new Date());
		
		componentLockerMainRepository.save(componentLockerMain);
		
		return componentLockerMain;
	}

	@Override
	public void releaseLock(String wfInstanceId) {
		componentLockerMainRepository.delete("sysWfProcess#" + wfInstanceId);
	}

	@Override
	public ComponentLockerMain isLocked(String wfInstanceId) {
		ComponentLockerMain componentLockerMain = componentLockerMainRepository.findOne("sysWfProcess#" + wfInstanceId);
	
		return componentLockerMain;
	}
}
