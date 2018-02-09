package com.vispractice.fmc.business.service.sys.config.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entity.sys.config.SysWfBusiInterface;
import com.vispractice.fmc.business.entity.sys.config.repository.SysWfBusiInterfaceRepository;
import com.vispractice.fmc.business.service.sys.config.ISysWfBusiInterface;

@Service
@Transactional
public class SysWfBusiInterfaceImpl implements ISysWfBusiInterface {
	@Autowired
	private SysWfBusiInterfaceRepository sysWfBusiInterfaceRepository;

	@Override
	public void saveGrantInterface(String fdBusiId,String interfaceIds) {
		sysWfBusiInterfaceRepository.deleteByTypeAndId("0",fdBusiId);
		
		if (StringUtils.isNotEmpty(interfaceIds)) {
			String[] interfaceIdStr = interfaceIds.split(";");
			for (String fdInterfaceId : interfaceIdStr) {
				SysWfBusiInterface sysWfBusiInterface = new SysWfBusiInterface();
				sysWfBusiInterface.setFdBusiId(fdBusiId);
				sysWfBusiInterface.setFdInterfaceId(fdInterfaceId);
				sysWfBusiInterface.setFdBusiType("0");
				sysWfBusiInterfaceRepository.save(sysWfBusiInterface);
			}
		}
	}

	@Override
	public void saveGrantCallback(String fdBusiId,String isCallback,String address) {
		sysWfBusiInterfaceRepository.deleteByTypeAndId("1",fdBusiId);
		
		if (StringUtils.isNotEmpty(isCallback)) {
			SysWfBusiInterface sysWfBusiInterface = new SysWfBusiInterface();
			sysWfBusiInterface.setFdBusiId(fdBusiId);
			sysWfBusiInterface.setFdIsBack(isCallback);
			sysWfBusiInterface.setFdBackAddress(address);
			sysWfBusiInterface.setFdBusiType("1");
			
			sysWfBusiInterfaceRepository.save(sysWfBusiInterface);
		}
	}

	@Override
	public List<SysWfBusiInterface> findBusiInterface(String fdBusiId,String fdType) {
		return sysWfBusiInterfaceRepository.findByTypeAndId(fdType,fdBusiId);
	}
}
