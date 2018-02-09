package com.vispractice.fmc.business.service.sys.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entity.sys.workflow.SysWfAuditNote;
import com.vispractice.fmc.business.entity.sys.workflow.repository.SysWfAuditNoteRepository;
import com.vispractice.fmc.business.service.sys.workflow.ISysWfAuditNoteService;

@Service
@Transactional
public class SysWfAuditNoteServiceImpl implements ISysWfAuditNoteService {

	@Autowired
	private SysWfAuditNoteRepository sysWfAuditNoteRepository;

	@Override
	public List<SysWfAuditNote> findAllByFdProcessId(String id) {
		return sysWfAuditNoteRepository.findByFdProcessIdOrderByFdCreateDateAsc(id);
	}

	@Override
	public void deleteAllByFdProcessId(List<String> fdProcessIdList) {
		sysWfAuditNoteRepository.deleteAllByFdProcessId(fdProcessIdList);
	}

	@Override
	public void saveNote(SysWfAuditNote note) {
		sysWfAuditNoteRepository.save(note);
	}

	@Override
	public List<SysWfAuditNote> findManualNoteByFdProcessId(String id) {
		return sysWfAuditNoteRepository.findByFdProcessIdAndFdActionIdIsNotNullOrderByFdCreateDateAsc(id);
	}

	@Override
	public List<SysWfAuditNote> findPrevNodeAuditNote(String processId, String FactNodeId, String handleId) {
		return sysWfAuditNoteRepository.findPrevNodeAuditNote(processId, FactNodeId, handleId);
	}

}
