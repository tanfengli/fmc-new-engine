package com.vispractice.fmc.business.service.sys.news;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMainOreader;

public interface ISysNewsMainOreaderService {

	public List<SysNewsMainOreader> findByMainId(String mainId);
	
	public void save(String fdMainId, String authOtherReaderId);
	
}
