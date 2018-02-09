package com.vispractice.fmc.business.service.sys.notify.impl;

import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodo;
import com.vispractice.fmc.business.entity.sys.notify.SysNotifyTodoDoneInfo;
import com.vispractice.fmc.business.entity.sys.notify.repository.SysNotifyTodoDoneInfoRepository;
import com.vispractice.fmc.business.entity.sys.notify.repository.SysNotifyTodoRepository;
import com.vispractice.fmc.business.entity.sys.org.SysOrgElement;
import com.vispractice.fmc.business.service.sys.notify.ISysNotifyTodoDoneInfoService;
/**
 * 编  号：
 * 名  称：SysNotifyTodoDoneInfoServiceImpl
 * 描  述：SysNotifyTodoDoneInfoServiceImpl.java
 * 完成日期：2017年7月15日 下午2:46:26
 * 编码作者：ZhouYanyao
 */
@Slf4j
@Service
@Transactional
public class SysNotifyTodoDoneInfoServiceImpl implements ISysNotifyTodoDoneInfoService{
	@Autowired
	private SysNotifyTodoRepository sysNotifyTodoRepository;
	
	@Autowired
	private SysNotifyTodoDoneInfoRepository sysNotifyTodoDoneInfoRepository;
	
	@Override
	public void setPersonsDone(SysNotifyTodo todo,List<String> personIds) {
		if (personIds == null || personIds.isEmpty()) {
			log.debug("参数为空,忽略删除待办中指定个人信息操作");
			
			return;
		}
		
		log.info("开始删除指定人待办,并设为已办操作.");
		try {
			List<SysOrgElement> todoTargets = sysNotifyTodoRepository.findTodoTargetElements(todo.getFdId());
			
			for(int i=0;i<todoTargets.size();i++){
				SysOrgElement element = todoTargets.get(i);
				if(personIds.contains(element.getFdId())){
					//移除指定人待办
					todoTargets.remove(i);
					//保存为已办
					this.addOne(todo.getFdId(), element.getFdId());
				}
			}
			sysNotifyTodoRepository.save(todo);
		} catch (Exception e) {
			log.error("删除指定人待办,并设为已办时出错,待办id:{}",todo.getFdId());
		}
		
	}
	
	@Override
	public void addOne(String todoId,String elementId){
		SysNotifyTodoDoneInfo doneInfo = new SysNotifyTodoDoneInfo();
		
		doneInfo.setFdTodoid(todoId);
		doneInfo.setFdElementid(elementId);
		doneInfo.setFdFinishTime(new Date());
		
		sysNotifyTodoDoneInfoRepository.save(doneInfo);
	}
}
