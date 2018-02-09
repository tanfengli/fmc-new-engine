package com.vispractice.fmc.business.service.sys.news.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMainOreader;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsMainOreaderRepository;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainOreaderService;

@Service
public class SysNewsMainOreaderServiceImpl implements ISysNewsMainOreaderService {

	@Autowired
	private SysNewsMainOreaderRepository sysNewsMainOreaderRepository;
	
	@Override
	public List<SysNewsMainOreader> findByMainId(String mainId) {
		return sysNewsMainOreaderRepository.findByFdMainId(mainId);
	}

	@Override
	public void save(String fdMainId, String authOtherReaderId) {
		SysNewsMainOreader oreader = new SysNewsMainOreader();
		oreader.setFdMainId(fdMainId);
		oreader.setAuthOtherReaderId(authOtherReaderId);
		sysNewsMainOreaderRepository.save(oreader);
	}

}
