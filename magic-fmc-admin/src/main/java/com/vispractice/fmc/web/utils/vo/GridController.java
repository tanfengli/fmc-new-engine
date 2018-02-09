package com.vispractice.fmc.web.utils.vo;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;

public interface GridController {

	public Page<SysOrgPerson> findAll(@RequestParam String context, @RequestParam String pageVO);
	
	public void deleteOne(@RequestParam String context) throws Exception;
	
}
