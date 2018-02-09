package com.vispractice.fmc.business.service.sys.notify.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.vispractice.fmc.base.utils.ResourceUtil;
import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.constrant.SysNotifyConstant;
import com.vispractice.fmc.business.entity.sys.news.SysNewsMain;
import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodo;
import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodotarget;
import com.vispractice.fmc.business.entity.sys.notify.repository.SysNotifyTodoRepository;
import com.vispractice.fmc.business.entity.sys.notify.repository.SysNotifyTodotargetRepository;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.entity.sys.workflow.SysWfWorkitem;
import com.vispractice.fmc.business.service.sys.notify.ISysNotifyTodoDoneInfoService;
import com.vispractice.fmc.business.service.sys.notify.ISysNotifyTodoService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgElementService;
import com.vispractice.fmc.business.service.sys.org.ISysOrgGroupService;

/**
 * 编 号： 
 * 名 称：SysNotifyTodoServiceImpl 
 * 描 述：SysNotifyTodoServiceImpl.java
 * 完成日期：2017年7月15日 上午11:07:40 
 * 编码作者：ZhouYanyao
 */
@Slf4j
@Service
@Transactional
public class SysNotifyTodoServiceImpl implements ISysNotifyTodoService {
	@Autowired
	private SysNotifyTodoRepository sysNotifyTodoRepository;

	@Autowired
	private ISysNotifyTodoDoneInfoService notifyTodoDoneInfoService;

	@Autowired
	private ISysOrgGroupService sysOrgGroupService;

	@Autowired
	private SysNotifyTodotargetRepository sysNotifyTodotargetRepository;

	@Autowired
	private ISysOrgElementService sysOrgElementService;

	@Autowired
	private ResourceUtil resourceUtil;

	/**
	 * 置成已办
	 */
	@Override
	public void deleteTodoNotify(String key,String parameter1,SysWfWorkitem sysWfWorkitem,String userId) {
		if (null == userId) {
			log.error("当前登录用户为空,待办删除失败.");

			return;
		}

		List<String> personIds = new ArrayList<String>();
		personIds.add(userId);

		List<SysNotifyTodo> sysNotifyTodos = sysNotifyTodoRepository.findByFdKeyAndFdParameter1AndFdParameter2(key,parameter1,sysWfWorkitem.getFdId());
		for (SysNotifyTodo sysNotifyTodo : sysNotifyTodos) {
			notifyTodoDoneInfoService.setPersonsDone(sysNotifyTodo, personIds);
		}
	}

	/**
	 * 删除代办
	 */
	@Override
	public void clearTodoPersons(String key,String parameter1,String parameter2) {
		SysNotifyTodo todo = new SysNotifyTodo();
		todo.setFdKey(key);
		todo.setFdParameter1(parameter1);
		todo.setFdParameter2(parameter2);
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
		Example<SysNotifyTodo> example = Example.of(todo,matcher);
		// 获取到待办列表
		List<SysNotifyTodo> todoList = sysNotifyTodoRepository.findAll(example);
		
		List<String> todoIdList = new ArrayList<String>();
		for (SysNotifyTodo notifyTodo : todoList) {
			todoIdList.add(notifyTodo.getFdId());
		}

		sysNotifyTodotargetRepository.deleteAllByFdTodoidIn(todoIdList);

	}

	@Override
	public List<SysNotifyTodo> findByWfInstanceAndUserNo(String userNo,String wfInstanceId) {
		List<SysNotifyTodo> sysNotifyTodos = sysNotifyTodoRepository.findByUserNoAndWfInstance(userNo,wfInstanceId);

		return sysNotifyTodos;
	}

	private String getBundleString(String type, String key) {
		return resourceUtil.getLocaleMessage(type + "." + key);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void sendNotify(SysNewsMain sysNewsMain, SysWfWorkitem wfItem, String notifyType, HashMap replaceMap,
			String msgKey, Map<String, List<SysOrgElement>> accHandlers, Map<String, List<SysOrgElement>> readers,
			List<SysOrgElement> handlers) throws Exception {

		List<SysOrgElement> todoHandlers = new ArrayList<SysOrgElement>();
		List<SysOrgElement> todoReaders = new ArrayList<SysOrgElement>();

		if (handlers != null && !handlers.isEmpty()) {
			for (SysOrgElement handler : handlers) {

				List<SysOrgElement> tmpAccHandlers = accHandlers.get(handler.getFdId());
				
				if(tmpAccHandlers != null) {
					for (SysOrgElement tmpAccHandler : tmpAccHandlers) {
						if (!todoHandlers.contains(tmpAccHandler) && !handlers.contains(tmpAccHandler)) {
							todoHandlers.add(tmpAccHandler);
						}
					}

				}
				List<SysOrgElement> tmpAccReaders = readers.get(handler.getFdId());
				
				if(tmpAccReaders != null) {
					for (SysOrgElement tmpAccReader : tmpAccReaders) {
						if (!todoReaders.contains(tmpAccReader) && !handlers.contains(tmpAccReader)) {
							todoReaders.add(tmpAccReader);
						}
					}
				}

			}
			todoHandlers.addAll(handlers);
			
			sendAuthHandleNotify(sysNewsMain, wfItem, notifyType, SysNotifyConstant.NOTIFY_TODOTYPE_MANUAL, replaceMap,
					msgKey, todoHandlers);
			
			sendAuthReaderNotify(sysNewsMain, notifyType, SysNotifyConstant.NOTIFY_TODOTYPE_ONCE, replaceMap,
					"sysWfProcess.handler.copyto.notify.handler", todoReaders);

		}
		// sendAuthReaderNotify(sysNewsMain, notifyType,
		// SysNotifyConstant.NOTIFY_TODOTYPE_ONCE, replaceMap, msgKey,
		// readers);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void sendAuthReaderNotify(SysNewsMain sysNewsMain, String notifyType, int fdType, HashMap replaceMap,
			String msgKey, List<SysOrgElement> readers) throws Exception {

		sendTodoNotify(sysNewsMain, null, notifyType, fdType, replaceMap, msgKey, readers);
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void sendAuthHandleNotify(SysNewsMain sysNewsMain, SysWfWorkitem wfItem, String notifyType, int fdType,
			HashMap replaceMap, String msgKey, List<SysOrgElement> handlers) throws Exception {
		sendTodoNotify(sysNewsMain, wfItem, notifyType, fdType, replaceMap, msgKey, handlers);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void sendTodoNotify(SysNewsMain sysNewsMain, SysWfWorkitem wfItem, String notifyType, int fdType,
			HashMap replaceMap, String msgKey, List<SysOrgElement> handlers) throws Exception {
		if (sysNewsMain == null || handlers == null || handlers.isEmpty())
			return;

		SysNotifyTodo todo = new SysNotifyTodo();
		todo.setFdModelName(SysNewsMain.class.getName());
		todo.setFdCreateTime(new Date());
		todo.setFdModelId(sysNewsMain.getFdId());
		todo.setFdKey(sysNewsMain.getFdId());
		if (wfItem != null) {
			todo.setFdParameter1(wfItem.getFdNodeId());
			todo.setFdParameter2(wfItem.getFdId());
		}
		todo.setFdType(new Long((long) fdType));
		todo.setFdBundle(msgKey);

		String subject = getBundleString(msgKey, "subject");

		replaceMap.put("docSubject", sysNewsMain.getDocSubject());

		JSONArray jsonArray = new JSONArray();
		if (replaceMap != null) {
			Iterator keys = replaceMap.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				String fromTxt = "%" + key + "%";
				Object repObj = replaceMap.get(key);
				String repTxt = null;
				if (repObj == null)
					repTxt = "";
				else
					repTxt = repObj.toString();
				if (subject.indexOf(fromTxt) >= 0) {
					JSONObject json = new JSONObject();
					json.put("key", fromTxt);
					json.put("value", repTxt);
					jsonArray.add(json);
				}
				subject = StringUtil.replace(subject, fromTxt, repTxt);
				// context.setLinkSubject(StringUtil.replace(context
				// .getLinkSubject(), fromTxt, repTxt));
				// context.setContent(StringUtil.replace(context.getContent(),
				// fromTxt, repTxt));
			}
		}

		String fdLink = "/anonymous/viewFlowChart?fdId=" + sysNewsMain.getFdId();

		todo.setFdLink(fdLink);
		todo.setFdSubject(subject);
		todo.setFdReplaceText(jsonArray.toString());// 替换文本
		todo.setFdMd5(generateMD5(todo));

		sysNotifyTodoRepository.save(todo);
		// 人员解析
		List<SysOrgElement> todoTargetPersons = expandToPersonIds(handlers);

		List<SysNotifyTodotarget> todoTargets = new ArrayList<SysNotifyTodotarget>();

		for (SysOrgElement todoPerson : todoTargetPersons) {
			SysNotifyTodotarget todotarget = new SysNotifyTodotarget();
			todotarget.setFdTodoid(todo.getFdId());
			todotarget.setFdOrgid(todoPerson.getFdId());
			todoTargets.add(todotarget);
		}
		sysNotifyTodotargetRepository.save(todoTargets);

	}

	private List<SysOrgElement> expandToPersonIds(List<SysOrgElement> orgList) throws Exception {
		if (orgList == null || orgList.isEmpty()) {
			return new ArrayList<SysOrgElement>();
		}

		List<SysOrgElement> personIds = new ArrayList<SysOrgElement>();

		for (int i = 0; i < orgList.size(); i++) {
			SysOrgElement element = (SysOrgElement) orgList.get(i);
			switch (element.getFdOrgType().intValue()) {
			case SysOrgElement.ORG_TYPE_ORG:
			case SysOrgElement.ORG_TYPE_DEPT:
				// List<SysOrgElement> deptPersons =
				// sysOrgElementService(element.getFdHierarchyId());
				// personIds.addAll(deptPersons);
				// hierarchyIds.add(element.getFdHierarchyId());
				break;
			case SysOrgElement.ORG_TYPE_POST:
				List<SysOrgElement> postPersons = sysOrgElementService.findPostPerson(element.getFdId());
				personIds.addAll(postPersons);
				break;
			case SysOrgElement.ORG_TYPE_GROUP:
				List<SysOrgElement> groupPersons = sysOrgGroupService.findOrgElementsByGroupId(element.getFdId());
				if (groupPersons != null && !groupPersons.isEmpty()) {
					personIds.addAll(groupPersons);
				}
				break;
			case SysOrgElement.ORG_TYPE_PERSON:
				if (!personIds.contains(element.getFdId()))
					personIds.add(element);

			}
		}

		return personIds;
	}

	private String generateMD5(SysNotifyTodo todo) {
		String str = StringUtil.getString(todo.getFdSubject()) + StringUtil.getString(todo.getFdLink())
				+ StringUtil.getString(todo.getFdKey()) + (todo.getFdType() == null ? "" : todo.getFdType().toString())
				+ StringUtil.getString(todo.getFdParameter1()) + StringUtil.getString(todo.getFdParameter2());
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}

}
