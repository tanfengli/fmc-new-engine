package com.vispractice.fmc.business.service.sys.news;

import java.util.List;

import com.vispractice.fmc.business.entity.sys.news.SysNewsMainOeditor;

public interface ISysNewsMainOeditorService {

	public List<SysNewsMainOeditor> findByMainId(String mainId);
	
	public void deleteByFdMainIdAndAuthOtherEditorId(String fdMainId, String authOeditId);
	
}
