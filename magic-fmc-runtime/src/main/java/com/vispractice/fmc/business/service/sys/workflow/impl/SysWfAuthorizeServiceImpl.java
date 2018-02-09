package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.base.exception.WorkflowException;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuthorize;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuthorizeRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfAuthorizeService;

@Service
@Transactional
public class SysWfAuthorizeServiceImpl implements ISysWfAuthorizeService {

	@Autowired
	private SysWfAuthorizeRepository sysWfAuthorizeRepository;

	@Override
	public List<SysWfAuthorize> getHandlerAuthorize(Date time, String fdTemplateId, String elementId)
			throws WorkflowException {

		Date date = new Date(time.getYear(), time.getMonth(), time.getDate());
		List<SysWfAuthorize> authList = sysWfAuthorizeRepository
				.findAuthorizeHandlerByTemplateId(SysWfAuthorize.AUTHORIZE_PROCESS, date, fdTemplateId, elementId);
		List<SysWfAuthorize> allScopeList = sysWfAuthorizeRepository
				.findAuthorizeHandlerWithAllScope(SysWfAuthorize.AUTHORIZE_PROCESS, date, elementId);
		if (allScopeList != null && !allScopeList.isEmpty()) {
			authList.addAll(allScopeList);
		}
		return authList;
	}

	@Override
	public List<SysWfAuthorize> getReaderAuthorize(String fdTemplateId, String elementId) throws WorkflowException {
		List<SysWfAuthorize> authList = sysWfAuthorizeRepository
				.findAuthorizeReaderByTemplateId(SysWfAuthorize.AUTHORIZE_READING, fdTemplateId, elementId);
		List<SysWfAuthorize> allScopeList = sysWfAuthorizeRepository.findAuthorizeReaderWithAllScope(elementId);
		if (allScopeList != null && !allScopeList.isEmpty()) {
			authList.addAll(allScopeList);
		}
		return authList;
	}

}
