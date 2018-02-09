package com.vispractice.fmc.business.service.sys.news.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMainOeditor;
import com.vispractice.fmc.business.entity.sys.news.repository.SysNewsMainOeditorRepository;
import com.vispractice.fmc.business.service.sys.news.ISysNewsMainOeditorService;

@Service
public class SysNewsMainOeditorServiceImpl implements ISysNewsMainOeditorService {

	@Autowired
	private SysNewsMainOeditorRepository sysNewsMainOeditorRepository;
	
	@Override
	public List<SysNewsMainOeditor> findByMainId(String mainId) {
		return sysNewsMainOeditorRepository.findByFdMainId(mainId);
	}

	@Override
	public void deleteByFdMainIdAndAuthOtherEditorId(String fdMainId, String authOeditId) {
		sysNewsMainOeditorRepository.deleteByFdMainIdAndAuthOtherEditorId(fdMainId, authOeditId);
	}

}
