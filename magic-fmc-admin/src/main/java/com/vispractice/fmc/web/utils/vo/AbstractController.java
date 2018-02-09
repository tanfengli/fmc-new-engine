package com.vispractice.fmc.web.utils.vo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.business.entity.sys.org.SysOrgPerson;
import com.vispractice.fmc.web.domain.security.SaltedUser;
import com.vispractice.fmc.web.utils.json.ObjectMapperFactory;

public abstract class AbstractController<T> {
	
	@Autowired
	protected ResourceUtil resourceUtil;
	
	/**
	 * 获取系统登录人
	 * @return
	 */
	public SysOrgPerson getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof SaltedUser) {
			return ((SaltedUser) principal).getSysOrgPerson();
		}
		
		return null;
	}

	protected Object coventJsonToEntity(String context,Class<T> valueType)
			throws JsonParseException,JsonMappingException,ClassNotFoundException,IOException {
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
		
		return  mapper.readValue(context,valueType);
	}
}
